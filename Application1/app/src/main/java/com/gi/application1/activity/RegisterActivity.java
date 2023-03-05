package com.gi.application1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gi.application1.staticfunction.DatePicker;
import com.gi.application1.R;
import com.gi.application1.model.User;
import com.gi.application1.staticfunction.Validation;

import java.text.DateFormat;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText edDOB, edEmail, edFname, edLname, edPassword;
    TextView submit, reset;
    int c = 0;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String namePattern = "^[A-Za-z]\\w{3, 30}$";
    private boolean is8char = false, hasUpper = false, hasnum = false, hasSpecialSymbol = false, isSignupClickable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edDOB = findViewById(R.id.dobEdt);
        edFname = findViewById(R.id.fnameEdt);
        edLname = findViewById(R.id.lnameEdt);
        edEmail = findViewById(R.id.emailEdt);
        edPassword = findViewById(R.id.passwordEdt);
        submit = findViewById(R.id.submitBtn);
        reset = findViewById(R.id.resetBtn);

        edDOB.setOnClickListener(new View.OnClickListener() {
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
                edFname.setText("");
                edLname.setText("");
                edEmail.setText("");
                edPassword.setText("");
                edDOB.setText("");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String firstname = edFname.getText().toString();
                String lastname = edLname.getText().toString();
                String dob1 = edDOB.toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();

                Validation v1 = new Validation();
                if (v1.nameValidation(edFname)==false) {
                    System.out.println(firstname);
                    edFname.setError("Enter First Name");
                    c++;
                } else if (v1.nameValidation(edLname)==false) {
                    edLname.setError("Enter Last Name");
                    c++;
                } else if (dob1.isEmpty()) {
                    edDOB.setError("Enter Date Of Birth");
                    c++;
                } else if (v1.emailValidator(edEmail)==false) {
                    edEmail.setError("Enter Email id");
                    c++;
                } else if (v1.passwordValidator(edPassword)==false) {
                    edPassword.setError("Enter Password");
                    c++;
                } else {

                    User user = User.getInstance();
                    user.setUser_name(edFname + " " +edLname);
                    user.setEmail(email);
                    user.setPass(password);
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
        edDOB.setText(selectedDate);
    }

}
