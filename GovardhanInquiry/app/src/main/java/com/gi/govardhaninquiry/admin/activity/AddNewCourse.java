package com.gi.govardhaninquiry.admin.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.content.CursorLoader;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.model.CourseModel;
import com.gi.govardhaninquiry.admin.model.FileResponse;
import com.gi.govardhaninquiry.retro.RetroInterface;
import com.gi.govardhaninquiry.retro.RetrofitClient;
import com.gi.govardhaninquiry.static_method.ImagePathCreator;
import com.google.android.material.textfield.TextInputEditText;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewCourse extends AppCompatActivity {

    private static final int PICK_PROGRAM_REQUEST = 9533;
    private static final int PICK_SYLLABUS_REQUEST = 9544;
    private static final int PICK_LOGO_REQUEST = 9555;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String IMAGE_DIRECTORY = "/Govardhan Inquiry/";
    private static final int BUFFER_SIZE = 1024 * 2;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    TextInputEditText cname, cfees, cduration, cprerequest, csyallbus, cprogram, clogo;
    Button submit;
    int id = 0;
    Intent intent;

    String name, fees, duration, prerequest;
    File imageFile1, imageFile2, logo;
    String sPath, pPath, lPath;

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

    }

    public static String getFilePathFromURI(Context context, Uri contentUri) {
        //copy file and send new file path
        String fileName = getFileName(contentUri);
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }
        if (!TextUtils.isEmpty(fileName)) {
            File copyFile = new File(wallpaperDirectory + File.separator + fileName);
            // create folder if not exists

            copy(context, contentUri, copyFile);
            return copyFile.getAbsolutePath();
        }
        return null;
    }

    public static String getFileName(Uri uri) {
        if (uri == null) return null;
        String fileName = null;
        String path = uri.getPath();
        int cut = path.lastIndexOf('/');
        if (cut != -1) {
            fileName = path.substring(cut + 1);
        }
        return fileName;
    }

    public static void copy(Context context, Uri srcUri, File dstFile) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStream == null) return;
            OutputStream outputStream = new FileOutputStream(dstFile);
            copystream(inputStream, outputStream);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int copystream(InputStream input, OutputStream output) throws Exception, IOException {
        byte[] buffer = new byte[BUFFER_SIZE];

        BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);
        BufferedOutputStream out = new BufferedOutputStream(output, BUFFER_SIZE);
        int count = 0, n = 0;
        try {
            while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, n);
                count += n;
            }
            out.flush();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
            try {
                in.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
        }
        return count;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);
        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Add New Course</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        setid();
        verifyStoragePermissions(AddNewCourse.this);
        requestMultiplePermissions();
        cprogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(Intent.createChooser(intent, "Open File Manager"), PICK_PROGRAM_REQUEST);


            }
        });
        csyallbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(Intent.createChooser(intent, "Open File Manager"), PICK_SYLLABUS_REQUEST);


            }
        });
        clogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Open File Manager"), PICK_LOGO_REQUEST);

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = cname.getText().toString();
                fees = cfees.getText().toString();
                duration = cduration.getText().toString();
                prerequest = cprerequest.getText().toString();

                CourseModel courseModel = new CourseModel(name, fees, duration, prerequest);
                String spdfname = "PDF_Syllabus/" + name;
                String ppdfname = "PDF_Program/" + name;
                String lpdfname = "PDF_Logo/" + name;

//                MultipartBody.Part spdfupload = FilePathCreator.createMultiPart(sPath,spdfname);
                imageFile1 = new File(sPath);// Create a file using the absolute path of the image
                RequestBody requestBody1 = RequestBody.create(MediaType.parse("*/*"), imageFile1);
                MultipartBody.Part spdfupload = MultipartBody.Part.createFormData("spdf", imageFile1.getName(), requestBody1);
                RequestBody spdfile_request = RequestBody.create(MediaType.parse("text/plain"), spdfname);


                imageFile2 = new File(pPath);// Create a file using the absolute path of the image
                RequestBody requestBody2 = RequestBody.create(MediaType.parse("*/*"), imageFile2);
                MultipartBody.Part ppdfupload = MultipartBody.Part.createFormData("ppdffile", imageFile2.getName(), requestBody2);
                RequestBody ppdfile_request = RequestBody.create(MediaType.parse("text/plain"), ppdfname);

                logo = new File(pPath);// Create a file using the absolute path of the image
                RequestBody requestBody3 = RequestBody.create(MediaType.parse("multipart/form-file"), logo);
                MultipartBody.Part lpdfupload = MultipartBody.Part.createFormData("logo", logo.getName(), requestBody3);
                RequestBody lpdfile_request = RequestBody.create(MediaType.parse("text/plain"), lpdfname);

                Log.d("gilog_sfilepath", sPath);
                Log.d("gilog_pfilepath", pPath);

                Log.d("gilog_sfilei", imageFile1 + "");
                Log.d("gilog_pfilei", imageFile2 + "");

                Log.d("gilog_sfile", spdfupload + "\n" + spdfile_request);
                Log.d("gilog_pfile", ppdfupload + "\n" + ppdfile_request);

                RetrofitClient.getClient(AddNewCourse.this).create(RetroInterface.class).addcourse(courseModel, spdfupload, spdfile_request, ppdfupload, ppdfile_request,lpdfupload).enqueue(new Callback<FileResponse>() {
                    //                RetrofitClient.getClient(AddNewCourse.this).create(RetroInterface.class).addcourse(courseModel,spdfupload,spdfile_request).enqueue(new Callback<FileResponse>() {
                    @Override
                    public void onResponse(Call<FileResponse> call, Response<FileResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(AddNewCourse.this, "File Uploaded", Toast.LENGTH_SHORT).show();
                            Intent main = new Intent(AddNewCourse.this, AdminCourse.class);
                            main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(main);
                        }
                    }

                    @Override
                    public void onFailure(Call<FileResponse> call, Throwable t) {
                        Log.d("gilog_course", t.getMessage() + "\n" + t.toString());
                        Toast.makeText(AddNewCourse.this, "Request failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void setid() {
        cname = findViewById(R.id.cnameEdt);
        cfees = findViewById(R.id.cfeesEdt);
        cduration = findViewById(R.id.cdurationEdt);
        cprerequest = findViewById(R.id.cprerequestEdt);
        csyallbus = findViewById(R.id.csyallbusEdt);
        cprogram = findViewById(R.id.cprogramEdt);
        clogo = findViewById(R.id.clogoimg);
        submit = findViewById(R.id.csubmit_button);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 9544:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String uriString = uri.toString();
                    File myFile = new File(uriString);
                    sPath = getFilePathFromURI(AddNewCourse.this, uri);
                    csyallbus.setText(sPath);
                }
                break;
            case 9533:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    String uriString = uri.toString();
                    File myFile = new File(uriString);
                    pPath = getFilePathFromURI(AddNewCourse.this, uri);
                    cprogram.setText(pPath);
                }
                break;
            case 9555:

                if (resultCode == RESULT_OK) {
                    if (data == null) {
                        Toast.makeText(this, "Unable to choose file", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Uri imageuri = data.getData();
                    pPath = getRealPathFromUriImage(imageuri);
                    clogo.setText(pPath);
//                    ppdfupload = ImagePathCreator.createMultiPartImg(pPath, ppdfname, "pPdf");

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
    private String getRealPathFromUriImage(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this, uri, projection, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }
}