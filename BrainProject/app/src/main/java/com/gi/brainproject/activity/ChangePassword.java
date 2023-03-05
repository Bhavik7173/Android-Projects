package com.gi.brainproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.brainproject.R;
import com.gi.brainproject.model.FileResponse;
import com.gi.brainproject.retro.RetroInterface;
import com.gi.brainproject.retro.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {
    EditText email;
    TextView submit;
    int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);

        email = findViewById(R.id.et_email);
        cnt = 0;

        submit = findViewById(R.id.bt_forget);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uemail = email.getText().toString();

                if (email.getText().toString().trim().isEmpty()) {
                    cnt++;
                    email.setError("Fill your name!");
                }
                if (cnt == 0) {
                    RetrofitClient.getClient(ChangePassword.this).create(RetroInterface.class).checkEmail(uemail).enqueue(new Callback<FileResponse>() {

                        @Override
                        public void onResponse(Call<FileResponse> call, Response<FileResponse> response) {
                            Log.d("response_Change", response.body().toString());
                            if (response.body().getSuccess() == "true") {
                                Intent intent = new Intent(ChangePassword.this, ForgotPassword.class);
                                intent.putExtra("email", uemail);
                                startActivity(intent);
                            } else {
                                Log.d("response_change_failur", response.body().toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<FileResponse> call, Throwable t) {
                            Log.d("response_fail", t.toString());
                        }
                    });
                }
            }
        });
    }
}
