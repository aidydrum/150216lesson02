package com.CBA.B00627286;

//import OutOfReach.ResultListAdapter;

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
 * This is the code that iterates over inputs from
 */

public class ViewPumpRecord extends ListActivity {
    TextView totalrecords;
    PumpDatabaseHelper dbh;
    VehicleDetails vd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // Defining the custom screen layout:
        setContentView(R.layout.viewrecord);

        // textview readout of the number of records (int)
        totalrecords = (TextView) findViewById(R.id.totalrecords);
        // all the lists of information returned from the user inputs and calculated fields:
        ArrayList<VehicleDetails> userInputList = createUserInputList();
        ArrayList<VehicleDetails> fuelUsed_CalcList_Main = createFuelUsedCalcListMain();
        ArrayList<VehicleDetails> fuelUsed_CalcList_Second = createFuelUsedCalcListSecondary();
        ArrayList<VehicleDetails> miles_Travelled_main = milesTravelled_Main();
        ArrayList<VehicleDetails> miles_Travelled_second = milesTravelled_Second();
        ArrayList<VehicleDetails> cost_Per_Mile_Main = cost_Per_miles_Main();
        ArrayList<VehicleDetails> cost_Per_Mile_Second = cost_Per_miles_Second();

        // specifies the row template to use
        PumpResultListAdapter resultAdapter = new PumpResultListAdapter(this, R.layout.listview_row, userInputList, fuelUsed_CalcList_Main, fuelUsed_CalcList_Second,
                miles_Travelled_main, miles_Travelled_second, cost_Per_Mile_Main, cost_Per_Mile_Second, dbh);
        // Bind to the new Adapter, which will provide a new cursor for the list view
        setListAdapter(resultAdapter);
      /*
        getApplicationContext is used as Context is needed whose lifecycle is separate from the current context,
        that is tied to the lifetime of the process rather than the current component.
        db is being registered with the global state associated with the application.
        Thus it will never be unregistered before time.
      */
        dbh = new PumpDatabaseHelper(getApplicationContext());
        dbh.getWritableDatabase();
        vd = new VehicleDetails();
        dbh.close();
    }

    private ArrayList<VehicleDetails> createUserInputList() {
        ArrayList<VehicleDetails> inputArrayList = new ArrayList<VehicleDetails>();
        // clear whatever information is in inputArrayList
        inputArrayList.clear();
      /*
        getApplicationContext is used as Context is needed whose lifecycle is separate from the current context,
        that is tied to the lifetime of the process rather than the current component.
        db is being registered with the global state associated with the application.
        Thus it will never be unregistered before time.
      */
        dbh = new PumpDatabaseHelper(getApplicationContext());
        // open the database for reading and writing.
        dbh.getWritableDatabase();

        ArrayList<VehicleDetails> get_list = dbh.get_All_User_Inputs();
        // get and sets for items on database
        for (int i = 0; i < get_list.size(); i++) {
            String tDate = get_list.get(i).getInputDate();
            String tVid = get_list.get(i).getVehicleID();
            String tOdom = get_list.get(i).getOdometerRead();
            String tPREgauge = get_list.get(i).getPreGauge();
            String tCost = get_list.get(i).getTextCost();
            String tVolume = get_list.get(i).getTextLitres();
            String tPOSTgauge = get_list.get(i).getPostTextGauge();

            VehicleDetails set_list = new VehicleDetails();

            set_list.setInputDate(tDate);
            set_list.setVehicleID(tVid);
            set_list.setOdometerRead(tOdom);
            set_list.setPreTextGauge(tPREgauge);
            set_list.setPostTextGauge(tPOSTgauge);
            set_list.setTextCost(tCost);
            set_list.setTextLitres(tVolume);
            // sets the full set_list iterated over here to inputArrayList, which is returned
            // as the values the user has entered to onCreate for use by the resultAdapter
            inputArrayList.add(set_list);
        }

        totalrecords.setText("Record Size:" + inputArrayList.size());
        dbh.close();
        // returned for use in onCreate for the resultAdapter
        return inputArrayList;
    }

    private ArrayList<VehicleDetails> createFuelUsedCalcListMain() {
        ArrayList<VehicleDetails> arrayList_for_LitresUsed_Main = new ArrayList<VehicleDetails>();
        // clear whatever information is in arrayList_for_LitresUsed_Main
        arrayList_for_LitresUsed_Main.clear();
      /*
        getApplicationContext is used as Context is needed whose lifecycle is separate from the current context,
        that is tied to the lifetime of the process rather than the current component.
        db is being registered with the global state associated with the application.
        Thus it will never be unregistered before time.
      */
        dbh = new PumpDatabaseHelper(getApplicationContext());
        // open the database for reading and writing.
        dbh.getWritableDatabase();

        ArrayList<VehicleDetails> get_list = dbh.Result_Litres_Used_Main_Vehicle();
        // check here, says getList is size 1!!
        for (int i = 0; i < get_list.size(); i++) {  //get [2] results
            double resultLitresUsed = get_list.get(i).getOutputLitresUsed();
            VehicleDetails set_FuelUsedlist_Main = new VehicleDetails();
            //set [2] results
            set_FuelUsedlist_Main.setOutputLitresUsed(resultLitresUsed);
            arrayList_for_LitresUsed_Main.add(set_FuelUsedlist_Main);
        }
        dbh.close();
        return arrayList_for_LitresUsed_Main;
    }

    private ArrayList<VehicleDetails> createFuelUsedCalcListSecondary() {
        ArrayList<VehicleDetails> arrayList_for_LitresUsed_Secondary = new ArrayList<VehicleDetails>();
        arrayList_for_LitresUsed_Secondary.clear();
        dbh = new PumpDatabaseHelper(getApplicationContext());
        dbh.getWritableDatabase();

        ArrayList<VehicleDetails> get_list = dbh.Result_Litres_Used_Secondary();

        for (int i = 0; i < get_list.size(); i++) {
            double resultLitresUsed = get_list.get(i).getOutputLitresUsed();
            VehicleDetails set_FuelUsedlist_Secondary = new VehicleDetails();
            set_FuelUsedlist_Secondary.setOutputLitresUsed(resultLitresUsed);
            arrayList_for_LitresUsed_Secondary.add(set_FuelUsedlist_Secondary);
        }
        dbh.close();
        return arrayList_for_LitresUsed_Secondary;
    }

    private ArrayList<VehicleDetails> milesTravelled_Main() {
        ArrayList<VehicleDetails> arrayList_for_Miles_Main = new ArrayList<VehicleDetails>();
        arrayList_for_Miles_Main.clear();
        dbh = new PumpDatabaseHelper(getApplicationContext());
        dbh.getWritableDatabase();

        ArrayList<VehicleDetails> get_miles_list = dbh.milesTravelled_Main_Vehicle();

        for (int i = 0; i < get_miles_list.size(); i++) {
            double resultMiles = (get_miles_list.get(i).getOutputMiles());
            VehicleDetails set_Main_Miles = new VehicleDetails();
            set_Main_Miles.setOutputMiles(resultMiles);
            arrayList_for_Miles_Main.add(set_Main_Miles);
        }
        dbh.close();
        return arrayList_for_Miles_Main;
    }

    private ArrayList<VehicleDetails> milesTravelled_Second() {
        ArrayList<VehicleDetails> arrayList_for_Miles_Second = new ArrayList<VehicleDetails>();
        arrayList_for_Miles_Second.clear();
        dbh = new PumpDatabaseHelper(getApplicationContext());
        dbh.getWritableDatabase();

        ArrayList<VehicleDetails> get_list = dbh.milesTravelled_Second_Vehicle();

        for (int i = 0; i < get_list.size(); i++) {
            double resultMiles = (get_list.get(i).getOutputMiles());
            VehicleDetails set_Main_Miles = new VehicleDetails();
            set_Main_Miles.setOutputMiles(resultMiles);
            arrayList_for_Miles_Second.add(set_Main_Miles);
        }
        dbh.close();
        return arrayList_for_Miles_Second;
    }

    private ArrayList<VehicleDetails> cost_Per_miles_Main() {
        ArrayList<VehicleDetails> arrayList_for_Cost_Mile = new ArrayList<VehicleDetails>();
        arrayList_for_Cost_Mile.clear();
        dbh = new PumpDatabaseHelper(getApplicationContext());
        dbh.getWritableDatabase();

        ArrayList<VehicleDetails> get_list = dbh.Cost_Per_Mile_Main_Vehicle();

        for (int i = 0; i < get_list.size(); i++) {
            double resultMiles = (get_list.get(i).getOutputCostMiles());
            VehicleDetails set_Main_Miles = new VehicleDetails();
            set_Main_Miles.setOutputCostPerMile(resultMiles);
            arrayList_for_Cost_Mile.add(set_Main_Miles);
        }
        dbh.close();
        return arrayList_for_Cost_Mile;
    }

    private ArrayList<VehicleDetails> cost_Per_miles_Second() {
        ArrayList<VehicleDetails> arrayList_for_Cost_Mile = new ArrayList<VehicleDetails>();
        arrayList_for_Cost_Mile.clear();
        dbh = new PumpDatabaseHelper(getApplicationContext());
        dbh.getWritableDatabase();

        ArrayList<VehicleDetails> get_list = dbh.Cost_Per_Mile_Second_Vehicle();

        for (int i = 0; i < get_list.size(); i++) {
            double resultMiles = (get_list.get(i).getOutputCostMiles());
            VehicleDetails set_Main_Miles = new VehicleDetails();
            set_Main_Miles.setOutputCostPerMile(resultMiles);
            arrayList_for_Cost_Mile.add(set_Main_Miles);
        }
        dbh.close();
        return arrayList_for_Cost_Mile;
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


