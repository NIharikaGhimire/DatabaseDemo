package com.example.databasedemo.dbutil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.databasedemo.model.StudentModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "studentdb";
    private SQLiteDatabase db;

   public DBHelper(Context c){

       //version no change garyo bhne balla onupgrade call huncha
       super(c,DATABASE_NAME,null,1);
       db = getWritableDatabase();
   }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+
            "students(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name VARCHAR(255),"+
               "stid INTEGER,"+
               "faculty VARCHAR(255),"+
            "sem INTEGER)");


    }
//sql query execute
    //int i oldversion
    //inti1 newVersion

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS students");
       onCreate(sqLiteDatabase);


    }

    public void insertDataToDb(StudentModel studentModel) {
//       content value : function
       //SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", studentModel.getName());
        cv.put("stid", studentModel.getStdid());
        cv.put("faculty", studentModel.getFaculty());
        cv.put("sem",studentModel.getSem());
        db.insert("students", null, cv);
    }

    public ArrayList<StudentModel> retrieveData() {
       ArrayList<StudentModel> data = new ArrayList<>();
       String query = "Select * from students";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                StudentModel st = new StudentModel();
                st.setId(cursor.getInt(0));
                st.setName(cursor.getString(1));
                st.setStdid(cursor.getInt(2));
                st.setFaculty(cursor.getString(3));
                st.setSem(cursor.getInt(4));
                data.add(st);

            }while(cursor.moveToNext());
        }
       return data;
    }
}
