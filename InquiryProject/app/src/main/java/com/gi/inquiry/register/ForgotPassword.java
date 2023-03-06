package com.gi.inquiry.register;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.inquiry.Network.Retro;
import com.gi.inquiry.Network.RetroInterface;
import com.gi.inquiry.R;
import com.gi.inquiry.staticFunction.AlertMessage;
import com.gi.inquiry.staticFunction.ErrorDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    EditText newPass, confirmPass, userName;
    TextView submit, reset;

    ProgressDialog dialog;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        userName = findViewById(R.id.usernameEdt);
        newPass = findViewById(R.id.newPass);
        confirmPass = findViewById(R.id.confirmPass);
        submit = findViewById(R.id.submitBtn);
        reset = findViewById(R.id.resetBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPass();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName.setText("");
                newPass.setText("");
                confirmPass.setText("");
            }
        });
    }

    public void forgotPass() {
        int c = 0;
        if (newPass.getText().toString().isEmpty()) {
            newPass.setError("Enter New Password");
            c++;
        } else if (confirmPass.getText().toString().isEmpty()) {
            newPass.setError("Enter Confirm Password");
            c++;
        }
        if (c == 0) {
            dialog = AlertMessage.showProgressDialog(ForgotPassword.this);
            if (newPass.getText().toString().equals(confirmPass.getText().toString())) {
                Retro.getRetrofit().create(RetroInterface.class).forgotPassword(userName.getText().toString(), newPass.getText().toString()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        try {
                            if (response.body().equals("success")) {
                                Intent intent = new Intent(ForgotPassword.this, Login.class);
                                startActivity(intent);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(ForgotPassword.this, "Contact to admin", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        } catch (Exception e) {
                            builder = ErrorDialog.showBuilder(ForgotPassword.this);
                            builder.show();
                            dialog.dismiss();
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        builder = ErrorDialog.showBuilder(ForgotPassword.this);
                        builder.show();
                        dialog.dismiss();
                    }
                });
            } else {
                Toast.makeText(this, "Password Don't Match", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }
    }
}