package com.gi.sqlitecrud;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.gi.sqlitecrud.database.MyDbHandler;

import com.gi.sqlitecrud.database.WaitListContract;
import com.gi.sqlitecrud.model.StudentModel;

public class HomeActivity extends AppCompatActivity {

    EditText studentNameEdit, yearEdit;
    TextView addBtn, resetBtn;
    private SQLiteDatabase sqLiteDatabase;

    private boolean btnStatus = false;
    private MyDbHandler db = null;
    StudentModel studentModel = null;
    LinearLayout linearLayout;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

         studentNameEdit = findViewById(R.id.et_student_name);
        yearEdit = findViewById(R.id.et_student_year);
        addBtn = findViewById(R.id.submitBtn);
        resetBtn = findViewById(R.id.resetbtn);
        linearLayout = findViewById(R.id.linearlayout);

        db = new MyDbHandler(HomeActivity.this);


        Intent intent = getIntent();
        String name="";
        int year=0;
        try {
            name = intent.getStringExtra("name");
            year = Integer.parseInt(intent.getStringExtra("year"));
            id = Integer.parseInt(intent.getStringExtra("id"));
            TextView nameTextView = findViewById(R.id.et_student_name);
            nameTextView.setText(name);

            TextView phoneTextView = findViewById(R.id.et_student_year);
            phoneTextView.setText(year+"");

        }
        catch(Exception e)
        {}
        if(name!=null)
        {
            addBtn.setText("udpate");
            btnStatus = true;
        }


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String studentName = studentNameEdit.getText().toString();
                String studentYear = yearEdit.getText().toString();
                if (studentNameEdit.getText().length() == 0 || yearEdit.getText().length() == 0) {
                    Toast.makeText(HomeActivity.this, getResources().getString(R.string.insert_error), Toast.LENGTH_SHORT).show();

                    return;
                }
                if (btnStatus == false) {
                    insertStudent(studentName, studentYear);
                } else {
                    updateStudent(id,studentName, studentYear);
                    btnStatus = false;
                    addBtn.setText("Add");

                }

            }
        });


        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentNameEdit.setText("");
                yearEdit.setText("");

            }
        });

//        Intent intent = getIntent();
//        String name = intent.getStringExtra("Sname");
//        String phone = intent.getStringExtra("Syear");




    }



    private void updateStudent(int id,String studentName, String studentYear) {
        int n = Integer.parseInt(studentYear);
        if (n != 0) {
            StudentModel model = new StudentModel(id,studentName,studentYear);
           int n1 = db.updateStudentModel(model);
            Log.d("gilog", "Student are update successfully." );
            Toast.makeText(HomeActivity.this, "updated successfully", Toast.LENGTH_SHORT).show();

            yearEdit.clearFocus();
            studentNameEdit.getText().clear();
            yearEdit.getText().clear();

            finish();
        } else {
            Toast.makeText(HomeActivity.this, getResources().getString(R.string.student_graduate_year), Toast.LENGTH_SHORT).show();
        }
    }

    private void insertStudent(String studentName, String studentYear) {
        int n = Integer.parseInt(studentYear);
        if (n != 0) {
            StudentModel s1 = new StudentModel();
            s1.setSname(studentName);
            s1.setSyear(studentYear);
            db.addStudentModel(s1);
            Log.d("gilog", "Student are added");
            Toast.makeText(HomeActivity.this, getResources().getString(R.string.student_added), Toast.LENGTH_SHORT).show();

            yearEdit.clearFocus();
            studentNameEdit.getText().clear();
            yearEdit.getText().clear();
        } else {
            Toast.makeText(HomeActivity.this, getResources().getString(R.string.student_graduate_year), Toast.LENGTH_SHORT).show();
        }
    }

    public boolean removeGuest(float id) {
        return sqLiteDatabase.delete(WaitListContract.WaitListEntry.TABLE_NAME,
                WaitListContract.WaitListEntry._ID + "=" + id, null) > 0;
    }


}
