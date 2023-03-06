package com.gi.application1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.application1.Model.LoginInfo;

public class Login extends AppCompatActivity {

    EditText emailEdt, passwordEdt;
    TextView register, loginBtn, resetbtn;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEdt = findViewById(R.id.usernameEdt);
        register = findViewById(R.id.Register);
        passwordEdt = findViewById(R.id.pass);
        loginBtn = findViewById(R.id.login);
        resetbtn = findViewById(R.id.resetBtn);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                Log.d("mylog", "Register");
//                Toast.makeText(Login.this,"Register",Toast.LENGTH_SHORT).show();
            }
        });
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailEdt.setError("");
                passwordEdt.setError("");
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdt.getText().toString();
                String pass = passwordEdt.getText().toString();
                LoginInfo li = new LoginInfo();
                if(email.isEmpty())
                {
                    emailEdt.setError("Enter the Email Id");
                }
                else if(pass.isEmpty())
                {
                    passwordEdt.setError("Enter the password");
                }
                else {
                    if (li.getEmail().equals(email) && li.getPass().equals(pass)) {
                        Intent i = new Intent(Login.this, Home.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Login.this,"Please Check the Email and Password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
