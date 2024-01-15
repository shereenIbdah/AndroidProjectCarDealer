package com.example.androidproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ReservationDataBase extends DataBaseHelper {
    public ReservationDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create data base to store the email and password
        sqLiteDatabase.execSQL("Create table reservation (email text , carId text , date text , time text , PRIMARY KEY (email,carId,date,time))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertReservation(String email,String carId,String date,String time) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO reservation VALUES ('" + email + "','" + carId + "','" + date + "','" + time + "')");
    }
    public void removeReservation(String email,String carId,String date,String time) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM reservation WHERE email = '" + email + "' AND carId = '" + carId + "' AND date = '" + date + "' AND time = '" + time + "'");
    }
    public void removeAllReservations() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM reservation");
    }
    //get all the reservations for a specific user
    public Cursor getAllReservations(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM reservation WHERE email = '" + email + "'", null);
    }
    public boolean isReserved(String email,String carId) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM reservation WHERE email = '" + email + "' AND carId = '" + carId + "'", null);
        return cursor.getCount() > 0;
    }
    //remove reservation for a specific user
    public void removeReservation(String email,String carId) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM reservation WHERE email = '" + email + "' AND carId = '" + carId + "'");
    }

}
