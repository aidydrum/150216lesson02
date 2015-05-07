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

public class ViewWalkRecord extends ListActivity {
    TextView totalrecords;
    WalkDatabaseHelper wdbh;
    WalkDetails wd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewwalkrecord);

        totalrecords = (TextView) findViewById(R.id.totalWalkrecords);
        ArrayList<WalkDetails> userInputList = createUserInputList();

        WalkResultListAdapter resultAdapter = new WalkResultListAdapter(this, R.layout.listview_cycle_row, userInputList, wdbh);

        setListAdapter(resultAdapter);
        wdbh = new WalkDatabaseHelper(getApplicationContext());
        wdbh.getWritableDatabase();
        wd = new WalkDetails();

        wdbh.close();
    }

    private ArrayList<WalkDetails> createUserInputList() {
        ArrayList<WalkDetails> inputArrayList = new ArrayList<WalkDetails>();
        inputArrayList.clear();
        wdbh = new WalkDatabaseHelper(getApplicationContext());
        wdbh.getWritableDatabase();

        ArrayList<WalkDetails> get_list = wdbh.get_All_User_Inputs_Walk();

        for (int i = 0; i < get_list.size(); i++) {
            String tDate = get_list.get(i).getInputDate();

            String tCost = get_list.get(i).getTextCost();

            WalkDetails set_list = new WalkDetails();

            set_list.setInputDate(tDate);
            set_list.setTextCost(tCost);
            inputArrayList.add(set_list);
        }

        totalrecords.setText("Record Size:" + inputArrayList.size());
        wdbh.close();
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


