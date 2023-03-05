package com.example.samples.Frags;

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
import android.preference.PreferenceManager;
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
import androidx.fragment.app.Fragment;

import com.example.samples.Api;
import com.example.samples.MyRetrofit;

import com.example.samples.PostEventsResponse;
import com.example.samples.R;
import java.io.ByteArrayOutputStream;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class FragmentPostEvents extends Fragment {
    Api api;
    String email;
    EditText esd,eed,est,eet,el,ef,upload;
    Context context;
    String st,et,sst,set;
    TextView image;
    private static final int BUFFER_SIZE = 1024 * 2;
    private static final String IMAGE_DIRECTORY = "/demonuts_upload_gallery";
    int GALLERY_REQUEST=0;
    int PDF_REQUEST=1;
    Uri uri,pdf;

    String startDate,expireDate,startTime,EndTime,pdfpath;

    public FragmentPostEvents(Context context)
    {
        this.context=context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_postevent,null);

        //Log.d("email",email+"PostEvents");

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);
        //image=view.findViewById(R.id.image);
        //image.setText("No file Selected");
        Log.d("Bundle",email);
        final EditText EN = (EditText) view.findViewById(R.id.EN);
        final EditText ED = (EditText) view.findViewById(R.id.ED);

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
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent,PDF_REQUEST);

            }
        });

        Button PE = (Button) view.findViewById(R.id.PE);
        upload=(EditText) view.findViewById(R.id.EB);
        upload.setText("No Banner Selected");

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
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
                tpd = new TimePickerDialog(context,R.style.DialogTheme, listener, 0,0,false);
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
                tpd = new TimePickerDialog(context,R.style.DialogTheme, listener, 0,0,false);
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



                    int ind = email.indexOf('@');
                    String path = "Events/" + EN.getText().toString() + "_" + email.substring(0, ind) + ".jpg";
                    String post_events = "'" + EN.getText().toString() + "','" + ED.getText().toString() + "','" + path + "','" + esd.getText().toString() + "','" + eed.getText().toString() + "','" + sst + "','" +
                            set + "','" + el.getText().toString() + "'";
                    String image = imageToString();

                    Log.d("post", post_events);
                    Log.d("post", path);
                    Log.d("post", image);
                    Log.d("post", email);
                    Retrofit instance = MyRetrofit.getRetrofit(getString(R.string.IP));

                    Call<PostEventsResponse> call;
                    api = instance.create(Api.class);
                   // call = api.postevents(post_events, email, image, path);

                    /*call.enqueue(new Callback<PostEventsResponse>() {
                        @Override
                        public void onResponse(Call<PostEventsResponse> call, Response<PostEventsResponse> response) {
                            PostEventsResponse postEventsResponse = response.body();
                            Log.d("responseP", postEventsResponse.getStatus());
                            finish(postEventsResponse.getStatus());
                        }

                        @Override
                        public void onFailure(Call<PostEventsResponse> call, Throwable t) {

                            Log.d("responseP", "error : " + t.toString());
                        }
                    });*/
                }
            }
        });
        return view;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PERMISSION_GRANTED)
        {
            Toast.makeText(context,"GRANTED",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_REQUEST);
        }
        else {
            Toast.makeText(context, "PERMISSION NOT TAKEN", Toast.LENGTH_LONG).show();
        }
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
        return Base64.encodeToString(image,Base64.DEFAULT);
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
                    upload.setText("No file Selected");
                }
                if(uri.toString().length()>0)
                {
                    upload.setText("Banner Selected");
                }
            }
            catch (Exception ex)
            {
                Log.d("Whyred",ex.getMessage());
            }

        }

    }

    void finish(String status) {
        if (status.equals("Success")) {
            Toast.makeText(context, "Event Posted", Toast.LENGTH_LONG).show();
        }
        if (status.equals("Failure")) {
            Toast.makeText(context, "Server Problem", Toast.LENGTH_LONG).show();

        }
    }
}


