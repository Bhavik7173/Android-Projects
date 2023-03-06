package com.gi.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class Register extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    EditText dob, email, fname, lname, password;
    TextView submit,reset;
    int c =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dob = findViewById(R.id.dobEdt);
        fname = findViewById(R.id.fnameEdt);
        lname = findViewById(R.id.lnameEdt);
        email = findViewById(R.id.emailEdt);
        password = findViewById(R.id.passwordEdt);
        submit = findViewById(R.id.submitBtn);
        reset = findViewById(R.id.resetBtn);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Please note that use your package name here
                DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname.setText("");
                lname.setText("");
                email.setText("");
                password.setText("");
                dob.setText("");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = fname.getText().toString();
                String lastname = lname.getText().toString();
                String dob1 = dob.toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                if(firstname.isEmpty())
                {
                    fname.setError("Enter First Name");
                    c++;
                }else if(lastname.isEmpty())
                {
                    lname.setError("Enter Last Name");
                    c++;
                }else if(dob1.isEmpty())
                {
                    dob.setError("Enter Date Of Birth");
                    c++;
                }else if(Email.isEmpty())
                {
                    email.setError("Enter Email id");
                    c++;
                }
                else if(Password.isEmpty())
                {
                    password.setError("Enter Password");
                    c++;
                }
                else
                {
                    Intent i = new Intent(Register.this,Login.class);
                    startActivity(i);
                    finish();

                }
            }
        });
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
        dob.setText(selectedDate);
    }
}
