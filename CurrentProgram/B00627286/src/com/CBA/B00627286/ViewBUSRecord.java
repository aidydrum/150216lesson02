package com.CBA.B00627286;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: anon
 * Date: 08/07/13
 * Time: 13:26
 * UPDATE COMMENT ABOUT PROGRAM HERE
 */

public class ViewBUSRecord extends ListActivity
{
   TextView totalrecords;
   BusDatabaseHelper bdbh;
   BusDetails bd;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      // TODO Auto-generated method stub
      super.onCreate(savedInstanceState);
      setContentView(R.layout.viewbusrecord);

      totalrecords = (TextView) findViewById(R.id.totalBusrecords);
      ArrayList<BusDetails> userInputList = createUserInputList();

      BusResultListAdapter resultAdapter = new BusResultListAdapter(this, R.layout.listview_bus_row, userInputList, bdbh);

      setListAdapter(resultAdapter);
      bdbh = new BusDatabaseHelper(getApplicationContext());
      bdbh.getWritableDatabase();
      bd = new BusDetails();

      bdbh.close();
   }

   private ArrayList<BusDetails> createUserInputList()
   {
      ArrayList<BusDetails> inputArrayList = new ArrayList<BusDetails>();
      inputArrayList.clear();
      bdbh = new BusDatabaseHelper(getApplicationContext());
      bdbh.getWritableDatabase();

      ArrayList<BusDetails> get_list = bdbh.get_All_User_Inputs_Bus();

      for (int i = 0; i < get_list.size(); i++)
      {
         String tDate = get_list.get(i).getInputDate();
         String tCost = get_list.get(i).getTextCost();
         String tDistance = get_list.get(i).getOutputMiles();
         double tCostPerMile = get_list.get(i).getOutputCostMiles();

         BusDetails set_list = new BusDetails();

         set_list.setInputDate(tDate);
         set_list.setTextCost(tCost);
         set_list.setInputDate(tDistance);
         set_list.setOutputCostMiles(tCostPerMile);
         inputArrayList.add(set_list);
      }

      totalrecords.setText("Record Size:" + inputArrayList.size());
      bdbh.close();
      return inputArrayList;
   }

   @Override
   protected void onResume()
   {
      // TODO Auto-generated method stub
      super.onResume();
   }

   protected void onPause()
   {
      super.onPause();
      Log.d("NumerousActivities", "Main Activity Pause");
   }

   protected void onStop()
   {
      super.onStop();
      Log.d("NumerousActivities", "Main Activity Stop");
   }
}


