package com.gi.brainproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.gi.brainproject.R;
import com.gi.brainproject.fragment.HomeFragment;
import com.gi.brainproject.fragment.PatientFragment;
import com.gi.brainproject.fragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class BrainHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener  {

    FrameLayout frameLayout;
    SharedPreferences prf;
    Intent intent;
    String name,email,uid;
    HomeFragment homeFragment;
    SettingFragment settingFragment;
    PatientFragment patientFragment;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_home);
        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Home</font>"));
        frameLayout = findViewById(R.id.flFragment);

        Toast.makeText(this, "Welcome to User.", Toast.LENGTH_SHORT).show();
        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        name = prf.getString("username",null);
        email = prf.getString("email",null);
        uid = prf.getString("uid",null);

        settingFragment = new SettingFragment(this,name,email);
        homeFragment = new HomeFragment(this);
        patientFragment = new PatientFragment(this,uid);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                return true;

            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, settingFragment).commit();
                return true;

            case R.id.addpatient:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, patientFragment).commit();
                return true;
        }
        return false;
    }
}