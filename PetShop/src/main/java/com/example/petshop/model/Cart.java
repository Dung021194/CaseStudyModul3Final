package com.example.petshop.model;

public class Cart {
     private int id;
    private int customerId;
    private int cartId;

    public Cart(int id, int customerId, int cartId) {
        this.id = id;
        this.customerId = customerId;
        this.cartId = cartId;
    }

    public Cart(int customerId, int cartId) {
        this.customerId = customerId;
        this.cartId = cartId;
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

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", cartId=" + cartId +
                '}';
    }
}
