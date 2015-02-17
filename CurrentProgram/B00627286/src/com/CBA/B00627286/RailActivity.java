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
 * Time: 13:06
 * UPDATE COMMENT ABOUT PROGRAM HERE
 */

//public class AndroidAdvanceSqliteActivity extends Activity implements View.OnClickListener

public class RailActivity extends Activity implements OnCheckedChangeListener, View.OnClickListener
{
   private Button btn_addRailRecord;
   RadioGroup rGroup;
   EditText txtCost;
   String railTime, distance;
   TextView eachDate, tv_multiple;
   ImageView image;
   RailDatabaseHelper dbRail;
   RailDetails rd;
   Intent otherIntentWay;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_rail);
      // Passed information from PumpOdometerActivity stamp&Vehicle&odometer

      image = (ImageView) findViewById(R.id.imageRailBus);

      Bundle extras = getIntent().getExtras();
      railTime = extras.getString("currentTime");
      distance = extras.getString("railDistance");

      eachDate = (TextView) findViewById(R.id.textViewDateRail);
      eachDate.setText(railTime);

      btn_addRailRecord = (Button) findViewById(R.id.btn_addsRecordRail);
      btn_addRailRecord.setOnClickListener(this);

      rGroup = (RadioGroup) findViewById(R.id.rail_radioGroup);
      rGroup.setOnCheckedChangeListener(this);

      txtCost = (EditText) findViewById(R.id.editTextRailFare);
   }

   @Override
   public void onCheckedChanged(RadioGroup group, int radioId)
   {
      tv_multiple = (TextView) findViewById(R.id.tvRailMultiple);

      RadioButton selected = (RadioButton) findViewById(radioId);

      String radioString = selected.getText().toString();
      tv_multiple.setText(String.format("%s", radioString));
      // String "%s",  was "%s ", which is where the space was coming from.
      selectedRadioButtonId = selected.getImeActionId();
   }

   int selectedRadioButtonId = 0;

   public void onClick(View v)
   {
      switch (v.getId())
      {
         case R.id.btn_addsRecordRail:

            if (txtCost.getText().toString().equals(""))
            {
               Toast.makeText(RailActivity.this, "Please Enter Ticket Value:", Toast.LENGTH_LONG).show();
            } else
            {
               String costPerMile = "";

               String test = txtCost.getText().toString();

               double cost =   Integer.parseInt(txtCost.getText().toString());
               double dist =   Integer.parseInt(distance);

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

               dbRail = new RailDatabaseHelper(getApplicationContext());
               dbRail.getWritableDatabase();
               rd = new RailDetails();

               rd.setInputDate(railTime);
               rd.setTextCost(txtCost.getText().toString());
               rd.setOutputMiles(distance);
               rd.setOutputCostMiles(Double.parseDouble(costPerMile));
               dbRail.addProduct2Raildb(rd);

               Toast.makeText(RailActivity.this, "Record Success.", Toast.LENGTH_LONG).show();
               otherIntentWay = new Intent(RailActivity.this, ViewRAILRecord.class);
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
      Log.d("RailActivity", "Bus Activity Resumed");
   }

   protected void onPause()
   {
      super.onPause();
      Log.d("RailActivity", "Bus Activity Pause");
   }

   protected void onStop()
   {
      super.onStop();
      Log.d("RailActivity", "Bus Activity Stop");
   }
}
