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

}
