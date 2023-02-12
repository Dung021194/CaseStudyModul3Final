package com.example.petshop.controller;

import com.example.petshop.dao.CustomerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "signupServlet", value = "/signupAccount")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            createCustomer(request,response);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void createCustomer(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password") ;
        String rePassword = request.getParameter("rePassword") ;
        String messSignup = "Password do not match!!!";
        String messCustomer = "Email is exits!!!";

        if (!password.equals(rePassword) && email.equals("admin@gmail.com")){
            request.setAttribute("messSignupRepass",messSignup);
            request.getRequestDispatcher("signup.jsp").forward(request,response);
        }else {
            CustomerDAO customerDAO = new CustomerDAO();
            if (customerDAO.checkExistCustomer(email) == null){
                customerDAO.creatCustomer(email,password,fullName);
                request.setAttribute("messLoginSuccess","Congratulations, Register success");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }else {
                request.setAttribute("messSignup",messCustomer);
                request.getRequestDispatcher("signup.jsp").forward(request,response);
            }
        }
    }
}