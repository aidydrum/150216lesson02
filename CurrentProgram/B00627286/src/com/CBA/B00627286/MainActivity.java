package com.CBA.B00627286;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: anon
 * Date: 08/07/13
 * Time: 13:05
 * This is the home screen of the application
 * The home screen comprises the buttons coded here:
 */

public class MainActivity extends Activity
{
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      Log.d("NumerousActivities", "Main Activity Created");
   }

   public void startPrePumpActivity(View v)
   {
      try
      { // Button to start Fuel Activities
         Intent intentPrePump = new Intent(MainActivity.this, PrePumpActivity.class);
         startActivity(intentPrePump);
      } catch (Exception ex)
      {
         Log.d(ex.getMessage(), "");
      }
   }

   public void startBusRailActivity(View v)
   {
      try
      { // Button to start Bus or Rail Activities
         Intent intentBusRail = new Intent(MainActivity.this, BusRailActivity.class);
         startActivity(intentBusRail);
      } catch (Exception ex)
      {
         Log.d(ex.getMessage(), "");
      }
   }

   public void startWalkCycleActivity(View v)
   {
      try
      { // Button to start Walk or Cycle Activities
         Intent intentWalkCcyle = new Intent(MainActivity.this, WalkCycleActivity.class);
         startActivity(intentWalkCcyle);
      } catch (Exception ex)
      {
         Log.d(ex.getMessage(), "");
      }
   }

   public void startStatsActivity(View v)
   {
      try
      { // Button to start Stats Activities
         Intent intent = new Intent(MainActivity.this, ViewPumpRecord.class);
         startActivity(intent);
      } catch (Exception ex)
      {
         Log.d(ex.getMessage(), "");
      }
   }

   protected void onResume()
   {
      super.onResume();
      Log.d("NumerousActivities", "Main Activity Resumed");
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
