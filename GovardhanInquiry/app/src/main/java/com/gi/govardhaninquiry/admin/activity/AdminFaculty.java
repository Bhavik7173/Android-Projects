package com.gi.govardhaninquiry.admin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.adapter.AdminFacultyAdapter;
import com.gi.govardhaninquiry.admin.adapter.AdminInquiryAdapter;
import com.gi.govardhaninquiry.admin.model.InquiryData;
import com.gi.govardhaninquiry.admin.model.MyFacultyData;
import com.gi.govardhaninquiry.retro.RetroInterface;
import com.gi.govardhaninquiry.retro.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminFaculty extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<MyFacultyData> modelArrayList;
    AdminFacultyAdapter adminFacultyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_faculty);
        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Faculty</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        recyclerView = findViewById(R.id.frev);

        RetrofitClient.getClient(AdminFaculty.this).create(RetroInterface.class).facultyDetail()
                .enqueue(new Callback<ArrayList<MyFacultyData>>() {
                    @Override
                    public void onResponse(Call<ArrayList<MyFacultyData>> call, Response<ArrayList<MyFacultyData>> response) {
                        modelArrayList = response.body();
                        Log.d("gilog",modelArrayList.toString());

                        adminFacultyAdapter = new AdminFacultyAdapter(modelArrayList);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdminFaculty.this, RecyclerView.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adminFacultyAdapter);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<MyFacultyData>> call, Throwable t) {
                        Toast.makeText(AdminFaculty.this," " + t.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.d("gisurat",t.toString());
                    }
                });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.faculty_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addnewfaculty:
                Intent i = new Intent(AdminFaculty.this, AddNewFaculty.class);
                startActivity(i);
                Toast.makeText(this, "Click on Add New Course", Toast.LENGTH_SHORT).show();
                break;

            case R.id.fsearch:
                Toast.makeText(this, "Click on Search", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}