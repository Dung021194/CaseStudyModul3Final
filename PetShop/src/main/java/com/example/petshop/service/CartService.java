package com.example.petshop.service;


import com.example.petshop.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    List<Cart> carts = new ArrayList<>();

    public List<Cart> findAll() {
        return new ArrayList<>(carts);
    }

    public void addCart(Cart cart) {
        carts.add(cart);
    }

    public Cart findById(int id) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getId() == id) {
                return carts.get(i);
            }
        }
        return null;
    }

    public int findIndexById(int id) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getId() == id) {
                return i;
            }
        }
        return 1;
    }

    public void updateCart(int id , Cart cart){
        carts.set( findIndexById(id) ,cart);
    }

    public void deleteCart(int id){
        carts.remove(findById(id));
    }
}
