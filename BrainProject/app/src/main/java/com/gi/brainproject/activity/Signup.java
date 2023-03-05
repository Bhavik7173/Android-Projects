package com.gi.brainproject.activity;

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

import androidx.appcompat.app.AppCompatActivity;

import com.gi.brainproject.R;
import com.gi.brainproject.retro.RetroInterface;
import com.gi.brainproject.retro.RetrofitClient;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    EditText uname, birth, mobno, Email, password;
    Button signin, reset;
    String name, dob, mob, email, pass;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Signup.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                birth.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, year, month, day);
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

                String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_^&+=])(?=\\S+$).{8,}$";
                Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
                int cnt = 0;
                if (uname.getText().toString().trim().isEmpty()) {
                    cnt++;
                    uname.setError("Fill your name!");
                }
                if (birth.getText().toString().trim().isEmpty()) {
                    cnt++;
                    birth.setError("Fill your date of birth!");
                }
                if (mobno.getText().toString().trim().isEmpty() || mobno.getText().toString().equals("+91")) {
                    cnt++;
                    mobno.setError("Fill your contact number!");
                } else {
                    if (!test(mobno))
                        cnt++;
                }
                if (Email.getText().toString().isEmpty()) {
                    Toast.makeText(Signup.this, "email empty", Toast.LENGTH_LONG).show();
                    cnt++;
                    Email.setError("Enter your email!");
                    Email.setVisibility(View.VISIBLE);
                }
                if (password.getText().toString().isEmpty()) {
                    Toast.makeText(Signup.this, "password empty", Toast.LENGTH_LONG).show();
                    cnt++;
                    password.setError("Enter your password!");
                    password.setVisibility(View.VISIBLE);
                } else {
                    Matcher m = pattern.matcher(password.getText().toString());

                    if (!m.matches()) {
                        cnt++;
                        password.setError("Password is invalid!");
                        password.setVisibility(View.VISIBLE);
                    } else {
                        password.setVisibility(View.GONE);
                    }

                }
                if (cnt == 0) {
                    RetrofitClient.getClient(Signup.this).create(RetroInterface.class).signup(name, dob, mob, email, pass).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(Signup.this, "Data added to API", Toast.LENGTH_SHORT).show();
                            String responseFromAPI = response.body();
                            String responseString = "Name : " + name + "\n" + "Contact : " + mob + "Email : " + email;
                            Toast.makeText(Signup.this, responseString, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Signup.this, Login.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(Signup.this, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("gisurat", t.toString());
                        }
                    });

                }
            }
        });

    }

    public boolean test(EditText edt) {
        String pattern = "^((\\+){0,1}91(\\s){0,1}(\\-){0,1}(\\s){0,1}){0,1}[6-9]{1}[0-9](\\s){0,1}(\\-){0,1}(\\s){0,1}[1-9]{1}[0-9]{7}$";
        if ((edt.getText().toString().matches(pattern))) {
            Toast.makeText(this, "Accepted", Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(this, "declined", Toast.LENGTH_LONG).show();
            edt.setError("Invalid number!");
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}