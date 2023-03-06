package com.gi.inquiry.admin.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gi.inquiry.admin.model.MyInquiryData;

import java.util.ArrayList;

public class InquiryDB {
    Context context;
    SQLiteDBHelper sqLiteDBHelper;
    SQLiteDatabase sqLiteDatabase;

    public InquiryDB(Context context) {
        this.context = context;
        sqLiteDBHelper = new SQLiteDBHelper(context);
    }


    public long addNewInquiry(String inameEdt1, String imobile1, String iemail1, String icourse1, String icollege1, String ipreffered1, String inote1, String idate1) {
        sqLiteDatabase = this.sqLiteDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(sqLiteDBHelper.INQUIRY_NAME_COL, inameEdt1);
        values.put(sqLiteDBHelper.INQUIRY_MOBILE_COL, imobile1);
        values.put(sqLiteDBHelper.INQUIRY_EMAIL_COL, iemail1);
        values.put(sqLiteDBHelper.INQUIRY_Course_COL, icourse1);
        values.put(sqLiteDBHelper.INQUIRY_NOTE_COL, inote1);
        values.put(sqLiteDBHelper.INQUIRY_COLLEGE_COL, icollege1);
        values.put(sqLiteDBHelper.INQUIRY_DATE_COL, idate1);
        values.put(sqLiteDBHelper.INQUIRY_PrefferedTime_COL, ipreffered1);

        return sqLiteDatabase.insert(sqLiteDBHelper.INQUIRY_TABLE_NAME, null, values);
    }


    @SuppressLint("Range")
    public ArrayList<MyInquiryData> getAllData() {
        ArrayList<MyInquiryData> allMyInquiryData = new ArrayList<>();
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        String[] columns = {sqLiteDBHelper.INQUIRY_NAME_COL, sqLiteDBHelper.INQUIRY_MOBILE_COL, sqLiteDBHelper.INQUIRY_Course_COL, sqLiteDBHelper.INQUIRY_DATE_COL};
        Cursor cursor = db.query(sqLiteDBHelper.INQUIRY_TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                MyInquiryData inquiry = new MyInquiryData();

                inquiry.setIname(cursor.getString(cursor.getColumnIndex(sqLiteDBHelper.INQUIRY_NAME_COL)));
                inquiry.setImobile(cursor.getString(cursor.getColumnIndex(sqLiteDBHelper.INQUIRY_MOBILE_COL)));
                inquiry.setIcourse(cursor.getString(cursor.getColumnIndex(sqLiteDBHelper.INQUIRY_Course_COL)));
                inquiry.setIdate(cursor.getString(cursor.getColumnIndex(sqLiteDBHelper.INQUIRY_DATE_COL)));

                allMyInquiryData.add(inquiry);
            } while (cursor.moveToNext());
        }
        return allMyInquiryData;
    }
}
