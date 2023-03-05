package com.gi.waitinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RvViewHolder> {
    Context context;
    ArrayList<StudentModel> models;
    Onclick onclick;
    public interface Onclick {
        void onEvent(StudentModel model,int pos);
    }
    public MyAdapter(Context context, ArrayList<StudentModel> models, Onclick onclick) {
        this.context = context;
        this.models = models;
        this.onclick = onclick;
    }
    View view;
    @Override
    public MyAdapter.RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        RvViewHolder rvViewHolder = new RvViewHolder(view);
        return rvViewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.RvViewHolder holder, final int position) {
        final StudentModel model = models.get(position);
        if (model.getName() != null) {
            holder.stu_name.setText(model.getName());
        }
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                models.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.onEvent(model,position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return models.size();
    }
    public class RvViewHolder extends RecyclerView.ViewHolder {
        TextView stu_name,stu_year;
        Button editBtn,deleteBtn;
        LinearLayout llItem;
        public RvViewHolder(View itemView) {
            super(itemView);
            stu_name = itemView.findViewById(R.id.tv_guest_name);
            stu_year = itemView.findViewById(R.id.tv_party_size);
            editBtn = itemView.findViewById(R.id.editBtn1);
            deleteBtn = itemView.findViewById(R.id.deleteBtn1);
            llItem = itemView.findViewById(R.id.ll_item);
        }
    }
}
