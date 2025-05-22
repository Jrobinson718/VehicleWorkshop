package com.pluralsight;

import java.util.InputMismatchException;
import java.util.List;

public class UI {

    //   === Attributes of a User Interface object ===
    private Dealership dealership;
    private Console console;
    private DealershipFileManager fileManager;

    public UI() {
        this.console = new Console();
        this.fileManager = new DealershipFileManager();
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
                "\n99) Exit");
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
                case "99":
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

}
