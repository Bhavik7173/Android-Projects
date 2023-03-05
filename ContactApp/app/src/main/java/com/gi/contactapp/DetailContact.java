package com.gi.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailContact extends AppCompatActivity {

    Intent intent;
    RetrofitClient retrofitClient;
    MyListAdapter adapter;
    RecyclerView recyclerView;
    List<Contact> myListData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        retrofitClient = new RetrofitClient();
        RetrofitClient.getClient(DetailContact.this).create(RetroInterface.class).disp_contact()
                .enqueue(new Callback<ArrayList<Contact>>() {
            @Override
            public void onResponse(Call<ArrayList<Contact>> call, Response<ArrayList<Contact>> response) {
                myListData = response.body();
                Log.d("gilog",myListData.toString());

                adapter = new MyListAdapter(myListData);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(DetailContact.this));
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Contact>> call, Throwable t) {
                Toast.makeText(DetailContact.this," " + t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.d("gisurat",t.toString());
            }
        });


    }
}