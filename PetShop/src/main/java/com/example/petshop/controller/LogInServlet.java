package com.example.petshop.controller;

import com.example.petshop.dao.CustomerDAO;
import com.example.petshop.model.Customer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/login")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            loginProcess(request,response);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            loginProcess(request,response);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void loginProcess(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.checkLogin(email, password);
        if (customer==null){
            request.setAttribute("messLogin","Wrong email or Password, enter again");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("account",customer);
            response.sendRedirect("home");

        }

    }
}
