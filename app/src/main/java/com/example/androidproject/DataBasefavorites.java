package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBasefavorites extends SQLiteOpenHelper {
    public DataBasefavorites(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE FavoriteCar(" +
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
        sqLiteDatabase.insert("FavoriteCar", null, contentValues);
    }

    public Cursor getAllFav() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM FavoriteCar", null);
    }

    public Cursor getCar(String id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM FavoriteCar WHERE id = ? ", new String[]{id});
    }

    public void removeCar(String id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM FavoriteCar WHERE id = ?", new String[]{id});
    }

    public void removeAllFavoriteCars() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM FavoriteCar");
    }
}
