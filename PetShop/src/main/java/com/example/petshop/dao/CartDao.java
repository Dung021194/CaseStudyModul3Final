package com.example.petshop.dao;

import com.example.petshop.model.Cart;
import com.example.petshop.model.CartDetail;
import com.example.petshop.model.Dog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    String jdbcURL = "jdbc:mysql://localhost:3306/petshop";
    String jdbcUsername = "root";
    String jdbcPassword = "123456";
    private List<Cart> getListCart() throws ClassNotFoundException {
        String query = "select * from cart where status=true";
        List<Cart> cartList = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword))
        {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                cartList.add(new Cart(rs.getInt(1),rs.getInt(2),
                        rs.getString(3),rs.getDouble(4),rs.getBoolean(5)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartList;
    }
    public Cart findCartById(int id) throws ClassNotFoundException {
            String query = "select * from cart where customer_id = ?";
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword))
            {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                     return new Cart(rs.getInt(1));
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
        public void createCart(Cart cart) throws ClassNotFoundException {
            String query = "insert into cart(customer_id,payment_date,total_payment,status) values (?,?,?,?)";
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword)){
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1,cart.getCustomerId());
                ps.setString(2, cart.getPaymentDate());
                ps.setDouble(3,cart.getTotalPayment());
                ps.setBoolean(4,cart.isStatus());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        public void updateCart(Cart cart) throws ClassNotFoundException {
            String query = "update cart set total_payment =? where customer_id = ?";
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword)){
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setDouble(1, cart.getTotalPayment());
                ps.setInt(2,cart.getCustomerId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    public void deleteAllCart(int customer_id) throws ClassNotFoundException {
        String query = "update cart set status = false where customer_id=?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,customer_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

}
