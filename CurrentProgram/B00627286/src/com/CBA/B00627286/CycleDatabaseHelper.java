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
public class CycleDatabaseHelper extends SQLiteOpenHelper
{
   public static String CYCLEDATABASENAME = "CBAsqliteCycle";  // db Name
   public String CYCLE_RECORD_TABLE = "CycleRecord";   // table name
   public static String cyclecolId = "id";
   public static String cyclecolDate = "thedate";
   public static String cyclecolCost = "cycleCost";
   public static String buscolDistance = "cycleDistance";
   public static String buscolCostMile = "cycleCostMile";

   private ArrayList<CycleDetails> database_List = new ArrayList<CycleDetails>();

   Context cntx;

   //constructor
   public CycleDatabaseHelper(Context context)
   {
      super(context, CYCLEDATABASENAME, null, 1);
      cntx = context;
   }

   @Override
   public void onCreate(SQLiteDatabase db)
   {
      Log.d("SQLtest", "Creating SQLite table");
      db.execSQL("CREATE TABLE if not exists " + CYCLE_RECORD_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, thedate TEXT, cycleCost TEXT cycleDistance TEXT, cycleCostMile TEXT)");
   }

   @Override
   public void onUpgrade(SQLiteDatabase dbcycle, int oldVersion, int newVersion)
   {
      dbcycle.execSQL("DROP TABLE IF EXISTS " + CYCLE_RECORD_TABLE);
      onCreate(dbcycle);
   }


   // get all products from database
   public ArrayList<CycleDetails> get_All_User_Inputs_Cycle()
   {
      database_List.clear();
      SQLiteDatabase dbc = this.getWritableDatabase();

      Cursor cursor = dbc.rawQuery("select * from " + CYCLE_RECORD_TABLE, null);

      if (cursor.getCount() != 0)
      {
         if (cursor.moveToFirst())
         {
            do
            {
               CycleDetails item = new CycleDetails();
               item.inputDate = cursor.getString(cursor.getColumnIndex("thedate"));
               item.textCost = cursor.getString(cursor.getColumnIndex("cycleCost"));
               item.distance = cursor.getString(cursor.getColumnIndex("cycleDistance"));
               item.textCost = cursor.getString(cursor.getColumnIndex("cycleCostMile"));
               database_List.add(item);

            } while (cursor.moveToNext());
         }
      }
      cursor.close();
      dbc.close();
      return database_List;
   }

   public void addProduct2Cycledb(CycleDetails item)
   {
      SQLiteDatabase dbCycle = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();

      contentValues.put("thedate", item.getInputDate());
      contentValues.put("cycleCost", item.getTextCost());
      contentValues.put("cycleDistance", item.getOutputMiles());
      contentValues.put("cycleCostMile", item.getOutputCostMiles());

      dbCycle.insert(CYCLE_RECORD_TABLE, null, contentValues);
      dbCycle.close();
   }
}


