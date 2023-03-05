package com.gi.dailogbox;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements Runnable {

    TextView t1, t2, t3, t4, t5, t6;
    Handler handler;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.alert);
        t2 = findViewById(R.id.datepicker);
        t3 = findViewById(R.id.timepicker);
        t4 = findViewById(R.id.progress);
        t5 = findViewById(R.id.custom);
        t6 = findViewById(R.id.progress2);
    }

    public void alert(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert Dailog");
        builder.setMessage("Do you want to delete?");
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                t1.setText("Positive Button Clicked.");
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                t1.setText("Negative Button Clicked.");
            }
        });
        //builder.setNeutralButton("Cancel",null);
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void datepicker(View view) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                t2.setText(i + "/" + (i1 + 1) + "/" + i2);
            }
        }, calendar.YEAR, calendar.MONTH, calendar.DAY_OF_MONTH);
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();
    }

    public void timepicker(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                if (i > 12) {
                    t3.setText((i - 12) + " : " + i1 + "PM");
                } else {
                    t3.setText(i + " : " + i1 + "AM");
                }
            }
        }, 11, 18, false);
        timePickerDialog.setCancelable(false);
        timePickerDialog.show();
    }

    public void progress(View view) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Progress Dailog...");
        handler = new Handler();
        handler.postDelayed(this, 3000);
        progressDialog.show();
        t4.setText("Progress Dailog Spinner...");

    }

    public void progress2(View view) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Progress Dailog...");
        progressDialog.setMax(100);
        progressDialog.setProgress(10);
        handler = new Handler();
        handler.postDelayed(this, 1000);
        progressDialog.show();
        t6.setText("Progress Dailog Horizontally...");
    }

    public void custom(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dailog);
        dialog.show();
    }

    @Override
    public void run() {
        progressDialog.dismiss();
    }
}