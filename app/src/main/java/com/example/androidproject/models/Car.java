package com.example.androidproject.models;
public class Car {
    private String factoryName;
    private String type;
    private String id;
    private String model;
    private double price;
    private String name;
    private boolean offers;  // Added property for offers
    private String fuelType; // Added property for fuel type


    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOffers() {
        return offers;
    }

    public void setOffers(boolean offers) {
        this.offers = offers;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    //setter and getter for rating


    @Override
    public String toString() {
        return "Car{" +
                "factoryName='" + factoryName + '\'' +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", offers=" + offers +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }
}
