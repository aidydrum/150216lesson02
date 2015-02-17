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
 * Time: 14:06
 * UPDATE COMMENT ABOUT PROGRAM HERE
 */

public class BusActivity extends Activity implements OnCheckedChangeListener, View.OnClickListener
{
   private Button btn_addBusRecord;
   RadioGroup rGroup;
   EditText txtCost;
   String busTime, distance;
   TextView eachDate, tv_multiple;
   ImageView image;
   BusDatabaseHelper dbBus;
   BusDetails bd;
   Intent otherIntentWay;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_bus);
      // Passed information from PumpOdometerActivity stamp&Vehicle&odometer
      Bundle extras = getIntent().getExtras();

      busTime = extras.getString("currentTime");
      distance = extras.getString("busDistance");

      image = (ImageView) findViewById(R.id.imageRailBus);

      eachDate = (TextView) findViewById(R.id.textViewDateBus);
      eachDate.setText(busTime);

      rGroup = (RadioGroup) findViewById(R.id.bus_radioGroup);
      rGroup.setOnCheckedChangeListener(this);
      txtCost = (EditText) findViewById(R.id.editTextBusFare);
      btn_addBusRecord = (Button) findViewById(R.id.btn_addsRecordBus);
      btn_addBusRecord.setOnClickListener(this);
   }

   @Override
   public void onCheckedChanged(RadioGroup group, int radioId)
   {
      tv_multiple = (TextView) findViewById(R.id.tvBusMultiple);

      RadioButton selected = (RadioButton) findViewById(radioId);

      String radioString = selected.getText().toString();
      tv_multiple.setText(String.format("%s", radioString)); // String "%s",  was "%s ", which is where the space was coming from.

      selectedRadioButtonId = selected.getImeActionId();
   }

   int selectedRadioButtonId = 0;

   public void onClick(View v)
   {
      switch (v.getId())
      {
         case R.id.btn_addsRecordBus:
            if ((txtCost.getText().toString().equals("")) || (tv_multiple.getText().toString().equals("")))
            {
               Toast.makeText(BusActivity.this, "Please Enter Ticket Value:", Toast.LENGTH_LONG).show();
            } else
            {
                String costPerMile = "";

                String test = txtCost.getText().toString();

                double cost = Integer.parseInt(txtCost.getText().toString());
                double dist = Integer.parseInt(distance);

                switch(selectedRadioButtonId)
                {
                    case 1:                         // 10 miles / £5 x days
                        costPerMile = String.valueOf((dist / cost) *1);
                        break;
                    case 2:
                        costPerMile = String.valueOf((dist / cost) *2);
                        break;
                    case 3:
                        costPerMile = String.valueOf((dist / cost) *7);
                        break;
                    case 4:
                        costPerMile = String.valueOf((dist / cost) *30);
                        break;
                }

                dbBus = new BusDatabaseHelper(getApplicationContext());
                dbBus.getWritableDatabase();
                bd = new BusDetails();

                bd.setInputDate(busTime);
                bd.setTextCost(txtCost.getText().toString());
                bd.setOutputMiles(distance);
                bd.setOutputCostMiles(Double.parseDouble(costPerMile));
                dbBus.addProduct2Busdb(bd);

                Toast.makeText(BusActivity.this, "Record Success.", Toast.LENGTH_LONG).show();
                otherIntentWay = new Intent(BusActivity.this, ViewBUSRecord.class);
                startActivity(otherIntentWay);
            }
            break;
         default:
            break;
      }
   }

   protected void onResume()
   {
      super.onResume();
      Log.d("BusActivities", "Bus Activity Resumed");
   }

   protected void onPause()
   {
      super.onPause();
      Log.d("BusActivities", "Bus Activity Pause");
   }

   protected void onStop()
   {
      super.onStop();
      Log.d("BusActivities", "Bus Activity Stop");
   }


}
