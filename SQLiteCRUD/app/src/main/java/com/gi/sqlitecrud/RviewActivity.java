package com.gi.sqlitecrud;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.sqlitecrud.database.MyDbHandler;
import com.gi.sqlitecrud.database.StudentListAdapter;
import com.gi.sqlitecrud.model.StudentModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RviewActivity extends AppCompatActivity {
    private StudentListAdapter studentListAdapter;
    RecyclerView studentListRecyclerView;
    private ArrayList<StudentModel> studentArrayList;
    SQLiteDatabase sqLiteDatabase;
    LinearLayout linearLayout;
    private MyDbHandler db = null;
    FloatingActionButton mAddFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rview);
        studentListRecyclerView = findViewById(R.id.rv_student_list);
        mAddFab = findViewById(R.id.add_fab);

        linearLayout = findViewById(R.id.linearlayout);

        db = new MyDbHandler(this);

        studentListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }

    public void setData() {

        studentArrayList = new ArrayList<>();
        List<StudentModel> allStudent = db.getAllStudentModels();
        for (StudentModel contact : allStudent) {

            Log.d("GiLog", "\nId: " + contact.getId() + "\n" +
                    "Name: " + contact.getSname() + "\n" +
                    "Year: " + contact.getSyear() + "\n");

            studentArrayList.add(contact);
        }
        studentListAdapter = new StudentListAdapter(this, studentArrayList);
        studentListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentListRecyclerView.setAdapter(studentListAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        studentListRecyclerView.setLayoutManager(layoutManager);

        mAddFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(RviewActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                });

    }





}