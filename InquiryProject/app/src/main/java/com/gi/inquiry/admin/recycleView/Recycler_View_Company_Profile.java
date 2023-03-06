package com.gi.inquiry.admin.recycleView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.inquiry.R;
import com.gi.inquiry.admin.settings.AddCompanyProfile;

public class Recycler_View_Company_Profile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view__company__profile);
    }

    public void add(View view) {
        Log.d("WhyredOkay", "Hello");

        Intent intent = new Intent(this, AddCompanyProfile.class);
        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        intent.putExtra("email", sharedPreferences.getString("email", null));
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
