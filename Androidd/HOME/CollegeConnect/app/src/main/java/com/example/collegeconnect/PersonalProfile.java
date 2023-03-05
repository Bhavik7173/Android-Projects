package com.example.collegeconnect;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class PersonalProfile extends AppCompatActivity implements Runnable {
    private static final int PICKFILE_RESULT_CODE = 1;
    EditText ed1, ed2, ed3, ed4;
    ImageView iv;
    ProgressDialog pd,pd1;
    private DatePicker dp;
    private Calendar cal;
    private int year, month, day;
    private TextView dw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed4 = findViewById(R.id.contact);
        iv = findViewById(R.id.img1);
        dw = findViewById(R.id.date1);
        cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

    }

    public void date(View view) {
        Calendar cl;
        cl = Calendar.getInstance();

        DatePickerDialog dpd;
        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                dw.setText(i2 + "/" + (i1 + 1) + "/" + i);
            }

        }, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
        dpd.setCancelable(false);

        dpd.show();
    }


    public void previous(View view) {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivityForResult(intent,200);
        finish();
    }

    public void next(View view) {
        Intent i = new Intent(this, PersonalProfile2.class);
        startActivity(i);
    }


    public void attach1(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        startActivityForResult(intent, PICKFILE_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PICKFILE_RESULT_CODE:
                if (resultCode == RESULT_OK) {
                    String FilePath = data.getData().getPath();
                    iv.setImageResource(Integer.parseInt(FilePath));
                }
                break;

        }
    }

    public void progress2(View view) {
        /*pd1 = new ProgressDialog(this);
        pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd1.setMessage("working....");
        pd1.setMax(100);

        Handler h = new Handler();
        h.postDelayed(this, 1000);

        pd1.show();*/
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Uploading....");
        pd.show();
        Handler h = new Handler();
        h.postDelayed(this,3000);
        //pd.dismiss();
    }
    public void onBackPressed() {
        Intent i = new Intent(this,HomeActivity.class);
        setResult(100,i);
        finish();
        //super.onBackPressed();
    }
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            pd1.setProgress(i);

        }
        //pd1.dismiss();
    }
}