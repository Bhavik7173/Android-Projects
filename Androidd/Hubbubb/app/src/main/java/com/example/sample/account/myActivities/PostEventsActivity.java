package com.example.sample.account.myActivities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.sample.Api;
import com.example.sample.FileUtil;
import com.example.sample.MyRetrofit;
import com.example.sample.NotificationSender.APIService;
import com.example.sample.NotificationSender.Client;
import com.example.sample.NotificationSender.Data;
import com.example.sample.NotificationSender.MyResponse;
import com.example.sample.NotificationSender.NotificationSender;
import com.example.sample.PostEventsResponse;
import com.example.sample.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class PostEventsActivity extends AppCompatActivity {

    String fcmurl = "https://fcm.googleapis.com/";
    Api api;
    String email,pdfpath,imagepath,id;
    EditText esd,eed,est,eet,el,ef,upload,EN,ED;
    Context context;
    String st,et,sst,set;
    File file,imagefile;
    TextView image;
    View view;
    private static final int BUFFER_SIZE = 1024 * 2;
    private static final String IMAGE_DIRECTORY = "/demonuts_upload_gallery";
    int GALLERY_REQUEST=0;
    int PDF_REQUEST=1;
    Uri uri,pdf;

    public PostEventsActivity()
    {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_events);
        //requestMultiplePermissions();

        context = this;
        view = getWindow().getDecorView().findViewById(android.R.id.content);

        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);
        id = sharedPreferences.getString("id",null);
        //image=view.findViewById(R.id.image);
        //image.setText("No file Selected");
        Log.d("Bundle",email);
        EN = (EditText) view.findViewById(R.id.EN);
        ED = (EditText) view.findViewById(R.id.ED);

        esd  = (EditText) view.findViewById(R.id.ESD);
        eed = (EditText) view.findViewById(R.id.EED);
        est = (EditText) view.findViewById(R.id.EST);
        eet = (EditText) view.findViewById(R.id.EET);
        el = (EditText) view.findViewById(R.id.EL);
        ef=(EditText)view.findViewById(R.id.EF);
        ef.setText("No PDF Selected");

        ef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ef.setText("PDF Not Selected");
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PDF_REQUEST);
//
            }
        });

        Button PE = (Button) view.findViewById(R.id.PE);
        upload=(EditText) view.findViewById(R.id.EB);
        upload.setText("No Banner Selected");

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},GALLERY_REQUEST);
            }
        });
        esd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener listener;
                listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        esd.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        esd.setError(null);
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

        est.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener listener;
                listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker arg0, int h, int m) {
                        String a = "";
                        if(h >= 12)
                        {
                            a = "PM";
                            h = h -12;
                        }
                        else
                        {
                            a = "AM";
                        }
                        String mm,hh;
                        if(m<10) {
                            mm="0"+m;
                        }
                        else
                        {
                            mm=m+"";
                        }
                        if(h<10)
                        {
                            hh="0"+h;
                        }
                        else
                        {
                            hh=h+"";
                        }

                        est.setText(hh + " : " + mm + "  " + a);
                        est.setError(null);
                        st = hh + ":" + mm + " " + a;
                        Log.d("Whyred",est.getText().toString());
                    }
                };
                Calendar c1 = Calendar.getInstance();
                TimePickerDialog tpd;
                tpd = new TimePickerDialog(context, R.style.DialogTheme, listener, 0,0,false);
                tpd.show();

            }
        });

        eet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener listener;
                listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker arg0, int h, int m) {
                        String a = "";
                        if(h >= 12)
                        {
                            a = "PM";
                            h = h -12;
                        }
                        else
                        {
                            a = "AM";
                        }
                        String mm,hh;
                        if(m<10) {
                            mm="0"+m;
                        }
                        else
                        {
                            mm=m+"";
                        }
                        if(h<10)
                        {
                            hh="0"+h;
                        }
                        else
                        {
                            hh=h+"";
                        }

                        eet.setText(hh + " : " + mm + "  " + a);
                        eet.setError(null);
                        et = hh + ":" + mm + " " + a;
                        Log.d("Whyred",eet.getText().toString());
                    }
                };
                TimePickerDialog tpd;
                tpd = new TimePickerDialog(context, R.style.DialogTheme, listener, 0,0,false);
                tpd.show();
            }
        });



        eed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener listener;
                listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        eed.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        eed.setError(null);
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


        PE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Frags","Button Pressed");
                int cnt=0;
                DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
                DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
                Date d = null;
                Date dst=null,det=null;

                if(EN.getText().toString().trim().isEmpty())
                {
                    cnt++;
                    EN.setError("Fill event title!");
                }
                if(ED.getText().toString().trim().isEmpty())
                {
                    cnt++;
                    ED.setError("Fill event details!");
                }
                if(est.getText().toString().trim().isEmpty())
                {
                    cnt++;
                    est.setError("Fill event starting time!");
                }
                else
                {
                    est.setError(null);
                    try {
                        dst = d = dateFormat.parse(st);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    sst = dateFormat1.format(d);

                    Log.d("Frags", st);
                }
                if(eet.getText().toString().trim().isEmpty())
                {
                    cnt++;
                    eet.setError("Fill event ending time!");
                }
                else
                {
                    eet.setError(null);
                    try {
                        det = d = dateFormat.parse(et);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    set = dateFormat1.format(d);
                    Log.d("Frags", et);
                }
                if(esd.getText().toString().trim().isEmpty())
                {
                    cnt++;
                    esd.setError("Fill event starting date!");
                }
                else
                {
                    esd.setError(null);
                }
                if(eed.getText().toString().trim().isEmpty())
                {
                    cnt++;
                    eed.setError("Fill event ending date!");
                }
                else
                {
                    eed.setError(null);
                }
                if(esd.getText().toString().trim().equals(eed.getText().toString().trim()) && !esd.getText().toString().trim().isEmpty() && !eed.getText().toString().trim().isEmpty() && !eet.getText().toString().trim().isEmpty() && !est.getText().toString().trim().isEmpty())
                {
                    if(det.before(dst) || sst.equals(set))
                    {
                        cnt++;
                        eet.setError("Fill valid event ending time!");
                        est.setError("Fill valid event starting time!");
                    }
                }
                if(el.getText().toString().trim().isEmpty())
                {
                    cnt++;
                    el.setError("Fill event location!");
                }
                if(upload.getText().toString().trim().isEmpty() || upload.getText().toString().equals("No Banner Selected"))
                {
                    cnt++;
                    upload.setError("Choose your image!");
                }
                if(cnt==0) {
                    Log.d("Frags", st);
                    /*int ind = email.indexOf('@');
                    File imagefile=new File(imagepath);
                    File file = new File(pdfpath);

                    String imageextension=imagefile.toString();
                    int index=imageextension.lastIndexOf('.');

                    String pdfname="PDF_Events/" + EN.getText().toString() + "_" + email.substring(0, ind)+".pdf";

                    String imagename = "Events/" + EN.getText().toString() + "_" + email.substring(0, ind) +".jpg";*/
                    // Parsing any Media type file
                    RequestBody requestBody1 = RequestBody.create(MediaType.parse("*/*"), file);
                    RequestBody requestBody2 = RequestBody.create(MediaType.parse("*/*"), imagefile);

                    MultipartBody.Part pdfupload = MultipartBody.Part.createFormData("pdffile", file.getName(), requestBody1);
                    MultipartBody.Part imageupload = MultipartBody.Part.createFormData("imagefile", imagefile.getName(), requestBody2);

                    RequestBody pdfile_request = RequestBody.create(MediaType.parse("text/plain"), file.getName());
                    RequestBody imagefile_request = RequestBody.create(MediaType.parse("text/plain"), imagefile.getName());

                    Retrofit instance = MyRetrofit.getRetrofit(getString(R.string.IP1));

                    Call<PostEventsResponse> call;
                    api = instance.create(Api.class);
                    call = api.postevents1(pdfupload,imageupload,EN.getText().toString(),ED.getText().toString(),esd.getText().toString(),eed.getText().toString(),sst,set,el.getText().toString(),id);
                    Log.d("event",call.toString());

                    call.enqueue(new Callback<PostEventsResponse>() {
                        @Override
                        public void onResponse(Call<PostEventsResponse> call, Response<PostEventsResponse> response) {
                            Log.d("events",response.body().toString());
                            finish(response.body().getStatus());

                        }
                        @Override
                        public void onFailure(Call<PostEventsResponse> call, Throwable t) {
                          Log.d("Response",t.toString());
                        }
                    });
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == GALLERY_REQUEST && grantResults[0] == PERMISSION_GRANTED)
        {
            //Toast.makeText(contsext,"GRANTED",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_REQUEST);
        }
        else if (requestCode == PDF_REQUEST && grantResults[0] == PERMISSION_GRANTED)
        {
            //Toast.makeText(context,"GRANTED",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent,PDF_REQUEST);

        }
        else {
            Toast.makeText(context, "Permission not Granted", Toast.LENGTH_LONG).show();
        }

    }
    private String imageToString()
    {
        Bitmap bitmap=null;
        try
        {
            bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_REQUEST && resultCode==RESULT_OK)
        {
            try
            {
                uri = data.getData();
                upload.setText("File Selected");
                imagefile = FileUtil.from(this, uri);
                upload.setText(imagefile.getPath());
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
                ef.setText("File Selected");
                file = FileUtil.from(this, uri);
                ef.setText(file.getPath());
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

    void finish(String status)
    {
        Log.d("Mylog",status);
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
            msg.setText("Your Event has been Posted");
            alertDialog.show();

            api.getKey().enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String usertoken = response.body();
                    Log.d("tokerec", usertoken);
                    sendNotifications(usertoken, "New Event",ED.getText().toString().trim());
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

        }
        if (status.equals("failure")) {
            Toast.makeText(context, "Server Problem", Toast.LENGTH_LONG).show();

        }
    }
    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    public void onAttachFragment(android.app.Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    public void sendNotifications(String usertoken, String title, String message) {
        Data data = new Data(title, message);
        usertoken = "fTZTBsrmRau36PqeNZd8gp:APA91bHtobEf-Q9j_--57GH5lP8yoCcsUz0NjAOPohv9v67NXAPt32TWg8YtW2EPXO72GWG_tlZSoiZQNGNSkVhcEcJmP7XL1EBbG2qQkh-PxX2UKTDonpZlZ2uUdnHmhUUWbRJl3b38";
        NotificationSender sender = new NotificationSender(data, usertoken);

        APIService apiService = Client.getClient(fcmurl).create(APIService.class);

        apiService.sendNotifcation(sender).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                Log.d("TRIED",response+""+response.code()+"_"+response.body().success);

                if (response.code() == 200) {
                    if (response.body().success != 1) {
                        Log.d("Notification","Nhi hua");
                    } else {
                        Log.d("Notification","Hogaya");
                    }
                }
            }
            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Log.d("notification", t.toString());
            }
        });
    }
    /*private void  requestMultiplePermissions(){
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
    }*/

}


