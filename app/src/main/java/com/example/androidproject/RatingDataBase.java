package com.example.androidproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RatingDataBase extends UserDataBase {
    public RatingDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create data base to store the email and password
        sqLiteDatabase.execSQL("Create table rate (email text , carId text , rating float , comment text, primary key (email , carId))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
    public void addRate(String email , String carId , float rating , String comment){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("insert into rate values ('"+email+"' , '"+carId+"' , '"+rating+"' , '"+comment+"')");
    }
    public boolean isExist(String email , String carId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from rate where email = '"+email+"' and carId = '"+carId+"'" , null);
        if(cursor.getCount() == 0){
            return false;
        }
        return true;
    }
    public void updateRate(String email , String carId , float rating ){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("update rate set rating = '"+rating+"' where email = '"+email+"' and carId = '"+carId+"'");
    }
    // return the rating of the car
    public float getRating(String carId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from rate where carId = '"+carId+"'" , null);
        float sum = 0;
        int count = 0;
        while(cursor.moveToNext()){
            sum += cursor.getFloat(2);
            count++;
        }
        if(count == 0){
            return 0;
        }
        // return the average of the rating as percentage
        return (sum / count) * 20;


    }
    //get rating of the user
    public float getRating2(String email , String carId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from rate where email = '"+email+"' and carId = '"+carId+"'" , null);
        if(cursor.getCount() == 0){
            return 0;
        }
        cursor.moveToNext();
        return cursor.getFloat(2);
    }
  // update the comment for user on car
    public void updateComment(String email , String carId , String comment){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("update rate set comment = '"+comment+"' where email = '"+email+"' and carId = '"+carId+"'");
    }
    // get the comment of the user on the car
    public String getComment(String email , String carId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from rate where email = '"+email+"' and carId = '"+carId+"'" , null);
        if(cursor.getCount() == 0){
            return "";
        }
        cursor.moveToNext();
        return cursor.getString(3);
    }

}
