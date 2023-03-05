package com.gi.brainproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.brainproject.R;
import com.gi.brainproject.retro.RetroInterface;
import com.gi.brainproject.retro.RetrofitClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    TextView submit;
    EditText npass, ncpass;
    String newpass, newcpass;
    int cnt = 0;
    String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_^&+=])(?=\\S+$).{8,}$";
    Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        npass = findViewById(R.id.newPass);
        ncpass = findViewById(R.id.confirmPass);
        submit = findViewById(R.id.submit);

        Intent intent = getIntent();
        String str = intent.getStringExtra("email");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newpass = npass.getText().toString();
                newcpass = ncpass.getText().toString();
                if (npass.getText().toString().isEmpty()) {
                    Toast.makeText(ForgotPassword.this, "p empty", Toast.LENGTH_LONG).show();
                    cnt++;
                    npass.setError("Enter your password!");

                } else {
                    Matcher m = pattern.matcher(npass.getText().toString());

                    if (!m.matches()) {
                        cnt++;
                        npass.setError("Password is invalid!");

                    } else {

                    }

                }
                if (ncpass.getText().toString().isEmpty()) {
                    cnt++;
                    Toast.makeText(ForgotPassword.this, "Cp empty", Toast.LENGTH_LONG).show();
                    ncpass.setError("Enter your password!");
                } else {
                    Matcher m = pattern.matcher(ncpass.getText().toString());
                    if (!m.matches()) {
                        cnt++;
                        ncpass.setError("Password is invalid!");
                    }

                }
                if (!npass.getText().toString().equals(ncpass.getText().toString())) {
                    cnt++;
                    ncpass.setError("Password do not match!");

                    Toast.makeText(ForgotPassword.this, "PASSWORD DO NOT MATCH!", Toast.LENGTH_LONG).show();
                }
                if (cnt == 0) {

                    RetrofitClient.getClient(ForgotPassword.this).create(RetroInterface.class).forgotpassword(str, newpass, newcpass).enqueue(new Callback<String>() {

                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(ForgotPassword.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPassword.this, Login.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(ForgotPassword.this, t.toString(), Toast.LENGTH_SHORT).show();
                            Log.d("response_fail", t.toString());
                        }
                    });
                } else {
                    Toast.makeText(ForgotPassword.this, "Please check the Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}