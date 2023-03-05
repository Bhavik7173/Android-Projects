package com.example.hibbub.ProfessionalProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hibbub.R;
import com.example.hibbub.StudentProfile.StudentProfileActivity;

public class ProfessionalProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_profile);
    }

    public void next(View view) {
        Intent i = new Intent(this, StudentProfileActivity.class);
        startActivity(i);
    }
}