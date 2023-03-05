package com.gi.govardhaninquiry.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.fragment.HomeFragment;
import com.gi.govardhaninquiry.admin.fragment.SearchFragment;
import com.gi.govardhaninquiry.admin.fragment.SettingFragment;
import com.gi.govardhaninquiry.register.Login;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class AdminHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    FrameLayout frameLayout;
    CardView inquiry_admin, course_admin, faculty_admin;
    private Object Menu;
    SearchFragment searchFragment;
    HomeFragment homeFragment;
    SettingFragment settingFragment;
    int manageid = 0;
    BottomNavigationView bottomNavigationView;
    SharedPreferences sharedPre;
    Intent intent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Home</font>"));
        frameLayout = findViewById(R.id.flFragment);
        inquiry_admin = findViewById(R.id.inquiry_cardview);
        course_admin = findViewById(R.id.course_cardview);
        faculty_admin = findViewById(R.id.faculty_cardview);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        sharedPre = getSharedPreferences("login", Context.MODE_PRIVATE);
        intent = new Intent(AdminHome.this, Login.class);
        String name = sharedPre.getString("username",null);
        String email = sharedPre.getString("email",null);

        settingFragment = new SettingFragment(this,name,email);
        homeFragment = new HomeFragment(this);
        searchFragment = new SearchFragment(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.search:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment).commit();
                return true;

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                return true;

            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, settingFragment).commit();
                return true;
        }
        return false;
    }
}

