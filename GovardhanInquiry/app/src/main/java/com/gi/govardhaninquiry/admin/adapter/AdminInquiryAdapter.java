package com.gi.govardhaninquiry.admin.adapter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.activity.AddNewCourse;
import com.gi.govardhaninquiry.admin.activity.AddNewInquiry;
import com.gi.govardhaninquiry.admin.activity.AdminInquiry;
import com.gi.govardhaninquiry.admin.model.InquiryData;
import com.gi.govardhaninquiry.retro.RetroInterface;
import com.gi.govardhaninquiry.retro.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminInquiryAdapter extends RecyclerView.Adapter<AdminInquiryAdapter.ViewHolder> {

    static Context context;
    ArrayList<InquiryData> inquiries = new ArrayList<>();


    public AdminInquiryAdapter(List<InquiryData> mData) {
        inquiries = (ArrayList<InquiryData>) mData;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_admin_inquiry, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        InquiryData model = inquiries.get(position);
        holder.name.setText(model.getName());
        holder.mobile.setText(model.getMobile());
        holder.course.setText(model.getCourse());
        holder.date.setText(model.getDate());
        holder.ll_design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + model.getName(), Toast.LENGTH_LONG).show();
            }
        });
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, AddNewInquiry.class);
                intent.putExtra("iname",model.getName());
                intent.putExtra("imobile",model.getMobile());
                intent.putExtra("icourse",model.getCourse());
                intent.putExtra("idate",model.getDate());
                intent.putExtra("icollege",model.getCollege());
                intent.putExtra("iemail",model.getEmail());
                intent.putExtra("inote",model.getNote());
                intent.putExtra("ipreffered",model.getPreferred());
                context.startActivity(intent);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RetrofitClient.getClient(context).create(RetroInterface.class).deleteInquiry(model.getName(),model.getMobile())
                        .enqueue(new Callback<ArrayList<InquiryData>>() {
                            @Override
                            public void onResponse(Call<ArrayList<InquiryData>> call, Response<ArrayList<InquiryData>> response) {
                                Intent intent = new Intent(context,AdminInquiry.class);
                                context.startActivity(intent);

                            }

                            @Override
                            public void onFailure(Call<ArrayList<InquiryData>> call, Throwable t) {
                                Toast.makeText(context," " + t.getMessage(),Toast.LENGTH_SHORT).show();
                                Log.d("gisurat",t.toString());
                            }
                        });

            }
        });
    }


    @Override
    public int getItemCount() {
        return inquiries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public TextView name, date, course, mobile;
        public RelativeLayout ll_design;
        public ImageButton editBtn, deleteBtn;


        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.iname);
            mobile = itemView.findViewById(R.id.imobile);
            course = itemView.findViewById(R.id.icourse);
            date = itemView.findViewById(R.id.idate);
            editBtn = itemView.findViewById(R.id.iedit);
            deleteBtn = itemView.findViewById(R.id.idelete);
            ll_design = itemView.findViewById(R.id.ll_design);
            itemView.setOnCreateContextMenuListener(this);

        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.inquiry_drawer, menu);
        }
    }
}

