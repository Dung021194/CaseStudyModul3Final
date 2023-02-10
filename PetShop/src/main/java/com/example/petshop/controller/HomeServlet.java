package com.example.petshop.controller;

import com.example.petshop.dao.DogDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "homeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
             break;
            default:
                try {
                    displayALlPet(request, response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
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
                break;
            default:
                try {
                    displayALlPet(request, response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
        }
    }
    public void displayALlPet(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        DogDAO dogDAO = new DogDAO();
        request.setAttribute("listPet",dogDAO.getListDog());
        request.getRequestDispatcher("home.jsp").forward(request,response);

    }
}
