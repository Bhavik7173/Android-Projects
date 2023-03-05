package com.gi.sqlitecrud.database;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.sqlitecrud.HomeActivity;
import com.gi.sqlitecrud.R;
import com.gi.sqlitecrud.RviewActivity;
import com.gi.sqlitecrud.model.StudentModel;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {

    private RviewActivity context;
    private Cursor cursor;
    MyDbHandler db;
    private List<StudentModel> studentList;
    AlertDialog dialog = null;

    public StudentListAdapter(RviewActivity context, List<StudentModel> studentList) {
        this.context = context;
        this.studentList = studentList;
        db = new MyDbHandler(context);
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentModel student = studentList.get(position);
        holder.setStudentId(student.getId());
        holder.StudentNameTextView.setText(student.getSname());
        holder.StudentYearTextView.setText(student.getSyear());
        int pos = position;
//        StudentModel student = studentList.get(pos);
        holder.deletestudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete?");
                
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deletestudent(student);
                        context.setData();
                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });
        holder.editstudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("gilog", "Clicked on add Buttton");

               String name = student.getSname();
                String year = student.getSyear();
                int id = student.getId();
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("year",year+"");
                intent.putExtra("id",id+"");

                context.startActivity(intent);
                //Toast.makeText(context, "The position is " + String.valueOf(position) + " Name: " + name + ", Phone:" + year, Toast.LENGTH_SHORT).show();

                db.getAllStudentModels();
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }


    public class StudentViewHolder extends RecyclerView.ViewHolder{
        final TextView StudentNameTextView;
        final TextView StudentYearTextView;
        private int studentId;
        final ImageButton editstudentButton, deletestudentButton;

        public StudentViewHolder(View itemView) {
            super(itemView);
            StudentNameTextView = itemView.findViewById(R.id.edit_student_name);
            StudentYearTextView = itemView.findViewById(R.id.edit_student_year);
            editstudentButton = itemView.findViewById(R.id.editBtn1);
            deletestudentButton = itemView.findViewById(R.id.deleteBtn1);


        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }
    }

}
