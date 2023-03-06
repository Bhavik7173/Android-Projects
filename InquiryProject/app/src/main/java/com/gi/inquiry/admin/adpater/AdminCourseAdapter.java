package com.gi.inquiry.admin.adpater;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.gi.inquiry.R;
import com.gi.inquiry.admin.DAO.CourseDB;
import com.gi.inquiry.admin.DAO.SQLiteDBHelper;
import com.gi.inquiry.admin.addNew.AddNewCourse;
import com.gi.inquiry.admin.model.MyCourseData;

import java.util.ArrayList;

public class AdminCourseAdapter extends RecyclerView.Adapter<AdminCourseAdapter.ViewHolder> {
    private MyCourseData[] listdata;

    ArrayList<MyCourseData> courses = new ArrayList<MyCourseData>();
    public static Context context;
    SQLiteDatabase sqLiteDatabase;
    SQLiteDBHelper sqLiteDBHelper;


    public AdminCourseAdapter(Context context, ArrayList<MyCourseData> mData) {
        this.context = context;
        courses = mData;
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

        MyCourseData model = courses.get(position);
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
                Bundle bundle = new Bundle();
                bundle.putInt("course_id", model.getCid());
                bundle.putString("course_name", model.getCname());
                bundle.putString("course_fees", model.getCfee());
                bundle.putString("course_duration", model.getCduration());
                bundle.putString("course_prerequest", model.getCprerequest());
                bundle.putString("course_syllabus", model.getCsyllabus());
                bundle.putString("course_program", model.getCprogram());
                Log.d("mylog", bundle + "");
                Intent intent = new Intent(context, AddNewCourse.class);
                intent.putExtra("userdata", bundle);
                context.startActivity(intent);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            CourseDB dBmain = new CourseDB(context);

            @Override
            public void onClick(View v) {
                sqLiteDatabase = sqLiteDBHelper.getReadableDatabase();
                long delele = sqLiteDatabase.delete("course", "course_id=" + model.getCid(), null);
                if (delele != -1) {
                    Toast.makeText(context, "deleted data successfully", Toast.LENGTH_SHORT).show();
                    courses.remove(position);
                    notifyDataSetChanged();
                }
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
        CourseDB courseDB;
        SQLiteDatabase sqLiteDatabase;
        SQLiteDBHelper sqLiteDBHelper;
        int id = 0;

        public ViewHolder(View itemView) {
            super(itemView);

            cname = itemView.findViewById(R.id.cname);
            cfee = itemView.findViewById(R.id.cfees);
            cduration = itemView.findViewById(R.id.cduration);
            cprerequest = itemView.findViewById(R.id.crequest);
            editBtn = itemView.findViewById(R.id.iedit);
            deleteBtn = itemView.findViewById(R.id.idelete);
            sqLiteDBHelper = new SQLiteDBHelper(context);
            ll_cdesign = itemView.findViewById(R.id.ll_cdesign);
            itemView.setOnCreateContextMenuListener(this);
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ContentValues cv = new ContentValues();
                    cv.put("course_name", cname.getText().toString());
                    cv.put("course_fees", cfee.getText().toString());
                    cv.put("course_duration", cduration.getText().toString());
                    cv.put("course_prerequest", cprerequest.getText().toString());

                    sqLiteDatabase = sqLiteDBHelper.getReadableDatabase();
                    long recedit = sqLiteDatabase.update("course", cv, "course_id=" + id, null);
                    if (recedit != -1) {
                        Toast.makeText(context, "Data updated successfully", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(context, "something wrong try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.course_drawer, menu);
        }
    }
}
