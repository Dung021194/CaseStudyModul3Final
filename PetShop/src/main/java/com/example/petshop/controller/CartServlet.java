package com.example.petshop.controller;


import com.example.petshop.service.CartService;
import com.example.petshop.model.Cart;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends HttpServlet {
    CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateCart(request, response);
                break;
            case "update":
                showUpdateCart(request, response);
                break;
            case "delete":
                showDeleteCart(request, response);
                break;
            default:
                listCart(request, response);
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
                createCart(request, response);
                break;
            case "update":
                updateCart(request, response);
                break;
            case "delete":
                deleteCart(request, response);
                break;
            default:
                listCart(request, response);
        }
    }

    private void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cart cart = this.cartService.findById(id);
        RequestDispatcher dispatcher;
        if (cart == null) {
            dispatcher = request.getRequestDispatcher("");
        } else {
            request.setAttribute("carts", cart);
            dispatcher = request.getRequestDispatcher("Cart/deleteCustomer.jsp");
            this.cartService.deleteCart(id);
        }
        dispatcher.forward(request, response);
    }

    private void updateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int cartId = Integer.parseInt(request.getParameter("cartId"));
        Cart cart = new Cart(id, customerId, cartId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Cart/createCustomer.jsp");
        cart.setCustomerId(customerId);
        cart.setCartId(cartId);
        this.cartService.updateCart(id, cart);
        request.setAttribute("carts", cart);
        request.setAttribute("message", "Update success");
        dispatcher.forward(request, response);
    }

    private void createCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int cartId = Integer.parseInt(request.getParameter("cartId"));
        Cart cart = new Cart(id, customerId, cartId);
        this.cartService.addCart(cart);
        request.setAttribute("carts", cart);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Cart/createCustomer.jsp");
        request.setAttribute("message", "New cart was created");
        dispatcher.forward(request, response);

    }

    private void showUpdateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cart cart = this.cartService.findById(id);
        RequestDispatcher dispatcher;
        if (cart == null) {
            dispatcher = request.getRequestDispatcher("");
        } else {
            request.setAttribute("carts", cart);
            dispatcher = request.getRequestDispatcher("Cart/updateCustomer.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void showDeleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cart cart = this.cartService.findById(id);
        RequestDispatcher dispatcher;
        if (cart == null) {
            dispatcher = request.getRequestDispatcher("");
        } else {
            dispatcher = request.getRequestDispatcher("Cart/deleteCustomer.jsp");
            request.setAttribute("carts", cart);
        }
        dispatcher.forward(request, response);
    }

    private void showCreateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Cart/createCustomer.jsp");
        dispatcher.forward(request, response);
    }

    private void listCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cart> carts = this.cartService.findAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("Cart/listCustomer.jsp");
        request.setAttribute("carts", carts);
        dispatcher.forward(request, response);
    }
}
