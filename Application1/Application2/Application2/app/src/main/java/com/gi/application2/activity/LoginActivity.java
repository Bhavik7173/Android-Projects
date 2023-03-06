package com.gi.application2.activity;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.application2.R;
import com.gi.application2.model.User;
import com.gi.application2.staticfunction.Validation;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    EditText emailEdt, passwordEdt;
    TextView register, loginBtn, resetbtn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");

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
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                Log.d("mylog", "Register");

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
                User user = User.getInstance();
                Validation v1 = new Validation();
                if (v1.emailValidator(emailEdt) == false) {
                    emailEdt.setError("Enter the Email Id");
                } else if (v1.passwordValidator(passwordEdt) == false) {
                    passwordEdt.setError("Enter the password");
                } else if (user.getEmail() == null) {
                    Toast.makeText(LoginActivity.this, "register first", Toast.LENGTH_SHORT).show();
                } else {
                    if (user.getEmail().equals(email) && user.getPass().equals(pass)) {
                        Intent i = new Intent(LoginActivity.this, QuizActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Please Check the Email and Password", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }


    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}

