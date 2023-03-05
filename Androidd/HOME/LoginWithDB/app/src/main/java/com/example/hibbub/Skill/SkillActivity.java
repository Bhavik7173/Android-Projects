package com.example.hibbub.Skill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hibbub.Home.HomeActivity;
import com.example.hibbub.R;

public class SkillActivity extends AppCompatActivity {

    EditText ed1,ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill);
        ed1 = findViewById(R.id.skill);
        ed2 = findViewById(R.id.tmskill);

        String a = ed1.getText().toString();
        String b = ed2.getText().toString();
    }

    public void submit(View view) {
        Intent i = new Intent(this, HomeActivity.class);
        startActivityForResult(i,1);
    }
}