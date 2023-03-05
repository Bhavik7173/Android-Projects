package com.gi.ginquiry.admin.activity;

import android.content.Intent;
import android.database.Cursor;
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

import com.gi.ginquiry.R;
import com.gi.ginquiry.admin.DAO.FacultyDB;
import com.gi.ginquiry.admin.addNew.AddNewFaculty;
import com.gi.ginquiry.admin.adpater.AdminFacultyAdapter;
import com.gi.ginquiry.admin.model.MyFacultyData;

import java.util.ArrayList;

public class AdminFaculty extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<MyFacultyData> modelArrayList;
    FacultyDB facultyDB;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    AdminFacultyAdapter adminFacultyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_faculty);
        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Faculty</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        recyclerView = findViewById(R.id.frev);

        modelArrayList = new ArrayList<>();
        facultyDB = new FacultyDB(this);
        modelArrayList = facultyDB.getAllFaculties();
        adminFacultyAdapter = new AdminFacultyAdapter(this, modelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdminFaculty.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adminFacultyAdapter);
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