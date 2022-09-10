package com.example.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME ="record.db";

    public DBHelper(Context context) {
        super(context, "record.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table user_info(username TEXT primary key, password TEXT, address TEXT,email TEXT, contactno INT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists user_info");
    }

    public boolean insertuser (String username,String  password, String address , String email,long contactno ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("address",address);
        contentValues.put("email",email);
        contentValues.put("contactno",contactno);

        long result = sqLiteDatabase.insert("user_info",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
//    public boolean checkuser (String username){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        Cursor cursor =sqLiteDatabase.rawQuery("Select * from user_info where username = ? ", new String[] {username});
//        if(cursor.getCount() > 0){
//            return true;
//        }
//        else
//            return false;
//    }
    public boolean checkpassword (String username,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from user_info where username = ? and password = ? ", new String[] {username , password});
        if(cursor.getCount() > 0){
            return true;
        }
        else
            return false;
    }
}
