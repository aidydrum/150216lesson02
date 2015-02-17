package com.CBA.B00627286;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: anon
 * Date: 08/07/13
 * Time: 13:18
 * UPDATE COMMENT ABOUT PROGRAM HERE
 */
public class BusDatabaseHelper extends SQLiteOpenHelper
{
   public static String BUSDATABASENAME = "CBAsqlitebus";  // db Name
   public String BUS_RECORD_TABLE = "BusRecord";   // table name
   public static String buscolId = "id";
   public static String buscolDate = "thedate";
   public static String buscolCost = "busCost";
   public static String buscolDistance = "busDistance";
   public static String buscolCostMile = "busCostMile";

   private ArrayList<BusDetails> database_List = new ArrayList<BusDetails>();

   Context cntx;

   //constructor
   public BusDatabaseHelper(Context context)
   {
      super(context, BUSDATABASENAME, null, 1);
      cntx = context;
   }

   @Override
   public void onCreate(SQLiteDatabase db)
   {
      Log.d("SQLtest", "Creating SQLite table");
      db.execSQL("CREATE TABLE if not exists " + BUS_RECORD_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, thedate TEXT, busCost TEXT, busDistance TEXT, busCostMile TEXT)");
   }

   @Override
   public void onUpgrade(SQLiteDatabase dbBus, int oldVersion, int newVersion)
   {
      dbBus.execSQL("DROP TABLE IF EXISTS " + BUS_RECORD_TABLE);
      onCreate(dbBus);
   }


   // get all products from database
   public ArrayList<BusDetails> get_All_User_Inputs_Bus()
   {
      database_List.clear();
      SQLiteDatabase db = this.getWritableDatabase();

      Cursor cursor = db.rawQuery("select * from " + BUS_RECORD_TABLE, null);

      if (cursor.getCount() != 0)
      {
         if (cursor.moveToFirst())
         {
            do
            {
               BusDetails item = new BusDetails();
               item.inputDate = cursor.getString(cursor.getColumnIndex("thedate"));
               item.textCost = cursor.getString(cursor.getColumnIndex("busCost"));
               item.distance = cursor.getString(cursor.getColumnIndex("busDistance"));
               item.textCost = cursor.getString(cursor.getColumnIndex("busCostMile"));

               database_List.add(item);

            } while (cursor.moveToNext());
         }
      }
      cursor.close();
      db.close();
      return database_List;
   }

public void addProduct2Busdb(BusDetails item)
   {
      SQLiteDatabase dbBus = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();

      contentValues.put("thedate", item.getInputDate());
      contentValues.put("busCost", item.getTextCost());
      contentValues.put("busDistance", item.getOutputMiles());
      contentValues.put("busCostMile", item.getOutputCostMiles());

      dbBus.insert(BUS_RECORD_TABLE, null, contentValues);
      dbBus.close();
   }
}


