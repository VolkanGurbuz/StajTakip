package com.volkangurbuz.stajtakip.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserManager {

  private static final String baseUrl = "https://www.volkangurbuz.com/restapi/";
  public static Retrofit retrofit;


  public static final String LOGIN_URL =
          "https://www.volkangurbuz.com/restapi/product/userLogin.php";


  public static Retrofit getClient() {
    if (retrofit == null) {
      retrofit =
          new Retrofit.Builder()
              .baseUrl(baseUrl)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
    }

    return retrofit;
  }
}
