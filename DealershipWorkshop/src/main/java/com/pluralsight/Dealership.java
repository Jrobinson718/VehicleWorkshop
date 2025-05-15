package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    //   === Attributes of a Dealership object ===
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

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
        return new ArrayList<>();
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return new ArrayList<>();
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        return new ArrayList<>();
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return new ArrayList<>();
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        return new ArrayList<>();
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        return new ArrayList<>();
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(this.inventory);
    }

    public void addVehicle(Vehicle vehicle) {
        this.inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        this.inventory.remove(vehicle);
    }

}
