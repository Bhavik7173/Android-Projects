package com.gi.govardhaninquiry.admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.adapter.AdminAdmissionAdapter;
import com.gi.govardhaninquiry.admin.fragment.ExistingStudentFragment;
import com.gi.govardhaninquiry.admin.fragment.InquiryFromFragment;
import com.gi.govardhaninquiry.admin.fragment.NewAdmissionFragment;
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
        
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.d("gilog_Admission",tab.getPosition()+"");


            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("gilog_Admission1",tab.getPosition()+"");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("gilog_Admission2",tab.getPosition()+"");
            }
        });

    }
}