package com.gi.waitlist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.waitlist.data.DemoAdapter;
import com.gi.waitlist.data.WaitListContract;
import com.gi.waitlist.data.WaitListDbHelper;

import java.util.LinkedList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private EditText guestNameEditText;
    private EditText partySizeEditText;
    private GuestListAdapter guestListAdapter;
    private SQLiteDatabase sqLiteDatabase;
    Button editButton,deleteButton;
    RecyclerView guestListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        List<String> items = new LinkedList<>();
//        items.add("Code It");
        guestNameEditText = (EditText) findViewById(R.id.et_student_name);
        partySizeEditText = (EditText) findViewById(R.id.et_student_year);

        guestListRecyclerView = (RecyclerView) findViewById(R.id.rv_guest_list);
        guestListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        DemoAdapter adapter = new DemoAdapter(items);
//        guestListRecyclerView.setAdapter(adapter);

        WaitListDbHelper waitListDbHelper = new WaitListDbHelper(this);
        sqLiteDatabase = waitListDbHelper.getWritableDatabase();
        Cursor cursor = getAllGuest();

        guestListAdapter = new GuestListAdapter(this, cursor);
        guestListRecyclerView.setAdapter(guestListAdapter);
        editButton = findViewById(R.id.editBtn1);

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
        }).attachToRecyclerView(guestListRecyclerView);



    }

    public boolean removeGuest(float id) {
        return sqLiteDatabase.delete(WaitListContract.WaitListEntry.TABLE_NAME,
                WaitListContract.WaitListEntry._ID + "=" + id, null) > 0;
    }

    public void addToWaitList(View view) {
        if (guestNameEditText.getText().length() == 0 || partySizeEditText.getText().length() == 0)
            return;

        String guestName = guestNameEditText.getText().toString();
        int partySize = Integer.parseInt(partySizeEditText.getText().toString());

        if (partySize != 0) {
            addNewGuest(guestName, partySize);
            guestListAdapter.swapCursor(getAllGuest());

            Toast.makeText(this, getResources().getString(R.string.toast_guest_added), Toast.LENGTH_SHORT).show();

            partySizeEditText.clearFocus();
            guestNameEditText.getText().clear();
            partySizeEditText.getText().clear();
        } else {
            Toast.makeText(this, getResources().getString(R.string.toast_error_party_size), Toast.LENGTH_SHORT).show();
        }
    }

    private void addNewGuest(String guestName, int partySize) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WaitListContract.WaitListEntry.GUEST_NAME, guestName);
        contentValues.put(WaitListContract.WaitListEntry.PARTY_SIZE, partySize);
        sqLiteDatabase.insert(WaitListContract.WaitListEntry.TABLE_NAME, null, contentValues);
    }

    private Cursor getAllGuest() {
        return sqLiteDatabase.query(
                WaitListContract.WaitListEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                WaitListContract.WaitListEntry.PARTY_SIZE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

//    public void deleteBtn(View view) {
//        Toast.makeText(this, "Press The Delete Button", Toast.LENGTH_SHORT).show();
////        DemoAdapter adapter = new DemoAdapter();
////        int newPosition = adapter.;
////        model.remove(newPosition);
////        notifyItemRemoved(newPosition);
////        notifyItemRangeChanged(newPosition, model.size());
//
//    }
    public void editBtn(View view) {
        Toast.makeText(this, "Press The Edit Button", Toast.LENGTH_SHORT).show();
    }
}
