package com.example.collegeconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ed1 = findViewById(R.id.name1);
        ed2 = findViewById(R.id.email1);
        ed3 = findViewById(R.id.pwd);
        ed4 = findViewById(R.id.cpwd);
    }

    public void submit(View view) {
        String a = ed1.getText().toString();
        String b = ed2.getText().toString();
        String c = ed3.getText().toString();
        String d = ed4.getText().toString();
        if(a.length()==0)
        {
            ed1.setError("Name is not Entered");
            ed1.requestFocus(1);
        }
        else if(b.length()==0)
        {
            ed2.setError("Email is not Entered");
            ed2.requestFocus(2);
        }
        else if(c.length()==0)
        {
            ed3.setError("Password is not Entered");
            ed3.requestFocus(3);
        }
        else if(d.length()==0)
        {
            ed4.setError("Confirm Password is not Entered");
            ed4.requestFocus(4);
        }
        else if(c.equals(d))
        {
            SharedPreferences spf;
            spf = getSharedPreferences("Signup_file", MODE_PRIVATE);

            SharedPreferences.Editor ed = spf.edit();
            ed.putString("status", "signup");
            ed.commit();
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this, "Password is not matched", Toast.LENGTH_SHORT).show();
        }
    }

    public void reset(View view) {
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
        ed1.requestFocus();
    }
}