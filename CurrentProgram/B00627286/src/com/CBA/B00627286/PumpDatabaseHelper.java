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
public class PumpDatabaseHelper extends SQLiteOpenHelper
{
   public static String DATABASENAME = "CBAsqlite";  // db Name
   public String PUMP_RECORD_TABLE = "PumpRecord";   // table name

   public static String colId = "id";
   public static String colDate = "thedate";
   public static String colVehicleID = "vehicleID";
   public static String colOdometer = "odometerRead";
   public static String colPREGauge = "preTextGauge";
   public static String colPOSTGauge = "postTextGauge";
   public static String colCost = "fuelCost";
   public static String colLitres = "fuelLitres";

   // public final String SQL_FUEL_USED_MAIN_VEH = "SELECT *, odometerRead - (SELECT odometerRead FROM PumpRecord WHERE id = (SELECT MAX(id) FROM PumpRecord WHERE ID < PRT.id) ) as MilesTravelled,julianday(thedate)- julianday((SELECT thedate FROM PumpRecord WHERE id = (SELECT MAX(id) FROM PumpRecord WHERE ID < PRT.id ))) as datediff, preTextGauge - (SELECT postTextGauge FROM PumpRecord WHERE id = (SELECT MAX(id) FROM PumpRecord WHERE ID < PRT.id )) as fuelUsed FROM PumpRecord PRT WHERE  vehicleID = 'Main Vehicle '";
   // public final String SQL_FUEL_USED_SECONDARY_VEH = "SELECT *, odometerRead - (SELECT odometerRead FROM PumpRecord WHERE id = (SELECT MAX(id) FROM PumpRecord WHERE ID < PRT.id) ) as MilesTravelled,julianday(thedate)- julianday((SELECT thedate FROM PumpRecord WHERE id = (SELECT MAX(id) FROM PumpRecord WHERE ID < PRT.id ))) as datediff, preTextGauge - (SELECT postTextGauge FROM PumpRecord WHERE id = (SELECT MAX(id) FROM PumpRecord WHERE ID < PRT.id )) as fuelUsed FROM PumpRecord PRT WHERE  vehicleID = 'Secondary Vehicle '";

   private ArrayList<VehicleDetails> myNewList;


   private ArrayList<VehicleDetails> database_List = new ArrayList<VehicleDetails>();
   private ArrayList<VehicleDetails> calculated_Main_Litres_Used = new ArrayList<VehicleDetails>();
   private ArrayList<VehicleDetails> calculated_Secondary_Litres_Used = new ArrayList<VehicleDetails>();
   private ArrayList<VehicleDetails> calculated_Main_Miles = new ArrayList<VehicleDetails>();
   private ArrayList<VehicleDetails> calculated_Secondary_Miles = new ArrayList<VehicleDetails>();
   private ArrayList<VehicleDetails> cost_Main_Miles = new ArrayList<VehicleDetails>();
   private ArrayList<VehicleDetails> cost_Second_Miles = new ArrayList<VehicleDetails>();

   Context cntx;

   //constructor
   public PumpDatabaseHelper(Context context)
   {
      super(context, DATABASENAME, null, 1);
      cntx = context;
   }

