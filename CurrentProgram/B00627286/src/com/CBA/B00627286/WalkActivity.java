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

public class WalkActivity extends Activity implements OnCheckedChangeListener, View.OnClickListener
{
   private Button btn_addWalkRecord;
   RadioGroup rGroup;
   EditText txtCost;
   String walkTime, distance;
   TextView eachDate, tv_multiple;
   ImageView image;
   WalkDatabaseHelper dbWalk;
   WalkDetails wd;
   Intent i;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_walk);

      image = (ImageView) findViewById(R.id.imageWalkCycle);

      Bundle extras = getIntent().getExtras();
      walkTime = extras.getString("currentTime");
      distance = extras.getString("walkDistance");

      eachDate = (TextView) findViewById(R.id.textViewDateWalk);
      eachDate.setText(walkTime);

      btn_addWalkRecord = (Button) findViewById(R.id.btn_addsRecordWalk);
      btn_addWalkRecord.setOnClickListener(this);

      rGroup = (RadioGroup) findViewById(R.id.walk_radioGroup);
      rGroup.setOnCheckedChangeListener(this);

      txtCost = (EditText) findViewById(R.id.editTextPerWalk);
   }

   @Override
   public void onCheckedChanged(RadioGroup group, int radioId)
   {
      tv_multiple = (TextView) findViewById(R.id.tvWalkMultiple);

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
         case R.id.btn_addsRecordWalk:

            if (txtCost.getText().toString().equals(""))
            {
               Toast.makeText(WalkActivity.this, "Peripheral Cost", Toast.LENGTH_LONG).show();
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

               dbWalk = new WalkDatabaseHelper(getApplicationContext());
               dbWalk.getWritableDatabase();
               wd = new WalkDetails();

               wd.setInputDate(walkTime);
               wd.setTextCost(txtCost.getText().toString());
               wd.setOutputMiles(distance);
               wd.setOutputCostMiles(Double.parseDouble(costPerMile));

               dbWalk.addProduct2Walkdb(wd);

               Toast.makeText(WalkActivity.this, "Record Success.", Toast.LENGTH_LONG).show();
               i = new Intent(WalkActivity.this, ViewWalkRecord.class);
               startActivity(i);
            }
            break;
         default:
            break;
      }
   }

   protected void onResume()
   {
      super.onResume();
      Log.d("WalkActivities", "Walk Activity Resumed");
   }

   protected void onPause()
   {
      super.onPause();
      Log.d("WalkActivities", "Walk Activity Pause");
   }

   protected void onStop()
   {
      super.onStop();
      Log.d("WalkActivities", "Walk Activity Stop");
   }
}
