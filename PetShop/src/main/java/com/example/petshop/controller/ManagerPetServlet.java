package com.example.petshop.controller;

import com.example.petshop.dao.DogDAO;
import com.example.petshop.dao.SpeciesDao;
import com.example.petshop.model.Dog;
import com.example.petshop.model.SpeciesDog;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ManagerPetServlet", value = "/ManagerPet")
public class ManagerPetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "update":
                try {
                    updatePet(request,response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "delete":
                try {
                    deletePet(request,response);
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
            case "update":
                try {
                    updateForm(request,response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "create":
                try {
                    createPet(request,response);
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
        SpeciesDao speciesDao =  new SpeciesDao();
        request.setAttribute("listSpecies",speciesDao.getListSpecies());
        request.setAttribute("listP",dogDAO.getListDog());
        request.getRequestDispatcher("ManagerPet.jsp").forward(request,response);

    }
    public void updatePet(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        SpeciesDao speciesDao = new SpeciesDao();
        DogDAO dogDAO = new DogDAO();
        RequestDispatcher rd = request.getRequestDispatcher("UpdatePet.jsp");
        request.setAttribute("listSpecies",speciesDao.getListSpecies());
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("detail", dogDAO.getDogById(id));
        rd.forward(request, response);
    }
    private void updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        SpeciesDao speciesDao = new SpeciesDao();
        DogDAO dogDAO = new DogDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        Double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        SpeciesDog speciesDog = speciesDao.findSpeciesById(Integer.parseInt(request.getParameter("species")));
        String image = request.getParameter("image");
        dogDAO.updatePet(new Dog(id,name,age,price,quantity,speciesDog,image));
       response.sendRedirect("/ManagerPet");
    }
    private void deletePet(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {
        DogDAO dogDAO = new DogDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        if (dogDAO.checkDogInCart(id) ==null ) {
            dogDAO.deletePet(id);
            response.sendRedirect("/ManagerPet");
        }else {
            request.setAttribute("messDelete","This pet cannot be deleted because it is in the cart");
            request.getRequestDispatcher("/ManagerPet?action=").forward(request,response);

        }
    }
    private void createPet(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        SpeciesDao speciesDao1 = new SpeciesDao();
        DogDAO dogDAO = new DogDAO();
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        Double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        SpeciesDog speciesDog = speciesDao1.findSpeciesById(Integer.parseInt(request.getParameter("species")));
        String image = request.getParameter("image");
        Dog dog = new Dog(name,age,price,quantity,speciesDog,image);
        dogDAO.createPet(dog);
        request.setAttribute("messCreate","Create Pet success");
        request.getRequestDispatcher("/ManagerPet?action=").forward(request,response);
    }
}
