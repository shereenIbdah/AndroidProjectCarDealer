package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper  extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create data base to store the email and password
       sqLiteDatabase.execSQL("Create table user (email text primary key , password text, FirstName text , LastName text , PhoneNumber text , Gender text,Country text , City text )");
       // SAJA SHAREEF
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }


    //query to insert the email and password
    public void insertUser(String email,String password,String FirstName, String LastName , String PhoneNumber, String Gender , String Country , String City) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("FirstName", FirstName);
        contentValues.put("LastName", LastName);
        contentValues.put("PhoneNumber", PhoneNumber);
        contentValues.put("Gender",Gender);
        contentValues.put("Country",Country);
        contentValues.put("City",City);
        sqLiteDatabase.insert("user", null, contentValues);
    }
    //query to get the email and password
    public Cursor getAllUsers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM user", null);
    }
    public Cursor getUser(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM user WHERE email = '" + email + "'", null);
    }
    // remove all the users from the data base
    public void removeAllUsers() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM user");
    }

}
