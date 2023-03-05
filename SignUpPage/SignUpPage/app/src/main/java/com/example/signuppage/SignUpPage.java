package com.example.signuppage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpPage extends AppCompatActivity {
    EditText uname,birth,mobno,Email,password;
    Button signin,reset;
    String name,dob,mob,email,pass;
    SharedPreferences pref;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        uname = findViewById(R.id.unameEdt);
        birth = findViewById(R.id.birthEdt);
        mobno = findViewById(R.id.mobnoEdt);
        Email = findViewById(R.id.EmailEdt);
        password = findViewById(R.id.passEdt);
        signin = findViewById(R.id.signinbtn);
        reset = findViewById(R.id.resetbtn);
        pref = getSharedPreferences("Register_details",MODE_PRIVATE);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname.setText("");
                birth.setText("");
                mobno.setText("");
                Email.setText("");
                password.setText("");
            }
        });
    }
    public void SignupButton(View v)
    {
        name = uname.getText().toString();
        dob=birth.getText().toString();
        mob=mobno.getText().toString();
        email=Email.getText().toString();
        pass = password.getText().toString();

        //System.out.println(name+" : "+pass);
        Log.d("mylog",name+" : "+pass);
        Toast.makeText(this, "Welcome to User.", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name",name);
        editor.putString("dob",dob);
        editor.putString("mobileno",mob);
        editor.putString("email",email);
        editor.putString("password",pass);
        editor.commit();
        Intent i = new Intent(SignUpPage.this,MainActivity.class);
        startActivity(i);
    }
}