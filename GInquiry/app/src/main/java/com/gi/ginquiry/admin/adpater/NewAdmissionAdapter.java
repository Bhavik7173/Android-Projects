package com.gi.ginquiry.admin.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.ginquiry.R;
import com.gi.ginquiry.admin.model.MyNewAdmission;

import java.util.ArrayList;

public class NewAdmissionAdapter extends RecyclerView.Adapter<NewAdmissionAdapter.ViewHolder> {
    Context context;
    ArrayList<MyNewAdmission> modelArrayList;

    public NewAdmissionAdapter(Context context, ArrayList<MyNewAdmission> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_admin_new_admission, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyNewAdmission model = modelArrayList.get(position);
        holder.sname.setText("Name: " + model.getSname());
        holder.smobile.setText("Mobile: " + model.getSmobile());
        holder.semail.setText("Email: " + model.getSemail());
        holder.scourse.setText("Course: " + model.getScourse());
        holder.sfaculty.setText("Faculty: " + model.getSfaculty());
        holder.ll_newAddesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + model.getSname(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView sname, smobile, semail, scourse, sfaculty;
        public ImageView sphoto;
        public RelativeLayout ll_newAddesign;
        public ImageButton editBtn, deleteBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            sphoto = itemView.findViewById(R.id.sphoto);
            sname = itemView.findViewById(R.id.sname);
            smobile = itemView.findViewById(R.id.smobile);
            semail = itemView.findViewById(R.id.semail);
            scourse = itemView.findViewById(R.id.scourse);
            sfaculty = itemView.findViewById(R.id.sfaculty);
            ll_newAddesign = itemView.findViewById(R.id.ll_newAddesign);
        }


    }
}
