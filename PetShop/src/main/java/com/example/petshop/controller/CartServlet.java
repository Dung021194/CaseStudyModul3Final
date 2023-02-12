package com.example.petshop.controller;

import com.example.petshop.dao.CartDao;
import com.example.petshop.dao.CartDetailDAO;
import com.example.petshop.dao.DogDAO;
import com.example.petshop.model.Cart;
import com.example.petshop.model.CartDetail;
import com.example.petshop.model.Customer;
import com.example.petshop.model.Dog;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                try {
                    addToCart(request,response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "addPaymentPet":
                try {
                    addToCartPayment(request,response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delCartPayment":
                try {
                    delCartPayment(request,response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "paymentReview":
                try {
                    paymentReview(request,response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "deletePet":
                try {
                    delPetInCart(request,response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "deleteCart":
                try {
                    delCart(request,response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "order":
                orderNow(request,response);
                break;

            default:
                response.sendRedirect("home");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "paymentReview":
                try {
                    paymentReview(request,response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;

            default:
                response.sendRedirect("home");

        }
    }
    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {
        DogDAO dogDAO = new DogDAO();
        CartDetailDAO cartDetailDAO = new CartDetailDAO();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("account");
        int cartDetailNum = (int)session.getAttribute("cartDetailNum") +1;
        session.setAttribute("cartDetailNum",cartDetailNum);
        int id = Integer.parseInt(request.getParameter("id"));
        boolean status = true;
        Dog dog = dogDAO.getDogById(id);
        Object obj = session.getAttribute("cart");
        if (obj==null){
            CartDetail cartDetail = new CartDetail();
            cartDetail.setDog(dog);
            cartDetail.setQuantity(1);
            cartDetail.setCustomerId(customer.getId());
            cartDetail.setPrice(dog.getPrice());
            cartDetail.setStatus(status);
            cartDetailDAO.createCartDetail(cartDetail);
            Map<Integer,CartDetail> cartDetailMap = new HashMap<>();
            cartDetailMap.put(id,cartDetail);
            session.setAttribute("cart",cartDetailMap);
            response.sendRedirect("home");
        }else {
            Map<Integer,CartDetail> cartDetailMap = (Map<Integer,CartDetail>) obj;
            CartDetail cartDetail = cartDetailMap.get(id);
            if (cartDetail == null){
                cartDetail = new CartDetail();
                cartDetail.setDog(dog);
                cartDetail.setQuantity(1);
                cartDetail.setCustomerId(customer.getId());
                cartDetail.setPrice(dog.getPrice());
                cartDetail.setStatus(status);
                cartDetailMap.put(id,cartDetail);
                cartDetailDAO.createCartDetail(cartDetail);
            }
            else {
                cartDetail.setQuantity(cartDetail.getQuantity()+1);
                cartDetailDAO.updateQuantityCartDetail(cartDetail);
            }
            session.setAttribute("cart",cartDetailMap);
            response.sendRedirect("home");
        }
    }
    private void paymentReview(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
    HttpSession session = request.getSession();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    CartDao cartDao = new CartDao();
    Map<Integer,CartDetail> cartDetailMap = (Map<Integer,CartDetail>) session.getAttribute("cart");
    Customer customer = (Customer) session.getAttribute("account");
    boolean status = true;
    if (cartDetailMap!=null){
        Cart cart = new Cart();
        cart.setCustomerId(customer.getId());
        cart.setPaymentDate(sdf.format(new Date()));
        cart.setStatus(status);
        Double totalPayment = 0d;
        List<CartDetail> cartDetailList = new ArrayList<>();
        for (Map.Entry<Integer,CartDetail> entry : cartDetailMap.entrySet() ) {
            CartDetail cartDetail = entry.getValue();
            cartDetailList.add(cartDetail);
            totalPayment += cartDetail.getPrice()*cartDetail.getQuantity();
        }
        Double vat = totalPayment*0.1;
        Double total = totalPayment + vat;
        cart.setTotalPayment(total);
        Cart findCart = cartDao.findCartById(cart.getCustomerId());
        if (findCart ==null){
            cartDao.createCart(cart);
        }else {
            cartDao.updateCart(cart);}
        session.setAttribute("payment",cart);
        request.setAttribute("listPayment",cartDetailList);
        request.setAttribute("petPayment",totalPayment);
        request.setAttribute("vat",vat);
        request.setAttribute("total",total);
        request.getRequestDispatcher("Cart.jsp").forward(request,response);
    }else {
        request.setAttribute("messPayment","No pet in your cart!");
        request.getRequestDispatcher("home?action=").forward(request,response);
    }
    }
    private void addToCartPayment(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {;
        HttpSession session = request.getSession();
        CartDetailDAO cartDetailDAO = new CartDetailDAO();
        int cartDetailNum = (int)session.getAttribute("cartDetailNum") +1;
        session.setAttribute("cartDetailNum",cartDetailNum);
        int id = Integer.parseInt(request.getParameter("id"));
        Map<Integer,CartDetail> cartDetailMap = (Map<Integer,CartDetail>) session.getAttribute("cart");
        CartDetail cartDetail = cartDetailMap.get(id);
        cartDetail.setQuantity(cartDetail.getQuantity()+1);
         CartDetail updateCartDetail = cartDetailDAO.findCartDetail(id,cartDetail.getCustomerId());
        cartDetailDAO.updateQuantityCartDetail(updateCartDetail);
        session.setAttribute("cart",cartDetailMap);
        response.sendRedirect("/CartServlet?action=paymentReview");
        }
    private void delCartPayment(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {;
        HttpSession session = request.getSession();
        CartDetailDAO cartDetailDAO = new CartDetailDAO();
        int cartDetailNum = (int)session.getAttribute("cartDetailNum") -1;
        if (cartDetailNum >0){
        session.setAttribute("cartDetailNum",cartDetailNum);}else {
            session.setAttribute("cartDetailNum",0);
        }
        int id = Integer.parseInt(request.getParameter("id"));
        Map<Integer,CartDetail> cartDetailMap = (Map<Integer,CartDetail>) session.getAttribute("cart");
        CartDetail cartDetail = cartDetailMap.get(id);
        if (cartDetail.getQuantity() > 0){
        cartDetail.setQuantity(cartDetail.getQuantity()-1);
            CartDetail updateCartDetail = cartDetailDAO.findCartDetail(id,cartDetail.getCustomerId());
            cartDetailDAO.updateCartDetail(updateCartDetail);
        }
        else {
            request.setAttribute("delPetMess","you can't reduce because quantity = 0");
            request.getRequestDispatcher("/CartServlet?action=paymentReview").forward(request,response);
        }
        session.setAttribute("cart",cartDetailMap);
        response.sendRedirect("/CartServlet?action=paymentReview");
    }
    private void delPetInCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        CartDetailDAO cartDetailDAO = new CartDetailDAO();
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = (Customer) session.getAttribute("account");
        Map<Integer,CartDetail> cartDetailMap = (Map<Integer,CartDetail>) session.getAttribute("cart");
        cartDetailMap.remove(id);
        cartDetailDAO.deleteCartDetail(id,customer.getId());
        response.sendRedirect("/CartServlet?action=paymentReview");
    }
    private void delCart(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {
        CartDetailDAO cartDetailDAO = new CartDetailDAO();
        CartDao cartDao = new CartDao();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("account");
        cartDetailDAO.deleteAllCartDetail(customer.getId());
        cartDao.deleteAllCart(customer.getId());
        Map<Integer,CartDetail> cartDetailMap = (Map<Integer,CartDetail>) session.getAttribute("cart");
        cartDetailMap.clear();
        response.sendRedirect("/CartServlet?action=paymentReview");
        session.setAttribute("cartDetailNum",0);
    }
    private void orderNow(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Map<Integer,CartDetail> cartDetailMap = (Map<Integer,CartDetail>) session.getAttribute("cart");
        int cartNumber = (int) session.getAttribute("cartDetailNum");
        if (cartNumber > 0) {
            cartDetailMap.clear();
            session.removeAttribute("cart");
            session.setAttribute("cartDetailNum", 0);
            request.setAttribute("messPayment", "Your order is successfully, thank you");
            request.getRequestDispatcher("home").forward(request,response);
        }
        else {
            response.sendRedirect("home");
        }
    }
}
