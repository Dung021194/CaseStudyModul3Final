package com.example.petshop.dao;

import com.example.petshop.model.Cart;
import com.example.petshop.model.CartDetail;

import java.sql.*;

public class CartDetailDAO {
    String jdbcURL = "jdbc:mysql://localhost:3306/petshop";
    String jdbcUsername = "root";
    String jdbcPassword = "123456";
    public void createCartDetail(CartDetail cartDetail) throws ClassNotFoundException {
        String query = "insert into cart_detail(dog_id,quantity,price,customer_id,status) values (?,?,?,?,?)";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword)){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,cartDetail.getDog().getId());
            ps.setInt(2, cartDetail.getQuantity());
            ps.setDouble(3,cartDetail.getPrice());
            ps.setInt(4,cartDetail.getCustomerId());
            ps.setBoolean(5,cartDetail.isStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateQuantityCartDetail(CartDetail cartDetail) throws ClassNotFoundException {
        String query = "update cart_detail set quantity = ? where dog_id = ? && customer_id = ?";
        int newQuantity = cartDetail.getQuantity() + 1;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword)){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,newQuantity);
            ps.setInt(2, cartDetail.getDog().getId());
            ps.setInt(3,cartDetail.getCustomerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateCartDetail(CartDetail cartDetail) throws ClassNotFoundException {
        String query = "update cart_detail set quantity = ? where dog_id = ? && customer_id = ?";
        int newQuantity = cartDetail.getQuantity()-1;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword)){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,newQuantity);
            ps.setInt(2, cartDetail.getDog().getId());
            ps.setInt(3,cartDetail.getCustomerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CartDetail findCartDetail(int dog_id,int customer_id) throws ClassNotFoundException {
        String query = "select * from cart_detail where dog_id = ? && customer_id=?";
        Class.forName("com.mysql.cj.jdbc.Driver");
        DogDAO dogDAO = new DogDAO();
        try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword))
        {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,dog_id);
            ps.setInt(2,customer_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
               return new CartDetail(rs.getInt(1),dogDAO.getDogById(rs.getInt(2)),
                       rs.getInt(3),rs.getDouble(4),rs.getInt(5));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void deleteCartDetail(int dog_id,int customer_id) throws ClassNotFoundException {
        String query = "update cart_detail set status = false where dog_id = ? && customer_id=?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,dog_id);
            ps.setInt(2,customer_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void deleteAllCartDetail(int customer_id) throws ClassNotFoundException {
        String query = "update cart_detail set status = false where customer_id=?";

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

    public static void main(String[] args) throws ClassNotFoundException {
        CartDetailDAO cartDetailDAO = new CartDetailDAO();
        System.out.println(cartDetailDAO.findCartDetail(2,2));
    }



}
