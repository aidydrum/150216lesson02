package com.CBA.B00627286;

/**
 * Created with IntelliJ IDEA.
 * User: anon
 * Date: 08/07/13
 * Time: 13:20
 * UPDATE COMMENT ABOUT PROGRAM HERE
 */
public class VehicleDetails
{
   public String inputDate = "";
   public String vehicleID = "";
   public String odometerRead = "";
   public String pretextGauge = "";
   public String postTextGauge = "";
   public String textCost = "";
   public String textLitres = "";
   public String outputDatediff = "";

   public double outputMiles = 000000.000;
   public double outputLitreUsed = 0000.000;
   public double outputFuelUsed = 0000.000;
   public double outputCostPerMile = 0000.000;

   public String getInputDate()
   {
      return inputDate;
   }

   public void setInputDate(String zinputDate)
   {
      this.inputDate = zinputDate.trim();
   }

   public String getVehicleID()
   {
      return vehicleID;
   }

   public void setVehicleID(String zvehicleID)
   {
      //this.vehicleID = zvehicleID.trim();
      this.vehicleID = zvehicleID;
   }

   public String getOdometerRead()
   {
      return odometerRead;
   }

   public void setOdometerRead(String zodometerRead)
   {
      this.odometerRead = zodometerRead;
   }

   public String getPreGauge()
   {
      return pretextGauge;
   }

   public void setPreTextGauge(String zpretextGauge)
   {
      this.pretextGauge = zpretextGauge;
   }

   public String getPostTextGauge()
   {
      return postTextGauge;
   }

   public void setPostTextGauge(String zpostTextGauge)
   {
      this.postTextGauge = zpostTextGauge;
   }

   public String getTextCost()
   {
      return textCost;
   }

   public void setTextCost(String ztextCost)
   {
      this.textCost = ztextCost;
   }

   public String getTextLitres()
   {
      return textLitres;
   }

   public void setTextLitres(String ztextLitres)
   {
      this.textLitres = ztextLitres;
   }

   public String getOutputdatediff()
   {
      return outputDatediff;
   }

   public void setOutputdatediff(String zoutputdatediff)
   {
      this.outputDatediff = zoutputdatediff;
   }

   public double getOutputFuelUsed()
   {
      return outputFuelUsed;
   }

   public void setOutputFuelUsed(double zoutputfuelused)
   {
      this.outputFuelUsed = zoutputfuelused;
   }

   public double getOutputLitresUsed()
   {
      return outputLitreUsed;
   }

   public void setOutputLitresUsed(double zoutputLitresused)
   {
      this.outputLitreUsed = zoutputLitresused;
   }

   public double getOutputMiles()
   {
      return outputMiles;
   }

   public void setOutputMiles(double zoutputMiles)
   {
      this.outputMiles = zoutputMiles;
   }

   public double getOutputCostMiles()
   {
      return outputCostPerMile;
   }

   public void setOutputCostPerMile(double zoutputCostMiles)
   {
      this.outputCostPerMile = zoutputCostMiles;
   }
}



