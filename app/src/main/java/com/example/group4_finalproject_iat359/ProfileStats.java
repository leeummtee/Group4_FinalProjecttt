package com.example.group4_finalproject_iat359;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileStats extends MainActivity implements AdapterView.OnItemClickListener{
    RecyclerView myRecycler;
    MyDatabase db;
    Adapter myAdapter;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_stats);
        myRecycler = (RecyclerView) findViewById(R.id.recycler);

        db = new MyDatabase(this);
        helper = new Helper(this);

        SQLiteDatabase myDatabase = helper.getWritableDatabase();

        Cursor cursor = db.getData();

        int index1 = cursor.getColumnIndex(Constants.STEPS);
//        int index2 = cursor.getColumnIndex(Constants.TYPE);
//        int index3 = cursor.getColumnIndex(Constants.LOCATION);
//        int index4 = cursor.getColumnIndex(Constants.LATIN_NAME);

        ArrayList<String> mArrayList = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String stepsGoal = cursor.getString(index1);
//            String plantType = cursor.getString(index2);
//            String location = cursor.getString(index3);
//            String plantLatinName = cursor.getString(index4);
            String s = stepsGoal;
            mArrayList.add(s);
            cursor.moveToNext();
        }

        myAdapter = new Adapter(mArrayList);
        myRecycler.setAdapter(myAdapter);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LinearLayout clickedRow = (LinearLayout) view;
        TextView stepsTextView = (TextView) view.findViewById(R.id.stepsEntry);
        TextView kmTextView = (TextView) view.findViewById(R.id.kmEntry);
        TextView kcalTextView = (TextView) view.findViewById(R.id.kcalEntry);
        Toast.makeText(this, "row " + (1+position) + ":  " + stepsTextView.getText() +" "+kmTextView.getText() + " " + kcalTextView.getText(), Toast.LENGTH_LONG).show();
    }
}
