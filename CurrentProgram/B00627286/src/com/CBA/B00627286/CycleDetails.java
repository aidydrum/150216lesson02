package com.CBA.B00627286;

/**
 * Created with IntelliJ IDEA.
 * User: anon
 * Date: 08/07/13
 * Time: 13:20
 * UPDATE COMMENT ABOUT PROGRAM HERE
 */
public class CycleDetails {
    public String inputDate = "";
    public String textCost = "";
    public String distance = "";
    public String totalCost = "";
    public String multiple = "";
    public Double costPerMile = 0.00;

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String zinputDate) {
        this.inputDate = zinputDate.trim();
    }

    public String getTextCost() {
        return textCost;
    }

    public void setTextCost(String ztextCost) {
        this.textCost = ztextCost.trim();
    }

    public String getOutputMiles() {
        return distance;
    }

    public void setOutputMiles(String zoutputMiles) {
        this.distance = zoutputMiles.trim();
    }

    public Double getOutputCostMiles() {
        return costPerMile;
    }

    public void setOutputCostMiles(Double zoutputMiles) {
        this.costPerMile = zoutputMiles;
    }
}



