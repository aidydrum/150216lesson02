package com.CBA.B00627286;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * Created with IntelliJ IDEA.
 * User: anon
 * Date: 08/07/13
 * Time: 13:07
 * Fuel: User Chooses Vehicle (Main or Secondary)
 */

public class PumpVehChoiceActivity extends Activity implements OnCheckedChangeListener, View.OnClickListener
{
   RadioGroup rGroup;
   String pumpTime;
   String carSent;
   TextView carID;
   private Button btn_proceed;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_choosevehicle);
      // Passed information from PrePumpActivity (date/time stamp)
      Bundle extras = getIntent().getExtras();

      pumpTime = extras.getString("currentTime");
      // the radio buttons are contained in radio group for control
      rGroup = (RadioGroup) findViewById(R.id.veh_radioGroup);
      rGroup.setOnCheckedChangeListener(this);
      carID = (TextView) findViewById(R.id.tvCarId);

      btn_proceed = (Button) findViewById(R.id.btn_toOdometer);
      btn_proceed.setOnClickListener(this);
   }

   @Override
   public void onCheckedChanged(RadioGroup group, int radioId)
   {
      TextView textView = (TextView) findViewById(R.id.tvCarId);

      RadioButton selected = (RadioButton) findViewById(radioId);

      String radioString = selected.getText().toString();
      textView.setText(String.format("%s", radioString)); // String "%s",  was "%s ", which is where the space was coming from.
      carSent = radioString;
   }

   public void onClick(View v)
   {
      switch (v.getId())
      {
         case R.id.btn_toOdometer:
            if (carID.getText().toString().equals(""))
            {
               Toast.makeText(this, "Please Choose A Vehicle", Toast.LENGTH_LONG).show();
            } else
            {                       // leads to user inputting odometer readout to screen
               Intent startOdometerActivity = new Intent(this, PumpOdometerActivity.class);
               // bundled extras for sending to PumpOdometer
               startOdometerActivity.putExtra("currentTime", pumpTime);
               startOdometerActivity.putExtra("vehicleChoice", carSent);
               startActivity(startOdometerActivity);
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