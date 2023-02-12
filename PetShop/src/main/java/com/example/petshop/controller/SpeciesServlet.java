package com.example.petshop.controller;

import com.example.petshop.dao.SpeciesDao;
import com.example.petshop.model.SpeciesDog;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SpeciesServlet", value = "/SpeciesServlet")
public class SpeciesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                deleteSpecies(request,response);
                break;
            default:
                try {
                    displaySpecies(request, response);
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
            case "create":
                try {
                    createSpecies(request,response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                deleteSpecies(request,response);
            default:
                try {
                    displaySpecies(request, response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

        }
    }
    public void createSpecies(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        SpeciesDao speciesDao = new SpeciesDao();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        boolean status = true;
        speciesDao.createSpecies(new SpeciesDog(name,description,status));
        request.setAttribute("messCreate","Create Species success");
        request.getRequestDispatcher("/ManagerPet?action=").forward(request,response);
    }
    public void displaySpecies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        SpeciesDao speciesDao = new SpeciesDao();
        RequestDispatcher rd = request.getRequestDispatcher("showSpecies.jsp");
        request.setAttribute("listSpecies",speciesDao.getListSpecies());
        rd.forward(request, response);
    }
    public void deleteSpecies(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SpeciesDao speciesDao = new SpeciesDao();
        int id = Integer.parseInt(request.getParameter("id"));
        speciesDao.deleteSpecies(id);
        response.sendRedirect("/SpeciesServlet");

    }
}
