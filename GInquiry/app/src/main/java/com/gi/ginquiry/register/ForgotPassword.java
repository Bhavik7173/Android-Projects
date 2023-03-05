package com.gi.ginquiry.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.ginquiry.R;
import com.gi.ginquiry.Retro.RetroInterface;
import com.gi.ginquiry.Retro.RetrofitClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    EditText email, password, cpassword;
    TextView ph;
    Button submit;
    String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_^&+=])(?=\\S+$).{8,}$";
    Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.emailEdt);
        password = findViewById(R.id.Password);
        ph = findViewById(R.id.ph);
        cpassword = findViewById(R.id.CPassword);
        submit = findViewById(R.id.nextBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uemail = email.getText().toString();
                String upass = password.getText().toString();
                String ucpass = cpassword.getText().toString();
                if (email.getText().toString().trim().isEmpty()) {
                    cnt++;
                    email.setError("Fill your email!");
                }
                if (upass.isEmpty()) {
                    Toast.makeText(ForgotPassword.this, "p empty", Toast.LENGTH_LONG).show();
                    cnt++;
                    password.setError("Enter your password!");
                    ph.setVisibility(View.VISIBLE);
                } else {
                    Matcher m = pattern.matcher(password.getText().toString());

                    if (!m.matches()) {
                        cnt++;
                        password.setError("Password is invalid!");
                        ph.setVisibility(View.VISIBLE);
                    } else {
                        ph.setVisibility(View.GONE);
                    }

                }
                if (ucpass.isEmpty()) {
                    cnt++;
                    Toast.makeText(ForgotPassword.this, "Cp empty", Toast.LENGTH_LONG).show();
                    cpassword.setError("Enter your password!");
                } else {
                    Matcher m = pattern.matcher(ucpass);
                    if (!m.matches()) {
                        cnt++;
                        cpassword.setError("Password is invalid!");
                    }

                }
                if (!upass.equals(ucpass)) {
                    cnt++;
                    cpassword.setError("Password do not match!");

                    Toast.makeText(ForgotPassword.this, "PASSWORD DO NOT MATCH!", Toast.LENGTH_LONG).show();
                }

                if (cnt == 0) {
                    RetrofitClient.getClient(ForgotPassword.this).create(RetroInterface.class).forgotpassword(uemail, upass).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(ForgotPassword.this, "Data added to API", Toast.LENGTH_SHORT).show();
                            String responseFromAPI = response.body();
                            //Toast.makeText(Signup.this, responseFromAPI, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPassword.this, Login.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(ForgotPassword.this, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("gisurat", t.toString());
                        }
                    });
                }
            }
        });
    }
}