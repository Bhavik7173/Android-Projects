package com.gi.govardhaninquiry.admin.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.activity.AdminAdmission;
import com.gi.govardhaninquiry.admin.activity.AdminCourse;
import com.gi.govardhaninquiry.admin.activity.AdminFaculty;
import com.gi.govardhaninquiry.admin.activity.AdminInquiry;

public class HomeFragment extends Fragment {
    FrameLayout frameLayout;
    CardView inquiry_admin, course_admin, faculty_admin, admission_admin;
    Context context;

    public HomeFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setid(view);
        inquiry_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AdminInquiry.class);
                startActivity(i);
            }
        });
        course_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AdminCourse.class);
                startActivity(i);
            }
        });
        faculty_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AdminFaculty.class);
                startActivity(i);
            }
        });
        admission_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AdminAdmission.class);
                startActivity(i);
            }
        });
        return view;
    }

    public void setid(View view) {
        frameLayout = view.findViewById(R.id.flFragment);
        inquiry_admin = view.findViewById(R.id.inquiry_cardview);
        course_admin = view.findViewById(R.id.course_cardview);
        admission_admin = view.findViewById(R.id.admission_cardview);
        faculty_admin = view.findViewById(R.id.faculty_cardview);
    }
}