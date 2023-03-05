package com.example.walletapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Response;
import com.google.android.material.textfield.TextInputEditText;

import javax.security.auth.callback.Callback;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    TextInputEditText ed1, ed2;
    Button btn1;
    String e;
    Call call;
    int cnt;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed1 = findViewById(R.id.et_username);
        ed2 = findViewById(R.id.et_password);
        btn1 = findViewById(R.id.btn_login);

        SharedPreferences sharedPreferences = getSharedPreferences("Username", MODE_PRIVATE);
        if (sharedPreferences.contains("Username") && sharedPreferences.contains("password")) {
            String username = sharedPreferences.getString("Username", "");
            String password = sharedPreferences.getString("password", "");
            Toast.makeText(this, username + password, Toast.LENGTH_LONG).show();
            if (password.equals("Bhavik@123") && username.equals("Bhavik_3797")) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("Username", username);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        } else {
            //Toast.makeText(this,"not contain",Toast.LENGTH_LONG).show();
        }
    }

    public void login(View view) {
        if (ed1.getText().toString().trim().isEmpty()) {
            ed1.setError("Enter your Username!");
        } else if (ed2.getText().toString().isEmpty()) {
            ed2.setError("Enter your Password!");
        } else {
            if (!e.equals(ed1.getText().toString())) {
                cnt = 0;
            }
            e = ed1.getText().toString();
            Retrofit instance = MyRetrofit.getRetrofit(getString(R.string.IP));
            retrofit2.Call<LoginResponse> call;
            api = instance.create(Api.class);
            call = Api.login(ed1.getText().toString().trim(), ed2.getText().toString());

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    cnt++;
                    LoginResponse response1 = response.body();
                    Log.d("login", response1.getStatus());
                    if (response1.getStatus().equals("A")) {
                        success(response1.getStatus());
                    } else {
                        fail(response1.getStatus().toString());
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.d("Whyred", t.toString());
                }
            });
        }
    }

    void success(String status) {

        SharedPreferences sharedPreferences = getSharedPreferences("Username", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", ed1.getText().toString().trim());
        editor.putString("password", ed2.getText().toString().trim());
        editor.commit();

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("email", ed1.getText().toString());
        //intent.putExtra("status","Alumni");
        //editor.putString("status","Alumni");
        //editor.commit();
        startActivity(intent);
    }

    void fail(String status) {

        SharedPreferences sharedPreferences = getSharedPreferences("Username", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", ed1.getText().toString().trim());
        editor.putString("password", ed2.getText().toString().trim());
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("email", ed1.getText().toString());
        //intent.putExtra("status","Alumni");
        //editor.putString("status","Alumni");
        //editor.commit();
        startActivity(intent);
    }
}