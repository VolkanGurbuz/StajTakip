package com.volkangurbuz.stajtakip.Requests;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.volkangurbuz.stajtakip.AppExecutors;
import com.volkangurbuz.stajtakip.Models.Student;
import com.volkangurbuz.stajtakip.Requests.Responses.StudentResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.volkangurbuz.stajtakip.Util.Constants.NETWORK_TIMEOUT;

public class StudentApiClient {

  private static StudentApiClient instance;
  private MutableLiveData<List<Student>> mStudents;
  private static final String TAG = "StudentApiClient";
  private RetrieveStudentsRunnable retrieveStudentsRunnable;
  private MutableLiveData<Boolean> mStudentRequestTimeout = new MutableLiveData<>();

  public static StudentApiClient getInstance() {

    if (instance == null) {
      instance = new StudentApiClient();
    }
    return instance;
  }

  private StudentApiClient() {
    mStudents = new MutableLiveData<>();
  }

  public LiveData<List<Student>> getStudents() {
    return mStudents;
  }

  public LiveData<Boolean> isStudentRequestTimedOut() {
    return mStudentRequestTimeout;
  }

  public void showAllStudentsApi() {

    if (retrieveStudentsRunnable != null) {

      retrieveStudentsRunnable = null;
    }

    retrieveStudentsRunnable = new RetrieveStudentsRunnable();

    final Future handler = AppExecutors.getInstance().networkIO().submit(retrieveStudentsRunnable);

    mStudentRequestTimeout.setValue(false);

    // TIMEOUT\
    AppExecutors.getInstance()
        .networkIO()
        .schedule(
            new Runnable() {
              @Override
              public void run() {

                // let user know its timed out
                mStudentRequestTimeout.postValue(true);
                handler.cancel(true);
              }
            },
            NETWORK_TIMEOUT,
            TimeUnit.MILLISECONDS);
  }

  private class RetrieveStudentsRunnable implements Runnable {

    // student name or etc to search
    private String query;
    // user wants to cancel the process or timeout
    boolean cancelRequest;

    public RetrieveStudentsRunnable(String query) {
      this.query = query;
      cancelRequest = false;
    }

    public RetrieveStudentsRunnable() {
      this.cancelRequest = false;
    }

    @Override
    public void run() {
      try {
        Response response = getStudents().execute();

        if (cancelRequest) {
          return;
        }

        // the request was success
        if (response.code() == 200) {
          List<Student> students =
              new ArrayList<>(((StudentResponse) response.body()).getStudentList());

          mStudents.postValue(students);

        } else {
          String error = response.errorBody().string();
          Log.d(TAG, "Error: " + error);
          mStudents.postValue(null);
        }

      } catch (IOException e) {
        e.printStackTrace();
        mStudents.postValue(null);
      }
    }

    private Call<StudentResponse> getStudents() {

      return ServiceGenerator.getWebApi().getAllStudentIntern();
    }

    private void cancelRequest() {

      Log.d(TAG, "cancelingRequest: ");
      cancelRequest = true;
    }
  }
}
