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

public class CycleActivity extends Activity implements OnCheckedChangeListener, View.OnClickListener {
    RadioGroup rGroup;
    EditText txtCost;
    String cycleTime, distance;
    TextView eachDate, tv_multiple;
    ImageView image;
    CycleDatabaseHelper dbCycle;
    CycleDetails cd;
    Intent i;
    int selectedRadioButtonId = 0;
    private Button btn_addCycleRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);

        image = (ImageView) findViewById(R.id.imageWalkCycle);

        Bundle extras = getIntent().getExtras();
        cycleTime = extras.getString("currentTime");
        distance = extras.getString("cycleDistance");
        eachDate = (TextView) findViewById(R.id.textViewDateCycle);
        eachDate.setText(cycleTime);

        btn_addCycleRecord = (Button) findViewById(R.id.btn_addsRecordcycle);
        btn_addCycleRecord.setOnClickListener(this);

        rGroup = (RadioGroup) findViewById(R.id.bus_radioGroup);
        rGroup.setOnCheckedChangeListener(this);

        txtCost = (EditText) findViewById(R.id.editTextPeriph);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int radioId) {
        tv_multiple = (TextView) findViewById(R.id.tvCycleMultiple);

        RadioButton selected = (RadioButton) findViewById(radioId);

        String radioString = selected.getText().toString();
        tv_multiple.setText(String.format("%s", radioString)); // String "%s",  was "%s ", which is where the space was coming from.

        selectedRadioButtonId = selected.getImeActionId();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addsRecordBus:

                if (txtCost.getText().toString().equals("")) {
                    Toast.makeText(CycleActivity.this, "Peripheral Cost:", Toast.LENGTH_LONG).show();
                } else {
                    String costPerMile = "";

                    String test = txtCost.getText().toString();

                    double cost = Integer.parseInt(txtCost.getText().toString());
                    double dist = Integer.parseInt(distance);

                    switch (selectedRadioButtonId) {
                        case 1:                         // 10 miles / £5 x days
                            costPerMile = String.valueOf((dist / cost) * 1);
                            break;
                        case 2:
                            costPerMile = String.valueOf((dist / cost) * 2);
                            break;
                        case 3:
                            costPerMile = String.valueOf((dist / cost) * 7);
                            break;
                        case 4:
                            costPerMile = String.valueOf((dist / cost) * 30);
                            break;
                    }

                    dbCycle = new CycleDatabaseHelper(getApplicationContext());
                    dbCycle.getWritableDatabase();
                    cd = new CycleDetails();

                    cd.setInputDate(cycleTime);
                    cd.setTextCost(txtCost.getText().toString());
                    cd.setOutputMiles(distance);
                    cd.setOutputCostMiles(Double.parseDouble(costPerMile));

                    dbCycle.addProduct2Cycledb(cd);
                    //dbBus.add(bd);
                    Toast.makeText(CycleActivity.this, "Record Success.", Toast.LENGTH_LONG).show();
                    i = new Intent(CycleActivity.this, ViewCYCLERecord.class);
                    startActivity(i);
                }
                break;
            default:
                break;
        }
    }

    protected void onResume() {
        super.onResume();
        Log.d("Cycle", "Cycle Activity Resumed");
    }

    protected void onPause() {
        super.onPause();
        Log.d("Cycle", "Cycle Activity Pause");
    }

    protected void onStop() {
        super.onStop();
        Log.d("Cycle", "Cycle Activity Stop");
    }
}
