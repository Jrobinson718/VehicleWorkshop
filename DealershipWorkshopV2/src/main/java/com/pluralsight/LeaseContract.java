package com.pluralsight;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;

    private static final double EXPECTED_ENDING_VALUE_RATE = 0.50;
    private static final double LEASE_FEE_RATE = 0.07;
    private static final double LEASE_INTEREST_RATE = 0.04;
    private static final int LEASE_TERM_MONTHS = 36;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, false, vehicle);
        calculateLeaseValues();
    }

    private void calculateLeaseValues() {
        double vehiclePrice = getVehicle().getPrice();
        this.expectedEndingValue = vehiclePrice * EXPECTED_ENDING_VALUE_RATE;
        this.leaseFee = vehiclePrice * LEASE_FEE_RATE;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return expectedEndingValue + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double vehiclePrice = getVehicle().getPrice();

        double principal = vehiclePrice - expectedEndingValue + leaseFee;
        double monthlyRate = LEASE_INTEREST_RATE / 12;

        double monthlyPayment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -LEASE_TERM_MONTHS));

        return monthlyPayment;
    }

    @Override
    public String toString() {
        Vehicle vehicle = getVehicle();
        return String.format("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f",
                getDate(), getCustomerName(), getCustomerEmail(),
                vehicle.getVin(), vehicle.getYear(), vehicle.getMake(),
                vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(),
                vehicle.getOdometer(), vehicle.getPrice(),
                expectedEndingValue, leaseFee, getTotalPrice(), getMonthlyPayment());
    }
}
