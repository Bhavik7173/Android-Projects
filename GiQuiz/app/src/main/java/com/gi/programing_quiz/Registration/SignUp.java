package com.gi.programing_quiz.Registration;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.programing_quiz.Network.Retro;
import com.gi.programing_quiz.Network.RetroInterface;
import com.gi.programing_quiz.R;
import com.gi.programing_quiz.StaticFunction.AlertMessage;
import com.gi.programing_quiz.StaticFunction.ErrorDialog;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    TextInputEditText name, email, password, confirmPass,mobile;
    Button signup;
    TextView login;
    String userMobile;
    ProgressDialog dialog;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirmPass);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        userMobile = getIntent().getStringExtra("userMobile");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

    }

    public void signUp() {
        int c = 0;
        if (name.getText().toString().isEmpty()) {
            name.setError("Enter Name");
            c++;
        } else if (email.getText().toString().isEmpty()) {
            email.setError("Enter Email");
            c++;
        } else if (password.getText().toString().isEmpty()) {
            password.setError("Enter password");
            c++;
        }
        if (c == 0) {
            dialog = AlertMessage.showProgressDialog(SignUp.this);
            if (password.getText().toString().equals(confirmPass.getText().toString())) {
                Retro.getRetrofit(this).create(RetroInterface.class).signup(name.getText().toString(), email.getText().toString(), mobile.getText().toString(), password.getText().toString())
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.d("gilog4",response.body().toString());
                                try {
                                    if (response.body().equals("success")) {
                                        Log.d("gilog1",response.body().toString());
                                        Intent intent = new Intent(SignUp.this, Login.class);
                                        startActivity(intent);
                                        dialog.dismiss();
                                    } else {
                                        Log.d("gilog2",response.body().toString());
                                        Toast.makeText(SignUp.this, "Contact to admin", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }

                                } catch (Exception e) {
                                    Log.d("gilog3",e.toString());
                                    builder = ErrorDialog.showBuilder(SignUp.this);
                                    builder.show();
                                    dialog.dismiss();
                                }


                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d("gilog_signup", t.toString());
                                builder = ErrorDialog.showBuilder(SignUp.this);
                                builder.show();
                                dialog.dismiss();
                            }
                        });
            } else {
                Toast.makeText(this, "Password don't match", Toast.LENGTH_SHORT).show();
            }

        }

    }
}