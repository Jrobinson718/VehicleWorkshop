package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    //   === Attributes of a Dealership object ===
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;
    private DealershipFileManager fileManager = new DealershipFileManager();

    //   === Constructor ===
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    //   === Getters & Setters ===

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    //   === Methods ===

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        if (this.inventory == null || this.inventory.isEmpty()) {
            return matchingVehicles;
        }
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        if (this.inventory == null || this.inventory.isEmpty()) {
            return matchingVehicles;
        }

        boolean makeProvided = make != null && !make.trim().isEmpty();
        boolean modelProvided = model != null && !model.trim().isEmpty();

        for (Vehicle vehicle : this.inventory) {
            boolean makeMatch = !makeProvided || vehicle.getMake().equalsIgnoreCase(make.trim());
            boolean modelMatch = !modelProvided || vehicle.getModel().equalsIgnoreCase(model.trim());

            if (makeProvided && modelProvided) {
                if (makeMatch && modelMatch) {
                    matchingVehicles.add(vehicle);
                }
            } else if (makeProvided) {
                if (makeMatch) {
                    matchingVehicles.add(vehicle);
                }
            } else if (modelProvided) {
                if (modelMatch) {
                    matchingVehicles.add(vehicle);
                }
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        if (this.inventory == null || this.inventory.isEmpty()) {
            return matchingVehicles;
        }
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        if (this.inventory == null || this.inventory.isEmpty()) {
            return matchingVehicles;
        }
        String searchColor = color.trim();
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getColor().equalsIgnoreCase(searchColor)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        if (this.inventory == null || this.inventory.isEmpty()) {
            return matchingVehicles;
        }
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        if (this.inventory == null || this.inventory.isEmpty()) {
            return matchingVehicles;
        }
        String searchType = vehicleType.trim();
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(searchType)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getAllVehicles() {
        if (this.inventory == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(this.inventory);
    }

    public void addVehicle(Vehicle vehicle) {
        if (this.inventory == null) {
            this.inventory = new ArrayList<>();
        }
        this.inventory.add(vehicle);
        fileManager.saveDealership(this);
    }

    public void removeVehicle(Vehicle vehicle) {
        if (this.inventory != null && vehicle != null) {
            this.inventory.remove(vehicle);
            fileManager.saveDealership(this);
        }
    }
}
