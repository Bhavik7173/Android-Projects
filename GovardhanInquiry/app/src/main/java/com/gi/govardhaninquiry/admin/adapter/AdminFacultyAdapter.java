package com.gi.govardhaninquiry.admin.adapter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.model.MyFacultyData;
import com.gi.govardhaninquiry.retro.RetrofitClient;

import java.util.ArrayList;

public class AdminFacultyAdapter extends RecyclerView.Adapter<AdminFacultyAdapter.ViewHolder> {

    public static Context context;

    ArrayList<MyFacultyData> facultyData = new ArrayList<MyFacultyData>();


    public AdminFacultyAdapter(ArrayList<MyFacultyData> modelArrayList) {
        facultyData = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layour_admin_faculty, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MyFacultyData model = facultyData.get(position);
        holder.fname.setText("Name: " + model.getName());
        holder.fdob.setText("DOB: " + model.getDob());
        holder.fmobile.setText("Mobile: " + model.getMobile());
        holder.femail.setText("Email: " + model.getEmail());
        holder.ftechnology.setText("Technology: " + model.getTechnology());
//        Glide.with(context)
//                .load(RetrofitClient.IpAddress +model.getPhoto())
////                .override(300, 200)
//                .into(holder.fphoto);
        holder.ll_fdesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + model.getName(), Toast.LENGTH_LONG).show();
            }
        });
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(context, AddNewFaculty.class);
//                intent.putExtra("userdata", bundle);
//                context.startActivity(intent);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return facultyData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public TextView fname, fdob, ftechnology, fmobile, femail;
        public ImageView fphoto;
        public RelativeLayout ll_fdesign;
        public ImageButton editBtn, deleteBtn;


        public ViewHolder(View itemView) {
            super(itemView);
            fphoto = itemView.findViewById(R.id.fphoto);
            fname = itemView.findViewById(R.id.fname);
            fdob = itemView.findViewById(R.id.fdob);
            fmobile = itemView.findViewById(R.id.fmobile);
            femail = itemView.findViewById(R.id.femail);
            ftechnology = itemView.findViewById(R.id.ftechnology);
            editBtn = itemView.findViewById(R.id.fedit);
            deleteBtn = itemView.findViewById(R.id.fdelete);

            ll_fdesign = itemView.findViewById(R.id.ll_fdesign);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.faculty_drawer, menu);
        }
    }
}