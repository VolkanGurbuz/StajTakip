package com.volkangurbuz.stajtakip.Util;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.volkangurbuz.stajtakip.AppExecutors;
import com.volkangurbuz.stajtakip.Requests.Responses.ApiResponse;

// CacheObject: Type for the Resource data. (database cache)
// RequestObject: Type for the API response. (network request)
public abstract class NetworkBoundResource<CacheObject, RequestObject> {

  private AppExecutors appExecutors;
  private MediatorLiveData<Resource<CacheObject>> results = new MediatorLiveData<>();
  private static final String TAG = "NetworkBoundResource";

  public NetworkBoundResource(AppExecutors appExecutors) {
    this.appExecutors = appExecutors;
    init();
  }

  private void init() {
    // update livedata for loading status
    results.setValue((Resource<CacheObject>) Resource.loading(null));

    // observe LiveData source from local db
    final LiveData<CacheObject> dbSource = loadFromDb();

    results.addSource(
        dbSource,
        new Observer<CacheObject>() {
          @Override
          public void onChanged(@Nullable CacheObject cacheObject) {

            results.removeSource(dbSource);

            if (shouldFetch(cacheObject)) {
              // get data from network
              fetchFromNetwork(dbSource);
            } else {
              results.addSource(
                  dbSource,
                  new Observer<CacheObject>() {
                    @Override
                    public void onChanged(@Nullable CacheObject cacheObject) {
                      setValue(Resource.success(cacheObject));
                    }
                  });
            }
          }
        });
  }

  /*
  observe local db
  if <condition> querry the network
  stop observing the local db
  insert new data info local db
  begin observing local db again to see refreshed data from network
   */

  private void fetchFromNetwork(final LiveData<CacheObject> dbSource) {
    Log.d(TAG, "fetchFromNetwork:  called");
    // update livedata for loading status
    results.addSource(
        dbSource,
        new Observer<CacheObject>() {
          @Override
          public void onChanged(@Nullable CacheObject cacheObject) {
            setValue(Resource.loading(cacheObject));
          }
        });

    final LiveData<ApiResponse<RequestObject>> apiResponse = createCall();

    results.addSource(
        apiResponse,
        new Observer<ApiResponse<RequestObject>>() {
          @Override
          public void onChanged(
              @Nullable final ApiResponse<RequestObject> requestObjectApiResponse) {
            results.removeSource(dbSource);
            results.removeSource(apiResponse);
            /*
            //three cases
            apisuccess, apierrorresponse, apiemoptyresponse
             */

            if (requestObjectApiResponse instanceof ApiResponse.ApiSuccessResponse) {

              Log.d(TAG, "onChanged: apisucces");

              appExecutors
                  .diskIO()
                  .execute(
                      new Runnable() {
                        @Override
                        public void run() {
                          // save the response to the local db
                          saveCallResult(
                              (RequestObject)
                                  processResponse(
                                      (ApiResponse.ApiSuccessResponse) requestObjectApiResponse));

                          // use setvalue as a postValue
                          // postvalue wont post immidiately, setvalue will, so we need to set on
                          // mainthread
                          appExecutors
                              .mMainThread()
                              .execute(
                                  new Runnable() {
                                    @Override
                                    public void run() {
                                      results.addSource(
                                          loadFromDb(),
                                          new Observer<CacheObject>() {
                                            @Override
                                            public void onChanged(
                                                @Nullable CacheObject cacheObject) {
                                              setValue(Resource.success(cacheObject));
                                            }
                                          });
                                    }
                                  });
                        }
                      });

            } else if (requestObjectApiResponse instanceof ApiResponse.ApiEmptyResponse) {

              Log.d(TAG, "onChanged: api empty ");

              appExecutors
                  .mMainThread()
                  .execute(
                      new Runnable() {
                        @Override
                        public void run() {
                          results.addSource(
                              loadFromDb(),
                              new Observer<CacheObject>() {
                                @Override
                                public void onChanged(@Nullable CacheObject cacheObject) {
                                  setValue(Resource.success(cacheObject));
                                }
                              });
                        }
                      });

            } else if (requestObjectApiResponse instanceof ApiResponse.ApiErrorResponse) {
              Log.d(TAG, "onChanged: error");

              results.addSource(
                  dbSource,
                  new Observer<CacheObject>() {
                    @Override
                    public void onChanged(@Nullable CacheObject CacheObject) {
                      setValue(
                          Resource.error(
                              ((ApiResponse.ApiErrorResponse) requestObjectApiResponse)
                                  .getErrorMessage(),
                              CacheObject));
                    }
                  });
            }
          }
        });
  }

  private CacheObject processResponse(ApiResponse.ApiSuccessResponse response) {
    return (CacheObject) response.getBody();
  }

  private void setValue(Resource<CacheObject> newValue) {
    if (results.getValue() != newValue) {
      results.setValue(newValue);
    }
  }

  // Called to save the result of the API response into the database.
  @WorkerThread
  public abstract void saveCallResult(@NonNull RequestObject item);

  // Called with the data in the database to decide whether to fetch
  // potentially updated data from the network.
  @MainThread
  public abstract boolean shouldFetch(@Nullable CacheObject data);

  // Called to get the cached data from the database.
  @NonNull
  @MainThread
  public abstract LiveData<CacheObject> loadFromDb();

  // Called to create the API call.
  @NonNull
  @MainThread
  public abstract LiveData<ApiResponse<RequestObject>> createCall();

  // Returns a LiveData object that represents the resource that's implemented
  // in the base class.
  public final LiveData<Resource<CacheObject>> getAsLiveData() {
    return results;
  };
}
