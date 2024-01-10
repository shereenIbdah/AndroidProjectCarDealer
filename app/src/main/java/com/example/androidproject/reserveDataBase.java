package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class reserveDataBase extends SQLiteOpenHelper {
    public List<Car> cars;

    public reserveDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create data base to store the email and password
        sqLiteDatabase.execSQL("CREATE TABLE reservedCars (id TEXT PRIMARY KEY, type TEXT, model TEXT, price TEXT, name TEXT, time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Handle upgrades if needed
    }

    // Query to insert a reservation
    public void insertCar(String type, String model, String price, String name, String time) {
        cars = MainActivity.cars;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", cars.get(0).getId());
        contentValues.put("type", type);
        contentValues.put("model", model);
        contentValues.put("price", price);
        contentValues.put("name", name);
        contentValues.put("time", time);
        sqLiteDatabase.insert("reservedCars", null, contentValues);
    }

    // Query to get all reservations
    public Cursor getAllReservations() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM reservedCars", null);
    }

    // Query to get a specific car by ID
    public Cursor getCar(String id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM reservedCars WHERE id = ?", new String[]{id});
    }

    // Query to remove all reservations
    public void removeAllReservations() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM reservedCars");
    }
}
