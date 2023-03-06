package com.gi.inquiry.admin.adpater;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gi.inquiry.admin.fragment.ExistingStudentFragment;
import com.gi.inquiry.admin.fragment.InquiryFromFragment;
import com.gi.inquiry.admin.fragment.NewAdmissionFragment;


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
                NewAdmissionFragment newAdmission = new NewAdmissionFragment();
                return newAdmission;
            case 1:
                InquiryFromFragment inquiryFrom = new InquiryFromFragment();
                return inquiryFrom;
            case 2:
                ExistingStudentFragment existingStudent = new ExistingStudentFragment();
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
