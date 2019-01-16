package com.khan.monis.carpooling;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBconnect extends SQLiteOpenHelper {

    public DBconnect(Context context) {
        super(context, "CarPooling" , null, 1);
    }

    public DBconnect getter(){
        return this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS user (" +
                "id INTEGER PRIMARY KEY," +
                "name VARCHAR," +
                "dob VARCHAR," +
                "email VARCHAR," +
                "password VARCHAR," +
                "verify VARCHAR," +
                "phone VARCHAR," +
                "rider VARCHAR" +
                ")");

        db.execSQL("CREATE TABLE IF NOT EXISTS ride (" +
                "id INTEGER PRIMARY KEY," +
                "did INT," +
                "source VARCHAR," +
                "destination VARCHAR," +
                "carName VARCHAR," +
                "carNum VARCHAR," +
                "totalSeat INT," +
                "availSeat INT," +
                "date VARCHAR," +
                "time VARCHAR," +
                "cost VARCHAR" +
                ")");

    }

    public boolean insertUser (String name, String dob, String email, String password,String verify, String phone, String rider) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("dob", dob);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("verify", verify);
        contentValues.put("phone", phone);
        contentValues.put("rider", rider);
        db.insert("user", null, contentValues);
        return true;
    }

    public boolean insertRide (int did, String source, String destination, String carName,String carNum, int totalSeat, int availSeat, String date, String time, String cost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("did", did);
        contentValues.put("source", source);
        contentValues.put("destination", destination);
        contentValues.put("carName", carName);
        contentValues.put("carNum", carNum);
        contentValues.put("totalSeat", totalSeat);
        contentValues.put("availSeat", availSeat);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("cost", cost);
        db.insert("ride", null, contentValues);
        return true;
    }

    public Cursor getData(String startLoc, String endLoc, String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM ride WHERE source = '"+startLoc+"' AND destination = '"+endLoc+"' AND date = '"+date+"'", null );
        return res;
    }

    public Cursor getUser(int did) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM ride WHERE id = "+did, null );
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
