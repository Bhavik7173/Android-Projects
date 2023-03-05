package com.gi.govardhaninquiry.admin.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gi.govardhaninquiry.admin.fragment.ExistingStudentFragment;
import com.gi.govardhaninquiry.admin.fragment.InquiryFromFragment;
import com.gi.govardhaninquiry.admin.fragment.NewAdmissionFragment;


public class AdminAdmissionAdapter extends FragmentPagerAdapter {
    public Context myContext;
    int totalTabs;

    public AdminAdmissionAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                NewAdmissionFragment newAdmission = new NewAdmissionFragment(myContext);
                return newAdmission;
            case 1:
                InquiryFromFragment inquiryFrom = new InquiryFromFragment(myContext);
                return inquiryFrom;
            case 2:
                ExistingStudentFragment existingStudent = new ExistingStudentFragment(myContext);
                return existingStudent;
            default:
                return null;
        }
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }


}
