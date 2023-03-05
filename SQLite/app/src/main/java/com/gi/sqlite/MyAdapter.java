package com.gi.sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    List<String> stu_name,stu_year;

    public MyAdapter(List<String> stu_name, List<String> stu_year) {
        this.stu_name = stu_name;
        this.stu_year = stu_year;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View view = inflator.inflate(R.layout.list_item_layout,null);

        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.s_name.setText(stu_name.get(position));
        holder.s_year.setText(stu_year.get(position));

    }

    @Override
    public int getItemCount() {
        return stu_year.size();
    }

    class MyHolder extends RecyclerView.ViewHolder
    {
        TextView s_name,s_year;
        MyHolder(View v)
        {
            super(v);
            s_name = v.findViewById(R.id.et_student_name);
            s_year = v.findViewById(R.id.et_student_year);
        }
    }
}
