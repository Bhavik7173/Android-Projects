package com.gi.projectgroup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText uname, password;
    Button login, reset;
    SharedPreferences pref, pref1;
    Intent intent, intent1;
    String pass, rname, rpass,email;
    List<User> myListData;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = findViewById(R.id.unameEdt);
        password = findViewById(R.id.passEdt);
        login = findViewById(R.id.loginbtn);
        reset = findViewById(R.id.resetbtn);

        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(MainActivity.this, HomeActivity.class);
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
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = uname.getText().toString();
                pass = password.getText().toString();

                RetrofitClient.getClient(MainActivity.this).create(RetroInterface.class).login(email)
                        .enqueue(new Callback<List<User>>() {
                            @Override
                            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                                myListData = response.body();
                                Log.d("gilog", myListData + "");
                                for (int i = 0; i < myListData.size(); i++) {
                                    Log.d("gilog", myListData.get(i).getPassword() + "");
                                    Log.d("gilog", myListData.get(i).getEmail() + "");
                                    if (email.equals(myListData.get(i).getEmail()) && pass.equals(myListData.get(i).getPassword())) {
                                        SharedPreferences.Editor editor = pref.edit();
                                        editor.putString("username", myListData.get(i).getName());
                                        editor.putString("email", email);
                                        editor.putString("password", pass);
                                        editor.commit();
                                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                                        startActivity(intent);
                                    }
                                }

//
                            }

                            @Override
                            public void onFailure(Call<List<User>> call, Throwable t) {
                                Toast.makeText(MainActivity.this, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("gisurat", t.toString());
                            }
                        });
            }
        });

    }

    public void RegisterButton(View v) {
        Intent i = new Intent(MainActivity.this, SignUpPage.class);
        startActivity(i);
    }
}