package com.example.retrofit28042021;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Protocol;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ImageView mImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImg = findViewById(R.id.imageView);


        // https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                .connectTimeout(30 , TimeUnit.SECONDS)
                                .writeTimeout(30 , TimeUnit.SECONDS)
                                .readTimeout(30 , TimeUnit.SECONDS)
                                .retryOnConnectionFailure(true)
                                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                                .build();

        // Khai báo gson
        Gson gson = new GsonBuilder().setLenient().disableHtmlEscaping().create();

        // 1 : Khai báo retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://khoapham.vn/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // 2 : Khai báo interface

        ApiRequest apiRequest = retrofit.create(ApiRequest.class);

        // 3 : Call request

//        Call<Demo1> callbackDemo1 = apiRequest.fetchApiDemo1();
//
//        callbackDemo1.enqueue(new Callback<Demo1>() {
//            @Override
//            public void onResponse(Call<Demo1> call, Response<Demo1> response) {
//                Demo1 demo1 = response.body();
//                Glide.with(MainActivity.this)
//                        .load("https://wallpaperaccess.com/full/3521073.jpg")
//                        .placeholder(R.mipmap.ic_launcher)
//                        .error(R.drawable.ic_launcher_background)
//                        .into(mImg);
//            }
//
//            @Override
//            public void onFailure(Call<Demo1> call, Throwable t) {
//                Log.d("BBB",t.getMessage());
//            }
//        });

        // Demo4
        Call<List<Demo4>> callbackDemo4 = apiRequest.fetchApiDemo4("demo4.json");
        callbackDemo4.enqueue(new Callback<List<Demo4>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Demo4>> call, Response<List<Demo4>> response) {
                List<Demo4> list = response.body();
                list.forEach(demo4 -> {
                    Log.d("BBB",demo4.getKhoahoc());
                    Log.d("BBB",demo4.getHocphi());
                });
            }

            @Override
            public void onFailure(Call<List<Demo4>> call, Throwable t) {

            }
        });
    }
}