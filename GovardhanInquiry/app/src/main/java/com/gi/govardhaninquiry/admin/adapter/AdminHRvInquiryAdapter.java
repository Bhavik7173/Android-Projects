package com.gi.govardhaninquiry.admin.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.activity.AdminInquiry;
import com.gi.govardhaninquiry.admin.model.CourseModel;
import com.gi.govardhaninquiry.retro.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

public class AdminHRvInquiryAdapter extends RecyclerView.Adapter<AdminHRvInquiryAdapter.ViewHolder> {
    public List<CourseModel> list;
    public Context context;

    public AdminHRvInquiryAdapter(AdminInquiry adminInquiry, List<CourseModel> personImages){ ;
        this.list = (ArrayList<CourseModel>) personImages;
        context = adminInquiry;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.rv_layout_admin_inquiry, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseModel model = list.get(position);
        Log.d("gilog_clogo",model.getClogo());
        Glide.with(context)
                .load(RetrofitClient.IpAddress +model.getClogo())
//                .override(300, 200)
                .into(holder.course);
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
