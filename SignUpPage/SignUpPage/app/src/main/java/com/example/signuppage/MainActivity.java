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

public class MainActivity extends AppCompatActivity {

    EditText uname,password;
    Button login,reset;
    SharedPreferences pref,pref1;
    Intent intent;
    String name,pass,rname,rpass;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = findViewById(R.id.unameEdt);
        password = findViewById(R.id.passEdt);
        login=findViewById(R.id.loginbtn);
        reset = findViewById(R.id.resetbtn);

        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        intent = new Intent(MainActivity.this,HomeActivity.class);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname.setText("");
                password.setText("");
            }
        });

    }
    public void LoginButton(View v)
    {
        name = uname.getText().toString();
        pass = password.getText().toString();
        pref1 = getSharedPreferences("Register_details",MODE_PRIVATE);
        intent = new Intent(this,SignUpPage.class);
        rname= pref1.getString("name",null);
        rpass = pref1.getString("password",null);
        if(name==rname && pass==pass){
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("username",name);
            editor.putString("password",pass);
            editor.commit();
            Toast.makeText(getApplicationContext(), "Login Successful",Toast.LENGTH_SHORT).show();

            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Credentials are not valid",Toast.LENGTH_SHORT).show();
        }
        //System.out.println(name+" : "+pass);
        Log.d("mylog",name+" : "+pass);
        Toast.makeText(this, "Welcome to User.", Toast.LENGTH_SHORT).show();
    }

    public void RegisterButton(View v)
    {
        Intent i = new Intent(MainActivity.this,SignUpPage.class);
        startActivity(i);
    }
 }