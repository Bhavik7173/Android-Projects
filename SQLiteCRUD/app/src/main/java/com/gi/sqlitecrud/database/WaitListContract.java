package com.gi.sqlitecrud.database;


import android.provider.BaseColumns;

public class WaitListContract {

    public static final class WaitListEntry implements BaseColumns {
        public static final String TABLE_NAME = "waitList";
        public static final String STUDENT_NAME = "studentName";
        public static final String STUDENT_YEAR = "studentYear";
        public static final String TIME_STAMP = "timeStamp";
    }

}
