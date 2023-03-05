package com.gi.waitinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class RviewActivity extends AppCompatActivity {
    MyAdapter adapter;
    RecyclerView studentListRecyclerView;
    ArrayList<StudentModel> models = new ArrayList<StudentModel>();
    SQLiteDatabase sqLiteDatabase;
    private GuestListAdapter guestListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rview);
        studentListRecyclerView = findViewById(R.id.rv_guest_list);
        studentListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentListRecyclerView.setAdapter(guestListAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                float id = (float) viewHolder.itemView.getTag();
                if (removeGuest(id)) {
                    Toast.makeText(HomeActivity.this,getResources().getString(R.string.toast_guest_removed), Toast.LENGTH_SHORT).show();
                }
                guestListAdapter.swapCursor(getAllGuest());
            }
        }).attachToRecyclerView(studentListRecyclerView);
        studentListRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        studentListRecyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(getApplicationContext(), models,
                new MyAdapter.Onclick() {
                    @Override
                    public void onEvent(StudentModel model, int pos) {
                        position = pos;
                        studentNameEdit.setText(model.getName());
                    }
                });
        studentListRecyclerView.setAdapter(adapter);
    }
    public boolean removeGuest(float id) {
        return sqLiteDatabase.delete(StudentModel.WaitListEntry.TABLE_NAME,
                StudentModel.WaitListEntry._ID + "=" + id, null) > 0;
    }

}