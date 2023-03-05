package com.gi.brainproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gi.brainproject.R;

public class ValidIPAddress extends AppCompatActivity {

    TextView submit;
    EditText ipEdt;
    public static String ipaddress;
    SharedPreferences prf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valid_ipaddress);
        submit = findViewById(R.id.isubmit);
        ipEdt = findViewById(R.id.ipEdt);
        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ipaddress = ipEdt.getText().toString();
                if(prf.contains("status")) {
                    Intent intent = new Intent(ValidIPAddress.this, BrainHome.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(ValidIPAddress.this, Login.class);
                    startActivity(intent);
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