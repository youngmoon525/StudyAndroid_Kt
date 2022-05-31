package com.example.connectionec.retrofit;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface ApiInterface {
   /* @POST("test")
    Call<String> getSearchLocationDetail(
            @Query("data") String data
    );*/
    @FormUrlEncoded
    @POST
    Call<String> getData(@Url String url ,
            @FieldMap HashMap<String,Object> parameters);



    @POST("file.f")
    @Multipart
    Call<String> sendFile(@Part List<MultipartBody.Part> files );



}