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
 * Fuel: User Inputs Odometer Readout
 */

public class PumpOdometerActivity extends Activity implements View.OnClickListener {
    EditText odom;
    String pumpTime, vehicleID;
    private Button btn_addRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odometer);

        Bundle extras = getIntent().getExtras();
        // Passed information from PumpVehChoiceActivity (date/time stamp)&Veh.choice
        pumpTime = extras.getString("currentTime");
        vehicleID = extras.getString("vehicleChoice");

        btn_addRecord = (Button) findViewById(R.id.btn_addsRecordOdometer);
        btn_addRecord.setOnClickListener(this);

        odom = (EditText) findViewById(R.id.editTextOdometer);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addsRecordOdometer:

                if (odom.getText().toString().equals("")) {  // User instruction should they loose track of their required actions
                    Toast.makeText(PumpOdometerActivity.this, "Enter Miles On Odometer, eg. '004529'", Toast.LENGTH_LONG).show();
                } else {  // make string value for sending to next activity
                    String odomSent = odom.getText().toString();  // leads to user inputting Cost & Volume to screen
                    Intent addintent = new Intent(PumpOdometerActivity.this, PumpCostAmountActivity.class);
                    addintent.putExtra("currentTime", pumpTime);
                    addintent.putExtra("vehicleChoice", vehicleID);
                    addintent.putExtra("odometer", String.valueOf(odomSent));
                    Log.d("MemoryAddressIssue", odomSent);
                    startActivity(addintent);
                }
                break;
            default:
                break;
        }
    }

    protected void onResume() {
        super.onResume();
        Log.d("NumerousActivities", "Main  Activity Resumed");
    }

    protected void onPause() {
        super.onPause();
        Log.d("NumerousActivities", "Main Activity Pause");
    }

    protected void onStop() {
        super.onStop();
        Log.d("NumerousActivities", "Main Activity Stop");
    }
}