package com.example.connectionec;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.connectionec.retrofit.CommonAskTask;
import com.example.connectionec.retrofit.CommonMethod;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ListActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent>  launcher ;


    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cusor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cusor.moveToFirst()) {
            int column_index = cusor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cusor.getString(column_index);
        }
        return res;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        checkDangerousPermissions();
        launcher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Log.d("TAG", "onActivityResult: ");
                if(result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Log.d("", "onActivityResult: " + result.getData().getData());

                    RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), getbitMap(result.getData().getData() ));
                    MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.jpg", fileBody);
                    ImageView imgv = findViewById(R.id.imgv);
                    Glide.with(ListActivity.this).load( getbitMap(result.getData().getData() ) ).into(imgv);
                    CommonAskTask conn = new CommonAskTask("file.f", ListActivity.this);
                    conn.setFiles(filePart);
                    conn.excuteAsk(CommonAskTask.Category.SEND_FILEPARAMS , (data, result1) -> {
                        if(result1){
                            Log.d("TAG", "onActivityResult: " + data);
                        }
                    });
                }

            }
        });



        findViewById(R.id.btn_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask conn = new CommonAskTask("test" , ListActivity.this);
                conn.setParams("data" , "테스트합니당.");
                conn.excuteAsk(CommonAskTask.Category.SEND_PARAMS, new CommonAskTask.AsyckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean result) {
                        if(result){

                            ArrayList<CustomerVO> list = (ArrayList<CustomerVO>) CommonMethod.getList(data,new TypeToken<ArrayList<CustomerVO>>(){});
                            Log.d("Ag", "onResult: " + list.get(0).getEmail());
                        }else{
                            Log.d("오", "onResult: 류");
                        }
                    }
                });



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


        findViewById(R.id.btn_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");


                launcher.launch(intent);
            }
        });
    }
    public File getbitMap(Uri fileUri){
        ContentResolver contentResolver = getContentResolver();
        try {
            InputStream inputStream =  contentResolver.openInputStream(fileUri);

            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            File curFile = File.createTempFile("TempImage" , ".jpg" , getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            OutputStream out = new FileOutputStream(curFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG , 100 ,out);

            return curFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    };

    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_MEDIA_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }



}