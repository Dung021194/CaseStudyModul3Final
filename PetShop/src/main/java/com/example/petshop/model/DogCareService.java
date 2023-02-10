package com.example.petshop.model;

public class DogCareService {
    private int id;
    private String name;
    private String description;
    private double price;
    private int customerId;

    public DogCareService(int id, String name, String description, double price, int customerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.customerId = customerId;
    }

    public DogCareService(String name, String description, double price, int customerId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.customerId = customerId;
    }

    public DogCareService() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "DogCareService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", customerId=" + customerId +
                '}';
    }
}
