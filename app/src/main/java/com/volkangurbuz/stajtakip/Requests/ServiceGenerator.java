package com.volkangurbuz.stajtakip.Requests;

import com.volkangurbuz.stajtakip.Util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

  public static Retrofit.Builder retrofitBuilder =
      new Retrofit.Builder()
          .baseUrl(Constants.baseUrl)
          .addConverterFactory(GsonConverterFactory.create());

  private static Retrofit retrofit = retrofitBuilder.build();

  private static RecipeApi recipeApi = retrofit.create(RecipeApi.class);

  public static RecipeApi getRecipeApi() {

    return recipeApi;
  }
}
