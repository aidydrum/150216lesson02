package com.CBA.B00627286;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: anon
 * Date: 08/07/13
 * Time: 13:18
 * UPDATE COMMENT ABOUT PROGRAM HERE
 */
public class RailDatabaseHelper extends SQLiteOpenHelper {
    public static String RAILDATABASENAME = "CBAsqliteRail";  // db Name
    public static String buscolId = "id";
    public static String buscolDate = "thedate";
    public static String buscolCost = "railCost";
    public static String buscolDistance = "railDistance";
    public static String buscolCostMile = "railCostMile";
    public String RAIL_RECORD_TABLE = "RailRecord";           // table name
    Context cntx;
    private ArrayList<RailDetails> database_List = new ArrayList<RailDetails>();

    //constructor
    public RailDatabaseHelper(Context context) {
        super(context, RAILDATABASENAME, null, 1);
        cntx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("SQLtest", "Creating SQLite table");
        db.execSQL("CREATE TABLE if not exists " + RAIL_RECORD_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, thedate TEXT, railCost TEXT, railDistance TEXT, railCostMile TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbBus, int oldVersion, int newVersion) {
        dbBus.execSQL("DROP TABLE IF EXISTS " + RAIL_RECORD_TABLE);
        onCreate(dbBus);
    }


    // get all products from database
    public ArrayList<RailDetails> get_All_User_Inputs_Rail() {
        database_List.clear();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + RAIL_RECORD_TABLE, null);

        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    RailDetails item = new RailDetails();
                    item.inputDate = cursor.getString(cursor.getColumnIndex("thedate"));
                    item.textCost = cursor.getString(cursor.getColumnIndex("railCost"));
                    item.distance = cursor.getString(cursor.getColumnIndex("railDistance"));
                    item.textCost = cursor.getString(cursor.getColumnIndex("railCostMile"));
                    database_List.add(item);

                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return database_List;
    }

    public void addProduct2Raildb(RailDetails item) {
        SQLiteDatabase dbBus = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("thedate", item.getInputDate());
        contentValues.put("railCost", item.getTextCost());
        contentValues.put("railDistance", item.getOutputMiles());
        contentValues.put("railCostMile", item.getOutputCostMiles());
        dbBus.insert(RAIL_RECORD_TABLE, null, contentValues);
        dbBus.close();
    }
}


