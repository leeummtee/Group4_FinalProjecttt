package com.example.group4_finalproject_iat359;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

//from lab 6 demo code
public class Helper extends SQLiteOpenHelper {
    private Context context;

    private static final String CREATE_TABLE =
            "CREATE TABLE "+
                    com.example.group4_finalproject_iat359.Constants.TABLE_NAME + " (" +
                    com.example.group4_finalproject_iat359.Constants.UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    com.example.group4_finalproject_iat359.Constants.KM + " TEXT, " +
                    com.example.group4_finalproject_iat359.Constants.KCAL + " TEXT, " +
                    com.example.group4_finalproject_iat359.Constants.STEPS + " TEXT);" ;

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + com.example.group4_finalproject_iat359.Constants.TABLE_NAME;

    public Helper(Context context) {
        super(context, com.example.group4_finalproject_iat359.Constants.DATABASE_NAME,null, com.example.group4_finalproject_iat359.Constants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
           db.execSQL(CREATE_TABLE);
           Toast.makeText(context, "onCreate() called", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(context, "exception onCreate() db", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            if (newVersion > oldVersion) {
                db.execSQL("ALTER TABLE TRACKINGTABLE ADD COLUMN Location DEFAULT 0");
                db.execSQL("ALTER TABLE TRACKINGTABLE ADD COLUMN LatinName DEFAULT 0");
            }
            db.execSQL(DROP_TABLE);
            onCreate(db);
            Toast.makeText(context, "onUpgrade called", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(context, "exception onUpgrade() db", Toast.LENGTH_LONG).show();
        }
    }
}
