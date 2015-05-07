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
public class WalkDatabaseHelper extends SQLiteOpenHelper {
    public static String WALKDATABASENAME = "CBAsqlitewalk";  // db Name
    public static String walkcolId = "id";
    public static String walkcolDate = "thedate";
    public static String walkcolCost = "walkCost";
    public String WALK_RECORD_TABLE = "WalkRecord";   // table name

    // public final String SQL_FUEL_USED_MAIN_VEH = "SELECT *, odometerRead - (SELECT odometerRead FROM PumpRecord WHERE id = (SELECT MAX(id) FROM PumpRecord WHERE ID < PRT.id) ) as MilesTravelled,julianday(thedate)- julianday((SELECT thedate FROM PumpRecord WHERE id = (SELECT MAX(id) FROM PumpRecord WHERE ID < PRT.id ))) as datediff, preTextGauge - (SELECT postTextGauge FROM PumpRecord WHERE id = (SELECT MAX(id) FROM PumpRecord WHERE ID < PRT.id )) as fuelUsed FROM PumpRecord PRT WHERE  vehicleID = 'Main Vehicle '";
    // public final String SQL_FUEL_USED_SECONDARY_VEH = "SELECT *, odometerRead - (SELECT odometerRead FROM PumpRecord WHERE id = (SELECT MAX(id) FROM PumpRecord WHERE ID < PRT.id) ) as MilesTravelled,julianday(thedate)- julianday((SELECT thedate FROM PumpRecord WHERE id = (SELECT MAX(id) FROM PumpRecord WHERE ID < PRT.id ))) as datediff, preTextGauge - (SELECT postTextGauge FROM PumpRecord WHERE id = (SELECT MAX(id) FROM PumpRecord WHERE ID < PRT.id )) as fuelUsed FROM PumpRecord PRT WHERE  vehicleID = 'Secondary Vehicle '";
    Context cntx;
    private ArrayList<WalkDetails> database_List = new ArrayList<WalkDetails>();

    //constructor
    public WalkDatabaseHelper(Context context) {
        super(context, WALKDATABASENAME, null, 1);
        cntx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("SQLtest", "Creating SQLite table");
        db.execSQL("CREATE TABLE if not exists " + WALK_RECORD_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, thedate TEXT, walkCost TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbWalk, int oldVersion, int newVersion) {
        dbWalk.execSQL("DROP TABLE IF EXISTS " + WALK_RECORD_TABLE);
        onCreate(dbWalk);
    }


    // get all products from database
    public ArrayList<WalkDetails> get_All_User_Inputs_Walk() {
        database_List.clear();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + WALK_RECORD_TABLE, null);

        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    WalkDetails item = new WalkDetails();
                    item.inputDate = cursor.getString(cursor.getColumnIndex("thedate"));
                    item.textCost = cursor.getString(cursor.getColumnIndex("walkCost"));
                    database_List.add(item);

                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return database_List;
    }

    // remove specific record from table
    public void removeProduct(String thedate, String p_car_ID, String p_miles) {
        try {
            String[] args =
                    {
                            thedate
                    };
            getWritableDatabase().delete(WALK_RECORD_TABLE, walkcolId, args);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // delete entire data from table
    public void emptyProduct() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("delete from " + WALK_RECORD_TABLE);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProduct2Walkdb(WalkDetails item) {
        SQLiteDatabase dbWalk = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("thedate", item.getInputDate());
        contentValues.put("walkCost", item.getTextCost());

        dbWalk.insert(WALK_RECORD_TABLE, null, contentValues);
        dbWalk.close();
    }
}


