package com.gi.ginquiry.admin.activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.gi.ginquiry.R;
import com.gi.ginquiry.admin.adpater.AdminAdmissionAdapter;
import com.google.android.material.tabs.TabLayout;

public class AdminAdmission extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_admission);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);


        tabLayout.addTab(tabLayout.newTab().setText("New Admission"));
        tabLayout.addTab(tabLayout.newTab().setText("From Inquiry"));
        tabLayout.addTab(tabLayout.newTab().setText("Existing Student"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final AdminAdmissionAdapter adapter = new AdminAdmissionAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setBackgroundColor(Color.WHITE);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}