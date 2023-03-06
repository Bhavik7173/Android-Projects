package com.gi.inquiry.admin.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.inquiry.R;

import java.util.ArrayList;
import java.util.List;

public class AdminHRvInquiryAdapter extends RecyclerView.Adapter<AdminHRvInquiryAdapter.ViewHolder> {
    public List<Integer> list;
    public Context context;

    public AdminHRvInquiryAdapter(Context context, ArrayList<Integer> personImages) {
        this.context = context;
        this.list = personImages;
    }

    @NonNull
    @Override
    public AdminHRvInquiryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.rv_layout_admin_inquiry, parent, false);
        AdminHRvInquiryAdapter.ViewHolder viewHolder = new AdminHRvInquiryAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminHRvInquiryAdapter.ViewHolder holder, int position) {
        holder.course.setImageResource(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView course;

        public ViewHolder(View view) {
            super(view);

            course = (ImageView) view.findViewById(R.id.course);
        }
    }
}
