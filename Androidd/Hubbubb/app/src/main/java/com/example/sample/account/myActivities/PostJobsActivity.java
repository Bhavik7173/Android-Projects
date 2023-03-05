package com.example.sample.account.myActivities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sample.Api;
import com.example.sample.FileUtil;
import com.example.sample.MyRetrofit;
import com.example.sample.NotificationSender.APIService;
import com.example.sample.NotificationSender.Client;
import com.example.sample.NotificationSender.Data;
import com.example.sample.NotificationSender.MyResponse;
import com.example.sample.NotificationSender.NotificationSender;
import com.example.sample.PostJobsResponse;
import com.example.sample.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class PostJobsActivity extends AppCompatActivity {

    String fcmurl = "https://fcm.googleapis.com/";
    String CLPath,pdfpath;
    String email,id;
    Api api;
    File imagefile,file;
    String expDate,postDate;
    Context context;
    TextView image;
    int GALLERY_REQUEST=0;
    int PDF_REQUEST=1;
    Uri uri;
    EditText CL,JT,JRE,JD,JS,JSD,JED,JL,JF;
    private static final int BUFFER_SIZE = 1024 * 2;
    private static final String IMAGE_DIRECTORY = "/demonuts_upload_gallery";
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_jobs);

        context = this;
        view = getWindow().getDecorView().findViewById(android.R.id.content);

        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);
        id = sharedPreferences.getString("id",null);
        JT = (EditText) view.findViewById(R.id.JT);
        JRE = (EditText) view.findViewById(R.id.JP);
        JD = (EditText) view.findViewById(R.id.JD);
        JS = (EditText) view.findViewById(R.id.JS);
        JSD = (EditText) view.findViewById(R.id.JSD);
        JED = (EditText) view.findViewById(R.id.JED);
        CL = (EditText) view.findViewById(R.id.CL);
        JL = (EditText) view.findViewById(R.id.JL);
        JF = (EditText) view.findViewById(R.id.JF);
        JF.setText("No PDF Selected");

        JF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMultiplePermissions();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent,1);

            }
        });
        CL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
        });


        Button PJ = (Button) view.findViewById(R.id.PJ);

        PJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("POST JOBS","BUTTON PRESSED");

                int cnt=0;
                if(JT.getText().toString().trim().isEmpty())
                {
                    cnt++;
                    JT.setError("Fill job title!");
                }
                if(JRE.getText().toString().trim().isEmpty())
                {
                    cnt++;
                    JRE.setError("Fill job post!");
                }
                if(JD.getText().toString().trim().isEmpty())
                {
                    cnt++;
                    JD.setError("Fill job details!");
                }
                if(JS.getText().toString().trim().isEmpty())
                {
                    cnt++;
                    JS.setError("Fill salary for job!");
                }
                if(JSD.getText().toString().trim().isEmpty())
                {
                    cnt++;
                    JSD.setError("Fill job application starting date!");
                }
                else
                {
                    JSD.setError(null);
                }
                if(JED.getText().toString().trim().isEmpty())
                {
                    cnt++;
                    JED.setError("Fill job application starting date!");
                }
                else
                {
                    JED.setError(null);
                }
                if(JL.getText().toString().trim().isEmpty())
                {
                    cnt++;
                    JL.setError("Fill job location!");
                }
                if(CL.getText().toString().trim().isEmpty() || CL.getText().toString().equals("No Company Logo Selected"))
                {
                    cnt++;
                    CL.setError("Choose your image!");
                }

                if(cnt==0) {
                    int ind = email.indexOf('@');

                    //File imagefile=new File(CLPath);
                    //File file = new File(pdfpath);

                    String imageextension=imagefile.toString();
                    int index=imageextension.lastIndexOf('.');
                    String extension="";
                    if(index > 0) {
                        extension = imageextension.substring(index + 1);

                    }
                    String pdfname="PDF_Jobs/" + JT.getText().toString() + "_" + email.substring(0, ind)+".pdf";
                    String imagename = "Jobs/" + JT.getText().toString() + "_" + email.substring(0, ind) +".jpg";
                    // Parsing any Media type file
                    RequestBody requestBody1 = RequestBody.create(MediaType.parse("*/*"), file);
                    RequestBody requestBody2 = RequestBody.create(MediaType.parse("*/*"), imagefile);

                    MultipartBody.Part pdfupload = MultipartBody.Part.createFormData("pdffile", pdfname, requestBody1);
                    MultipartBody.Part imageupload = MultipartBody.Part.createFormData("companylogo", imagename, requestBody2);

                    RequestBody pdfile_request = RequestBody.create(MediaType.parse("text/plain"), pdfname);
                    RequestBody companylogo_request = RequestBody.create(MediaType.parse("text/plain"), imagename);



                    Retrofit instance = MyRetrofit.getRetrofit(getString(R.string.IP1));
                    Call<PostJobsResponse> call;
                    api = instance.create(Api.class);
                    call = api.postjobs1(JT.getText().toString(),JRE.getText().toString(),JD.getText().toString(),JSD.getText().toString(),JED.getText().toString(),JS.getText().toString(),JL.getText().toString(),pdfupload,imageupload,id);

                    call.enqueue(new Callback<PostJobsResponse>() {
                        @Override
                        public void onResponse(Call<PostJobsResponse> call, Response<PostJobsResponse> response) {
                            PostJobsResponse postJobsResponse = response.body();
                            String status = postJobsResponse.getStatus();
                            Log.d("responseP", response.toString());
                            finish(status);
                        }

                        @Override
                        public void onFailure(Call<PostJobsResponse> call, Throwable t) {
                            Log.d("responseP", "error : " + t.toString());
                        }
                    });
                }
            }
        });
        JSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener listener;
                listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        JSD.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        JSD.setError(null);
                    }
                };
                Date d = new Date();
                Calendar c1 = Calendar.getInstance();
                Date d1 = new Date(d.getYear(),d.getMonth(),d.getDay()+15);
                c1.setTime(d1);
                DatePickerDialog dp;
                dp = new DatePickerDialog(context, R.style.DialogTheme, listener, d.getYear() + 1900, d.getMonth(), d.getDay() + 15);
                dp.getDatePicker().setMinDate(c1.getTime().getTime());
                dp.show();
            }
        });
        JED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener listener;
                listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        JED.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        JED.setError(null);
                    }
                };
                Date d = new Date();
                Calendar c1 = Calendar.getInstance();
                Date d1 = new Date(d.getYear(),d.getMonth(),d.getDay()+15);
                c1.setTime(d1);
                DatePickerDialog dp;
                dp = new DatePickerDialog(context, R.style.DialogTheme, listener, d.getYear() + 1900, d.getMonth(), d.getDay() + 15);
                dp.getDatePicker().setMinDate(c1.getTime().getTime());
                dp.show();
            }
        });

    }
    private String imageToString()
    {
        Bitmap bitmap=null;
        try
        {
            bitmap= MediaStore.Images.Media.getBitmap(context.getContentResolver(),uri);
        }
        catch (Exception ex)
        {
            Log.d("Error",ex.toString());
        }
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] image=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(image, Base64.DEFAULT);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PERMISSION_GRANTED)
        {
            Toast.makeText(context,"GRANTED", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_REQUEST);
        }
        else {
            Toast.makeText(context, "PERMISSION NOT TAKEN", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_REQUEST && resultCode==RESULT_OK)
        {
            try
            {
                uri = data.getData();
                CL.setText("File Selected");
                imagefile = FileUtil.from(this, uri);
                CL.setText(imagefile.getPath());
            }
            catch (Exception ex)
            {
                Log.d("Whyred",ex.getMessage());
            }

        }

        else if (requestCode==PDF_REQUEST && resultCode== RESULT_OK) {

            try
            {
                uri = data.getData();
                JF.setText("File Selected");
                file = FileUtil.from(this, uri);
                JF.setText(file.getPath());
            }
            catch (Exception ex)
            {
                Log.d("Whyred",ex.getMessage());
            }
        }
    }




    public static String getFilePathFromURI(Context context, Uri contentUri) {
        //copy file and send new file path
        String fileName = getFileName(contentUri);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
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
    private void  requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(

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
    void finish(String status)
    {
        if (status.equals("success")) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            ViewGroup viewGroup = (ViewGroup)this.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(context).inflate(R.layout.successdialog, viewGroup, false);
            final Button[] ok = new Button[1];
            ok[0] = (Button)dialogView.findViewById(R.id.ok);
            builder.setView(dialogView);
            builder.setCancelable(false);
            final AlertDialog alertDialog = builder.create();
            TextView msg = dialogView.findViewById(R.id.msg);
            msg.setText("Your Job has been Posted");
            alertDialog.show();

            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();


                }
            });
            api.getKey().enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String usertoken = response.body();
                    Log.d("tokerec", usertoken);
                    sendNotifications(usertoken, "New Job",JT.getText().toString().trim());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("mylog", t.toString());
                }
            });

            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();


                }
            });
            //Toast.makeText(context, "Event Posted", Toast.LENGTH_LONG).show();
        }
        if (status.equals("failure")) {
            Toast.makeText(context, "Server Problem", Toast.LENGTH_LONG).show();

        }
    }
    public void sendNotifications(String usertoken, String title, String message) {


        Data data = new Data(title, message);

        NotificationSender sender = new NotificationSender(data, usertoken);

        APIService apiService = Client.getClient(fcmurl).create(APIService.class);

        apiService.sendNotifcation(sender).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                Log.d("TRIED",response+""+response.code()+response.body().success);

                if (response.code() == 200) {
                    if (response.body().success != 1) {
                        Toast.makeText(getApplicationContext(), "Failed ", Toast.LENGTH_LONG);
                    } else {
                        Toast.makeText(getApplicationContext(), "notification has been sent...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Log.d("notification", t.toString());
            }
        });
    }
}

