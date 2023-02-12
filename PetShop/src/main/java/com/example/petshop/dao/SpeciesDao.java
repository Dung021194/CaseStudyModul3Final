package com.example.petshop.dao;

import com.example.petshop.model.SpeciesDog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpeciesDao {
    String jdbcURL = "jdbc:mysql://localhost:3306/petshop";
    String jdbcUsername = "root";
    String jdbcPassword = "123456";
    public List<SpeciesDog> getListSpecies() throws ClassNotFoundException {
        String query = "select * from species_dog";
        List<SpeciesDog> speciesDogList = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword))
        {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                speciesDogList.add(new SpeciesDog(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getBoolean(4)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return speciesDogList;
    }
    public SpeciesDog findSpeciesById(int id) throws ClassNotFoundException {
        String query = "select * from species_dog where id = ?";
        Class.forName("com.mysql.cj.jdbc.Driver");
        SpeciesDog speciesDog = null;
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                speciesDog = new SpeciesDog(rs.getInt(1), rs.getString(2),
                        rs.getString(3),rs.getBoolean(4));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return speciesDog;
    }
    public void createSpecies(SpeciesDog speciesDog) throws ClassNotFoundException {
        String query = "insert into species_dog(species_name,description,status) values (?,?,?)";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword)){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,speciesDog.getName());
            ps.setString(2,speciesDog.getDescription());
            ps.setBoolean(3,speciesDog.isStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteSpecies(int id){
        String query = "delete from species_dog where id = ?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        SpeciesDao speciesDao = new SpeciesDao();
         SpeciesDog list = speciesDao.findSpeciesById(1);

            System.out.println(list);
        }



}
