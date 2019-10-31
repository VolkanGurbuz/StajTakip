package com.volkangurbuz.stajtakip.Requests.Responses;

import java.io.IOException;

import retrofit2.Response;

public class ApiResponse<T> {

  public ApiResponse<T> create(Throwable error) {

    return new ApiErrorResponse<>(
        !error.getMessage().equals("")
            ? error.getMessage()
            : "unknown error, check the network connection");
  }

  public ApiResponse<T> create(Response<T> response) {

    if (response.isSuccessful()) {

      T body = response.body();
      // handle with the error 204 which is the empty content
      if (body == null || response.code() == 204) {
        return new ApiEmptyResponse<>();

      } else {
        return new ApiSuccessResponse<>(body);
      }
    } else {

      String errorMessage = "";
      try {

        errorMessage = response.errorBody().string();

      } catch (IOException e) {
        e.printStackTrace();
        errorMessage = response.message();
      }
      return new ApiErrorResponse<>(errorMessage);
    }
  }

  public class ApiSuccessResponse<T> extends ApiResponse<T> {

    private T body;

    ApiSuccessResponse(T body) {
      this.body = body;
    }

    // this will return all type of the response
    public T getBody() {
      return body;
    }
  }

  public class ApiErrorResponse<T> extends ApiResponse<T> {

    private String errorMessage;

    ApiErrorResponse(String errorMessage) {

      this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
      return errorMessage;
    }
  }
  // sometimes the api success 200 but get the body empty
  public class ApiEmptyResponse<T> extends ApiResponse<T> {}
}
