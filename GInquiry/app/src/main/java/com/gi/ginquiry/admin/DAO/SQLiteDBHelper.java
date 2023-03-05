package com.gi.ginquiry.admin.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "inquiryDB";

    //faculty Table
    public static final int DB_VERSION = 2;
    public static final String FACULTY_TABLE_NAME = "faculty";
    public static final String FACULTY_ID_COL = "faculty_id";
    public static final String FACULTY_NAME_COL = "faculty_name";
    public static final String FACULTY_DOB_COL = "faculty_dob";
    public static final String FACULTY_DOJ_COL = "faculty_doj";
    public static final String FACULTY_MOBILE_COL = "faculty_mobile";
    public static final String FACULTY_WHATSAPP_COL = "faculty_whatsapp";
    public static final String FACULTY_EMAIL_COL = "faculty_email";
    public static final String FACULTY_YEAR_OF_EXPERIENCE = "faculty_yoe";
    public static final String FACULTY_ADDRESS_COL = "faculty_address";
    public static final String FACULTY_REFERENCE_NAME_COL = "faculty_reference_name";
    public static final String FACULTY_REFERENCE_NO_COL = "faculty_reference_no";
    public static final String FACULTY_TECHNOLOGY_COL = "faculty_technology";
    public static final String FACULTY_NOTE_COL = "faculty_note";
    public static final String FACULTY_QUALIFICATION_COL = "faculty_qaulification";
    public static final String FACULTY_AADHARCARD_COL = "faculty_aadhar_card";
    public static final String FACULTY_RESUME_COL = "faculty_resume";
    public static final String FACULTY_PHOTO_COL = "faculty_photo";

    //Inquiry table
    public static final String INQUIRY_TABLE_NAME = "inquiry";
    public static final String INQUIRY_ID_COL = "inquiry_id";
    public static final String INQUIRY_NAME_COL = "inquiry_name";
    public static final String INQUIRY_MOBILE_COL = "inquiry_mobile";
    public static final String INQUIRY_Course_COL = "inquiry_course";
    public static final String INQUIRY_DATE_COL = "inquiry_date";
    public static final String INQUIRY_EMAIL_COL = "inquiry_email";
    public static final String INQUIRY_NOTE_COL = "inquiry_note";
    public static final String INQUIRY_COLLEGE_COL = "inquiry_college";
    public static final String INQUIRY_PrefferedTime_COL = "inquiry_preferred_time";

    //course Table
    public static final String COURSE_TABLE_NAME = "course";
    public static final String COURSE_ID_COL = "course_id";
    public static final String COURSE_NAME_COL = "course_name";
    public static final String COURSE_FEES_COL = "course_fees";
    public static final String COURSE_DURATION_COL = "course_duration";
    public static final String COURSE_PREREQUEST_COL = "course_prerequest";
    public static final String COURSE_SYLLABUS_COL = "course_syllabus";
    public static final String COURSE_PROGRAM_COL = "course_program";

    public SQLiteDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

//    public SQLiteDBHelper() {
//    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String faculty_query = "CREATE TABLE " + FACULTY_TABLE_NAME + " ("
                + FACULTY_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FACULTY_NAME_COL + " TEXT,"
                + FACULTY_DOB_COL + " TEXT,"
                + FACULTY_DOJ_COL + " TEXT,"
                + FACULTY_MOBILE_COL + " TEXT,"
                + FACULTY_WHATSAPP_COL + " TEXT,"
                + FACULTY_EMAIL_COL + " TEXT,"
                + FACULTY_YEAR_OF_EXPERIENCE + " TEXT,"
                + FACULTY_ADDRESS_COL + " TEXT,"
                + FACULTY_REFERENCE_NAME_COL + " TEXT,"
                + FACULTY_REFERENCE_NO_COL + " TEXT,"
                + FACULTY_TECHNOLOGY_COL + " TEXT,"
                + FACULTY_NOTE_COL + " TEXT,"
                + FACULTY_QUALIFICATION_COL + " TEXT,"
                + FACULTY_AADHARCARD_COL + " TEXT,"
                + FACULTY_RESUME_COL + " TEXT,"
                + FACULTY_PHOTO_COL + " TEXT)";
        sqLiteDatabase.execSQL(faculty_query);

        String inquiry_query = "CREATE TABLE " + INQUIRY_TABLE_NAME + " ("
                + INQUIRY_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + INQUIRY_NAME_COL + " TEXT,"
                + INQUIRY_MOBILE_COL + " TEXT,"
                + INQUIRY_Course_COL + " TEXT,"
                + INQUIRY_DATE_COL + " TIMESTAMP,"
                + INQUIRY_EMAIL_COL + " TEXT,"
                + INQUIRY_NOTE_COL + " TEXT,"
                + INQUIRY_COLLEGE_COL + " TEXT,"
                + INQUIRY_PrefferedTime_COL + " TEXT)";

        sqLiteDatabase.execSQL(inquiry_query);

        String course_query2 = "CREATE TABLE " + COURSE_TABLE_NAME + " ("
                + COURSE_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COURSE_NAME_COL + " TEXT,"
                + COURSE_FEES_COL + " TEXT,"
                + COURSE_DURATION_COL + " TEXT,"
                + COURSE_PREREQUEST_COL + " TEXT,"
                + COURSE_SYLLABUS_COL + " TEXT,"
                + COURSE_PROGRAM_COL + " TEXT)";
        sqLiteDatabase.execSQL(course_query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FACULTY_TABLE_NAME);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + INQUIRY_TABLE_NAME);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + COURSE_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