   @Override
   public void onCreate(SQLiteDatabase db)
   {
      Log.d("SQLtest", "Creating SQLite table");
      db.execSQL("CREATE TABLE if not exists " + PUMP_RECORD_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, thedate TEXT, vehicleID TEXT, odometerRead TEXT, preTextGauge TEXT, postTextGauge TEXT, fuelCost TEXT, fuelLitres TEXT)");
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
   {
      Log.d("SQLtest", "ONUPGRADE IN PROGRESS");
      db.execSQL("DROP TABLE IF EXISTS " + PUMP_RECORD_TABLE);
      onCreate(db);
   }

   public void addProduct2db(VehicleDetails item)
   {  // this is called in PumpFuelGaugesActivity.java
      // for writing the users input to the db
      Log.d("SQLtest", "ADDING ITEMS TO db");
      SQLiteDatabase db = this.getWritableDatabase();
      // Creates an empty set of values using the default initial size
      ContentValues contentValues = new ContentValues();
      // gets item and puts to db
      contentValues.put("thedate", item.getInputDate());
      contentValues.put("vehicleID", item.getVehicleID());
      contentValues.put("odometerRead", item.getOdometerRead());
      contentValues.put("preTextGauge", item.getPreGauge());
      contentValues.put("postTextGauge", item.getPostTextGauge());
      contentValues.put("fuelCost", item.getTextCost());
      contentValues.put("fuelLitres", item.getTextLitres());
      //contentValues.put("fuelUsed", item.outputFuelUsed);
      //contentValues.put("milesTravelled", item.outputMiles);

      // insert to pump record table
      // public long insert(java.lang.String table, java.lang.String nullColumnHack, android.content.ContentValues values)
      db.insert(PUMP_RECORD_TABLE, null, contentValues);
      // close the db
      db.close();
   }

   // get all user input from database (called in ViewRecordActivity.java to assign
   public ArrayList<VehicleDetails> get_All_User_Inputs()
   {  // clear the list
      database_List.clear();
      // assigns the readable / writable database to db
      SQLiteDatabase db = this.getWritableDatabase();
      // Cursor provides read-write access to the results returned the below database query.
      Cursor cursor = db.rawQuery("select * from " + PUMP_RECORD_TABLE, null);
      // if there are values in the database
      // 12.11.14 introduced the below to try and get cursor set to zero.
      //cursor.moveToFirst();
      if (cursor.getCount() != 0)
      {  // position the cursor at index position Zero
         if (cursor.moveToNext())
         {
            do
            {  // VehicleDetails objects
               // use the cursor to get the string in the column index of "outlined" db table components
               // and assign got string to the 'item . required value'
               VehicleDetails item = new VehicleDetails();
               item.inputDate = cursor.getString(cursor.getColumnIndex("thedate"));
               item.vehicleID = cursor.getString(cursor.getColumnIndex("vehicleID"));
               item.odometerRead = cursor.getString(cursor.getColumnIndex("odometerRead"));
               item.pretextGauge = cursor.getString(cursor.getColumnIndex("preTextGauge"));
               item.postTextGauge = cursor.getString(cursor.getColumnIndex("postTextGauge"));
               item.textCost = cursor.getString(cursor.getColumnIndex("fuelCost"));
               item.textLitres = cursor.getString(cursor.getColumnIndex("fuelLitres"));
               // add these items to the database_list
               database_List.add(item);
               // while there is another cursor index position to move to, move to it
            } while (cursor.moveToNext());
         }
      }
      // turn off the cursor
      // close the database
      cursor.close();
      db.close();
      // return the database_List to ViewRecordActivity.java to equal ArrayList<VehicleDetails> get_list
      return database_List;
   } // get all user input

   public ArrayList<VehicleDetails> Result_Litres_Used_Main_Vehicle()
   {  // clear the calculated list
      calculated_Main_Litres_Used.clear();
      // assigns the readable / writable database to db
      SQLiteDatabase db = this.getWritableDatabase();

      // Cursor provides read-write access to the results returned the below database query.
      Cursor cursor = db.rawQuery("select * from " + PUMP_RECORD_TABLE + " WHERE vehicleID = 'Main Vehicle'", null);
      // here is where the mystery space can be seen, on the end of Main Vehicle.  This is curable with .trim()
      // located in the get and set constructor class of VehicleDetails.
      // This has been left in to highlight the importance of being able to see the database & what is in it
      // refer to Dissertation 5.1 for discussion.
      int recordCheck = cursor.getCount();

      //if (cursor.getCount() > 0)
       if (cursor.getCount() != 0)
       {
         //if (cursor.moveToPosition(cursorMover))
           if (cursor.moveToFirst())
           {
            // cursor.moveToPrevious();
            // Cursor is set to start at index pos 0
            myNewList = new ArrayList<VehicleDetails>();
            VehicleDetails currentItem = new VehicleDetails(); // look at current index
            VehicleDetails nextItem = new VehicleDetails();    // look to next index

            VehicleDetails sendingCalc = new VehicleDetails();

            //nextItem.outputLitreUsed = 0.00;                   // set to Zero as no calculation is possible in index 0
            //calculated_Main_Litres_Used.add(currentItem);      // add this to the list

            // position the cursor at index position Zero
            do
            {
               // #1 columnDate assigned the cursor index position of databases colDate location
               int columnDate = cursor.getColumnIndex(colDate);
               // #2 then set the input date to the string value of that columnDate
               currentItem.setInputDate(cursor.getString(columnDate));
               // #1 ...
               int columnVehicle = cursor.getColumnIndex(colVehicleID);
               int columnPreGauge = cursor.getColumnIndex(colPREGauge);
               int columnPostGauge = cursor.getColumnIndex(colPOSTGauge);
               int columnCost = cursor.getColumnIndex(colCost);
               // litres
               int columnLitres = cursor.getColumnIndex(colLitres);
               // #2 ...
               currentItem.setVehicleID(cursor.getString(columnVehicle));
               currentItem.setPreTextGauge(cursor.getString(columnPreGauge));
               currentItem.setPostTextGauge(cursor.getString(columnPostGauge));
               currentItem.setTextCost(cursor.getString(columnCost));
               currentItem.setTextLitres(cursor.getString(columnLitres));
               try
               {  // try catch is used as there is possibility of record reaching
                  // unrecorded row position, which would throw a NPE
                  // cursor move to the next row in the db ..ie next record
                  cursor.moveToNext();
                  // now that the cursor has moved the cursor is looking
                  // at the next item
                  // which the cursor gets the String value of the columnValue
                  // and assigns it the nextItem . vehicleDetail Value
                  nextItem.vehicleID = cursor.getString(columnVehicle);
                  nextItem.pretextGauge = cursor.getString(columnPreGauge);
                  nextItem.postTextGauge = cursor.getString(columnPostGauge);
                  nextItem.textCost = cursor.getString(columnCost);
                  nextItem.textLitres = cursor.getString(columnLitres);
                  // having achieved these values the calculations can be performed:
                  // get the fuel gauge pin position moved between fills I.E. Between Current PRE fill & Current POST fill
                  double pinMoves = Double.parseDouble(currentItem.getPostTextGauge()) - Double.parseDouble(currentItem.getPreGauge());
                  // then calculate how many pins per fuel tanks litre
                  double pinsPerLitre = pinMoves / Double.parseDouble(currentItem.getTextLitres());
                  // then calculate how many pins have been used up between fuel refills - leading to fuel used here
                  double pinsUsed = Double.parseDouble(currentItem.getPostTextGauge()) - Double.parseDouble(nextItem.getPreGauge());
                  // calculate how many litres of fuel has been used
                  double litresUsed = pinsUsed / pinsPerLitre;
                  //double costPerLitre = Double.parseDouble(currentItem.getOutputMiles()) / litresUsed;

                  // output the litres used to string and
                  nextItem.outputLitreUsed = Double.parseDouble(Integer.toString((int) litresUsed));
                  // nextItem.outputFuelUsed = Double.parseDouble(Integer.toString((int)costPerLitre));
                  // add the calculated value of litres used to calculated list
                  //                       // 10 miles / £5 x days
                  // costPerMile = String.valueOf((dist / cost) *1);
                  calculated_Main_Litres_Used.add(nextItem);
                  cursor.moveToPrevious();
                  //cursorMover++;
               } catch (Exception e)
               {
                  Log.d("Test", "Main @ Calc Main Litres Used");
               }
               // while the cursor can move to next position (!null)
            } while (cursor.moveToNext());
         }
      }
      // turn the cursor off
      // close the database
      cursor.close();
      db.close();
      // send the calculated list to ViewRecordActivity.java
      return calculated_Main_Litres_Used;
   } // get litres used main vehicle

   public ArrayList<VehicleDetails> Result_Litres_Used_Secondary()
   {  // clear the calculated list
      calculated_Secondary_Litres_Used.clear();
      // assigns the readable / writable database to db
      SQLiteDatabase db = this.getWritableDatabase();

      // Cursor provides read-write access to the results returned the below database query.
      Cursor cursor = db.rawQuery("select * from " + PUMP_RECORD_TABLE + " WHERE vehicleID = 'Secondary Vehicle'", null);
      int recordCheck = cursor.getCount();

      if (cursor.getCount() > 0)
      {
         if (cursor.moveToPosition(0))
         {  // Cursor is set to start at index pos 0
            VehicleDetails currentItem = new VehicleDetails(); // look at current index
            VehicleDetails nextItem = new VehicleDetails();    // look to next index

            nextItem.outputLitreUsed = 0.00;                   // set to Zero as no calculation is possible in index 0
            calculated_Secondary_Litres_Used.add(currentItem);      // add this to the list

            // position the cursor at index position Zero
            do
            {
               // #1 columnDate assigned the cursor index position of databases colDate location
               int columnDate = cursor.getColumnIndex(colDate);
               // #2 then set the input date to the string value of that columnDate
               currentItem.setInputDate(cursor.getString(columnDate));
               // #1 ...
               int columnPreGauge = cursor.getColumnIndex(colPREGauge);
               int columnPostGauge = cursor.getColumnIndex(colPOSTGauge);
               int columnLitres = cursor.getColumnIndex(colLitres);
               // #2 ...
               currentItem.setPreTextGauge(cursor.getString(columnPreGauge));
               currentItem.setPostTextGauge(cursor.getString(columnPostGauge));
               currentItem.setTextLitres(cursor.getString(columnLitres));
               try
               {  // try catch is used as there is possibility of record reaching
                  // unrecorded row position, which would throw a NPE
                  // cursor move to the next row in the db ..ie next record
                  cursor.moveToNext();
                  // now that the cursor has moved the cursor is looking
                  // at the next item
                  // which the cursor gets the String value of the columnValue
                  // and assigns it the nextItem . vehicleDetail Value
                  nextItem.pretextGauge = cursor.getString(columnPreGauge);
                  nextItem.postTextGauge = cursor.getString(columnPostGauge);
                  nextItem.textLitres = cursor.getString(columnLitres);
                  // having achieved these values the calculations can be performed:
                  // get the fuel gauge pin position moved between fills I.E. Between Current PRE fill & Current POST fill
                  double pinMoves = Double.parseDouble(currentItem.getPostTextGauge()) - Double.parseDouble(currentItem.getPreGauge());
                  // then calculate how many pins per fuel tanks litre
                  double pinsPerLitre = pinMoves / Double.parseDouble(currentItem.getTextLitres());
                  // then calculate how many pins have been used up between fuel refills - leading to fuel used here
                  double pinsUsed = Double.parseDouble(currentItem.getPostTextGauge()) - Double.parseDouble(nextItem.getPreGauge());
                  // calculate how many litres of fuel has been used
                  double litresUsed = pinsUsed / pinsPerLitre;
                  // output the litres used to string and
                  nextItem.outputLitreUsed = Double.parseDouble(Integer.toString((int) litresUsed));
                  // nextItem.outputFuelUsed = Double.parseDouble(Integer.toString((int)costPerLitre));
                  // add the calculated value of litres used to calculated list
                  calculated_Secondary_Litres_Used.add(nextItem);
               } catch (Exception e)
               {
                  Log.d("Test", "Second @ Calc Main Litres Used");
               }
               // while the cursor can move to next position (!null)
            } while (cursor.moveToNext());
         }
      }
      // turn the cursor off
      // close the database
      cursor.close();
      db.close();
      // send the calculated list to ViewRecordActivity.java
      return calculated_Secondary_Litres_Used;
   } // get litres used secondary

   public ArrayList<VehicleDetails> milesTravelled_Main_Vehicle()
   {// clear the calculated list
      calculated_Main_Miles.clear();
      // assigns the readable / writable database to db
      SQLiteDatabase db = this.getWritableDatabase();

      // Cursor provides read-write access to the results returned the below database query.
      Cursor cursor = db.rawQuery("select * from " + PUMP_RECORD_TABLE + " WHERE vehicleID = 'Main Vehicle'", null);
      // here is where the mystery space can be seen, on the end of Main Vehicle.  This is curable with .trim()
      // located in the get and set constructor class of VehicleDetails.
      // This has been left in to highlight the importance of being able to see the database & what is in it
      // refer to Dissertation 5.1 for discussion.
      int recordCheck = cursor.getCount();
      if (cursor.getCount() > 0)
      {
         if (cursor.moveToPosition(0))
         {  // Cursor is set to start at index pos 0
            VehicleDetails currentItem = new VehicleDetails(); // look at current index
            VehicleDetails nextItem = new VehicleDetails();    // look to next index

            nextItem.outputMiles = 0.00;                 // set to Zero as no calculation is possible in index 0
            calculated_Main_Miles.add(currentItem);      // add this to the list

            do
            {  // assign the sought item (columnValue) to the cursors index position in the databases required col location
               int columnMiles = cursor.getColumnIndex(colOdometer);
               // then set the input value to the string value of that column value
               currentItem.setOdometerRead(cursor.getString(columnMiles));
               try
               {
                  cursor.moveToNext();
                  nextItem.odometerRead = cursor.getString(columnMiles);

                  double next_miles = Double.parseDouble(nextItem.getOdometerRead());
                  double current_miles = Double.parseDouble(currentItem.getOdometerRead());
                  double miles_answer = next_miles - current_miles;

                  nextItem.outputMiles = Double.parseDouble(Integer.toString((int) miles_answer));
                  // add the calculated value to calculated list
                  calculated_Main_Miles.add(nextItem);
               } catch (Exception e)
               {
                  Log.d("Test", "Main @ Calc Main Miles");
               }
               // while the cursor can move to next position (!null)
            } while (cursor.moveToNext());
         }
      }
      // turn the cursor off
      // close the databse
      cursor.close();
      db.close();
      // send the calculated list to the
      return calculated_Main_Miles;
   } // get Main Miles

   public ArrayList<VehicleDetails> milesTravelled_Second_Vehicle()
   {  // clear the calculated list
      calculated_Secondary_Miles.clear();
      // assigns the readable / writable database to db
      SQLiteDatabase db = this.getWritableDatabase();

      int quickCheck = 0;
      // quickcheck was for testing to ensure algorithm was working
      // Cursor provides read-write access to the results returned the below database query.
      Cursor cursor = db.rawQuery("select * from " + PUMP_RECORD_TABLE + " WHERE vehicleID = 'Secondary Vehicle'", null);
      // here is where the mystery space was, on the end of Main Vehicle.  This was curable with .trim()
      // located in the get and set constructor class of VehicleDetails.
      // This has been left in to highlight the importance of being able to see the database & what is in it
      // refer to Dissertation 5.1 for discussion.
      int recordCheck = cursor.getCount();
      if (cursor.getCount() > 0)
      {
         if (cursor.moveToPosition(0))
         {  // Cursor is set to start at index pos 0
            VehicleDetails currentItem = new VehicleDetails(); // look at current index
            VehicleDetails nextItem = new VehicleDetails();    // look to next index

            nextItem.outputMiles = 0.00;                 // set to Zero as no calculation is possible in index 0
            calculated_Secondary_Miles.add(currentItem); // add this to the list

            // position the cursor at index position Zero
            do
            {  // assign the sought item (columnValue) to the cursors index position in the databases required col location
               int columnMiles = cursor.getColumnIndex(colOdometer);
               // then set the input value to the string value of that column value
               currentItem.setOdometerRead(cursor.getString(columnMiles));
               try
               {
                  cursor.moveToNext();
                  nextItem.odometerRead = cursor.getString(columnMiles);

                  double next_miles = Double.parseDouble(nextItem.getOdometerRead());
                  double current_miles = Double.parseDouble(currentItem.getOdometerRead());
                  double miles_answer = next_miles - current_miles;

                  nextItem.outputMiles = Double.parseDouble(Integer.toString((int) miles_answer));
                  // add the calculated value to calculated list
                  calculated_Secondary_Miles.add(nextItem);
               } catch (Exception e)
               {
                  Log.d("Test", "Second @ Calc Main Miles");
               }
               // while the cursor can move to next position (!null)
            } while (cursor.moveToNext());
         }
      }
      // turn the cursor off
      // close the databse
      cursor.close();
      db.close();
      // send the calculated list to the
      return calculated_Secondary_Miles;
   }  // Get Second Miles

   // get cost per mile
   public ArrayList<VehicleDetails> Cost_Per_Mile_Main_Vehicle()
   {  // clear the calculated list
      cost_Main_Miles.clear();
      // assigns the readable / writable database to db
      SQLiteDatabase db = this.getWritableDatabase();

      // Cursor provides read-write access to the results returned the below database query.
      Cursor cursor = db.rawQuery("select * from " + PUMP_RECORD_TABLE + " WHERE vehicleID = 'Main Vehicle'", null);
      // here is where the mystery space can be seen, on the end of Main Vehicle.  This is curable with .trim()
      // located in the get and set constructor class of VehicleDetails.
      // This has been left in to highlight the importance of being able to see the database & what is in it
      // refer to Dissertation 5.1 for discussion.
      int recordCheck = cursor.getCount();
      if (cursor.getCount() > 0)
      {
         if (cursor.moveToPosition(0))
         {  // Cursor is set to start at index pos 0
            VehicleDetails currentItem = new VehicleDetails(); // look at current index
            VehicleDetails nextItem = new VehicleDetails();    // look to next index

            nextItem.outputCostPerMile = 0.00;     // set to Zero as no calculation is possible in index 0
            cost_Main_Miles.add(currentItem);      // add this to the list

            // position the cursor at index position Zero
            do
            {  // #1 columnDate assigned the cursor index position of databases colDate location
               int columnDate = cursor.getColumnIndex(colDate);
               // #2 then set the input date to the string value of that columnDate
               currentItem.setInputDate(cursor.getString(columnDate));
               // #1 ...
               int columnMiles = cursor.getColumnIndex(colOdometer);
               int columnPreGauge = cursor.getColumnIndex(colPREGauge);
               int columnPostGauge = cursor.getColumnIndex(colPOSTGauge);
               int columnCost = cursor.getColumnIndex(colCost);
               // litres
               int columnLitres = cursor.getColumnIndex(colLitres);
               // #2 ...
               currentItem.setOdometerRead(cursor.getString(columnMiles));
               currentItem.setPreTextGauge(cursor.getString(columnPreGauge));
               currentItem.setPostTextGauge(cursor.getString(columnPostGauge));
               currentItem.setTextCost(cursor.getString(columnCost));
               currentItem.setTextLitres(cursor.getString(columnLitres));
               try
               { // try catch is used as there is possibility of record reaching
                  // unrecorded row position, which would throw a NPE
                  // cursor move to the next row in the db ..ie next record
                  cursor.moveToNext();
                  // now that the cursor has moved the cursor is looking
                  // at the next item
                  // which the cursor gets the String value of the columnValue
                  // and assigns it the nextItem . vehicleDetail Value
                  nextItem.odometerRead = cursor.getString(columnMiles);
                  nextItem.pretextGauge = cursor.getString(columnPreGauge);
                  nextItem.postTextGauge = cursor.getString(columnPostGauge);
                  nextItem.textCost = cursor.getString(columnCost);
                  nextItem.textLitres = cursor.getString(columnLitres);
                  // having achieved these values the calculations can be performed:
                  // get the fuel gauge pin position moved between fills I.E. Between Current PRE fill & Current POST fill
                  double pinMoves = Double.parseDouble(currentItem.getPostTextGauge()) - Double.parseDouble(currentItem.getPreGauge());

                  double pinsPerLitre = pinMoves / Double.parseDouble(currentItem.getTextLitres());
                  double pinsUsed = Double.parseDouble(currentItem.getPostTextGauge()) - Double.parseDouble(nextItem.getPreGauge());
                  double litresUsed = pinsUsed / pinsPerLitre;
                  double cost = Double.parseDouble(currentItem.getTextCost());
                  double volume = Double.parseDouble(currentItem.getTextLitres());
                  double costPerLitre = cost / volume;
                  double fuelCostUsed = litresUsed * costPerLitre;
                  double next_miles = Double.parseDouble(nextItem.getOdometerRead());
                  double current_miles = Double.parseDouble(currentItem.getOdometerRead());
                  double miles_answer = next_miles - current_miles;
                  double costPerMile = miles_answer / fuelCostUsed;

                  // output the cost per mile to string and
                  nextItem.outputCostPerMile = Double.parseDouble(Integer.toString((int) costPerMile));
                  //nextItem.outputFuelUsed = Double.parseDouble(Integer.toString((int)costPerLitre));
                  // add the calculated value of litres used to calculated list
                  cost_Main_Miles.add(nextItem);

               } catch (Exception e)
               {
                  Log.d("Test", "Main @ Calc Cost Per Mile");
               }
               // while the cursor can move to next position (!null)
            } while (cursor.moveToNext());
         }
      }
      // turn the cursor off
      // close the database
      cursor.close();
      db.close();
      // send the calculated list to ViewRecordActivity.java
      return cost_Main_Miles;
   } // get cost per mile

   // get cost per mile
   public ArrayList<VehicleDetails> Cost_Per_Mile_Second_Vehicle()
   {  // clear the calculated list
      cost_Second_Miles.clear();
      // assigns the readable / writable database to db
      SQLiteDatabase db = this.getWritableDatabase();

      // Cursor provides read-write access to the results returned the below database query.
      Cursor cursor = db.rawQuery("select * from " + PUMP_RECORD_TABLE + " WHERE vehicleID = 'Second Vehicle'", null);

      int recordCheck = cursor.getCount();
      if (cursor.getCount() > 0)
      {
         if (cursor.moveToPosition(0))
         {  // Cursor is set to start at index pos 0
            VehicleDetails currentItem = new VehicleDetails(); // look at current index
            VehicleDetails nextItem = new VehicleDetails();    // look to next index

            nextItem.outputCostPerMile = 0.00;     // set to Zero as no calculation is possible in index 0
            cost_Second_Miles.add(currentItem);      // add this to the list

            // position the cursor at index position Zero
            do
            {  // #1 columnDate assigned the cursor index position of databases colDate location
               int columnDate = cursor.getColumnIndex(colDate);
               // #2 then set the input date to the string value of that columnDate
               currentItem.setInputDate(cursor.getString(columnDate));
               // #1 ...
               int columnMiles = cursor.getColumnIndex(colOdometer);
               int columnPreGauge = cursor.getColumnIndex(colPREGauge);
               int columnPostGauge = cursor.getColumnIndex(colPOSTGauge);
               int columnCost = cursor.getColumnIndex(colCost);
               // litres
               int columnLitres = cursor.getColumnIndex(colLitres);
               // #2 ...
               currentItem.setOdometerRead(cursor.getString(columnMiles));
               currentItem.setPreTextGauge(cursor.getString(columnPreGauge));
               currentItem.setPostTextGauge(cursor.getString(columnPostGauge));
               currentItem.setTextCost(cursor.getString(columnCost));
               currentItem.setTextLitres(cursor.getString(columnLitres));
               try
               {
                  cursor.moveToNext();

                  nextItem.pretextGauge = cursor.getString(columnPreGauge);
                  nextItem.postTextGauge = cursor.getString(columnPostGauge);
                  nextItem.textCost = cursor.getString(columnCost);
                  nextItem.textLitres = cursor.getString(columnLitres);

                  double pinMoves = Double.parseDouble(currentItem.getPostTextGauge()) - Double.parseDouble(currentItem.getPreGauge());

                  double pinsPerLitre = pinMoves / Double.parseDouble(currentItem.getTextLitres());
                  double pinsUsed = Double.parseDouble(currentItem.getPostTextGauge()) - Double.parseDouble(nextItem.getPreGauge());
                  double litresUsed = pinsUsed / pinsPerLitre;
                  double costPerLitre = currentItem.getOutputMiles() / litresUsed;
                  double fuelCostUsed = litresUsed * costPerLitre;
                  double next_miles = Double.parseDouble(nextItem.getOdometerRead());
                  double current_miles = Double.parseDouble(currentItem.getOdometerRead());
                  double miles_answer = next_miles - current_miles;
                  double costPerMile = miles_answer / fuelCostUsed;

                  nextItem.outputCostPerMile = Double.parseDouble(Integer.toString((int) costPerMile));

                  cost_Second_Miles.add(nextItem);

               } catch (Exception e)
               {
                  Log.d("Test", "Second @ Calc Cost Per Mile");
               }
               // while the cursor can move to next position (!null)
            } while (cursor.moveToNext());
         }
      }
      // turn the cursor off
      // close the database
      cursor.close();
      db.close();
      // send the calculated list to ViewRecordActivity.java
      return cost_Second_Miles;
   } // get cost per mile
}
