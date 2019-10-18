package com.volkangurbuz.stajtakip.Requests;

import com.volkangurbuz.stajtakip.Models.InternDate;
import com.volkangurbuz.stajtakip.Models.Sinif;
import com.volkangurbuz.stajtakip.Models.Student;
import com.volkangurbuz.stajtakip.Requests.Responses.StudentResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebApi {

  @GET("product/readAllStudent.php")
  Call<StudentResponse> getAllStudentIntern();

  @POST("product/updateIntern.php")
  Call<Student> updateIntern(@Body Student student);

  @GET("product/readInternAll.php")
  Call<InternDate> getAllIntern();

  @GET("product/readInternAllOgrenci.php")
  Call<Student> getAllInternOgrenci();

  @GET("product/readClassAll.php")
  Call<Sinif> getClassAll();

  // TODO
  /*
    develop a searchResponse
      @GET("api/search")
  Call<RecipeSearchResponse> searchRecipe(
      @Query("key") String key, @Query("q") String query, @Query("page") String page);
     */

}
