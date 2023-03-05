package com.example.hibbub.StudentProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hibbub.PostJob.PostJobActivity;
import com.example.hibbub.R;

public class StudentProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
    }

    public void next(View view) {
        Intent i = new Intent(this, PostJobActivity.class);
        startActivity(i);
    }
}