package com.gi.inquiry.admin.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gi.inquiry.admin.model.MyCourseData;

import java.util.ArrayList;

public class CourseDB {

    Context context;
    SQLiteDBHelper sqLiteDBHelper;
    SQLiteDatabase sqLiteDatabase;

    public CourseDB(Context context) {
        this.context = context;
        sqLiteDBHelper = new SQLiteDBHelper(context);
    }


    @SuppressLint("Range")
    public ArrayList<MyCourseData> getAllData() {
        ArrayList<MyCourseData> allMycourseData = new ArrayList<>();
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        String[] columns = {sqLiteDBHelper.COURSE_NAME_COL, sqLiteDBHelper.COURSE_FEES_COL, sqLiteDBHelper.COURSE_DURATION_COL, sqLiteDBHelper.COURSE_PREREQUEST_COL};
        Cursor cursor = db.query(sqLiteDBHelper.COURSE_TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                MyCourseData course = new MyCourseData();

                course.setCname(cursor.getString(cursor.getColumnIndex(sqLiteDBHelper.COURSE_NAME_COL)));
                course.setCfee(cursor.getString(cursor.getColumnIndex(sqLiteDBHelper.COURSE_FEES_COL)));
                course.setCduration(cursor.getString(cursor.getColumnIndex(sqLiteDBHelper.COURSE_DURATION_COL)));
                course.setCprerequest(cursor.getString(cursor.getColumnIndex(sqLiteDBHelper.COURSE_PREREQUEST_COL)));

                allMycourseData.add(course);
            } while (cursor.moveToNext());
        }
        return allMycourseData;
    }

    public long addNewCourse(String name, String fees, String duration, String prerequest, String syallbus, String program) {
        SQLiteDatabase db = this.sqLiteDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(sqLiteDBHelper.COURSE_NAME_COL, name);
        values.put(sqLiteDBHelper.COURSE_FEES_COL, fees);
        values.put(sqLiteDBHelper.COURSE_DURATION_COL, duration);
        values.put(sqLiteDBHelper.COURSE_PREREQUEST_COL, prerequest);
        values.put(sqLiteDBHelper.COURSE_SYLLABUS_COL, syallbus);
        values.put(sqLiteDBHelper.COURSE_PROGRAM_COL, program);


        return db.insert(sqLiteDBHelper.COURSE_TABLE_NAME, null, values);
    }

    public ArrayList<MyCourseData> displayData() {
        sqLiteDatabase = sqLiteDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from course", null);
        ArrayList<MyCourseData> modelArrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                modelArrayList.add(new MyCourseData(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return modelArrayList;
    }
}
