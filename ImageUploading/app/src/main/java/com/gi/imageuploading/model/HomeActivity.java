package com.gi.imageuploading.model;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.gi.imageuploading.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    Button btnCamera;
    Intent intent;
    FileService fileService;
    public static final int RequestPermissionCode = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnCamera = findViewById(R.id.btncamera);
        EnableRuntimePermissionToAccessCamera();
        fileService = ApiUtils.getFileService();
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 7);
            }
        });
    }

    public void EnableRuntimePermissionToAccessCamera() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            // Printing toast message after enabling runtime permission.
            Toast.makeText(this, "CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }

    @SuppressLint("LongLogTag")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 7) {
                File photoFile = null;
                //                    photoFile = createImageFile();

                RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), f);
                MultipartBody.Part partImage = MultipartBody.Part.createFormData("file", f.getName(), reqBody);
                Call<FileResponse> upload = fileService.upload(partImage);
                Log.d("upload", upload.toString());
                upload.enqueue(new Callback<FileResponse>() {
                    @Override
                    public void onResponse(Call<FileResponse> call, Response<FileResponse> response) {
                        Log.d("response_success", response.body().toString());
                        if (response.isSuccessful()) {
                            Toast.makeText(HomeActivity.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                            Intent main = new Intent(HomeActivity.this, UploadActivity.class);
                            main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(main);
                        }
                    }

                    @Override
                    public void onFailure(Call<FileResponse> call, Throwable t) {
                        Log.d("response_fail", t.toString());
                        Toast.makeText(HomeActivity.this, "Request failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    }

}


//Bitmap imageFile = (Bitmap) data.getExtras().get("data");
////                imageView.setImageBitmap(photo);
////                File imageFile = new File(photo.get);
////                Log.d("file",imageFile.getAbsolutePath());// Create a file using the absolute path of the image
//                File f = new File(this.getCacheDir(), "temp");
//                try {
//                    f.createNewFile();
//                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                    imageFile.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, bos);
//        byte[] bitmapdata = bos.toByteArray();
//
////write the bytes in file
//        FileOutputStream fos = null;
//        fos = new FileOutputStream(f);
//
//        fos.write(bitmapdata);
//        fos.flush();
//        fos.close();
//
//        } catch (IOException e) {
//        e.printStackTrace();
//        }
