package com.example.group4_finalproject_iat359;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//this class is based on the recyclerview database lab activity
public class Database {
    private Context context;
    private final Helper helper;

    public Database (Context c) {
        context = c;
        helper = new Helper(context);
    }

    public long insertData (String km, String kcal, String time, String steps) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(com.example.group4_finalproject_iat359.Constants.KM, km);
        contentValues.put(com.example.group4_finalproject_iat359.Constants.KCAL, kcal);
        contentValues.put(com.example.group4_finalproject_iat359.Constants.STEPS, steps);
        long id = db.insert(com.example.group4_finalproject_iat359.Constants.TABLE_NAME, null, contentValues);
        return id;
    }

    public Cursor getData() {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {com.example.group4_finalproject_iat359.Constants.UID, com.example.group4_finalproject_iat359.Constants.KM, com.example.group4_finalproject_iat359.Constants.KCAL, com.example.group4_finalproject_iat359.Constants.STEPS};
        Cursor cursor = db.query(com.example.group4_finalproject_iat359.Constants.TABLE_NAME, columns, null, null, null, null, null);
        return cursor;
    }

    public String getSelectedData(String data) {
        //select data from database based on steps
        // CAN CHANGE TO SOMETHING ELSE IF NEEDED
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {com.example.group4_finalproject_iat359.Constants.KM, com.example.group4_finalproject_iat359.Constants.KCAL, com.example.group4_finalproject_iat359.Constants.STEPS};

        String selection = com.example.group4_finalproject_iat359.Constants.STEPS + "='" + data + "'"; // Constants.STEPS = 'steps';
        Cursor cursor = db.query(com.example.group4_finalproject_iat359.Constants.TABLE_NAME, columns, selection, null, null, null, null);

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            int index1 = cursor.getColumnIndex(com.example.group4_finalproject_iat359.Constants.KM);
            int index2 = cursor.getColumnIndex(com.example.group4_finalproject_iat359.Constants.KCAL);
            int index3 = cursor.getColumnIndex(com.example.group4_finalproject_iat359.Constants.STEPS);

            String km = cursor.getString(index1);
            String kcal = cursor.getString(index2);
            String steps = cursor.getString(index3);

            buffer.append(km + " " + kcal + " " +  " " + steps + "\n");
        }
        return buffer.toString();
    }

}
