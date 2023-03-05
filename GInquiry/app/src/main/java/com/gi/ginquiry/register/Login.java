package com.gi.ginquiry.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.ginquiry.R;
import com.gi.ginquiry.Retro.RetroInterface;
import com.gi.ginquiry.Retro.RetrofitClient;
import com.gi.ginquiry.admin.AdminHome;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText email, password;
    String uemail, upass, spinnerText, umode;
    Spinner mode;
    SharedPreferences pref;
    TextView loginBtn, resetBtn, forgotpassword, signup;
    List<User> user;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.usernameEdt);
        password = findViewById(R.id.pass);
        loginBtn = findViewById(R.id.loginBtn);
        resetBtn = findViewById(R.id.resetBtn);
        mode = findViewById(R.id.spinner);
        forgotpassword = findViewById(R.id.forgotpassword);
        signup = findViewById(R.id.cl);
        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setText("");
                password.setText("");
                mode.setSelection(0);
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(Login.this);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mylog", "forgot password");
                Intent i = new Intent(Login.this, ForgotPassword.class);
                startActivity(i);
                finishAffinity();

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Signup.class);
                startActivity(i);
                finishAffinity();
            }
        });
        pref = getSharedPreferences("user_details", MODE_PRIVATE);
//        if (pref.contains("status")) {
//            intent = new Intent(Login.this, AdminHome.class);
//            if (pref.contains("username") && pref.contains("password")) {
//                startActivity(intent);
//            }
//        }else if(pref.contains("status"))
//        {
//
//        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uemail = email.getText().toString();
                upass = password.getText().toString();
                umode = spinnerText;
                String msg = uemail + ":" + upass + ":" + umode;
                Log.d("gilog", msg);
//                Toast.makeText(Login.this, msg,Toast.LENGTH_SHORT).show();
                checkMode(uemail, upass, umode);
            }
        });
    }

    public void checkMode(String uemail, String upass, String umode) {

        RetrofitClient.getClient(Login.this).create(RetroInterface.class).login(uemail, upass, umode)
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (umode.equals("Admin")) {
                            user = response.body();
                            for (int i = 0; i < user.size(); i++) {
                                Log.d("gilog", user.get(i).getUpassword() + "");
                                Log.d("gilog", user.get(i).getUemail() + "");
                                Log.d("gilog", user.get(i).getUstatus() + "");
                                if (uemail.equals(user.get(i).getUemail()) && upass.equals(user.get(i).getUpassword()) && umode.equals(user.get(i).getUstatus())) {
                                    intent = new Intent(Login.this, AdminHome.class);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("username", user.get(i).getUname());
                                    editor.putString("email", uemail);
                                    editor.putString("password", upass);
                                    editor.putString("status", umode);
                                    editor.apply();
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                            }
                            //Toast.makeText(Signup.this, responseFromAPI, Toast.LENGTH_SHORT).show();

                        } else if (umode.equals("Consellor")) {
                            Log.d("gilog", "Consellor");
                            Toast.makeText(Login.this, "Conseller Login", Toast.LENGTH_SHORT).show();
                        } else if (umode.equals("Manager")) {
                            Log.d("gilog", "Manager");
                            Toast.makeText(Login.this, "Manager Login", Toast.LENGTH_SHORT).show();
                        } else if (umode.equals("Faculty")) {
                            Log.d("gilog", "Faculty Mode");
                            Toast.makeText(Login.this, "Faculty Login", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(Login.this, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("gisurat", t.toString());
                    }
                });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerText = adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(this, spinnerText+" ", Toast.LENGTH_LONG).show();
        Log.d("gilog", spinnerText + " ");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}