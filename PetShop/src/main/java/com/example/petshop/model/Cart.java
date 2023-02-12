package com.example.petshop.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Cart {
     private int id;
    private int customerId;
    private String paymentDate;
    private double totalPayment;
    private boolean status;

    public Cart(int id, int customerId, String paymentDate, double totalPayment, boolean status) {
        this.id = id;
        this.customerId = customerId;
        this.paymentDate = paymentDate;
        this.totalPayment = totalPayment;
        this.status = status;
    }

    public Cart(int customerId, String paymentDate, double totalPayment,boolean status) {
        this.customerId = customerId;
        this.paymentDate = paymentDate;
        this.totalPayment = totalPayment;
        this.status = status;
    }

    public Cart(int customerId) {
        this.customerId = customerId;
    }

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", paymentDate=" + paymentDate +
                ", totalPayment=" + totalPayment +
                ", status=" + status +
                '}';
    }
}
