package com.example.hibbub.PostJob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hibbub.R;
import com.example.hibbub.Skill.SkillActivity;

public class PostJobActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);
    }

    public void next(View view) {
        Intent i =new Intent(this, SkillActivity.class);
        startActivity(i);
    }

    public void file(View view) {
    }
}