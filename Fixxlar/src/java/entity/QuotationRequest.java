/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author Joanne
 */
public class QuotationRequest {
    private int id;
    private String name;
    private String details;
    private String description;
    private int vehicleId;
    private String mileage;
    private String urgency;
    private double latitude;
    private double longitude;
    private String amenities;
    private String address;
    private String photos;
    private String carMake;
    private String carModel;
    private int carYear;
    private String plateNumber;
    private Timestamp date;
    private int status;
    private int workshopId;
    private double price;
    private int offerId;
    private Customer customer;
    private Vehicle vehicle;

    public QuotationRequest(int id, String name, String details, String description, Vehicle vehicle, String mileage, String urgency,
            String amenities, double latitude, double longitude, String address, String photos, Timestamp date, int status, 
            int workshopId, double price, int offerId, Customer customer) {
        this.details = details;
        this.name = name;
        this.address = address;
        this.id = id;
        this.description = description;
        this.vehicle = vehicle;
        this.mileage = mileage;
        this.urgency = urgency;
        this.amenities = amenities;
        this.latitude = latitude;
        this.longitude = longitude;
        this.photos = photos;
        this.date = date;
        this.status = status;
        this.workshopId = workshopId;
        this.price = price;
        this.offerId = offerId;
        this.customer = customer;
               

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getDescription() {
        return description;
    }

    public String getMileage() {
        return mileage;
    }

    public String getUrgency() {
        return urgency;
    }

    public String getAmenities() {
        return amenities;
    }

    public String getAddress() {
        return address;
    }

    public String getPhotos() {
        return photos;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Timestamp getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }
    
    public int getWorkshopId() {
        return workshopId;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getOfferId() {
        return offerId;
    }
    
    public Customer getCustomer() {
        return customer;
    }
}

