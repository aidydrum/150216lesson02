package com.CBA.B00627286;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: B00627286
 * Date: 26/08/13
 * Time: 16:40
 * update comment about program here
 */

public class RailDistanceActivity extends Activity implements View.OnClickListener
{
   private Button btn_addDistance;
   EditText txtDistance;

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activityraildistance);

      btn_addDistance = (Button) findViewById(R.id.btn_RailSave);
      btn_addDistance.setOnClickListener(this);

      txtDistance = (EditText) findViewById(R.id.edtTxtRailHowFar);
   }

   @Override
   public void onClick(View v)
   {
      // TODO Auto-generated method stub
      switch (v.getId())
      {
         case R.id.btn_BusSave:

            if (txtDistance.getText().toString().equals(""))
            {
               Toast.makeText(RailDistanceActivity.this, "Please Enter Rail Fare:", Toast.LENGTH_LONG).show();
            }
            else
            {                                               // 2014/01/10 12:59:00
               SimpleDateFormat sdatef = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
               Date d = new Date();
               String date = sdatef.format(d);
               String distance = txtDistance.getText().toString();
               // leads to users bus ticket screen
               Intent addIntent = new Intent(RailDistanceActivity.this, RailActivity.class);
               addIntent.putExtra("currentTime", date);
               addIntent.putExtra("railDistance", distance);
               startActivity(addIntent);
            }
            break;
         default:
            break;
      }
   }
}