package com.gi.waitinglist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    EditText studentNameEdit, yearEdit;
    TextView addBtn, resetBtn;
    private GuestListAdapter guestListAdapter;
    SQLiteDatabase sqLiteDatabase;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        studentNameEdit = findViewById(R.id.et_student_name);
        yearEdit = findViewById(R.id.et_student_year);
        addBtn = findViewById(R.id.submitBtn);
        resetBtn = findViewById(R.id.resetbtn);


        WaitListDbHelper waitListDbHelper = new WaitListDbHelper(this);
        sqLiteDatabase = waitListDbHelper.getWritableDatabase();
        Cursor cursor = getAllGuest();

        guestListAdapter = new GuestListAdapter(this, cursor);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (studentNameEdit.getText().length() == 0 || yearEdit.getText().length() == 0)
                    return;

                String guestName = studentNameEdit.getText().toString();
                int partySize = Integer.parseInt(yearEdit.getText().toString());

                if (partySize != 0) {
                    addNewGuest(guestName, partySize);
                    guestListAdapter.swapCursor(getAllGuest());

                    Toast.makeText(HomeActivity.this, getResources().getString(R.string.toast_guest_added), Toast.LENGTH_SHORT).show();

                    yearEdit.clearFocus();
                    studentNameEdit.getText().clear();
                    yearEdit.getText().clear();
                } else {
                    Toast.makeText(HomeActivity.this, getResources().getString(R.string.toast_error_party_size), Toast.LENGTH_SHORT).show();
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


    }


    public void addToWaitList(View view) {
        if (studentNameEdit.getText().length() == 0 || yearEdit.getText().length() == 0)
            return;

        String guestName = studentNameEdit.getText().toString();
        int partySize = Integer.parseInt(yearEdit.getText().toString());

        if (partySize != 0) {
            addNewGuest(guestName, partySize);
            guestListAdapter.swapCursor(getAllGuest());

            Toast.makeText(this, getResources().getString(R.string.toast_guest_added), Toast.LENGTH_SHORT).show();

            yearEdit.clearFocus();
            studentNameEdit.getText().clear();
            yearEdit.getText().clear();
        } else {
            Toast.makeText(this, getResources().getString(R.string.toast_error_party_size), Toast.LENGTH_SHORT).show();
        }
    }

    private void addNewGuest(String guestName, int partySize) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentModel.WaitListEntry.GUEST_NAME, guestName);
        contentValues.put(StudentModel.WaitListEntry.PARTY_SIZE, partySize);
        sqLiteDatabase.insert(StudentModel.WaitListEntry.TABLE_NAME, null, contentValues);
    }

    private Cursor getAllGuest() {
        return sqLiteDatabase.query(
                StudentModel.WaitListEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                StudentModel.WaitListEntry.PARTY_SIZE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_guest_name: {
                models.get(position).setName(studentNameEdit.getText().toString());
                adapter.notifyDataSetChanged();
                addBtn.setVisibility(View.GONE);
            }
            break;
        }
    }

}