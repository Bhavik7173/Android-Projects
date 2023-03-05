package com.gi.sqliteapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "studentdb.sqlite";
    public static final String CONTACTS_TABLE_NAME = "mystudent";
    public static final String CONTACTS_COLUMN_ID  = "id";
    public static final String CONTACTS_COLUMN_STUNAME = "name";
    public static final String CONTACTS_COLUMN_STUYEAR = "year";


    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table mystudent " +
                        "(id integer primary key autoincrement, name text,year text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mystudent");
        onCreate(db);
    }
    public boolean addStudentContact(String contactname,String contactphone){
        /*,*/
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put("name",contactname);
        contantValues.put("year", contactphone);

        db.insert("mystudent", null, contantValues);
        db.close();
        return true;
    }
    public boolean updateStudentContact(Integer contactid,String contactname,String contactphone)
    {
        /*,String contactname,*/
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put("name",contactname);
        contantValues.put("year", contactphone);

        db.update("mystudent", contantValues, "id = ?", new String[]{Integer.toString(contactid)});
        db.close();
        return true;
    }
    public Integer deleteContact(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("mystudent","id = ?",new String[]{Integer.toString(id)});
    }
    public Cursor getData(int contactid){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from mystudent where id = " + contactid + "", null);
        return res;
    }
    public int numberOfRows(){
        SQLiteDatabase db=this.getWritableDatabase();
        int numRows=(int) DatabaseUtils.queryNumEntries(db,CONTACTS_TABLE_NAME);
        return numRows;
    }
    @SuppressLint("Range")
    public ArrayList<String> getAllStudentContacts(){
        ArrayList<String> arraylist= new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from mystudent",null);

        if (cursor.moveToFirst()) {
            do {
//                arraylist.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_ID)));
                arraylist.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_STUNAME)));
                arraylist.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_STUYEAR)));

            } while (cursor.moveToNext());
        }
        return arraylist;
    }
}
