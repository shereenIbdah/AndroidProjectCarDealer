package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminDataBase extends SQLiteOpenHelper {
    public AdminDataBase(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create data base to store the email and password
        sqLiteDatabase.execSQL("Create table admin (email text primary key , password text, FirstName text , LastName text , PhoneNumber text , Gender text,Country text , City text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }


    //query to insert the email and password
    public void insertAdmin(String email, String password, String FirstName, String LastName, String PhoneNumber, String Gender, String Country, String City) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("FirstName", FirstName);
        contentValues.put("LastName", LastName);
        contentValues.put("PhoneNumber", PhoneNumber);
        contentValues.put("Gender", Gender);
        contentValues.put("Country", Country);
        contentValues.put("City", City);
        sqLiteDatabase.insert("admin", null, contentValues);

    }

    //query to get the email and password
    public Cursor getAllUAdmins() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM admin", null);
    }

    public Cursor getAdmin(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM admin WHERE email = '" + email + "'", null);
    }
}