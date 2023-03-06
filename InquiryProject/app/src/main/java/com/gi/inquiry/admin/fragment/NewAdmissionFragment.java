package com.gi.inquiry.admin.fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.inquiry.R;
import com.gi.inquiry.admin.DAO.NewAdmissionDB;
import com.gi.inquiry.admin.adpater.NewAdmissionAdapter;
import com.gi.inquiry.admin.model.MyNewAdmission;

import java.util.ArrayList;

public class NewAdmissionFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<MyNewAdmission> modelArrayList;
    NewAdmissionDB newAdmissionDB;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    NewAdmissionAdapter adminNewAdmission;
    Context context;

    public NewAdmissionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_admission, container, false);
        recyclerView = view.findViewById(R.id.fnadrev);
        adminNewAdmission = new NewAdmissionAdapter(context, modelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adminNewAdmission);
        return view;
    }
}
