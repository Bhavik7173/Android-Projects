package com.gi.inquiry.admin.addNew;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.inquiry.R;
import com.gi.inquiry.admin.DAO.CourseDB;
import com.gi.inquiry.admin.DAO.SQLiteDBHelper;
import com.gi.inquiry.admin.activity.AdminCourse;
import com.google.android.material.textfield.TextInputEditText;

public class AddNewCourse extends AppCompatActivity {

    TextInputEditText cname, cfees, cduration, cprerequest, csyallbus, cprogram;
    Button submit;
    public CourseDB dbHandler;
    Intent intent;
    SQLiteDatabase sqLiteDatabase;
    SQLiteDBHelper sqLiteDBHelper;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);
        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Add New Course</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        sqLiteDBHelper = new SQLiteDBHelper(this);
        cname = findViewById(R.id.cnameEdt);
        cfees = findViewById(R.id.cfeesEdt);
        cduration = findViewById(R.id.cdurationEdt);
        cprerequest = findViewById(R.id.cprerequestEdt);
        csyallbus = findViewById(R.id.csyallbusEdt);
        cprogram = findViewById(R.id.cprogramEdt);
        submit = findViewById(R.id.csubmit_button);
        dbHandler = new CourseDB(AddNewCourse.this);
        cprogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 7);

            }
        });
        csyallbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 8);

            }
        });
        editData();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = cname.getText().toString();
                String fees = cfees.getText().toString();
                String duration = cduration.getText().toString();
                String prerequest = cprerequest.getText().toString();
                String syallbus = csyallbus.getText().toString();
                String program = cprogram.getText().toString();

                long recid = dbHandler.addNewCourse(name, fees, duration, prerequest, syallbus, program);
                if (recid != -1) {
                    Toast.makeText(AddNewCourse.this, "successfully insert", Toast.LENGTH_SHORT).show();
                    cleardata();
                    startActivity(new Intent(AddNewCourse.this, AdminCourse.class));
                    finish();
                } else {
                    Toast.makeText(AddNewCourse.this, "something wrong try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 8:

                if (resultCode == RESULT_OK) {

                    String PathHolder = data.getData().getPath();
                    Log.d("mylog", PathHolder);
                    csyallbus.setText(PathHolder);
                    Toast.makeText(AddNewCourse.this, PathHolder, Toast.LENGTH_LONG).show();

                }
                break;
            case 7:

                if (resultCode == RESULT_OK) {

                    String PathHolder = data.getData().getPath();
                    cprogram.setText(PathHolder);
                    Toast.makeText(AddNewCourse.this, PathHolder, Toast.LENGTH_LONG).show();

                }
                break;

        }
    }

    private void editData() {
        if (getIntent().getBundleExtra("userdata") != null) {
            Bundle bundle = getIntent().getBundleExtra("userdata");
            id = bundle.getInt("course_id");
            cname.setText(bundle.getString("course_name"));
            cfees.setText(bundle.getString("course_fees"));
            cduration.setText(bundle.getString("course_duration"));
            cprerequest.setText(bundle.getString("course_prerequest"));
            csyallbus.setText(bundle.getString("course_syllabus"));
            cprogram.setText(bundle.getString("course_program"));
            submit.setText("Edit");
            //submit.setVisibility(View.GONE);
        }
    }

    private void cleardata() {
        cname.setText("");
        cfees.setText("");
        cduration.setText("");
        cprerequest.setText("");
        csyallbus.setText("");
        cprogram.setText("");

    }
}