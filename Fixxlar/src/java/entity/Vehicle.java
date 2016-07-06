/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Joanne
 */
public class Vehicle {
    
    private int id;
    private String make;
    private String model;
    private int year;
    private String plateNumber;
    private int customerID;
    
    public Vehicle(int id, String make, String model, int year, String plateNumber, int customerID) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.plateNumber = plateNumber;
        this.customerID = customerID;
    }
    
    public int getId() {
        return id;
    }
    
    public String getMake() {
        return make;
    }
    
    public String getModel() {
        return model;
    }
    
    public String getPlateNumber() {
        return plateNumber;
    }
    
    public int getYear() {
        return year;
    }
    
    public int getCustomerID() {
        return customerID;
    }
}
