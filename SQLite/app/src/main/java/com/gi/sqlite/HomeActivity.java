package com.gi.sqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity{
    EditText studentNameEdit, yearEdit;
    TextView addBtn, resetBtn;
    RecyclerView studentListRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        studentNameEdit = findViewById(R.id.et_student_name);
        yearEdit = findViewById(R.id.et_student_year);
        addBtn = findViewById(R.id.submitBtn);
        resetBtn = findViewById(R.id.resetbtn);
        studentListRecyclerView = findViewById(R.id.rv_guest_list);
        studentListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> name = new ArrayList<>();
        List<String> year = new ArrayList<>();

        for(int i=1;i<=50;i++)
        {
            name.add("name" + i);
            year.add("year" + i);
        }


        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentNameEdit.setText("");
                yearEdit.setText("");

            }
        });
    }
}
