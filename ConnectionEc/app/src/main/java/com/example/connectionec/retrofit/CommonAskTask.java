package com.example.connectionec.retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

public class CommonAskTask extends AsyncTask<String , String , String > {
    public AsyckTaskCallback callback;
    public Exception askException;
    ProgressDialog dialog ;
    public CommonAskTask(Context context  ,  AsyckTaskCallback callback) {
        this.callback = callback;
        this.dialog = new ProgressDialog(context);

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
        Gson gson = new Gson();
        gson.toJson("" , String.class);
        String rtnData = null;
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("data", "Send!!");
            rtnData = apiInterface.getData("test", map).execute().body();
            Log.d("TAG", "onClick: " + rtnData);
            return rtnData;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if(dialog == null) return;
        dialog.dismiss();
        callback.onResult(s);
    }
    public interface AsyckTaskCallback{
        void onResult(String result);
    }

}
