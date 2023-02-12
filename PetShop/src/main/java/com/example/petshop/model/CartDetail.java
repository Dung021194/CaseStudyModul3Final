package com.example.petshop.model;

public class CartDetail {
    private int id;
    private Dog dog;
    private int quantity;
    private double price;
    private int customerId;
    private boolean status;

    public CartDetail(int id, Dog dog, int quantity, double price, int customerId) {
        this.id = id;
        this.dog = dog;
        this.quantity = quantity;
        this.price = price;
        this.customerId = customerId;
        this.status = true;
    }

    public CartDetail(Dog dog, int quantity, double price, int customerId) {
        this.dog= dog;
        this.quantity = quantity;
        this.price = price;
        this.customerId = customerId;
    }

    public CartDetail(int id, Dog dog, int quantity) {
        this.id = id;
        this.dog = dog;
        this.quantity = quantity;
    }

    public CartDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CartDetail{" +
                "id=" + id +
                ", dog=" + dog +
                ", quantity=" + quantity +
                ", price=" + price +
                ", customerId=" + customerId +
                ", status=" + status +
                '}';
    }
}
