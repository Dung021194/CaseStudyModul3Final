package com.example.petshop.controller;


import com.example.petshop.model.Customer;
import com.example.petshop.service.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateCustomer(request, response);
                break;
            case "update":
                showUpdateCustomer(request, response);
                break;
            case "delete":
                showDeleteCustomer(request, response);
                break;
            default:
                listCustomer(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createCustomer(request, response);
                break;
            case "update":
                updateCustomer(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
            default:
                listCustomer(request, response);
        }
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = this.customerService.findById(id);
        RequestDispatcher dispatcher;
        if (customer == null) {
            dispatcher = request.getRequestDispatcher("");
        } else {
            request.setAttribute("customers", customer);
            dispatcher = request.getRequestDispatcher("CaseMD3/deleteCustomer.jsp");
            this.customerService.deleteCustomer(id);
        }
        dispatcher.forward(request, response);
    }

    private void showDeleteCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = this.customerService.findById(id);
        RequestDispatcher dispatcher;
        if (customer == null) {
            dispatcher = request.getRequestDispatcher("");
        } else {
            request.setAttribute("customers", customer);
            dispatcher = request.getRequestDispatcher("CaseMD3/deleteCustomer.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String passWord = request.getParameter("password");
        String fullName = request.getParameter("fullname");
        String phoneNumber = request.getParameter("phonenumber");
        String address = request.getParameter("address");
        Customer customer = new Customer(id, email, passWord, fullName, phoneNumber, address);
        RequestDispatcher dispatcher;
        customer.setEmail(email);
        customer.setPassword(passWord);
        customer.setFullName(fullName);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);
        this.customerService.updateCustomer(id, customer);
        request.setAttribute("customers", customer);
        request.setAttribute("message", "Update success");
        dispatcher = request.getRequestDispatcher("CaseMD3/updateCustomer.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createCustomer(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
        int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String passWord = request.getParameter("password");
        String fullName = request.getParameter("fullname");
        String phoneNumber = request.getParameter("phonenumber");
        String address = request.getParameter("address");
        Customer customer = new Customer(id, email, passWord, fullName, phoneNumber, address);
        this.customerService.addCustomer(customer);
        request.setAttribute("customers", customer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CaseMD3/createCustomer.jsp");
        request.setAttribute("message", "New customer was created");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void listCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = this.customerService.findAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("CaseMD3/listCustomer.jsp");
        request.setAttribute("customers", customers);
        dispatcher.forward(request, response);
    }

    private void showCreateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("CaseMD3/createCustomer.jsp");
        dispatcher.forward(request, response);
    }

    private void showUpdateCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = this.customerService.findById(id);
        RequestDispatcher dispatcher;
        if (customer == null) {
            dispatcher = request.getRequestDispatcher("");
        } else {
            request.setAttribute("customers", customer);
            dispatcher = request.getRequestDispatcher("CaseMD3/updateCustomer.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
