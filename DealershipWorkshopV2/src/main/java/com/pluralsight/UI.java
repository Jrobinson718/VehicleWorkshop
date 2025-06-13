package com.pluralsight;

import java.util.InputMismatchException;
import java.util.List;

public class UI {

    //   === Attributes of a User Interface object ===
    private Dealership dealership;
    private Console console;
    private DealershipFileManager fileManager;
    private ContractFileManager contractFileManager;

    public UI() {
        this.console = new Console();
        this.fileManager = new DealershipFileManager();
        this.contractFileManager = new ContractFileManager();
    }

    //   === Methods ===

    // Initialization method
    private void init() {
        this.dealership = this.fileManager.getDealership();

        if (this.dealership == null) {
            System.out.println("ERROR: Dealership data could not be loaded from the file");
        } else {
            System.out.println("\nDealership data loaded successfully for: " + dealership.getName() + "\n");
        }
    }

    // Helper method to display vehicles
    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("\nNo vehicles to display based on the given criteria.");
            return;
        }
        System.out.println("\n   === Vehicle Listings ===");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }
        System.out.println("=======================");
    }

    // Helper method to display menu
    private void displayMenu() {
        System.out.println("\n======= Dealership Menu =======" +
                "\n1) Find vehicles within a price range" +
                "\n2) Find vehicles by make and model" +
                "\n3) Find vehicles by year range" +
                "\n4) Find vehicles by color" +
                "\n5) Find vehicles by mileage range" +
                "\n6) Find vehicles by type (e.g., Car, Truck, SUV)" +
                "\n7) List ALL vehicles" +
                "\n8) Add a vehicle" +
                "\n9) Remove a vehicle" +
                "\n10) Sell/Lease a vehicle" +
                "\n0) Exit");
    }

    // Main display method for user interactions
    public void display() {
        init();

        if (this.dealership == null) {
            String choice = console.promptForString("Dealership data failed to load. Some functionalities may not work. " +
                    "\nDo you want you want to continue with an empty dealership placeholder? (yes/no): ");
            if (choice.equals("yes")) {
                System.out.println("Generating an empty dealership placeholder.");
                this.dealership = new Dealership("Temporary Placeholder", "N/A", "N/A");
            } else {
                System.out.println("Exiting application as dealership data is unavailable. Have a good day!");
                return;
            }

        }
        boolean keepRunning = true;
        while (keepRunning) {
            displayMenu();
            String command = console.promptForString("Please make a numbered selection: ");

            switch (command) {
                case "1":
                    processGetByPrice();
                    break;
                case "2":
                    processGetByMakeModel();
                    break;
                case "3":
                    processGetByYear();
                    break;
                case "4":
                    processGetByColor();
                    break;
                case "5":
                    processGetByMileage();
                    break;
                case "6":
                    processGetByType();
                    break;
                case "7":
                    processGetAllVehicles();
                    break;
                case "8":
                    processAddVehicle();
                    break;
                case "9":
                    processRemoveVehicle();
                    break;
                case "10":
                    processSellLeaseVehicle();
                    break;
                case "0":
                    System.out.println("Thank you for using the Dealership Application! Goodbye. :)");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void processGetByPrice() {
        System.out.println("\nFinding vehicles within price range...\n");
        if (this.dealership == null) {
            System.out.println("Dealership data is not available.");
            return;
        }
        try {
            float minPrice = console.promptForFloat("Enter minimum price: ");
            float maxPrice = console.promptForFloat("Enter maximum price: ");
            if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
                System.out.println("Invalid price range. Please enter positive numbers with min <= max.");
                return;
            }
            List<Vehicle> vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
            displayVehicles(vehicles);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input format for price. Please enter a number.");
        } catch (Exception e) {
            System.out.println("An error occurred while processing price range search: " + e.getMessage());
        }
    }

    private void processGetByMakeModel() {
        System.out.println("\nFinding vehicles by make/model...\n");
        if (this.dealership == null) {
            System.out.println("Dealership data is not available.");
            return;
        }

        try {
            String make = console.promptForString("Enter vehicle make: ");
            String model = console.promptForString("Enter vehicle model: ");
            if (make.isEmpty() && model.isEmpty()) {
                System.out.println("\nError: Make and model cannot both be empty.");
                return;
            }
            List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
            displayVehicles(vehicles);
        } catch (Exception e) {
            System.out.println("An error occurred while processing make/model search: " + e.getMessage());
        }
    }

    private void processGetByYear() {
        System.out.println("\nFinding vehicles by year range...\n");
        if (this.dealership == null) {
            System.out.println("Dealership data is not available.");
        }
        try {
            int minYear = console.promptForInt("Enter minimum year: ");
            int maxYear = console.promptForInt("Enter maximum year: ");
            if (minYear > maxYear || minYear < 1886 || maxYear > java.time.Year.now().getValue() + 1) {
                System.out.println("Invalid year range, please enter a valid range.");
                return;
            }
            List<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
            displayVehicles(vehicles);
        } catch (InputMismatchException e) {
            System.out.println("Invalid year format. Please enter a whole number.");
        } catch (Exception e) {
            System.out.println("An error occurred while processing year range search: " + e.getMessage());
        }
    }

    private void processGetByColor() {
        System.out.println("\nFinding vehicles by color...\n");
        if (this.dealership == null) {
            System.out.println("Dealership data is not available.");
        }
        try {
            String color = console.promptForString("Enter vehicle color: ");
            if (color.isEmpty()) {
                System.out.println("Color cannot be empty.");
                return;
            }
            List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
            displayVehicles(vehicles);
        } catch (Exception e) {
            System.out.println("An error occurred while processing color search: " + e.getMessage());
        }
    }

    private void processGetByMileage() {
        System.out.println("\nFinding vehicles by mileage...\n");
        if (this.dealership == null) {
            System.out.println("Dealership data is not available.");
        }
        try {
            int minMileage = console.promptForInt("Enter minimum mileage: ");
            int maxMileage = console.promptForInt("Enter maximum mileage: ");
            if (minMileage < 0 || maxMileage < 0 || minMileage > maxMileage) {
                System.out.println("Invalid mileage range. Please enter positive numbers with min <= max");
                return;
            }
            List<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);
            displayVehicles(vehicles);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input format for mileage. Please enter a whole number.");
        } catch (Exception e) {
            System.out.println("An error occurred while processing mileage range search: " + e.getMessage());
        }
    }

    private void processGetByType() {
        System.out.println("\nFinding vehicles by type...\n");
        if (this.dealership == null) {
            System.out.println("Dealership data is not available.");
            return;
        }
        try {
            String type = console.promptForString("Enter vehicle type (e.g., SUV, Sedan, Truck, Coupe): ");
            if (type.isEmpty()) {
                System.out.println("Vehicle type cannot be empty.");
                return;
            }
            List<Vehicle> vehicles = dealership.getVehiclesByType(type);
            displayVehicles(vehicles);
        } catch (Exception e) {
            System.out.println("An error occurred while processing vehicle type search " + e.getMessage());
        }
    }

    private void processGetAllVehicles() {
        System.out.println("\nFListing all vehicles...\n");
        if (this.dealership == null) {
            System.out.println("Dealership data is not available, cannot display vehicles.");
        }

        List<Vehicle> allVehicles = this.dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    private void processAddVehicle() {
        System.out.println("\nAdding a new vehicle...\n");
        if (this.dealership == null) {
            System.out.println("Dealership data is not available, cannot add vehicle.");
        }
        try {
            System.out.println("Enter details for the new vehicle: ");
            int vin = console.promptForInt("VIN: ");

            for (Vehicle v : dealership.getAllVehicles()) {
                if (v.getVin() == vin) {
                    System.out.println("ERROR: A vehicle with this VIN already exists.");
                    return;
                }
            }
            int year = console.promptForInt("Year: ");
            String make = console.promptForString("Make: ");
            String model = console.promptForString("Model: ");
            String vehicleType = console.promptForString("Vehicle Type: ");
            String color = console.promptForString("Color: ");
            int odometer = console.promptForInt("Odometer reading: ");
            double price = console.promptForFloat("Price: ");

            if (make.isEmpty() || model.isEmpty() || vehicleType.isEmpty() || color.isEmpty() || price < 0 || year < 1886 || odometer < 0) {
                System.out.println("Invalid vehicle data. Please make sure are fields are filled correctly with proper values.");
                return;
            }

            Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            this.dealership.addVehicle(newVehicle);
            System.out.println("\nVehicle added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid data format entered. Please endure only numbers are entered for numerical fields.");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the vehicle: " + e.getMessage());
        }
    }

    private void processRemoveVehicle() {
        System.out.println("\nRemoving a vehicle...\n");
        if (this.dealership == null) {
            System.out.println("Dealership data is not available, cannot remove vehicle.");
        }
        try {
            int vinToRemove = console.promptForInt("Enter VIN of the vehicle you'd like to remove.");
            Vehicle vehicleToBeRemoved = null;

            for (Vehicle v : this.dealership.getAllVehicles()) {
                if (v.getVin() == vinToRemove) {
                    vehicleToBeRemoved = v;
                    break;
                }
            }
            if (vehicleToBeRemoved != null) {
                dealership.removeVehicle(vehicleToBeRemoved);
                System.out.println("Vehicle with VIN: " + vinToRemove + ", removed successfully!");
            } else {
                System.out.println("Vehicle with VIN: " + vinToRemove + ", not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid VIN format. Please enter a whole number.");
        } catch (Exception e) {
            System.out.println("An error occurred while removing the vehicle: " + e.getMessage());
        }
    }

    private void processSellLeaseVehicle() {
        System.out.println("\nSell/Lease a vehicle...\n");

        if (this.dealership == null) {
            System.out.println("Dealership data is not available.");
            return;
        }

        try {
            int vin = console.promptForInt("Enter VIN of vehicle to sell/lease: ");

            Vehicle selectedVehicle = null;
            for (Vehicle v : dealership.getAllVehicles()) {
                if (v.getVin() == vin) {
                    selectedVehicle = v;
                    break;
                }
            }

            if (selectedVehicle == null) {
                System.out.println("Vehicle with VIN " + vin + " not found.");
                return;
            }

            System.out.println("Selected Vehicle:");
            System.out.println(selectedVehicle.toString());

            String customerName = console.promptForString("Customer name: ");
            String customerEmail = console.promptForString("Customer email: ");

            String date = java.time.LocalDate.now().toString();

            String contractType = console.promptForString("\nIs this a Sale or a Lease?\n" +
                    "Enter 'S' for Sale 'L' for lease: ");

            if (contractType.toLowerCase().startsWith("s")) {
                processSale(date, customerName, customerEmail, selectedVehicle);
            } else if (contractType.toLowerCase().startsWith("l")) {
                processLease(date, customerName, customerEmail, selectedVehicle);
            }else {
                System.out.println("Invalid option. Please enter 'S' for Sale or 'L' for Lease.");
                return;
            }

            dealership.removeVehicle(selectedVehicle);
            System.out.println("\nVehicle removed from inventory");
        }catch (Exception e) {
            System.out.println("An error occurred while processing the sale/lease: " + e.getMessage());
        }
    }

    private void processSale(String date, String customerName, String customerEmail, Vehicle vehicle) {
        try {
            String financeChoice = console.promptForString("Do you want to finance this vehicle? (Y/N): ");
            boolean finance = financeChoice.toLowerCase().startsWith("y");

            SalesContract salesContract = new SalesContract(date, customerName, customerEmail, vehicle, finance);

            System.out.println("\n=== SALES CONTRACT SUMMARY ===");
            System.out.printf("Vehicle: %d %s %s\n", vehicle.getYear(), vehicle.getMake(), vehicle.getModel());
            System.out.printf("Customer: %s (%s)\n", customerName, customerEmail);
            System.out.printf("Vehicle Price: $%.2f\n", vehicle.getPrice());
            System.out.printf("Sales Tax: $%.2f\n", salesContract.getSalesTax());
            System.out.printf("Recording Fee: $%.2f\n", salesContract.getRecordingFee());
            System.out.printf("Processing Fee: $%.2f\n", salesContract.getProcessingFee());
            System.out.printf("Total Price: $%.2f\n", salesContract.getTotalPrice());
            System.out.printf("Finance: %s\n", finance ? "YES" : "NO");

            if (finance) {
                System.out.printf("Monthly Payment: $%.2f\n", salesContract.getMonthlyPayment());
            }
            System.out.println("==============================");

            contractFileManager.saveContract(salesContract);

        }catch (Exception e) {
            System.out.println("Error processing sale: " + e.getMessage());
        }
    }

    private void processLease(String date, String customerName, String customerEmail, Vehicle vehicle) {
        try {
            int currentYear = java.time.Year.now().getValue();

            if (currentYear - vehicle.getYear() > 3) {
                System.out.println("ERROR: Cannot lease a vehicle over 3 years old");
                return;
            }

            LeaseContract leaseContract = new LeaseContract(date, customerName, customerEmail, vehicle);

            System.out.println("\n=== LEASE CONTRACT SUMMARY ===");
            System.out.printf("Vehicle: %d %s %s\n", vehicle.getYear(), vehicle.getMake(), vehicle.getModel());
            System.out.printf("Customer: %s (%s)\n", customerName, customerEmail);
            System.out.printf("Vehicle Price: $%.2f\n", vehicle.getPrice());
            System.out.printf("Expected Ending Value: $%.2f\n", leaseContract.getExpectedEndingValue());
            System.out.printf("Lease Fee: $%.2f\n", leaseContract.getLeaseFee());
            System.out.printf("Total Lease Cost: $%.2f\n", leaseContract.getTotalPrice());
            System.out.printf("Monthly Payment: $%.2f\n", leaseContract.getMonthlyPayment());
            System.out.println("==============================");

            contractFileManager.saveContract(leaseContract);

        }catch (Exception e) {
            System.out.println("Error processing lease: " + e.getMessage());
        }
    }
}
