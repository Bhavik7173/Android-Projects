package com.gi.inquiry.admin.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gi.inquiry.admin.model.MyFacultyData;

import java.util.ArrayList;

public class FacultyDB {

    Context context;
    SQLiteDBHelper sqLiteDBHelper;
    SQLiteDatabase sqLiteDatabase;

    public FacultyDB(Context context) {
        this.context = context;
        sqLiteDBHelper = new SQLiteDBHelper(context);
    }


    public ArrayList<MyFacultyData> getAllFaculties() {
        sqLiteDatabase = sqLiteDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + SQLiteDBHelper.FACULTY_TABLE_NAME, null);
        ArrayList<MyFacultyData> modelArrayList = new ArrayList<>();
        Log.d("mylog", modelArrayList + "");
        if (cursor.moveToFirst()) {
            do {

                modelArrayList.add(new MyFacultyData(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return modelArrayList;
    }


    public long addNewFaculty(String name, String dob, String doj, String mobile, String whatsapp, String email, String yoe, String address, String referencename, String referenceno, String technology, String note, String qualification, String resume, String aadharcard, String photo) {
        sqLiteDatabase = this.sqLiteDBHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SQLiteDBHelper.FACULTY_NAME_COL, name);
        cv.put(SQLiteDBHelper.FACULTY_DOB_COL, dob);
        cv.put(SQLiteDBHelper.FACULTY_DOJ_COL, doj);
        cv.put(SQLiteDBHelper.FACULTY_MOBILE_COL, mobile);
        cv.put(SQLiteDBHelper.FACULTY_WHATSAPP_COL, whatsapp);
        cv.put(SQLiteDBHelper.FACULTY_EMAIL_COL, email);
        cv.put(SQLiteDBHelper.FACULTY_YEAR_OF_EXPERIENCE, yoe);
        cv.put(SQLiteDBHelper.FACULTY_ADDRESS_COL, address);
        cv.put(SQLiteDBHelper.FACULTY_REFERENCE_NAME_COL, referencename);
        cv.put(SQLiteDBHelper.FACULTY_REFERENCE_NO_COL, referenceno);
        cv.put(SQLiteDBHelper.FACULTY_TECHNOLOGY_COL, technology);
        cv.put(SQLiteDBHelper.FACULTY_NOTE_COL, note);
        cv.put(SQLiteDBHelper.FACULTY_QUALIFICATION_COL, qualification);
        cv.put(SQLiteDBHelper.FACULTY_RESUME_COL, resume);
        cv.put(SQLiteDBHelper.FACULTY_AADHARCARD_COL, aadharcard);
        cv.put(SQLiteDBHelper.FACULTY_PHOTO_COL, photo);
        long recid = sqLiteDatabase.insert(SQLiteDBHelper.FACULTY_TABLE_NAME, null, cv);
        System.out.println("RECID: " + recid);
        return recid;
    }
}
