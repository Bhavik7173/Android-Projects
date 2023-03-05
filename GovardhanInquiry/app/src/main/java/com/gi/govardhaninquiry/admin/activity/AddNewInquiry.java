package com.gi.govardhaninquiry.admin.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.model.InquiryData;
import com.gi.govardhaninquiry.retro.RetroInterface;
import com.gi.govardhaninquiry.retro.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewInquiry extends AppCompatActivity {

    TextInputEditText inameEdt, imobile, iemail, icourse, icollege, ipreffered, inote, idate;
    Button submit;
    String name, mobile, email, course, college, preffered, note, date, uid, uemail;
    int id = 0;
    InquiryData inquiryData;
    ArrayList<InquiryData> inquiryArrayData;
    SharedPreferences sharedPre;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_inquiry);
        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Add New Inquiry</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        sharedPre = getSharedPreferences("login", Context.MODE_PRIVATE);
        uid = sharedPre.getString("userID", null);
        uemail = sharedPre.getString("email", null);
        Log.d("AddNewinquiry_id", uid);
        inameEdt = findViewById(R.id.inameEdt);
        imobile = findViewById(R.id.imobileEdt);
        iemail = findViewById(R.id.iemailEdt);
        icourse = findViewById(R.id.icourseEdt);
        icollege = findViewById(R.id.icollegeEdt);
        ipreffered = findViewById(R.id.ipreferredtimeEdt);
        inote = findViewById(R.id.inoteEdt);
        idate = findViewById(R.id.idateEdt);
        submit = findViewById(R.id.submit_button);

        inquiryArrayData = new ArrayList<InquiryData>();
        idate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewInquiry.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        idate.setText(i + "/" + (i1 + 1) + "/" + i2);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setCancelable(false);
                datePickerDialog.show();
            }
        });
        editData();

//        intent = getIntent();
//        name = intent.getStringExtra("iname");
//        course = intent.getStringExtra("icourse");
//        mobile = intent.getStringExtra("imobile");
//        date = intent.getStringExtra("idate");
//        email = intent.getStringExtra("iemail");
//        college = intent.getStringExtra("icollege");
//        note = intent.getStringExtra("inote");
//        preffered = intent.getStringExtra("ipreffered");
//
//        Log.d("gilog_inquiry",name+course+mobile);

        submit.setText("Submit");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (submit.getText().toString().equalsIgnoreCase("Submit")) {
//                inquiryData = getDetail();
                    name = inameEdt.getText().toString();
                    mobile = imobile.getText().toString();
                    email = iemail.getText().toString();
                    course = icourse.getText().toString();
                    college = icollege.getText().toString();
                    preffered = ipreffered.getText().toString();
                    note = inote.getText().toString();
                    date = idate.getText().toString();
                    RetrofitClient.getClient(AddNewInquiry.this).create(RetroInterface.class).addInquiry1(uid, name, mobile, email, course, college, preffered, note, date)
                            .enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    Toast.makeText(AddNewInquiry.this, "Inquiry Successfully Added!", Toast.LENGTH_SHORT).show();
                                    String responseFromAPI = response.body();
                                    //Toast.makeText(Signup.this, responseFromAPI, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AddNewInquiry.this, AdminInquiry.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Toast.makeText(AddNewInquiry.this, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d("gisurat", t.toString());
                                }
                            });
                }
                else if(submit.getText().toString().equalsIgnoreCase("Edit"))
                {
                    intent = getIntent();
                    name = intent.getStringExtra("iname");
                    course = intent.getStringExtra("icourse");
                    mobile = intent.getStringExtra("imobile");
                    date = intent.getStringExtra("idate");
                    email = intent.getStringExtra("iemail");
                    college = intent.getStringExtra("icollege");
                    note = intent.getStringExtra("inote");
                    preffered = intent.getStringExtra("ipreffered");

                    inameEdt.setText(name);
                    imobile.setText(mobile);
                    iemail.setText(email);
                    icourse.setText(course);
                    icollege.setText(college);
                    ipreffered.setText(preffered);
                    inote.setText(note);
                    idate.setText(date);

                    submit.setText("Submit");
                }
            }
        });
    }

    public InquiryData getDetail() {

        name = inameEdt.getText().toString();
        mobile = imobile.getText().toString();
        email = iemail.getText().toString();
        course = icourse.getText().toString();
        college = icollege.getText().toString();
        preffered = ipreffered.getText().toString();
        note = inote.getText().toString();
        date = idate.getText().toString();
        inquiryData = new InquiryData(name, mobile, email, course, college, preffered, note, date);
        return inquiryData;
    }

    private void editData() {
        if (getIntent().getBundleExtra("userdata") != null) {
            Bundle bundle = getIntent().getBundleExtra("userdata");
            id = bundle.getInt("inquiry_id");
            inameEdt.setText(bundle.getString("inquiry_name"));
            imobile.setText(bundle.getString("inquiry_mobile"));
            iemail.setText(bundle.getString("inquiry_email"));
            icourse.setText(bundle.getString("inquiry_course"));
            inote.setText(bundle.getString("inquiry_note"));
            icollege.setText(bundle.getString("inquiry_college"));
            ipreffered.setText(bundle.getString("inquiry_preferred_time"));
            idate.setText(bundle.getString("inquiry_date"));
            //submit.setText("Edit");
            //submit.setOnClickListener((View.OnClickListener) this);
            //submit.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void cleardata() {
        inameEdt.setText("");
        icourse.setText("");
        imobile.setText("");
        idate.setText("");
        iemail.setText("");
        ipreffered.setText("");
        inote.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

//RetrofitClient.getClient(AddNewInquiry.this).create(RetroInterface.class).addInquiry(inquiryData)
//        .enqueue(new Callback<String>() {
//@Override
//public void onResponse(Call<String> call, Response<String> response) {
//        Toast.makeText(AddNewInquiry.this, "Inquiry Successfully Added!", Toast.LENGTH_SHORT).show();
//        String responseFromAPI = response.body();
//        //Toast.makeText(Signup.this, responseFromAPI, Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(AddNewInquiry.this, AdminInquiry.class);
//        startActivity(intent);
//        finish();
//        }
//
//@Override
//public void onFailure(Call<String> call, Throwable t) {
//        Toast.makeText(AddNewInquiry.this, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
//        Log.d("gisurat", t.toString());
//        }
//        });