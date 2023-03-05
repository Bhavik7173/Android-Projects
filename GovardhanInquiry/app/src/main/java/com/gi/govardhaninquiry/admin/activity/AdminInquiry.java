package com.gi.govardhaninquiry.admin.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.adapter.AdminHRvInquiryAdapter;
import com.gi.govardhaninquiry.admin.adapter.AdminInquiryAdapter;
import com.gi.govardhaninquiry.admin.model.CourseModel;
import com.gi.govardhaninquiry.admin.model.InquiryData;
import com.gi.govardhaninquiry.register.Login;
import com.gi.govardhaninquiry.retro.RetroInterface;
import com.gi.govardhaninquiry.retro.RetrofitClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminInquiry extends AppCompatActivity {

    RecyclerView recyclerView, hrecyclerView;
    List<InquiryData> modelArrayList;
    AdminInquiryAdapter adminInquiryAdapter;
    ArrayAdapter<InquiryData> arrayAdapter;
    ImageButton editBtn, deleteBtn;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    AdminHRvInquiryAdapter adminHRvInquiryAdapter;
    LinearLayoutManager horizontalLayout;
    List<CourseModel> personImages;
    RetrofitClient retrofitClient;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_inquiry);
        recyclerView = findViewById(R.id.irev);
        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Inquiry</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        hrecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        editBtn = findViewById(R.id.iedit);
        deleteBtn = findViewById(R.id.idelete);

        RetrofitClient.getClient(AdminInquiry.this).create(RetroInterface.class).inquiryDetail()
                .enqueue(new Callback<ArrayList<InquiryData>>() {
                    @Override
                    public void onResponse(Call<ArrayList<InquiryData>> call, Response<ArrayList<InquiryData>> response) {
                        modelArrayList = response.body();
                        Log.d("gilog",modelArrayList.toString());

                        adminInquiryAdapter = new AdminInquiryAdapter(modelArrayList);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdminInquiry.this, RecyclerView.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adminInquiryAdapter);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<InquiryData>> call, Throwable t) {
                        Toast.makeText(AdminInquiry.this," " + t.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.d("gisurat",t.toString());
                    }
                });



        RetrofitClient.getClient(AdminInquiry.this).create(RetroInterface.class).getAllCoursePhoto()
                .enqueue(new Callback<ArrayList<CourseModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CourseModel>> call, Response<ArrayList<CourseModel>> response) {
                        personImages = response.body();
//                        Log.d("gilog",modelArrayList.toString());

                        adminHRvInquiryAdapter = new AdminHRvInquiryAdapter(AdminInquiry.this,personImages);
                        horizontalLayout = new LinearLayoutManager(AdminInquiry.this, RecyclerView.HORIZONTAL, false);
                        // Set adapter on recycler view
                        hrecyclerView.setLayoutManager(horizontalLayout);
                        hrecyclerView.setHasFixedSize(true);
                        hrecyclerView.setAdapter(adminHRvInquiryAdapter);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<CourseModel>> call, Throwable t) {
                        Toast.makeText(AdminInquiry.this," " + t.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.d("gisurat",t.toString());
                    }
                });
        //horizontal recycleview



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.inquiry_drawer, menu);

        MenuItem searchViewItem = menu.findItem(R.id.isearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               if (modelArrayList.contains(query)) {
                    Log.d("gilog_isearch",modelArrayList+"");
                   adminInquiryAdapter = new AdminInquiryAdapter(modelArrayList);
                   LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdminInquiry.this, RecyclerView.VERTICAL, false);
                   recyclerView.setLayoutManager(linearLayoutManager);
                   recyclerView.setHasFixedSize(true);
                   recyclerView.setAdapter(adminInquiryAdapter);
                } else {
                    Toast.makeText(AdminInquiry.this, "Not found", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        @Override
            public boolean onQueryTextChange(String newText) {

            adminInquiryAdapter = new AdminInquiryAdapter(modelArrayList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdminInquiry.this, RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adminInquiryAdapter);
                Log.d("gilog_isearch1",modelArrayList+"");
                return false;
            }
        });
        return true;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}