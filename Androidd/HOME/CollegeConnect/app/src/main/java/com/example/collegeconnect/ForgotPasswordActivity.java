package com.example.collegeconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ed1 = findViewById(R.id.email1);
        ed2 = findViewById(R.id.pwd);
        ed3 = findViewById(R.id.cpwd);
    }

    public void submit(View view) {
        String a = ed1.getText().toString();
        String b = ed2.getText().toString();
        String c = ed3.getText().toString();
        if(a.length()==0)
        {
            ed1.setError("Email is not Entered");
            ed1.requestFocus(1);
        }
        else if(b.length()==0)
        {
            ed2.setError("Password is not Entered");
            ed2.requestFocus(2);
        }
        else if(c.length()==0)
        {
            ed3.setError("Confirm Password is not Entered");
            ed3.requestFocus(3);
        }
        else if(b.equals(c))
        {
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this, "Password is not match.", Toast.LENGTH_SHORT).show();
        }
    }

    public void clear(View view) {
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
    }
}