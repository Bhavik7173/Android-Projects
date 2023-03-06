package com.example.inquiry.Register;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inquiry.Network.Retro;
import com.example.inquiry.Network.RetroInterface;
import com.example.inquiry.R;
import com.example.inquiry.SharedPreference.SharedPre;
import com.example.inquiry.SplashActivity;
import com.example.inquiry.StaticFunction.AlertMessage;
import com.example.inquiry.StaticFunction.ErrorDialog;
import com.example.inquiry.pojo.UserPojo;

import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText emailEdt, passwordEdt;
    ProgressDialog dialog;
    TextView forgotPass, signup;
    Button login;
    SharedPre sharedPre;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEdt = findViewById(R.id.usernameEdt);
        forgotPass = findViewById(R.id.forgotpassword);
        passwordEdt = findViewById(R.id.pass);


        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Login.this, ForgotOTP.class);
//                startActivity(intent);
                Log.d("myylog","forgot password");
            }
        });
    }


    public void loginBtn(View view) {
        int c = 0;
        if (emailEdt.getText().toString().isEmpty()) {
            emailEdt.setError("Enter Email id");
            c++;
        } else if (passwordEdt.getText().toString().isEmpty()) {
            passwordEdt.setError("Enter Password");
            c++;
        }
        else {

            dialog = AlertMessage.showProgressDialog(Login.this);
            Retro.getRetrofit(this).create(RetroInterface.class).login(emailEdt.getText().toString(), passwordEdt.getText().toString()).enqueue(new Callback<UserPojo>() {
                @Override
                public void onResponse(retrofit2.Call<UserPojo> call, Response<UserPojo> response) {
                    UserPojo pojo = response.body();
                    try {
                        if (pojo.getStatus().equals("success")) {
                            sharedPre.writeData("userID", pojo.getUser_id());
                            sharedPre.writeData("status", "LoggedIn");
                            Intent intent = new Intent(Login.this, SplashActivity.class);
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
                public void onFailure(retrofit2.Call<UserPojo> call, Throwable t) {
                    builder = ErrorDialog.showBuilder(Login.this);
                    builder.show();
                    dialog.dismiss();
                }


            });
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
