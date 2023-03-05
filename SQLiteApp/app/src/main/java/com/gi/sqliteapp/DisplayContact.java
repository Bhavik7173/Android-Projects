package com.gi.sqliteapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayContact extends AppCompatActivity {
    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb;
    EditText name;
    EditText year;

    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        name = findViewById(R.id.et_student_name);
        year = findViewById(R.id.et_student_year);


        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        {
            int Value = extras.getInt("id");

            if (Value > 0) {
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();
                Log.d("gilog",rs.moveToFirst()+"");
                Log.d("gilog",rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_STUNAME)+"");
                Log.d("gilog",rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_STUYEAR)+"");
                Log.d("gilog",rs.getColumnCount()+"");

                @SuppressLint("Range") String stuname = rs.getString(1);
                Log.d("gilog",stuname);
                @SuppressLint("Range") String stuyear = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_STUYEAR));


                if (!rs.isClosed()) {
                    rs.close();
                }
                TextView b = findViewById(R.id.submitBtn);
                b.setVisibility(View.INVISIBLE);

                name.setText((CharSequence) stuname);
                name.setFocusable(false);
                name.setClickable(false);

                year.setText((CharSequence) stuyear);
                year.setFocusable(false);
                year.setClickable(false);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                getMenuInflater().inflate(R.menu.menu_display_contact, menu);
            } else {
                getMenuInflater().inflate(R.menu.menu_main, menu);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.Edit_Contact:
                TextView b = (TextView) findViewById(R.id.submitBtn);
                b.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);

                year.setEnabled(true);
                year.setFocusableInTouchMode(true);
                year.setClickable(true);


                return true;
            case R.id.Delete_Contact:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteContact)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteContact(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure ?");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void saveData(View view) {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                if (mydb.updateStudentContact(id_To_Update, name.getText().toString(), year.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Record not updated", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (mydb.addStudentContact(name.getText().toString(), year.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Record not added", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }

    public void resetBtn(View v)
    {
        name.setText("");
        year.setText("");
    }
}
