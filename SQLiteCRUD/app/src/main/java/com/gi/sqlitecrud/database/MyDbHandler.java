package com.gi.sqlitecrud.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gi.sqlitecrud.model.StudentModel;
import com.gi.sqlitecrud.param.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE " + Params.Table_Name + "("
                + Params.Key_ID + " INTEGER PRIMARY KEY," + Params.student_name
                + " TEXT, " + Params.student_year + " TEXT" + ")";
        Log.d("gilog","Query being run is:"+ create);
        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WaitListContract.WaitListEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public MyDbHandler(Context context)
    {
        super(context, Params.DB_Name,null,Params.DB_VERSION);

    }
    public void addStudentModel(StudentModel StudentModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.student_name, StudentModel.getSname());
        values.put(Params.student_year, StudentModel.getSyear());


        db.insert(Params.Table_Name, null, values);
        Log.d("gilog", "Successfully inserted");
        db.close();
    }

    public List<StudentModel> getAllStudentModels(){
        List<StudentModel> StudentModelList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + Params.Table_Name;
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if(cursor.moveToFirst()){
            do{
                StudentModel StudentModel = new StudentModel();
                StudentModel.setId(Integer.parseInt(cursor.getString(0)));
                StudentModel.setSname(cursor.getString(1));
                StudentModel.setSyear(cursor.getString(2));
                StudentModelList.add(StudentModel);
            }while(cursor.moveToNext());
        }
        return StudentModelList;
    }

    public int updateStudentModel(StudentModel StudentModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.student_name, StudentModel.getSname());
        values.put(Params.student_year, StudentModel.getSyear());

        //Update record
        return db.update(Params.Table_Name, values, Params.Key_ID + "=?",
                new String[]{String.valueOf(StudentModel.getId())});


    }
    public void deleteStudentById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.Table_Name, Params.Key_ID +"=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deletestudent(StudentModel studentModel){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.Table_Name, Params.Key_ID +"=?", new String[]{String.valueOf(studentModel.getId())});
        db.close();
    }

    public int getCount(){
        String query = "SELECT  * FROM " + Params.Table_Name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();

    }
}
