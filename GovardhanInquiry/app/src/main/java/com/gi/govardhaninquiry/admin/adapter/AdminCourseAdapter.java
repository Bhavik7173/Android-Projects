package com.gi.govardhaninquiry.admin.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

import androidx.recyclerview.widget.RecyclerView;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.activity.AddNewCourse;
import com.gi.govardhaninquiry.admin.activity.AddNewInquiry;
import com.gi.govardhaninquiry.admin.activity.AdminCourse;
import com.gi.govardhaninquiry.admin.activity.AdminInquiry;
import com.gi.govardhaninquiry.admin.model.CourseModel;
import com.gi.govardhaninquiry.admin.model.InquiryData;
import com.gi.govardhaninquiry.retro.RetroInterface;
import com.gi.govardhaninquiry.retro.RetrofitClient;
import com.google.firebase.analytics.connector.AnalyticsConnector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminCourseAdapter extends RecyclerView.Adapter<AdminCourseAdapter.ViewHolder> {
    public static Context context;
    ArrayList<CourseModel> courses = new ArrayList<CourseModel>();
    private CourseModel[] listdata;


    public AdminCourseAdapter(List<CourseModel> mData) {
        this.context = context;
        courses = (ArrayList<CourseModel>) mData;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_admin_course, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        CourseModel model = courses.get(position);
        holder.cname.setText("Course: " + model.getCname());
        holder.cfee.setText(" " + model.getCfee());
        holder.cduration.setText(" " + model.getCduration());
        holder.cprerequest.setText(" " + model.getCprerequest());
        holder.ll_cdesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + model.getCname(), Toast.LENGTH_LONG).show();
            }
        });
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CourseModel user = new CourseModel(model.getId(),model.getName(), model.getMobile(),model.getCourse(), model.getDate(),model.getEmail(), model.getNote(), model.getCollege(),model.getPreferred());
//                Intent i = new Intent(context.getApplicationContext(), AddNewCourse.class);
//                i.putExtra("CourseModel",user);
//                context.startActivity(i);
//
//                Intent intent = new Intent(context, AddNewInquiry.class);
//                intent.putExtra("CourseModel", (Parcelable) model);
//                context.startActivity(intent);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitClient.getClient(context).create(RetroInterface.class).deleteCourse(model.getCname())
                        .enqueue(new Callback<ArrayList<CourseModel>>() {
                            @Override
                            public void onResponse(Call<ArrayList<CourseModel>> call, Response<ArrayList<CourseModel>> response) {
                                Intent intent = new Intent(context, AdminCourse.class);
                                context.startActivity(intent);
                                context.notifyAll();

                            }

                            @Override
                            public void onFailure(Call<ArrayList<CourseModel>> call, Throwable t) {
                                Toast.makeText(context, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("gisurat", t.toString());
                            }
                        });

            }
        });
    }


    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public TextView cname, cduration, cfee, cprerequest;
        public RelativeLayout ll_cdesign;
        public ImageButton editBtn, deleteBtn;


        public ViewHolder(View itemView) {
            super(itemView);

            cname = itemView.findViewById(R.id.cname);
            cfee = itemView.findViewById(R.id.cfees);
            cduration = itemView.findViewById(R.id.cduration);
            cprerequest = itemView.findViewById(R.id.crequest);
            editBtn = itemView.findViewById(R.id.cedit);
            deleteBtn = itemView.findViewById(R.id.cdelete);

            ll_cdesign = itemView.findViewById(R.id.cll_cdesign);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.course_drawer, menu);
        }
    }
}
