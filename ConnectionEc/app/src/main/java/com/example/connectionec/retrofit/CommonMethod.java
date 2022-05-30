package com.example.connectionec.retrofit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class CommonMethod {
    public static Object getObject(String data , Type src){
       return new Gson().fromJson(data ,  src);
    }

    public static  ArrayList<?> getList(String data , TypeToken typeToken) {

        ArrayList<?> list = new Gson().fromJson(data ,  typeToken.getType());
        return  list;
    }

}
