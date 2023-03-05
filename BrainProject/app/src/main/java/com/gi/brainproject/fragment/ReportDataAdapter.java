package com.gi.brainproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gi.brainproject.R;
import com.gi.brainproject.activity.ImagePreView;
import com.gi.brainproject.activity.ValidIPAddress;
import com.gi.brainproject.model.PatientResponse;

import java.util.ArrayList;

public class ReportDataAdapter extends RecyclerView.Adapter<ReportDataAdapter.ViewHolder> {
    ArrayList<PatientResponse> data = new ArrayList<>();
    public static Context context;

    public ReportDataAdapter(Context context, ArrayList<PatientResponse> modelArrayList) {
        this.context = context;
        data = modelArrayList;
    }


    @NonNull
    @Override
    public ReportDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_row_data, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReportDataAdapter.ViewHolder holder, int position) {
        PatientResponse model = data.get(position);
        holder.t1.setText("Name: " + model.getName());
        holder.t2.setText("Mobile: " + model.getContact());
        holder.t3.setText("Status: " + model.getStatus());
        holder.t3.setTextColor(Color.RED);

        Glide.with(context)
                .load(ValidIPAddress.ipaddress +model.getImage())
//                .override(300, 200)
                .into(holder.i1);
        Log.d("gilog",ValidIPAddress.ipaddress +model.getImage());
        holder.ll_design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + model.getName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ImagePreView.class);
                intent.putExtra("patient_name",model.getName());
                intent.putExtra("patient_mobile",model.getContact());
                intent.putExtra("patient_age",model.getAge());
                intent.putExtra("patient_gen",model.getGender());
                intent.putExtra("patient_status",model.getStatus());
                intent.putExtra("patient_image",ValidIPAddress.ipaddress +model.getImage());
                intent.putExtra("patient_image_path",model.getImage());
                context.startActivity(intent);
            }
        });
//        holder.t2.setText("Course: " + model.getName());
//        holder.i1.setImageDrawable(model.getImage());
//        holder.i2.setImageDrawable(model.getImage());
//        holder.ll_cdesign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "click on item: " + model.getCname(), Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        ImageView i1;
        TextView t1,t2,t3;
        LinearLayout ll_design;
        public ViewHolder(View listItem) {
            super(listItem);

            i1 =listItem.findViewById(R.id.nImg);
            t1 =listItem.findViewById(R.id.nEdt);
            t2 =listItem.findViewById(R.id.mEdt);
            t3 =listItem.findViewById(R.id.sEdt);
            ll_design = listItem.findViewById(R.id.ll_design);
            listItem.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

        }
    }
}
