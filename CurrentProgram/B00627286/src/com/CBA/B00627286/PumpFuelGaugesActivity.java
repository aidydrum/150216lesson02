package com.CBA.B00627286;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: anon
 * Date: 08/07/13
 * Time: 13:07
 * Fuel: User Enters Car Fuel Gauge Pin Locations Pre & Post Fill
 */

public class PumpFuelGaugesActivity extends Activity implements View.OnClickListener {
    EditText preTextGauge, postTextGauge;
    String pumpTime, vehicleID, odomSent, costSent, amountSent, preGauge, postGauge;
    ImageView image;
    PumpDatabaseHelper db;
    VehicleDetails vd;
    Intent intent;
    private Button btn_addRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuelgauges);

        image = (ImageView) findViewById(R.id.imageGauge);
        // Passed information from PumpCostAmountActivity stamp&Vehicle&odometer&Cost&Volume
        Bundle extras = getIntent().getExtras();
        pumpTime = extras.getString("currentTime");
        vehicleID = extras.getString("vehicleChoice");
        odomSent = extras.getString("odometer");
        costSent = extras.getString("costSent");
        amountSent = extras.getString("volumeSent");

        btn_addRecord = (Button) findViewById(R.id.btn_addsRecordFuelGauge);
        btn_addRecord.setOnClickListener(this);

        preTextGauge = (EditText) findViewById(R.id.editPREGauge);
        postTextGauge = (EditText) findViewById(R.id.editPOSTgauge);

        preGauge = String.valueOf(preTextGauge);
        postGauge = String.valueOf(postTextGauge);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addsRecordFuelGauge:

                if (preTextGauge.getText().toString().equals("") || (postTextGauge.getText().toString().equals(""))) {  // User instruction should they loose track of their required actions
                    Toast.makeText(PumpFuelGaugesActivity.this, "Enter Fuel Gauge Pin Position For Both PREFUELLING & POSTFUELLING ", Toast.LENGTH_LONG).show();
                } else {  // WRITE ALL CONTENT TO THE DB!
               /*
                   getApplicationContext is used as Context is needed whose lifecycle is separate from the current context,
                   that is tied to the lifetime of the process rather than the current component.
                   db is being registered with the global state associated with the application.
                   Thus it will never be unregistered before time.
               */
                    db = new PumpDatabaseHelper(getApplicationContext());
                    db.getWritableDatabase();
                    vd = new VehicleDetails();

                    vd.inputDate = pumpTime;
                    vd.vehicleID = vehicleID;
                    vd.odometerRead = odomSent;
                    vd.textCost = costSent;
                    vd.textLitres = amountSent;
                    vd.pretextGauge = (preTextGauge.getText().toString());
                    vd.postTextGauge = (postTextGauge.getText().toString());
                    // This is where items are added to the database using PumpDatabaseHelper
                    db.addProduct2db(vd);
                    // inform the user of record success
                    Toast.makeText(PumpFuelGaugesActivity.this, "Record Success.", Toast.LENGTH_LONG).show();
                    // start the next activity
                    intent = new Intent(PumpFuelGaugesActivity.this, ViewPumpRecord.class);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }

    protected void onResume() {
        super.onResume();
        Log.d("Gauges", "Gauges Activity Resumed");
    }

    protected void onPause() {
        super.onPause();
        Log.d("Gauges", "Gauges Activity Pause");
    }

    protected void onStop() {
        super.onStop();
        Log.d("Gauges", "Gauges Activity Stop");
    }
}