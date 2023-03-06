package com.gi.inquiry.admin.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.inquiry.R;
import com.gi.inquiry.admin.DAO.CourseDB;
import com.gi.inquiry.admin.DAO.SQLiteDBHelper;
import com.gi.inquiry.admin.addNew.AddNewCourse;
import com.gi.inquiry.admin.adpater.AdminCourseAdapter;
import com.gi.inquiry.admin.model.MyCourseData;

import java.util.ArrayList;

public class AdminCourse extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<MyCourseData> modelArrayList;
    SQLiteDBHelper sqLiteDBHelper;
    CourseDB courseDB;
    SQLiteDatabase sqLiteDatabase;
    AdminCourseAdapter adminCourseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_course);
        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Course</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        recyclerView = findViewById(R.id.crev);

        modelArrayList = new ArrayList<>();
        courseDB = new CourseDB(this);
        sqLiteDBHelper = new SQLiteDBHelper(this);
        modelArrayList = courseDB.displayData();
        adminCourseAdapter = new AdminCourseAdapter(this, modelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdminCourse.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adminCourseAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.course_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addnewcourse:
                Intent i = new Intent(AdminCourse.this, AddNewCourse.class);
                startActivity(i);
                Toast.makeText(this, "Click on Add New Course", Toast.LENGTH_SHORT).show();
                break;

            case R.id.csearch:
                Toast.makeText(this, "Click on Search", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}