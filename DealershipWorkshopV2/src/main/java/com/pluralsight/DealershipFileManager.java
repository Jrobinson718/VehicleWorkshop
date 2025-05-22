package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DealershipFileManager  {
    //   === Initializes the Inventory csv to the fileName variable
    private static final String fileName = "inventory.csv";

    public DealershipFileManager() {

    }

    //   === Methods ===
    public Dealership getDealership() {
        Dealership dealership = null;

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            String vehicleLine;

            // Reads first line for dealership details
            if ((line = reader.readLine()) != null) {
                String[] dealershipDetails = line.split("\\|");
                if (dealershipDetails.length == 3) {
                    String name = dealershipDetails[0];
                    String address = dealershipDetails[1];
                    String phone = dealershipDetails[2];
                    dealership = new Dealership(name, address, phone);
                }else {
                    System.out.println("Invalid dealership data format");
                    return null;
                }
            }else {
                System.out.println("Dealership file is empty or count not read first line");
            }

            while ((vehicleLine = reader.readLine()) != null) {
                String[] vehicleDetails = vehicleLine.split("\\|");
                if (vehicleDetails.length == 8) {
                    try {
                        Vehicle vehicle = getVehicle(vehicleDetails);
                        dealership.addVehicle(vehicle);
                    }catch (NumberFormatException e) {
                        System.out.println("Error parsing vehicle data: " + line + "." +
                                "\nSkipping vehicle.");
                    }
                }else {
                    System.out.println("Invalid vehicle data format: " + line + "." +
                            "\nSkipping vehicle.");
                }
            }
        }catch (IOException e) {
            System.out.println("Error reading dealership file: " + e.getMessage());
            return null;
        }
        return dealership;
    }

    private Vehicle getVehicle(String[] vehicleDetails) {
        int vin = Integer.parseInt(vehicleDetails[0]);
        int year = Integer.parseInt(vehicleDetails[1]);
        String make = vehicleDetails[2];
        String model = vehicleDetails[3];
        String vehicleType = vehicleDetails[4];
        String color = vehicleDetails[5];
        int odometer = Integer.parseInt(vehicleDetails[6]);
        double price = Double.parseDouble(vehicleDetails[7]);

        return new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
    }

    public void saveDealership(Dealership dealership) {

        List<Vehicle> vehicles = dealership.getAllVehicles();

        try(PrintWriter writer = new PrintWriter("inventory.csv")) {

            writer.printf("%s|%s|%s", dealership.getName(), dealership.getAddress(), dealership.getPhone());
            writer.println();

            for (Vehicle v : vehicles) {
                writer.printf("%d|%d|%s|%s|%s|%s|%d|%.2f%n",
                        v.getVin(),
                        v.getYear(),
                        v.getMake(),
                        v.getModel(),
                        v.getVehicleType(),
                        v.getColor(),
                        v.getOdometer(),
                        v.getPrice());
            }

        }catch (IOException e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
