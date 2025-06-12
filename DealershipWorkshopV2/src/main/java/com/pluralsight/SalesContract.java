package com.pluralsight;

public class SalesContract extends Contract{
    private double salesTax = 0.05;
    private double recordingFee = 100.0;
    private double processingFee;
    private boolean financeVehicle;
    

    public SalesContract(String date, String customerName, String customerEmail, boolean vehicleSold, double salesTax, double recordingFee, double processingFee, boolean financeVehicle) {
        super(date, customerName, customerEmail, true);
        this.salesTax = salesTax;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.financeVehicle = financeVehicle;
        calculateProcessingFee();
    }

    public double getSalesTax() {
        return salesTax = salesTax;
    }

    public double getRecordingFee() {
        return recordingFee = recordingFee;
    }

    private void calculateProcessingFee() {
        double price = vehicle.getPrice();

        if (price < 10000) {
            this.processingFee = 295;
        }else {
            this.processingFee = 495;
        }
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanceVehicle() {
        return financeVehicle;
    }

    public void setFinanceVehicle(boolean financeVehicle) {
        this.financeVehicle = financeVehicle;
    }
}
