package com.example.collegeconnect;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void logout(View view) {
        SharedPreferences spf = getSharedPreferences("login_file", MODE_PRIVATE);
        SharedPreferences.Editor ed = spf.edit();
        ed.putString("status", "logout");
        ed.commit();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        //Toast.makeText(this, "loggout", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void myacccount(View view) {
        Intent i = new Intent(this, PersonalProfile.class);
        startActivityForResult(i,1);

        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            if(resultCode==100)
                Log.d("gilog", "rtn from second activity : backpressed");
            else if(resultCode==200)
                Log.d("gilog", "rtn from second activity : finish button");
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

}