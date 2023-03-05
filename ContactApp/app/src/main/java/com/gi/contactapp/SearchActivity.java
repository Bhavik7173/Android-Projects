package com.gi.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    Intent intent;
    RetrofitClient retrofitClient;
    MyListAdapter adapter;
    RecyclerView recyclerView;
    List<Contact> myListData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        intent = getIntent();
        String str = intent.getStringExtra("message_key");

        retrofitClient = new RetrofitClient();
        RetrofitClient.getClient(SearchActivity.this).create(RetroInterface.class).searchContact(str)
                .enqueue(new Callback<List<Contact>>() {
                    @Override
                    public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                        myListData = response.body();
                        Log.d("gilog",response.body()+"");
                        Log.d("gilog",myListData.toString());
                        adapter = new MyListAdapter(myListData);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Contact>> call, Throwable t) {
                        Toast.makeText(SearchActivity.this," " + t.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.d("gisurat",t.toString());
                    }
                });

    }
}