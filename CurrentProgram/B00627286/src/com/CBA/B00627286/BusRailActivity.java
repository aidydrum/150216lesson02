package com.CBA.B00627286;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: anon
 * Date: 08/07/13
 * Time: 13:06
 * UPDATE COMMENT ABOUT PROGRAM HERE
 */

//public class AndroidAdvanceSqliteActivity extends Activity implements View.OnClickListener

public class BusRailActivity extends Activity implements View.OnClickListener
{
   private Button btn_bus, btn_rail;

   /**
    * Called when the activity is first created.
    */
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activitybusrail);

      btn_bus = (Button) findViewById(R.id.btn_bus);
      btn_rail = (Button) findViewById(R.id.btn_rail);
      btn_bus.setOnClickListener(this);
      btn_rail.setOnClickListener(this);
   }

   @Override
   public void onClick(View v)
   {
      // TODO Auto-generated method stub
      switch (v.getId())
      {
         case R.id.btn_bus:
            SimpleDateFormat sDateBus = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            Date d = new Date();
            String date = sDateBus.format(d);

            Intent busintent = new Intent(BusRailActivity.this, BusDistanceActivity.class);
            busintent.putExtra("currentTime", date);
            startActivity(busintent);
            break;

         case R.id.btn_rail:
            SimpleDateFormat sDateRail = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            Date dt = new Date();
            String dateR = sDateRail.format(dt);

            Intent railintent = new Intent(BusRailActivity.this, RailDistanceActivity.class);
            railintent.putExtra("currentTime", dateR);
            startActivity(railintent);
            break;
         default:
            break;
      }
   }
}
