package com.CBA.B00627286;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: anon
 * Date: 08/07/13
 * Time: 13:26
 * UPDATE COMMENT ABOUT PROGRAM HERE
 */

public class ViewRAILRecord extends ListActivity {
    TextView totalrecords;
    RailDatabaseHelper rdbh;
    RailDetails rd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewrailrecord);

        totalrecords = (TextView) findViewById(R.id.totalRailrecords);
        ArrayList<RailDetails> userInputList = createUserInputList();

        RailResultListAdapter resultAdapter = new RailResultListAdapter(this, R.layout.listview_rail_row, userInputList, rdbh);

        setListAdapter(resultAdapter);
        rdbh = new RailDatabaseHelper(getApplicationContext());
        rdbh.getWritableDatabase();
        rd = new RailDetails();

        rdbh.close();
    }

    private ArrayList<RailDetails> createUserInputList() {
        ArrayList<RailDetails> inputArrayList = new ArrayList<RailDetails>();
        inputArrayList.clear();
        rdbh = new RailDatabaseHelper(getApplicationContext());
        rdbh.getWritableDatabase();

        ArrayList<RailDetails> get_list = rdbh.get_All_User_Inputs_Rail();

        for (int i = 0; i < get_list.size(); i++) {
            String tDate = get_list.get(i).getInputDate();
            String tCost = get_list.get(i).getTextCost();
            String tDistance = get_list.get(i).getOutputMiles();
            double tCostPerMile = get_list.get(i).getOutputCostMiles();

            RailDetails set_list = new RailDetails();

            set_list.setInputDate(tDate);
            set_list.setTextCost(tCost);
            set_list.setInputDate(tDistance);
            set_list.setOutputCostMiles(tCostPerMile);
            inputArrayList.add(set_list);
        }

        totalrecords.setText("Record Size:" + inputArrayList.size());
        rdbh.close();
        return inputArrayList;
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
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


