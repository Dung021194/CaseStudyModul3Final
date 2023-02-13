package com.example.petshop.model;

public class CartFind {
    Dog dog;
    int quantity;
    Double total;
    private String paymentDate;

    public CartFind(Dog dogId, int quantity, Double total, String paymentDate) {
        this.dog = dogId;
        this.quantity = quantity;
        this.total = total;
        this.paymentDate = paymentDate;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dogId) {
        this.dog = dogId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "CartFind{" +
                "dog=" + dog +
                ", quantity=" + quantity +
                ", total=" + total +
                ", paymentDate='" + paymentDate + '\'' +
                '}';
    }
}
