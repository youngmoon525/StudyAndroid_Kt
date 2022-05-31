package com.example.connectionec.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://192.168.0.176/middle/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) //사용할 주소
                    .addConverterFactory(ScalarsConverterFactory.create()) //json String을 받을수있게끔해줌.

                    .client(new OkHttpClient.Builder().readTimeout(10 , TimeUnit.SECONDS).
                            build())
                 //   .addConverterFactory(GsonConverterFactory.create()) //json -> dto 자동변환
                    .build();
        }
        return  retrofit;
    }

}