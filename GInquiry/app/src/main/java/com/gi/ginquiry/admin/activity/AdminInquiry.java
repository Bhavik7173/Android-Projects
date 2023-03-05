package com.gi.ginquiry.admin.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.ginquiry.R;
import com.gi.ginquiry.admin.DAO.InquiryDB;
import com.gi.ginquiry.admin.DAO.SQLiteDBHelper;
import com.gi.ginquiry.admin.addNew.AddNewInquiry;
import com.gi.ginquiry.admin.adpater.AdminHRvInquiryAdapter;
import com.gi.ginquiry.admin.adpater.AdminInquiryAdapter;
import com.gi.ginquiry.admin.model.MyInquiryData;

import java.util.ArrayList;
import java.util.Arrays;

public class AdminInquiry extends AppCompatActivity {

    RecyclerView recyclerView, hrecyclerView;
    ArrayList<MyInquiryData> modelArrayList;
    InquiryDB inquiryDB;
    SQLiteDatabase sqLiteDatabase;
    SQLiteDBHelper sqLiteDBHelper;
    AdminInquiryAdapter adminInquiryAdapter;
    ImageButton editBtn, deleteBtn;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    AdminHRvInquiryAdapter adminHRvInquiryAdapter;
    LinearLayoutManager horizontalLayout;
    ArrayList<Integer> personImages = new ArrayList<>(Arrays.asList(R.drawable.c, R.drawable.cplus, R.drawable.java, R.drawable.python, R.drawable.react, R.drawable.dataanalysis, R.drawable.datastructure, R.drawable.flutter));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_inquiry);
        recyclerView = findViewById(R.id.irev);
        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Inquiry</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        editBtn = findViewById(R.id.iedit);
        deleteBtn = findViewById(R.id.idelete);
        modelArrayList = new ArrayList<>();
        inquiryDB = new InquiryDB(this);
        sqLiteDBHelper = new SQLiteDBHelper(this);
        modelArrayList = displayData();
        adminInquiryAdapter = new AdminInquiryAdapter(this, modelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdminInquiry.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adminInquiryAdapter);

        //horizontal recycleview
        hrecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adminHRvInquiryAdapter = new AdminHRvInquiryAdapter(this, personImages);
        horizontalLayout = new LinearLayoutManager(AdminInquiry.this, RecyclerView.HORIZONTAL, false);
        // Set adapter on recycler view
        hrecyclerView.setLayoutManager(horizontalLayout);
        hrecyclerView.setHasFixedSize(true);
        hrecyclerView.setAdapter(adminHRvInquiryAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.inquiry_drawer, menu);
        return true;
    }

    public ArrayList<MyInquiryData> displayData() {
        sqLiteDatabase = sqLiteDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from inquiry", null);
        ArrayList<MyInquiryData> modelArrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Log.d("mylog", "" + cursor.getString(1));
                Log.d("mylog", "" + cursor.getString(2));
                Log.d("mylog", "" + cursor.getString(3));
                Log.d("mylog", "" + cursor.getString(4));
                Log.d("mylog", "" + cursor.getString(5));
                Log.d("mylog", "" + cursor.getString(6));
                Log.d("mylog", "" + cursor.getString(7));
                Log.d("mylog", "" + cursor.getString(8));
                modelArrayList.add(new MyInquiryData(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return modelArrayList;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addnewinquiry:
                Intent i = new Intent(AdminInquiry.this, AddNewInquiry.class);
                startActivity(i);
                Toast.makeText(this, "Click on Add New Inquiry", Toast.LENGTH_SHORT).show();
                break;

            case R.id.applybatch:
                Toast.makeText(this, "Click on Apply", Toast.LENGTH_SHORT).show();
                break;
            case R.id.isearch:
                Toast.makeText(this, "Click on Search", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                super.onBackPressed();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}