package com.example.pqanh.myapp2.resttest;

import com.comics.shared.response.ChapterResponseObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by pqanh on 19-03-18.
 */

public interface ApiInterface {
    @GET("1b3h3v")
    Call<TeamResponse> getAll();
    @GET("json/get/cevOLHjlLm?indent=2")
    Call<List<Products>> getAllProduct();

    @GET("json/get/cfnWstgxmG?indent=2")
    Call<Products> getProduct();


//    @GET("api/chapter/id/5ab4d572bb9f120157fe9d4d")
//    Call<List<Chapter>> getChapter();

   // @GET("api/chapter/id/5ab89c80bb9f35ea50c4b343")
    //@GET("api/json/get/ceDibDKBxK?indent=2")
    @GET("api/chapter/id/5ab4d572bb9f120157fe9d4d")
    Call<ChapterResponseObject> getChapter();

}
