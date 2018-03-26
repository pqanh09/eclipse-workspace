package com.example.pqanh.myapp2.resttest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by pqanh on 19-03-18.
 */

public interface ApiInterface {
    @GET("1b3h3v")
    Call<TeamResponse> getAll();
    @GET("getallproducts.php")
    Call<List<Products>> getAllProduct();

//    @GET("api/chapter/id/5ab4d572bb9f120157fe9d4d")
//    Call<List<Chapter>> getChapter();

    @GET("api/chapter/id/5ab4d572bb9f120157fe9d4d")
    Call<ChapterResponseObject> getChapter();

}
