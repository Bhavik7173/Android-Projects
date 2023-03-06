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
import com.gi.inquiry.admin.DAO.InquiryDB;
import com.gi.inquiry.admin.DAO.SQLiteDBHelper;
import com.gi.inquiry.admin.addNew.AddNewInquiry;
import com.gi.inquiry.admin.model.MyInquiryData;

import java.util.ArrayList;

public class AdminInquiryAdapter extends RecyclerView.Adapter<AdminInquiryAdapter.ViewHolder> {

    ArrayList<MyInquiryData> inquiries = new ArrayList<MyInquiryData>();
    static Context context;
    SQLiteDatabase sqLiteDatabase;

    public AdminInquiryAdapter(Context context, ArrayList<MyInquiryData> mData) {
        this.context = context;
        inquiries = mData;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_admin_inquiry, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    public void updateData(ArrayList<MyInquiryData> viewModels) {
        inquiries.clear();
        inquiries.addAll(viewModels);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        MyInquiryData model = inquiries.get(position);
        holder.iname.setText(model.getIname());
        holder.imobile.setText(model.getImobile());
        holder.icourse.setText(model.getIcourse());
        holder.idate.setText(model.getIdate());
        holder.ll_design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + model.getIname(), Toast.LENGTH_LONG).show();
            }
        });
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("inquiry_id", model.getIid());
                bundle.putString("inquiry_name", model.getIname());
                bundle.putString("inquiry_mobile", model.getImobile());
                bundle.putString("inquiry_course", model.getIcourse());
                bundle.putString("inquiry_date", model.getIdate());
                bundle.putString("inquiry_email", model.getIemail());
                bundle.putString("inquiry_note", model.getInote());
                bundle.putString("inquiry_college", model.getIcollege());
                bundle.putString("inquiry_preferred_time", model.getIpreferred());
                Log.d("mylog", bundle + "");
                Intent intent = new Intent(context, AddNewInquiry.class);
                intent.putExtra("userdata", bundle);
                context.startActivity(intent);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(context);

            @Override
            public void onClick(View v) {
                sqLiteDatabase = sqLiteDBHelper.getReadableDatabase();
                long delele = sqLiteDatabase.delete("inquiry", "inquiry_id=" + model.getIid(), null);
                if (delele != -1) {
                    Toast.makeText(context, "deleted data successfully", Toast.LENGTH_SHORT).show();
                    inquiries.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return inquiries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public TextView iname, idate, icourse, imobile;
        public RelativeLayout ll_design;
        public ImageButton editBtn, deleteBtn;
        InquiryDB inquiryDB;
        SQLiteDatabase sqLiteDatabase;
        SQLiteDBHelper sqLiteDBHelper;
        int id = 0;

        public ViewHolder(View itemView) {
            super(itemView);
            inquiryDB = new InquiryDB(context);
            iname = itemView.findViewById(R.id.iname);
            imobile = itemView.findViewById(R.id.imobile);
            icourse = itemView.findViewById(R.id.icourse);
            idate = itemView.findViewById(R.id.idate);
            editBtn = itemView.findViewById(R.id.iedit);
            deleteBtn = itemView.findViewById(R.id.idelete);
            sqLiteDBHelper = new SQLiteDBHelper(context);
            ll_design = itemView.findViewById(R.id.ll_design);
            itemView.setOnCreateContextMenuListener(this);

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ContentValues cv = new ContentValues();
//                    cv.put("inquiry_id",);
                    cv.put("inquiry_name", iname.getText().toString());
                    cv.put("inquiry_mobile", imobile.getText().toString());
                    cv.put("inquiry_course", icourse.getText().toString());
                    cv.put("inquiry_date", idate.getText().toString());

                    sqLiteDatabase = sqLiteDBHelper.getReadableDatabase();
                    long recedit = sqLiteDatabase.update("inquiry", cv, "inquiry_id=" + id, null);
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
            inflater.inflate(R.menu.inquiry_drawer, menu);
        }


    }
}
