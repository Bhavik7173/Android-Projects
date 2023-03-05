package com.gi.govardhaninquiry.admin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.adapter.AdminCourseAdapter;
import com.gi.govardhaninquiry.admin.adapter.AdminInquiryAdapter;
import com.gi.govardhaninquiry.admin.model.CourseModel;
import com.gi.govardhaninquiry.admin.model.InquiryData;
import com.gi.govardhaninquiry.retro.RetroInterface;
import com.gi.govardhaninquiry.retro.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminCourse extends AppCompatActivity {

    RecyclerView recyclerView;
    List<CourseModel> modelArrayList;
    AdminCourseAdapter adminCourseAdapter;
    FloatingActionButton addNewCourse;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_course2);
        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Course</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        recyclerView = findViewById(R.id.crev);
        addNewCourse = findViewById(R.id.add_fab);

        addNewCourse.setOnClickListener(view -> {
            Intent i = new Intent(AdminCourse.this, AddNewCourse.class);
            startActivity(i);
        });

        modelArrayList = new ArrayList<>();
        RetrofitClient.getClient(AdminCourse.this).create(RetroInterface.class).courseDetail()
                .enqueue(new Callback<ArrayList<CourseModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CourseModel>> call, Response<ArrayList<CourseModel>> response) {
                        modelArrayList = response.body();
                        Log.d("gilog",modelArrayList.toString());

                        adminCourseAdapter = new AdminCourseAdapter(modelArrayList);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdminCourse.this, RecyclerView.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adminCourseAdapter);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<CourseModel>> call, Throwable t) {
                        Toast.makeText(AdminCourse.this," " + t.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.d("gisurat",t.toString());
                    }
                });




    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.course_drawer, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.addnewcourse:
//                Intent i = new Intent(AdminCourse.this, AddNewCourse.class);
//                startActivity(i);
//                Toast.makeText(this, "Click on Add New Course", Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.csearch:
//                Toast.makeText(this, "Click on Search", Toast.LENGTH_SHORT).show();
//                break;
//            case android.R.id.home:
//                super.onBackPressed();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}