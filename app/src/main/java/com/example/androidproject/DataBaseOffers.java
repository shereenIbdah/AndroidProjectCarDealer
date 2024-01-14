package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseOffers extends SQLiteOpenHelper {
    public DataBaseOffers(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE OffersCar(" +
                "id TEXT PRIMARY KEY," +
                "type TEXT," +
                "factoryname TEXT," +
                "model TEXT," +
                "price TEXT," +
                "name TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Handle upgrades if needed
    }
    public void insertCar(String id, String type, String factoryname, String model, String price, String name) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("type", type);
        contentValues.put("factoryname", factoryname);
        contentValues.put("model", model);
        contentValues.put("price", price);
        contentValues.put("name", name);
        sqLiteDatabase.insert("OffersCar", null, contentValues);
    }
    public Cursor getAllOffers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM OffersCar", null);
    }



}
