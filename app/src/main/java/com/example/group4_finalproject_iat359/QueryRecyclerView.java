package com.example.group4_finalproject_iat359;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QueryRecyclerView extends Activity {
    RecyclerView queryRecycler;
    Adapter queryAdapter;
    MyDatabase database;
    Helper queryHelper;
    private Context context;
    public static final String DEFAULT = "not available";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queryRecycler = (RecyclerView) findViewById(R.id.queryRecycler);

        database = new MyDatabase(this);
        queryHelper = new Helper(this);

        SQLiteDatabase myDatabase = queryHelper.getWritableDatabase();

        SharedPreferences sharedPrefs = getSharedPreferences("MyQuery", Context.MODE_PRIVATE);
        String queryResults = sharedPrefs.getString("Query Result", DEFAULT);

        SharedPreferences sharedPrefs2 = getSharedPreferences("UserInput", Context.MODE_PRIVATE);
        String userInput = sharedPrefs.getString("User Input", DEFAULT);

        if (queryResults.equals(DEFAULT)) {
            Toast.makeText(this, "No query found. Return to main page and input data with that type or look for something else", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, queryResults, Toast.LENGTH_SHORT).show();

            SQLiteDatabase db = queryHelper.getWritableDatabase();
            String[] columns = {Constants.STEPS};

            String selection = Constants.STEPS + "='" +userInput+ "'";  //Constants.TYPE = 'type'
            Cursor cursor = db.query(Constants.TABLE_NAME, columns, selection, null, null, null, null);

            ArrayList<String> queryArrayList = new ArrayList<>();

            int index1 = cursor.getColumnIndex(Constants.STEPS);
//            int index2 = cursor.getColumnIndex(Constants.TYPE);
//            int index3 = cursor.getColumnIndex(Constants.LOCATION);
//            int index4 = cursor.getColumnIndex(Constants.LATIN_NAME);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String stepGoal = cursor.getString(index1);
//                String plantType = cursor.getString(index2);
//                String location = cursor.getString(index3);
//                String plantLatinName = cursor.getString(index4);

//                String s = stepGoal + ", " + plantType + ", " + location + ", " + plantLatinName;
                String s = stepGoal;
                queryArrayList.add(s);
                cursor.moveToNext();
            }
        }
    }
}

