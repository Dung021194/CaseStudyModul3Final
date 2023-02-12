package com.example.petshop.dao;

import com.example.petshop.model.Customer;
import com.example.petshop.model.Dog;
import com.example.petshop.model.SpeciesDog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DogDAO {
    String jdbcURL = "jdbc:mysql://localhost:3306/petshop";
    String jdbcUsername = "root";
    String jdbcPassword = "123456";
    public List<Dog> getListDog() throws ClassNotFoundException {
        SpeciesDao speciesDao = new SpeciesDao();
        String query = "select * from dog";
        List<Dog> dogList = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword))
        {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                dogList.add(new Dog(rs.getInt(1),rs.getString(2),
                        rs.getInt(3),rs.getDouble(4),
                        rs.getInt(5),speciesDao.findSpeciesById(rs.getInt(6)),
                        rs.getString(7)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dogList;
    }
    public Dog getDogById(int id) throws ClassNotFoundException {
        SpeciesDao speciesDao = new SpeciesDao();
        String query = "select * from dog where id = ?";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword))
        {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return new Dog(rs.getInt(1),rs.getString(2),
                        rs.getInt(3),rs.getDouble(4),
                        rs.getInt(5),speciesDao.findSpeciesById(rs.getInt(6)),
                        rs.getString(7));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void updatePet(Dog dog) throws ClassNotFoundException {
        String query = "update dog set dog_name = ?,age = ?,price = ?,quantity=?,species_id =?,image = ? where id = ?";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword)){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,dog.getName());
            ps.setInt(2,dog.getAge());
            ps.setDouble(3,dog.getPrice());
            ps.setInt(4,dog.getQuantity());
            ps.setInt(5,dog.getSpecies().getId());
            ps.setString(6,dog.getImage());
            ps.setInt(7,dog.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void deletePet(int id) throws ClassNotFoundException {
        String query = "delete from dog where id = ?";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword)){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public Dog checkDogInCart(int id) throws ClassNotFoundException {
        String query = "select dog.id, dog.dog_name from dog join cart_detail" +
                " on dog.id = cart_detail.dog_id and dog.id = ? group by dog.id";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword)){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return new Dog(rs.getInt(1),rs.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void createPet(Dog dog) throws ClassNotFoundException {
        String query = "insert into dog(dog_name,age,price,quantity,species_id,image) values (?,?,?,?,?,?)";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword)){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,dog.getName());
            ps.setInt(2,dog.getAge());
            ps.setDouble(3,dog.getPrice());
            ps.setInt(4,dog.getQuantity());
            ps.setInt(5,dog.getSpecies().getId());
            ps.setString(6,dog.getImage());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Dog> getListDogBySpecies(int id) throws ClassNotFoundException {
        SpeciesDao speciesDao = new SpeciesDao();
        String query = "select * from dog where species_id = ?";
        List<Dog> dogList = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword))
        {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                dogList.add(new Dog(rs.getInt(1),rs.getString(2),
                        rs.getInt(3),rs.getDouble(4),
                        rs.getInt(5),speciesDao.findSpeciesById(rs.getInt(6)),
                        rs.getString(7)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dogList;
    }
    public List<Dog> searchDog(String search) throws ClassNotFoundException {
        List<Dog> all = getListDog();
        List<Dog> searchList= new ArrayList<>();

        for (Dog d:all) {
            if (d.getName().toLowerCase().contains(search.toLowerCase())){
                searchList.add(d);
            }
        }
        return searchList;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        DogDAO dogDAO = new DogDAO();
        List<Dog> dog = dogDAO.getListDogBySpecies(1);
        for (Dog d:dog
             ) {
            System.out.println(d);
        }
    }

}
