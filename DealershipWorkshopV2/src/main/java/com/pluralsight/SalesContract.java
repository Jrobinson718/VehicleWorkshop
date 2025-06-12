package com.pluralsight;

public class SalesContract extends Contract{
    private double salesTax = 0.05;
    private double recordingFee = 100.0;
    private double processingFee;
    private boolean financeVehicle;

    // Constants
    private static final double SALES_TAX_RATE = 0.05;
    private static final double RECORDING_FEE = 100.0;
    private static final double LOW_PRICE_PROCESSING_FEE = 295.0;
    private static final double HIGH_PRICE_PROCESSING_FEE = 495.0;
    private static final double PRICE_THRESHOLD = 10000.0;

    // Finance rates
    private static final double HIGH_PRICE_INTEREST_RATE = 0.0425;
    private static final double LOW_PRICE_INTEREST_RATE = 0.0525;
    private static final int HIGH_PRICE_LOAN_LENGTH = 48;
    private static final int LOW_PRICE_LOAN_LENGTH = 24;


    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean financeVehicle) {
        super(date, customerName, customerEmail, true, vehicle);
        this.financeVehicle = financeVehicle;
        this.recordingFee = RECORDING_FEE;
        calculateFees();
    }

    private void calculateFees() {
        double vehiclePrice = getVehicle().getPrice();

        this.salesTax = vehiclePrice * SALES_TAX_RATE;

        if (vehiclePrice < PRICE_THRESHOLD) {
            this.processingFee = LOW_PRICE_PROCESSING_FEE;
        }else {
            this.processingFee = HIGH_PRICE_PROCESSING_FEE;
        }
    }

    public double getSalesTax() {
        return salesTax = salesTax;
    }

    public double getRecordingFee() {
        return recordingFee = recordingFee;
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

    @Override
    public double getTotalPrice() {
        return getVehicle().getPrice() + salesTax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!financeVehicle) {
            return 0.0;
        }

        double vehiclePrice = getVehicle().getPrice();
        double totalPrice = getTotalPrice();
        double interestRate;
        int loanMonths;

        if (vehiclePrice >= PRICE_THRESHOLD) {
            interestRate = HIGH_PRICE_INTEREST_RATE;
            loanMonths = HIGH_PRICE_LOAN_LENGTH;
        }else {
            interestRate = LOW_PRICE_INTEREST_RATE;
            loanMonths = LOW_PRICE_LOAN_LENGTH;
        }

        double monthlyRate = interestRate / 12;
        double monthlyPayment = (totalPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -loanMonths));

        return monthlyPayment;
    }

    @Override
    public String toString() {
        Vehicle vehicle = getVehicle();
        return String.format("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                getDate(), getCustomerName(), getCustomerEmail(),
                vehicle.getVin(), vehicle.getYear(), vehicle.getMake(),
                vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(),
                vehicle.getOdometer(), vehicle.getPrice(),
                salesTax, recordingFee, processingFee, getTotalPrice(),
                financeVehicle ? "YES" : "NO", getMonthlyPayment());
    }
}
