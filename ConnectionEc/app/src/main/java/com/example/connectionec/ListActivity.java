package com.example.connectionec;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connectionec.retrofit.CommonAskTask;
import com.example.connectionec.retrofit.CommonMethod;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        findViewById(R.id.btn_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask conn = new CommonAskTask(ListActivity.this ,new CommonAskTask.AsyckTaskCallback() {
                    @Override
                    public void onResult(String result) {
/*
                        List<CustomerVO> list =   new Gson().fromJson(result,
                                new TypeToken<List<CustomerVO>>() {
                                }.getType());*/
                        ArrayList<CustomerVO> list = (ArrayList<CustomerVO>) CommonMethod.getList(result);
                        Log.d("Ag", "onResult: " + list.get(0).getEmail());

                    }
                } );
                conn.execute();

              /*  Call<String> test = apiInterface.getSearchLocationDetail("ttz");
                test.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: ");
                        Log.d(TAG, "onResponse: "+ response.body());
                        String zz =  response.body().toString();
                        Log.d(TAG, "onResponse: "+ response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(TAG, "onResponse: ");
                    }
                });*/
            }
        });
    }
}