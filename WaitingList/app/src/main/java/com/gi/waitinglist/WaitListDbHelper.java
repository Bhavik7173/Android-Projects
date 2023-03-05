package com.gi.waitinglist;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WaitListDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "waitlist.db";
    private static final int DATABASE_VERSION = 1;

    public WaitListDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + StudentModel.WaitListEntry.TABLE_NAME
                + " (" + StudentModel.WaitListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + StudentModel.WaitListEntry.GUEST_NAME + " TEXT NOT NULL, "
                + StudentModel.WaitListEntry.PARTY_SIZE + " INTEGER NOT NULL, "
                + StudentModel.WaitListEntry.TIME_STAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StudentModel.WaitListEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
