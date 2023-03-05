package com.gi.projectgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPage extends AppCompatActivity {
    EditText uname, birth, mobno, Email, password;
    Button signin, reset;
    String name, dob, mob, email, pass;
    SharedPreferences pref;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        uname = findViewById(R.id.unameEdt);
        birth = findViewById(R.id.birthEdt);
        mobno = findViewById(R.id.mobnoEdt);
        Email = findViewById(R.id.EmailEdt);
        password = findViewById(R.id.passEdt);
        signin = findViewById(R.id.signinbtn);
        reset = findViewById(R.id.resetbtn);
        pref = getSharedPreferences("Register_details", MODE_PRIVATE);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname.setText("");
                birth.setText("");
                mobno.setText("");
                Email.setText("");
                password.setText("");
            }
        });
        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        SignUpPage.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                birth.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = uname.getText().toString();
                dob = birth.getText().toString();
                mob = mobno.getText().toString();
                email = Email.getText().toString();
                pass = password.getText().toString();

                RetrofitClient.getClient(SignUpPage.this).create(RetroInterface.class).signup(name, dob, mob, email, pass).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(SignUpPage.this, "Data added to API", Toast.LENGTH_SHORT).show();
                        String responseFromAPI = response.body();
                        String responseString = "Name : " + name + "\n" + "Contact : " + mob + "Email : " + email;
                        Toast.makeText(SignUpPage.this,responseString,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpPage.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(SignUpPage.this," " + t.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.d("gisurat",t.toString());
                    }
                });


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
