package com.gi.ginquiry.admin.adpater;

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

import com.gi.ginquiry.R;
import com.gi.ginquiry.admin.DAO.FacultyDB;
import com.gi.ginquiry.admin.DAO.SQLiteDBHelper;
import com.gi.ginquiry.admin.addNew.AddNewFaculty;
import com.gi.ginquiry.admin.model.MyFacultyData;

import java.util.ArrayList;

public class AdminFacultyAdapter extends RecyclerView.Adapter<AdminFacultyAdapter.ViewHolder> {

    private MyFacultyData[] listdata;
    public static Context context;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<MyFacultyData> facultyData = new ArrayList<MyFacultyData>();

    public AdminFacultyAdapter(Context context, ArrayList<MyFacultyData> modelArrayList) {
        this.context = context;
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
        holder.fname.setText("Name: " + model.getFname());
        holder.fdob.setText("DOB: " + model.getFdob());
        holder.fmobile.setText("Mobile: " + model.getFmobile());
        holder.femail.setText("Email: " + model.getFemail());
        holder.ftechnology.setText("Technology: " + model.getFtechnology());
        holder.ll_fdesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + model.getFname(), Toast.LENGTH_LONG).show();
            }
        });
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("faculty_id", model.getFid());
                bundle.putString("faculty_name", model.getFname());
                bundle.putString("faculty_dob", model.getFdob());
                bundle.putString("faculty_doj", model.getFdoj());
                bundle.putString("faculty_mobile", model.getFmobile());
                bundle.putString("faculty_whatsapp", model.getFwhatapp());
                bundle.putString("faculty_email", model.getFemail());
                bundle.putString("faculty_yoe", model.getFyoe());
                bundle.putString("faculty_address", model.getFaddress());
                bundle.putString("faculty_reference_name", model.getFrefernecename());
                bundle.putString("faculty_reference_no", model.getFreferenceno());
                bundle.putString("faculty_technology", model.getFtechnology());
                bundle.putString("faculty_note", model.getFnote());
                bundle.putString("faculty_qaulification", model.getFqualification());
                bundle.putString("faculty_aadhar_card", model.getFaadharcard());
                bundle.putString("faculty_resume", model.getFresume());
                bundle.putString("faculty_photo", model.getFphoto());
                Log.d("mylog", bundle + "");
                Intent intent = new Intent(context, AddNewFaculty.class);
                intent.putExtra("userdata", bundle);
                context.startActivity(intent);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(context);

            @Override
            public void onClick(View v) {
                sqLiteDatabase = sqLiteDBHelper.getReadableDatabase();
                long delele = sqLiteDatabase.delete("faculty", "faculty_id=" + model.getFid(), null);
                if (delele != -1) {
                    Toast.makeText(context, "deleted data successfully", Toast.LENGTH_SHORT).show();
                    facultyData.remove(position);
                    notifyDataSetChanged();
                }
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
        FacultyDB facultyDB;
        SQLiteDBHelper sqLiteDBHelper;
        SQLiteDatabase sqLiteDatabase;
        int id = 0;

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
            sqLiteDBHelper = new SQLiteDBHelper(context);
            ll_fdesign = itemView.findViewById(R.id.ll_fdesign);
            itemView.setOnCreateContextMenuListener(this);
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ContentValues cv = new ContentValues();
                    cv.put("faculty_name", fname.getText().toString());
                    cv.put("faculty_dob", fdob.getText().toString());
                    cv.put("faculty_mobile", fmobile.getText().toString());
                    cv.put("faculty_email", fmobile.getText().toString());
                    cv.put("faculty_technology", ftechnology.getText().toString());

                    sqLiteDatabase = sqLiteDBHelper.getReadableDatabase();
                    long recedit = sqLiteDatabase.update("faculty", cv, "faculty_id=" + id, null);
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
            inflater.inflate(R.menu.faculty_drawer, menu);
        }
    }
}