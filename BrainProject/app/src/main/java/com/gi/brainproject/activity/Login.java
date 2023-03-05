package com.gi.brainproject.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.gi.brainproject.R;
import com.gi.brainproject.model.User;
import com.gi.brainproject.retro.RetroInterface;
import com.gi.brainproject.retro.RetrofitClient;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    EditText uname, password;
    TextView login, reset, register, forgotpassword;
    SharedPreferences pref, pref1;
    Intent intent, intent1;
    String pass, rname, rpass, email;
    List<User> myListData;

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname = findViewById(R.id.usernameEdt);
        password = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        reset = findViewById(R.id.resetBtn);
        register = findViewById(R.id.Register);
        forgotpassword = findViewById(R.id.forgotpassword);

        verifyStoragePermissions(Login.this);
        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(Login.this, BrainHome.class);
        if (pref.contains("username") && pref.contains("password")) {
            startActivity(intent);
        }
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname.setText("");
                password.setText("");
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, ChangePassword.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_^&+=])(?=\\S+$).{8,}$";
                Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

                int cnt = 0;
                email = uname.getText().toString();
                pass = password.getText().toString();
                if (uname.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "email empty", Toast.LENGTH_LONG).show();
                    cnt++;
                    uname.setError("Enter your email!");
                    uname.setVisibility(View.VISIBLE);
                }
                if (password.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "password empty", Toast.LENGTH_LONG).show();
                    cnt++;
                    password.setError("Enter your password!");
                    password.setVisibility(View.VISIBLE);
                } else {
                    Matcher m = pattern.matcher(password.getText().toString());

                    if (!m.matches()) {
                        cnt++;
                        password.setError("Password is invalid!");
                        password.setVisibility(View.VISIBLE);
                    }

                }
                if (cnt == 0) {
                    RetrofitClient.getClient(Login.this).create(RetroInterface.class).login(email, pass)
                            .enqueue(new Callback<List<User>>() {
                                @Override
                                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                                    myListData = response.body();
                                    Log.d("gilog", myListData + "");
                                    for (int i = 0; i < myListData.size(); i++) {
                                        Log.d("gilog", myListData.get(i).getId() + "");
                                        Log.d("gilog", myListData.get(i).getEmail() + "");
                                        SharedPreferences.Editor editor = pref.edit();
                                        editor.putString("username", myListData.get(i).getName());
                                        editor.putString("email", email);
                                        editor.putBoolean("status",true);
                                        editor.putString("password", pass);
                                        editor.putString("uid", myListData.get(i).getId());
                                        editor.commit();
                                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this, BrainHome.class);
                                        startActivity(intent);
                                    }
                                }


                                @Override
                                public void onFailure(Call<List<User>> call, Throwable t) {
                                    Toast.makeText(Login.this, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d("gisurat", t.toString());
                                }
                            });
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Signup.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}