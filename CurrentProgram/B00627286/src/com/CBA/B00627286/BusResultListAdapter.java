package com.CBA.B00627286;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: anon
 * Date: 24/07/13
 * Time: 15:41
 * UPDATE COMMENT ABOUT PROGRAM HERE
 */

class BusResultListAdapter extends ArrayAdapter<BusDetails> {
    BusDatabaseHelper dbh;
    private ArrayList<BusDetails> items;

    public BusResultListAdapter(Context context, int textVResourceId, ArrayList<BusDetails> items, BusDatabaseHelper dbh) {
        super(context, textVResourceId, items); // NOTE NO SUPER ON , miles_Calc_List_Main
        this.items = items;
        this.dbh = dbh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_bus_row, null);
        }

        BusDetails info = items.get(position);

        if (info != null) {
            TextView listv_dates = (TextView) convertView.findViewById(R.id.dataBusdate);
            TextView listv_cost = (TextView) convertView.findViewById(R.id.dataBuscost);
            TextView listv_distance = (TextView) convertView.findViewById(R.id.dataBusDistance);
            TextView listv_cost_per_mile = (TextView) convertView.findViewById(R.id.tvCPMileOutputBus);

            listv_dates.setText(info.getInputDate());
            listv_cost.setText(info.getTextCost());
            listv_distance.setText(info.getOutputMiles());
            listv_cost_per_mile.setText(info.getOutputCostMiles().toString());
        }

        (convertView.findViewById(R.id.btn_delete)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(getContext());
                alertbox.setCancelable(true);
                alertbox.setMessage("! ! Delete Record ! !");
                alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getContext(), "!Operation Not Permitted!", Toast.LENGTH_SHORT).show();
                    }
                });
                alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getContext(), "Nothing Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                alertbox.show();
            }
        });
        return convertView;
    }
}

