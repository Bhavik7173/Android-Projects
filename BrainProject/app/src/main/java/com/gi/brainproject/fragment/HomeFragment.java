package com.gi.brainproject.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.brainproject.R;
import com.gi.brainproject.model.PatientData;
import com.gi.brainproject.retro.RetroInterface;
import com.gi.brainproject.retro.RetrofitClient;
import com.gi.brainproject.model.PatientResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    FrameLayout frameLayout;
    SharedPreferences prf;
    Context context;
    RecyclerView recyclerView;
    ArrayList<PatientData> modelArrayList;
    ArrayList<PatientResponse> modelArrayResList;
    String uid;
    ReportDataAdapter reportDataAdapter;
    public HomeFragment(Context context) {
        this.context = context;
        prf = context.getSharedPreferences("user_details", MODE_PRIVATE);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        frameLayout = view.findViewById(R.id.flFragment);
        recyclerView = view.findViewById(R.id.crev);
        modelArrayList = new ArrayList<>();
        uid = prf.getString("uid",null);
        RetrofitClient.getClient(context).create(RetroInterface.class).getPatientDetail(uid)
                .enqueue(new Callback<ArrayList<PatientResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<PatientResponse>> call, Response<ArrayList<PatientResponse>> response) {
                        modelArrayResList = response.body();

                        reportDataAdapter = new ReportDataAdapter(context, modelArrayResList);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(reportDataAdapter);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<PatientResponse>> call, Throwable t) {
                       // Toast.makeText(context," " + t.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.d("gilog fail" ,t.toString());
                    }
                });

        return view;
    }

}