package com.example.connectionec.retrofit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CommonMethod {
    public static Object getObject(String data , Type src){
       return new Gson().fromJson(data ,  src);
    }
    public static ArrayList<?> getList(String data) {

        ArrayList<?> list = new Gson().fromJson(data , new TypeToken<ArrayList<?>>(){}.getType());
        return list;
    }
}
