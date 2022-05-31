package com.example.connectionec.retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;

public class CommonAskTask extends AsyncTask<String , String , String > {
    public enum Category{
       SEND_PARAMS , SEND_FILEPARAMS
    }
    Category category;

    public AsyckTaskCallback callback;
    ProgressDialog dialog ;
    HashMap<String , Object> params ;
    List<MultipartBody.Part> files;
    String url;
    public CommonAskTask(String url , Context context) {
        this.dialog = new ProgressDialog(context);
        this.params = new HashMap<>();
        this.files = new ArrayList<>();
        this.url = url;
    }

    public void setParams(HashMap<String,Object> params){
        this.params = params;
    }

    public void setParams(String key , Object value){
        this.params.put(key,value);
    }
    public void setFiles(List<MultipartBody.Part> files){
        this.files = files;
    }
    public void setFiles(MultipartBody.Part file){
        this.files.add(file);
    }

    public void excuteAsk(Category category, AsyckTaskCallback callback){
        this.callback = callback;
        this.category = category;
        this.execute();
    }

    @Override
    protected void onPreExecute() {
        if(dialog == null) return;
        dialog.setProgress(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("데이터를 가져올것");
        dialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        String rtnData = null;
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        try {
            if(category == Category.SEND_PARAMS) {
                rtnData = apiInterface.getData(url, params).execute().body();

            }else if(category == Category.SEND_FILEPARAMS){
                rtnData = apiInterface.sendFile(   files ).execute().body();

            }
            return rtnData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if(dialog == null) return;
        dialog.dismiss();
        if(s != null){
            callback.onResult(s , true);
        }else {
            callback.onResult(s, false);

        }
    }
    public interface AsyckTaskCallback{
        void onResult(String data , boolean result);
    }

}
