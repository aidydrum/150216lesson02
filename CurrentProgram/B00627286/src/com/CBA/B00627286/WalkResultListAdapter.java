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

class WalkResultListAdapter extends ArrayAdapter<WalkDetails> {
    WalkDatabaseHelper wdbh;
    private ArrayList<WalkDetails> items;

    public WalkResultListAdapter(Context context, int textVResourceId, ArrayList<WalkDetails> items, WalkDatabaseHelper cdbh) {
        super(context, textVResourceId, items);
        this.items = items;
        this.wdbh = cdbh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_walk_row, null);
        }

        WalkDetails info = items.get(position);

        if (info != null) {
            TextView listv_dates = (TextView) convertView.findViewById(R.id.dataWalkDate);
            TextView listv_cost = (TextView) convertView.findViewById(R.id.datawalkCost);
            listv_dates.setText(info.getInputDate());
            listv_cost.setText(info.getTextCost());

        }
        final int temp = position;

        (convertView.findViewById(R.id.btn_deletewalk)).setOnClickListener(new View.OnClickListener() {

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

