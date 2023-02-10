package com.example.petshop.service;


import com.example.petshop.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    List<Customer> customers = new ArrayList<>();

    public List<Customer> findAll() {
        return new ArrayList<>(customers);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findById(int id) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == id) {
                return customers.get(i);
            }
        }
        return null;
    }

    public int findIndexById(int id) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == id) {
                return i;
            }
        }
        return 1;
    }

    public void updateCustomer(int id , Customer customer){
        customers.set( findIndexById(id) ,customer);
    }

    public void deleteCustomer(int id){
        customers.remove(findById(id));
    }
}
