package com.gi.ginquiry.admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.gi.ginquiry.MainActivity;
import com.gi.ginquiry.R;
import com.gi.ginquiry.admin.fragment.HomeFragment;
import com.gi.ginquiry.admin.fragment.SearchFragment;
import com.gi.ginquiry.admin.fragment.SettingFragment;

import com.gi.ginquiry.register.Login;
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
    SharedPreferences prf;
    Intent intent;

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

        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        intent = new Intent(AdminHome.this, Login.class);
        String name = prf.getString("username",null);
        String email = prf.getString("email",null);

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

