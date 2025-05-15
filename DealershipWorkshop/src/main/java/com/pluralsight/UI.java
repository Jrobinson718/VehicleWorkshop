package com.pluralsight;

import java.util.List;

public class UI {

    //   === Attributes of a User Interface object ===
    private Dealership dealership;
    private Console console;

    public UI(){
        this.console = new Console();
    }

    //   === Methods ===

    // Initialization method
    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();

        if (this.dealership == null) {
            System.out.println("ERROR: Dealership data could not be loaded from the file");
        }else {
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
            }else {
                System.out.println("Exiting application as dealership data is unavailable. Have a good day!");
                return;
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
    }

    private void processGetByPrice() {
        if (this.dealership != null){

        }
    }

    private void processGetByMakeModel() {
        System.out.println("\nFinding vehicles within price range...\n");
        if (this.dealership == null){
            System.out.println("Dealership data is not available.");
            return;
        }
    }

    private void processGetByYear() {
        System.out.println("\nFinding vehicles by year range...\n");
        if (this.dealership == null){
            System.out.println("Dealership data is not available.");
        }
    }

    private void processGetByColor() {
        System.out.println("\nFinding vehicles by color...\n");
        if (this.dealership == null){
            System.out.println("Dealership data is not available.");
        }
    }

    private void processGetByMileage() {
        System.out.println("\nFinding vehicles by mileage...\n");
        if (this.dealership == null){
            System.out.println("Dealership data is not available.");
        }
    }

    private void processGetByType() {
        System.out.println("\nFinding vehicles by type...\n");
        if (this.dealership == null){
            System.out.println("Dealership data is not available.");
            return;
        }

        List<Vehicle> allVehicles = this.dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    private void processGetAllVehicles() {
        System.out.println("\nFListing all vehicles...\n");
        if (this.dealership == null){
            System.out.println("Dealership data is not available, cannot display vehicles.");
        }
    }

    private void processAddVehicle() {
        System.out.println("\nAdding a new vehicle...\n");
        if (this.dealership == null){
            System.out.println("Dealership data is not available, cannot add vehicle.");
        }
    }

    private void processRemoveVehicle() {
        System.out.println("\nRemoving a vehicle...\n");
        if (this.dealership == null){
            System.out.println("Dealership data is not available, cannot remove vehicle.");
        }
    }

}
