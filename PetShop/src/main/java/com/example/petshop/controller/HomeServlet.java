package com.example.petshop.controller;

import com.example.petshop.dao.DogDAO;
import com.example.petshop.dao.SpeciesDao;
import com.example.petshop.model.Dog;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "homeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "showSpecies":
                try {
                    displaySpecies(request, response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
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
            case "searchPet":
                try {
                    searchPet(request,response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
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
        SpeciesDao speciesDao = new SpeciesDao();
        request.setAttribute("listSpecies", speciesDao.getListSpecies());
        request.setAttribute("listPet", dogDAO.getListDog());
        request.getRequestDispatcher("home.jsp").forward(request, response);

    }

    public void displaySpecies(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        DogDAO dogDAO = new DogDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("listPet", dogDAO.getListDogBySpecies(id));
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    public void searchPet(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        DogDAO dogDAO = new DogDAO();
        String searchName = request.getParameter("searchPet");
        List<Dog> dogList = dogDAO.searchDog(searchName);
        if (!dogList.isEmpty()) {
            request.setAttribute("listPet", dogList);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }else {
            request.setAttribute("messPayment","No pet have this name");
            request.getRequestDispatcher("/home").forward(request, response);
        }
    }
}
