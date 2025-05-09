package com.pluralsight;

public class UI {

    //   === Attributes of a User Interface object ===
    private Dealership dealership;

    public UI(){

        Dealership dealership1 = new Dealership("Rolls Royce", "634 Random St", "342-5346-4552");
        initDealership(dealership1);
    }

    //   === Methods ===

    // Method to initialize a dealership (Will be changed/removed later)
    public void initDealership(Dealership dealership){
        this.dealership = dealership;
    }

    public void display() {

    }

    private void processGetByPrice() {
        if (this.dealership != null){

        }
    }

    private void processGetByMakeModel() {
        if (this.dealership != null){

        }
    }

    private void processGetByYear() {
        if (this.dealership != null){

        }
    }

    private void processGetByColor() {
        if (this.dealership != null){

        }
    }

    private void processGetByMileage() {
        if (this.dealership != null){

        }
    }

    private void processGetByType() {
        if (this.dealership != null){

        }
    }

    private void processGeAllVehicles() {
        if (this.dealership != null){

        }
    }

    private void processAddVehicles() {
        if (this.dealership != null){

        }
    }

    private void processRemoveVehicle() {
        if (this.dealership != null){

        }
    }

}
