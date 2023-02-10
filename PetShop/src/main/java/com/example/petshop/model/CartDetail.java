package com.example.petshop.model;

public class CartDetail {
    private int id;
    private int dogId;
    private int quantity;
    private int cartId;

    public CartDetail(int id, int dogId, int quantity, int cartId) {
        this.id = id;
        this.dogId = dogId;
        this.quantity = quantity;
        this.cartId = cartId;
    }

    public CartDetail(int dogId, int quantity, int cartId) {
        this.dogId = dogId;
        this.quantity = quantity;
        this.cartId = cartId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDogId() {
        return dogId;
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "CartDetail{" +
                "id=" + id +
                ", dogId=" + dogId +
                ", quantity=" + quantity +
                ", cartId=" + cartId +
                '}';
    }

}
