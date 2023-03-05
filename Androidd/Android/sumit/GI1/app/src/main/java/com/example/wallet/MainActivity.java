package com.example.wallet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wallet.Response.LoginResponse;
import com.example.wallet.Service.Api;
import com.example.wallet.Service.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    EditText MN, password;
    SharedPreferences spf;
    int cnt;
    String e;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MN = findViewById(R.id.mobileno);
        password = findViewById(R.id.password);

        spf = getSharedPreferences("MobileNo", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = spf.edit();

    }

    public void login(View view) {
        String mobile = MN.getText().toString();
        String pass = password.getText().toString();
        if (MN.getText().toString().trim().isEmpty()) {
            MN.setError("Enter your Mobile No!");
        } else if (password.getText().toString().trim().isEmpty()) {
            password.setError("Enter your Password");
        } else {
            Retrofit instance = MyRetrofit.getRetrofit(getString(R.string.IP1));
            Call<LoginResponse> call;
            api = instance.create(Api.class);
            call = api.userLogin(mobile,pass);

            Log.d("login","btn clicked");
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    LoginResponse response1 = response.body();
                    Log.d("login2",response1.getStatus().toString());
                    Log.d("login2", String.valueOf(response.body()));
                    if(response1.getStatus().equals("success")) {
                        Log.d("login", "hiii");
                        success(response1.getStatus());
                        Intent i = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                        onBackPressed();
                    }
                    else
                    {

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                    Log.d("login1", t.toString());
                    Intent i = new Intent(MainActivity.this, SignupActivity.class);
                    startActivity(i);
                    finish();
                    onBackPressed();
                }
            });

        }

    }

    void fail(String toString) {
        Toast.makeText(this, "Procress is not successfully......", Toast.LENGTH_SHORT).show();
    }

    void success(String status) {

        SharedPreferences sharedPreferences = getSharedPreferences("MobileNo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mobileno", MN.getText().toString().trim());

        Log.d("successlog",MN.getText().toString());
        editor.putString("password", password.getText().toString().trim());
        editor.commit();

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("mobile", MN.getText().toString());
        editor.commit();
        startActivity(intent);
    }
        @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void sign(View view) {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }
}