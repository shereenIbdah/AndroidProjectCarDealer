package com.example.androidproject;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FavoriteDataBase extends SQLiteOpenHelper {
    public FavoriteDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create data base to store the email and password
        sqLiteDatabase.execSQL("Create table favorite (email text , carId text  , PRIMARY KEY (email,carId))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertFavorite(String email,String carId) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO favorite VALUES ('" + email + "','" + carId + "')");
    }

    public void removeAllFavorites() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM favorite");
    }
    //get all the favorites for a specific user
    public Cursor getAllFavorites(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM favorite WHERE email = '" + email + "'", null);
    }
    public boolean isFavorite(String email,String carId) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM favorite WHERE email = '" + email + "' AND carId = '" + carId + "'", null);
        return cursor.getCount() > 0;
    }
    //remove favorite for a specific user
    public void removeFavorite(String email,String carId) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM favorite WHERE email = '" + email + "' AND carId = '" + carId + "'");

    }


}
