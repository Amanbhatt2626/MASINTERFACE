package com.example.tasktodo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class MyDbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "todo.db";
    private static final String TABLE_NAME = "users";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "PASSWORD";


    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " USERNAME TEXT UNIQUE, PASSWORD TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // returns true if data is inserted successfully
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE USERNAME=? AND PASSWORD=?", new String[]{username, password});
        return cursor.getCount() > 0;
    }


    public void createTable( String username){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("CREATE TABLE " + username + " (ID INTEGER PRIMARY KEY  AUTOINCREMENT," +
                " NOTES TEXT)");
    }
    public boolean insertUserData(String username, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOTES", notes);
        long result = db.insert(username, null, contentValues);
        return result != -1; // returns true if data is inserted successfully
    }
    public ArrayList<arryNotes> getNotes(String userName){

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<arryNotes> arry=new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + userName , null);
        if(cursor.moveToFirst()){
            do{

                arry.add(new arryNotes(cursor.getString(0),cursor.getString(1)));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return arry ;

    }

    public void deleteUser(String username){
        SQLiteDatabase db=this.getWritableDatabase();

      db.delete(TABLE_NAME,"USERNAME = ?",new String[]{username});

    }
    public void dropTable(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+username);

    }
    public void deleteTask(String username,String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(username,"ID = ?",new String[]{id});

    }


}
