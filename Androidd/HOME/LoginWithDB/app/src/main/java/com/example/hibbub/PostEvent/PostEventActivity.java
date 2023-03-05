package com.example.hibbub.PostEvent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hibbub.R;
import com.example.hibbub.Skill.SkillActivity;

public class PostEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event);
    }

    public void next(View view) {
        Intent i = new Intent(this, SkillActivity.class);
        startActivity(i);
    }
}