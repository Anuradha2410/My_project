package com.example.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME ="record1.db";

    public DBHelper(Context context) {
        super(context, "record1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table user_info(username TEXT primary key, password TEXT, address TEXT,email TEXT, contactno INT )");
        sqLiteDatabase.execSQL("create Table food_info(image INT primary key, name TEXT, price TEXT,description TEXT )");
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
    public  boolean additem(int image,String name,String price,String description) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("image", image);
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("description", description);
        long res = sqLiteDatabase.insert("food_info", null, contentValues);
        if (res == -1) {
            return false;
        } else {
            return false;
        }
    }
    public ArrayList<foodinfo> getitems(){
        ArrayList<foodinfo> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        String select="SELECT * FROM food_info";
        Cursor cursor=sqLiteDatabase.rawQuery(select,null);

        if(cursor.moveToFirst()){
            do{
                foodinfo foodinfo=new foodinfo();
//                foodinfo.setImage(Integer.parseInt(cursor.getString(0)));
                foodinfo.setName(cursor.getString(1));
                foodinfo.setPrice(cursor.getString(2));
                foodinfo.setDescription(cursor.getString(3));
                list.add(foodinfo);
            }while (cursor.moveToNext());
        }
        return list;
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
