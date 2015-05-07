package com.CBA.B00627286;

/**
 * Created with IntelliJ IDEA.
 * User: anon
 * Date: 08/07/13
 * Time: 13:20
 * UPDATE COMMENT ABOUT PROGRAM HERE
 */
public class VehicleDetails {
    protected String inputDate = "";
    protected String vehicleID = "";
    protected String odometerRead = "";
    protected String pretextGauge = "";
    protected String postTextGauge = "";
    protected String textCost = "";
    protected String textLitres = "";
    protected String outputDatediff = "";

    protected double outputMiles = 000000.000;
    protected double outputLitreUsed = 0000.000;
    protected double outputFuelUsed = 0000.000;
    protected double outputCostPerMile = 0000.000;

    protected String getInputDate() {
        return inputDate;
    }

    protected void setInputDate(String zinputDate) {
        this.inputDate = zinputDate.trim();
    }

    protected String getVehicleID() {
        return vehicleID;
    }

    protected void setVehicleID(String zvehicleID) {
        //this.vehicleID = zvehicleID.trim();
        this.vehicleID = zvehicleID;
    }

    protected String getOdometerRead() {
        return odometerRead;
    }

    protected void setOdometerRead(String zodometerRead) {
        this.odometerRead = zodometerRead;
    }

    protected String getPreGauge() {
        return pretextGauge;
    }

    protected void setPreTextGauge(String zpretextGauge) {
        this.pretextGauge = zpretextGauge;
    }

    protected String getPostTextGauge() {
        return postTextGauge;
    }

    protected void setPostTextGauge(String zpostTextGauge) {
        this.postTextGauge = zpostTextGauge;
    }

    protected String getTextCost() {
        return textCost;
    }

    protected void setTextCost(String ztextCost) {
        this.textCost = ztextCost;
    }

    protected String getTextLitres() {
        return textLitres;
    }

    protected void setTextLitres(String ztextLitres) {
        this.textLitres = ztextLitres;
    }

    protected String getOutputdatediff() {
        return outputDatediff;
    }

    protected void setOutputdatediff(String zoutputdatediff) {
        this.outputDatediff = zoutputdatediff;
    }

    protected double getOutputFuelUsed() {
        return outputFuelUsed;
    }

    protected void setOutputFuelUsed(double zoutputfuelused) {
        this.outputFuelUsed = zoutputfuelused;
    }

    protected double getOutputLitresUsed() {
        return outputLitreUsed;
    }

    protected void setOutputLitresUsed(double zoutputLitresused) {
        this.outputLitreUsed = zoutputLitresused;
    }

    protected double getOutputMiles() {
        return outputMiles;
    }

    protected void setOutputMiles(double zoutputMiles) {
        this.outputMiles = zoutputMiles;
    }

    protected double getOutputCostMiles() {
        return outputCostPerMile;
    }

    protected void setOutputCostPerMile(double zoutputCostMiles) {
        this.outputCostPerMile = zoutputCostMiles;
    }
}



