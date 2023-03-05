package com.example.hibbub.Acheivement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hibbub.ProfessionalProfile.ProfessionalProfileActivity;
import com.example.hibbub.R;

public class AcheivementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acheivement);
    }

    public void next(View view) {
        Intent i = new Intent(this, ProfessionalProfileActivity.class);
        startActivity(i);
    }
}