package com.example.sample.Frags;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import androidx.fragment.app.Fragment;

import com.example.sample.Api;
import com.example.sample.MyRetrofit;
import com.example.sample.PostJobsResponse;
import com.example.sample.R;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class FragmentPostJobs extends Fragment {

    String email;
    Api api;
    String expDate,postDate;
    Context context;
    TextView image;
    int GALLERY_REQUEST=0;
    Uri uri;
    EditText CL,JT,JRE,JD,JS,JSD,JED,JL;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.layout_postjob,null);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);

         JT = (EditText) view.findViewById(R.id.JT);
         JRE = (EditText) view.findViewById(R.id.JP);
         JD = (EditText) view.findViewById(R.id.JD);
         JS = (EditText) view.findViewById(R.id.JS);
         JSD = (EditText) view.findViewById(R.id.JSD);
         JED = (EditText) view.findViewById(R.id.JED);
         CL = (EditText) view.findViewById(R.id.CL);
         JL = (EditText) view.findViewById(R.id.JL);

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
                    String path = "Jobs/" + JT.getText().toString() + "_" + email.substring(0, ind) + ".jpg";
                    final String post_job = "'" + JT.getText().toString() + "','" + path + "','" + JRE.getText().toString() + "','" + JD.getText().toString() + "','" + JSD.getText().toString() + "','" + JED.getText().toString() + "','" + JS.getText().toString() + "','" + JL.getText().toString() + "'";
                    Log.d("job", post_job);
                    String base64 = imageToString();

                    Retrofit instance = MyRetrofit.getRetrofit(getString(R.string.IP));
                    Call<PostJobsResponse> call;
                    api = instance.create(Api.class);
                    call = api.postjobs(post_job, email, base64, path);

                    call.enqueue(new Callback<PostJobsResponse>() {
                        @Override
                        public void onResponse(Call<PostJobsResponse> call, Response<PostJobsResponse> response) {
                            PostJobsResponse postJobsResponse = response.body();
                            String status = postJobsResponse.getStatus();
                            Log.d("responseP", status);
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

        return view;
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
                if(!uri.toString().equals(""))
                {
                    CL.setText("File Selected");
                }
            }
            catch (Exception ex)
            {
                Log.d("Whyred",ex.getMessage());
            }

        }
    }

    public FragmentPostJobs(Context context) {
        this.context=context;
    }
    void finish(String status)
    {
        if(status.equals("Success"))
        {
            Toast.makeText(context,"Job Posted ", Toast.LENGTH_LONG).show();
        }
        if(status.equals("Failure"))
        {
            Toast.makeText(context,"Server Problem", Toast.LENGTH_LONG).show();

        }
    }
}

