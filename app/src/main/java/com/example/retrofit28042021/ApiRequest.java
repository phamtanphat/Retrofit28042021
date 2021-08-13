package com.example.retrofit28042021;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiRequest {


    // base url : https://khoapham.vn/

    // url gốc : https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json

    // url của get : KhoaPhamTraining/json/tien/demo1.json

    @GET("KhoaPhamTraining/json/tien/demo1.json")
    Call<Demo1> fetchApiDemo1();

    // params
    @GET("KhoaPhamTraining/json/tien/{endpoint}")
    Call<List<Demo4>> fetchApiDemo4(@Path("endpoint") String endPoint);
}
