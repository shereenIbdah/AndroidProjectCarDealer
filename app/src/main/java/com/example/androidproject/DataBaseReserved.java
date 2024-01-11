package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DataBaseReserved extends SQLiteOpenHelper {
    public DataBaseReserved(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE reservedCars(" +
                "id TEXT PRIMARY KEY," +
                "type TEXT," +
                "factoryname TEXT," +
                "model TEXT," +
                "price TEXT," +
                "name TEXT," +
                "time TEXT," +
                "date TEXT )");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Handle upgrades if needed
    }
    // Query to insert a reservation
    public void insertCar(String id, String type, String factoryname, String model, String price, String name) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Date date = new Date(System.currentTimeMillis());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        String formattedDate = dateFormat.format(date);
        String formattedTime = timeFormat.format(time);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("type", type);
        contentValues.put("factoryname", factoryname);
        contentValues.put("model", model);
        contentValues.put("price", price);
        contentValues.put("name", name);
        contentValues.put("time", formattedTime);
        contentValues.put("date", formattedDate);

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
        return sqLiteDatabase.rawQuery("SELECT * FROM reservedCars WHERE id = ? ", new String[]{id});
    }

    // Query to remove all reservations
    public void removeAllReservations() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM reservedCars");
    }
}
