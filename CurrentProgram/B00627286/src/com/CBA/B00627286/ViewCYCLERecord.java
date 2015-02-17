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

public class ViewCYCLERecord extends ListActivity
{
   TextView totalrecords;
   CycleDatabaseHelper cdbh;
   CycleDetails cd;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      // TODO Auto-generated method stub
      super.onCreate(savedInstanceState);
      setContentView(R.layout.viewcyclerecord);

      totalrecords = (TextView) findViewById(R.id.totalCyclerecords);
      ArrayList<CycleDetails> userInputList = createUserInputList();

      CyclelResultListAdapter resultAdapter = new CyclelResultListAdapter(this, R.layout.listview_cycle_row, userInputList, cdbh);

      setListAdapter(resultAdapter);
      cdbh = new CycleDatabaseHelper(getApplicationContext());
      cdbh.getWritableDatabase();
      cd = new CycleDetails();

      cdbh.close();
   }

   private ArrayList<CycleDetails> createUserInputList()
   {
      ArrayList<CycleDetails> inputArrayList = new ArrayList<CycleDetails>();
      inputArrayList.clear();
      cdbh = new CycleDatabaseHelper(getApplicationContext());
      cdbh.getWritableDatabase();

      ArrayList<CycleDetails> get_list = cdbh.get_All_User_Inputs_Cycle();

      for (int i = 0; i < get_list.size(); i++)
      {
         String tDate = get_list.get(i).getInputDate();

         String tCost = get_list.get(i).getTextCost();

         CycleDetails set_list = new CycleDetails();

         set_list.setInputDate(tDate);
         set_list.setTextCost(tCost);
         inputArrayList.add(set_list);
      }

      totalrecords.setText("Record Size:" + inputArrayList.size());
      cdbh.close();
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


