package com.CBA.B00627286;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: anon
 * Date: 08/07/13
 * Time: 13:07
 * Fuel: User Inputs Cost & Volume of Refill
 */

public class PumpCostAmountActivity extends Activity implements View.OnClickListener
{
   private Button btn_addRecord;
   EditText txtCost, txtVolume;
   String pumpTime, vehicleID, odomSent;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_pumpcostamount);
      // Passed information from PumpOdometerActivity stamp&Vehicle&odometer
      Bundle extras = getIntent().getExtras();

      pumpTime = extras.getString("currentTime");
      vehicleID = extras.getString("vehicleChoice");
      odomSent = extras.getString("odometer");

      btn_addRecord = (Button) findViewById(R.id.btn_addsRecordPumpCostVolume);
      btn_addRecord.setOnClickListener(this);

      txtCost = (EditText) findViewById(R.id.editTextCost);
      txtVolume = (EditText) findViewById(R.id.editTextVolume);
   }

   public void onClick(View v)
   {
      switch (v.getId())
      {
         case R.id.btn_addsRecordPumpCostVolume:

            if (txtCost.getText().toString().equals("") || (txtVolume.getText().toString().equals("")))
            {  // User instruction should they loose track of their required actions
               Toast.makeText(PumpCostAmountActivity.this, "Enter Total Cost of Refuel & Volume of Fuel Refilled ", Toast.LENGTH_LONG).show();
            } else
            {  // make string values for sending to next activity
               String textCost = txtCost.getText().toString();
               String textVolume = txtVolume.getText().toString();
               Intent addintent = new Intent(PumpCostAmountActivity.this, PumpFuelGaugesActivity.class);
               addintent.putExtra("currentTime", pumpTime);
               addintent.putExtra("vehicleChoice", vehicleID);
               addintent.putExtra("odometer", odomSent);
               addintent.putExtra("costSent", textCost);
               addintent.putExtra("volumeSent", textVolume);
               Log.d("SQLite", textCost);
               Log.d("SQLite", textVolume);
               startActivity(addintent);
            }
            break;
         default:
            break;
      }
   }

   protected void onResume()
   {
      super.onResume();
      Log.d("NumerousActivities", "Main  Activity Resumed");
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