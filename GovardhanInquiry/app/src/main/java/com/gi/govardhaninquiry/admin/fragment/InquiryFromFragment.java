package com.gi.govardhaninquiry.admin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.activity.AdminInquiry;
import com.gi.govardhaninquiry.admin.adapter.AdminInquiryAdapter;
import com.gi.govardhaninquiry.admin.model.BatchInfo;
import com.gi.govardhaninquiry.admin.model.InquiryData;
import com.gi.govardhaninquiry.retro.RetroInterface;
import com.gi.govardhaninquiry.retro.RetrofitClient;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquiryFromFragment extends Fragment {

    Context context;
    RecyclerView ifrev;
    List<BatchInfo> modelArrayList;
    public InquiryFromFragment(Context context) {
        // Required empty public constructor
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inquiry_from, container, false);
        ifrev = view.findViewById(R.id.ifrev);

        RetrofitClient.getClient(context).create(RetroInterface.class).batchdetail()
                .enqueue(new Callback<ArrayList<BatchInfo>>() {
                    @Override
                    public void onResponse(Call<ArrayList<BatchInfo>> call, Response<ArrayList<BatchInfo>> response) {
                        modelArrayList = response.body();
                        Log.d("gilog",modelArrayList.toString());

//                        adminInquiryAdapter = new AdminInquiryAdapter(modelArrayList);
//                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
//                        recyclerView.setLayoutManager(linearLayoutManager);
//                        recyclerView.setHasFixedSize(true);
//                        recyclerView.setAdapter(adminInquiryAdapter);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<BatchInfo>> call, Throwable t) {
                        Toast.makeText(context," " + t.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.d("gisurat",t.toString());
                    }
                });


        return view;
    }
}
