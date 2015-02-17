package com.CBA.B00627286;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
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
 *
 * That field id should reference a TextView in the larger layout resource.
 * However the TextView is referenced, it will be filled with the toString() of each object in the array.
 * Here add lists of custom objects. Override the toString() method of objects to determine what
 * text will be displayed for an item in the list.
 * Results fill the views, over-riding getView(int, View, ViewGroup)
 * to return the type of view required.
 */

class PumpResultListAdapter extends ArrayAdapter<VehicleDetails>
{
   private ArrayList<VehicleDetails> items;
   private ArrayList<VehicleDetails> fuel_Calc_List_Main;
   private ArrayList<VehicleDetails> fuel_Calc_List_Second;
   private ArrayList<VehicleDetails> miles_Calc_List_Main;
   private ArrayList<VehicleDetails> miles_Calc_List_Second;
   private ArrayList<VehicleDetails> cost_per_mile_Main;
   private ArrayList<VehicleDetails> cost_per_mile_Second;
   PumpDatabaseHelper dbh;

   public PumpResultListAdapter(Context context, int textViewResourceId, ArrayList<VehicleDetails> items, ArrayList<VehicleDetails> fuel_Calc_List_Main,
                                ArrayList<VehicleDetails> fuel_Calc_List_Second, ArrayList<VehicleDetails> miles_Calc_List_Main, ArrayList<VehicleDetails> miles_Calc_List_Second, ArrayList<VehicleDetails> cost_Per_Mile_Main, ArrayList<VehicleDetails> cost_Per_Mile_Second, PumpDatabaseHelper dbh)
   {
      super(context, textViewResourceId, items);
      this.items = items;
      this.fuel_Calc_List_Main = fuel_Calc_List_Main;
      this.fuel_Calc_List_Second = fuel_Calc_List_Second;
      this.miles_Calc_List_Main = miles_Calc_List_Main;
      this.miles_Calc_List_Second = miles_Calc_List_Second;
      this.cost_per_mile_Main = cost_Per_Mile_Main;
      this.cost_per_mile_Second = cost_Per_Mile_Second;
      this.dbh = dbh;
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent)
   {
      // TODO Auto-generated method stub
      if (convertView == null)
      {
         LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         convertView = inflater.inflate(R.layout.listview_row, null);
      }
      try
      {
         VehicleDetails info = items.get(position);

         if (info != null)
         {
            TextView listv_dates = (TextView) convertView.findViewById(R.id.datadate);
            TextView listv_car = (TextView) convertView.findViewById(R.id.dataCar);
            TextView listv_odom = (TextView) convertView.findViewById(R.id.dataOdom);
            TextView listv_PreGauge = (TextView) convertView.findViewById(R.id.PREdatagauge);
            TextView listv_PostGauge = (TextView) convertView.findViewById(R.id.POSTdatagauge);
            TextView listv_cost = (TextView) convertView.findViewById(R.id.datacost);
            TextView listv_litres = (TextView) convertView.findViewById(R.id.datalitres);

            listv_dates.setText(info.getInputDate());
            listv_car.setText(info.getVehicleID());
            listv_odom.setText(info.getOdometerRead());
            listv_PreGauge.setText(info.getPreGauge());
            listv_PostGauge.setText(info.getPostTextGauge());
            listv_cost.setText(info.getTextCost());
            listv_litres.setText(info.getTextLitres());
         }
      } catch (IndexOutOfBoundsException ex)
      {
         Log.d("Problem", "user inputs");
      } // catch

      try
      {
         VehicleDetails calculated_main = fuel_Calc_List_Main.get(position);

         if (calculated_main != null)
         {
            TextView listV_fuelLitres = (TextView) convertView.findViewById(R.id.tvResultAmntUsed);
            listV_fuelLitres.setText("" + (int) calculated_main.getOutputLitresUsed());
         } // if not null set output Litres Used
      } catch (IndexOutOfBoundsException ex)
      {
         Log.d("Problem", "Fuel Main");
      } // catch

      try
      {
         VehicleDetails calculated_secondary = fuel_Calc_List_Second.get(position);

         if (calculated_secondary != null)
         {
            TextView listV_fuelLitres = (TextView) convertView.findViewById(R.id.tvResultAmntUsed);
            listV_fuelLitres.setText("" + (int) calculated_secondary.getOutputLitresUsed());
         } // if not null set output Litres used

      } catch (IndexOutOfBoundsException ex)
      {
         Log.d("Problem", "Fuel Second");
      } // catch

      try
      {
         VehicleDetails calculated_Main_Miles_Travelled = miles_Calc_List_Main.get(position);

         if (calculated_Main_Miles_Travelled != null)
         {
            TextView listV_miles = (TextView) convertView.findViewById(R.id.tvResultMiles);
            listV_miles.setText("" + (int) calculated_Main_Miles_Travelled.getOutputMiles());
         } // if not null set miles travelled

      } catch (IndexOutOfBoundsException ex)
      {
         Log.d("Problem", "Miles Main");
      } // catch

      try
      {
         VehicleDetails calculated_Second_Miles_Travelled = miles_Calc_List_Second.get(position);

         if (calculated_Second_Miles_Travelled != null)
         {
            TextView listV_miles = (TextView) convertView.findViewById(R.id.tvResultMiles);
            listV_miles.setText("" + (int) calculated_Second_Miles_Travelled.getOutputMiles());
         } // if not null set miles travelled

      } catch (IndexOutOfBoundsException ex)
      {
         Log.d("Problem", "Miles Second");
      } // catch

      try
      {
         VehicleDetails costPerMileMain = cost_per_mile_Main.get(position);

         if (costPerMileMain != null)
         {
            TextView listV_miles = (TextView) convertView.findViewById(R.id.tvCostPerMileOutput);
            listV_miles.setText("" + (int) costPerMileMain.getOutputCostMiles());
         } // if not null set cost per mile

      } catch (IndexOutOfBoundsException ex)
      {
         Log.d("Problem", "Miles Main");
      } // catch

      try
      {
         VehicleDetails costPerMileSecond = cost_per_mile_Second.get(position);

         if (costPerMileSecond != null)
         {
            TextView listV_miles = (TextView) convertView.findViewById(R.id.tvCostPerMileOutput);
            listV_miles.setText("" + (int) costPerMileSecond.getOutputCostMiles());
         } // if not null set cost per mile

      } catch (IndexOutOfBoundsException ex)
      {
         Log.d("Problem", "Miles Second");
      } // catch

      (convertView.findViewById(R.id.btn_delete)).setOnClickListener(new View.OnClickListener()
      {

         public void onClick(View arg0)
         {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(getContext());
            alertbox.setCancelable(true);
            alertbox.setMessage("! ! Delete Record ! !");
            alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener()
            {
               public void onClick(DialogInterface arg0, int arg1)
               {
                  Toast.makeText(getContext(), "!Operation Not Permitted!", Toast.LENGTH_SHORT).show();
               }
            });
            alertbox.setNegativeButton("No", new DialogInterface.OnClickListener()
            {
               public void onClick(DialogInterface arg0, int arg1)
               {
                  Toast.makeText(getContext(), "Nothing Deleted", Toast.LENGTH_SHORT).show();
               }
            });
            alertbox.show();
         }
      });
      return convertView;
   }
}

