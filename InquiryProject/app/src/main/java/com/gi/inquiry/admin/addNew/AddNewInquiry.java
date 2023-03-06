package com.gi.inquiry.admin.addNew;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gi.inquiry.R;
import com.gi.inquiry.admin.DAO.InquiryDB;
import com.gi.inquiry.admin.DAO.SQLiteDBHelper;
import com.gi.inquiry.admin.activity.AdminInquiry;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class AddNewInquiry extends AppCompatActivity {

    TextInputEditText inameEdt, imobile, iemail, icourse, icollege, ipreffered, inote, idate;
    Button submit;
    private InquiryDB dbHandler;
    SQLiteDatabase sqLiteDatabase;
    SQLiteDBHelper sqLiteDBHelper;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_inquiry);
        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Add New Inquiry</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        inameEdt = findViewById(R.id.inameEdt);
        imobile = findViewById(R.id.imobileEdt);
        iemail = findViewById(R.id.iemailEdt);
        icourse = findViewById(R.id.icourseEdt);
        icollege = findViewById(R.id.icourseEdt);
        ipreffered = findViewById(R.id.ipreferredtimeEdt);
        inote = findViewById(R.id.inoteEdt);
        idate = findViewById(R.id.idateEdt);
        submit = findViewById(R.id.submit_button);
        dbHandler = new InquiryDB(AddNewInquiry.this);
        sqLiteDBHelper = new SQLiteDBHelper(this);
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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = sqLiteDBHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("inquiry_name", inameEdt.getText().toString());
                cv.put("inquiry_mobile", imobile.getText().toString());
                cv.put("inquiry_course", icourse.getText().toString());
                cv.put("inquiry_date", idate.getText().toString());
                cv.put("inquiry_email", iemail.getText().toString());
                cv.put("inquiry_college", icollege.getText().toString());
                cv.put("inquiry_preferred_time", ipreffered.getText().toString());
                cv.put("inquiry_note", inote.getText().toString());
                id = 0;
                /*String inameEdt1 = inameEdt.getText().toString();
                String imobile1 = imobile.getText().toString();
                String iemail1 = iemail.getText().toString();
                String icourse1 = icourse.getText().toString();
                String icollege1 = icollege.getText().toString();
                String ipreffered1 = ipreffered.getText().toString();
                String inote1 = inote.getText().toString();
                String idate1 = idate.getText().toString();*/
//                dbHandler.addNewInquiry(inameEdt1, imobile1, iemail1, icourse1, icollege1, ipreffered1, inote1, idate1);
//                Toast.makeText(AddNewInquiry.this, "Inquiry has been added.", Toast.LENGTH_SHORT).show();
//                finish();
                long recid = sqLiteDatabase.insert("inquiry", null, cv);
//                long recid  = dbHandler.addNewInquiry(inameEdt1, imobile1, iemail1, icourse1, icollege1, ipreffered1, inote1, idate1);
                if (recid != -1) {
                    Toast.makeText(AddNewInquiry.this, "successfully insert", Toast.LENGTH_SHORT).show();
                    cleardata();
                    startActivity(new Intent(AddNewInquiry.this, AdminInquiry.class));
                    finish();
                } else {
                    Toast.makeText(AddNewInquiry.this, "something wrong try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
}