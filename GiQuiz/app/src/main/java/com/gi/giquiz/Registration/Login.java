package com.gi.giquiz.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gi.giquiz.StaticFunction.AlertMessage;
import com.gi.giquiz.Home;
import com.gi.giquiz.Network.Retro;
import com.gi.giquiz.Network.RetroInterface;
import com.gi.giquiz.Pojo.UserPojo;
import com.gi.giquiz.R;
import com.gi.giquiz.SharedPrefrence.SharedPre;
import com.gi.giquiz.StaticFunction.ErrorDialog;
import com.gi.giquiz.StaticFunction.ErrorLogs;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextInputEditText mobile, password;
    TextView forgotPass, signup;
    Button login;
    SharedPreferences sharedPre;
    ProgressDialog dialog;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        forgotPass = findViewById(R.id.forgotPass);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        sharedPre = getSharedPreferences("login", Context.MODE_PRIVATE);


        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgotOTP.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogIn();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    public void LogIn() {
        int c = 0;
        if (mobile.getText().toString().isEmpty()) {
            mobile.setError("Enter mobile");
            c++;
        } else if (password.getText().toString().isEmpty()) {
            password.setError("Enter Password");
            c++;
        }
        if (c == 0) {
            if (mobile.getText().toString().length() < 10) {
                Toast.makeText(this, "Please enter valid mobile", Toast.LENGTH_SHORT).show();
            } else {
                dialog = AlertMessage.showProgressDialog(Login.this);
                Retro.getRetrofit(this).create(RetroInterface.class).login(mobile.getText().toString(), password.getText().toString()).enqueue(new Callback<UserPojo>() {
                    @Override
                    public void onResponse(Call<UserPojo> call, Response<UserPojo> response) {

                        UserPojo pojo = response.body();
                        try {
                            if (pojo.getStatus().equals("success")) {
                                SharedPreferences.Editor ed = sharedPre.edit();
                                ed.putString("userID", pojo.getUser_id());
                                ed.putBoolean("status1", true);
                                ed.putString("mobile", pojo.getuMobile());
                                ed.putString("name", pojo.getuName());
                                ed.putString("email", pojo.getuEmail());
                                ed.putString("status", pojo.getStatus());
                                Log.d("gilog1",pojo.getStatus());
                                ed.commit();
                                Intent intent = new Intent(Login.this, Home.class);
                                startActivity(intent);
                                dialog.dismiss();
                            } else {
                                dialog.dismiss();
                                Toast.makeText(Login.this, "Wrong Credential", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {

                            builder = ErrorDialog.showBuilder(Login.this);
                            builder.show();
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserPojo> call, Throwable t) {
                        builder = ErrorDialog.showBuilder(Login.this);
                        builder.show();
                        dialog.dismiss();
                    }
                });
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}