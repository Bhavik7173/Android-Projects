package com.example.collegeconnect;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText ed1, ed2;
    TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed1 = findViewById(R.id.email1);
        ed2 = findViewById(R.id.pwd);
        tx = findViewById(R.id.msg_view);
    }

    public void signup(View view) {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

    public void forgotpwd(View view) {
        Intent i = new Intent(this, ForgotPasswordActivity.class);
        startActivity(i);
    }

    public void login(View view) {
        String a = ed1.getText().toString();
        String b = ed2.getText().toString();

        if (a.length() == 0) {
            ed1.setError("Email is not Entered");
            ed1.requestFocus(1);
        } else if (b.length() == 0) {
            ed2.setError("Password is not Entered");
            ed2.requestFocus(2);
        } else if (a.equals("admin1") && b.equals("root")) {
            SharedPreferences spf;
            spf = getSharedPreferences("login_file", MODE_PRIVATE);

            SharedPreferences.Editor ed = spf.edit();
            ed.putString("status", "login");
            ed.commit();

            //Toast.makeText(this, "welcome...", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
            finish();
        } else {
            tx.setVisibility(TextView.VISIBLE);
            //Toast.makeText(this, "Wrong Password and Email", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);

        b.setMessage("Do you want to Exit?");
        //b.setIcon(R.drawable.alert_icon);
        b.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        b.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        b.setCancelable(false);
        // b.setNeutralButton("cancel",null);
        AlertDialog alt = b.create();
        alt.show();
    }

    public void reset(View view) {
        ed1.setText("");
        ed2.setText("");
        ed1.requestFocus();
    }
}