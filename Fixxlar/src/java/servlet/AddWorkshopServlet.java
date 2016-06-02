/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.UserDAO;
import dao.WorkshopDAO;
import entity.HashCode;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Joanne
 */
@WebServlet(name = "AddWorkshop", urlPatterns = {"/AddWorkshop"})
public class AddWorkshopServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        //Check for valid address using google web services
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String[] carBrandsID = request.getParameterValues("carBrands");

        ArrayList<String> errMsg = new ArrayList<String>();

        if (!password.equals(confirmPassword)) {
            errMsg.add("Passwords do not match.");
        }

        HashCode hc = new HashCode();
        password = hc.getSaltedHash(password);
        UserDAO uDAO = new UserDAO();
        if (uDAO.retrieveUser(email) != null) {
            errMsg.add("User exists.");
        } else {
            uDAO.addUser(email, name, password, "Workshop");
            User newUser = uDAO.retrieveUser(email);
            int userID = newUser.getId();
            WorkshopDAO wDAO = new WorkshopDAO();
            wDAO.addWorkshop(userID, email, name, address, carBrandsID);
        }
        
        if (carBrandsID == null) {
            errMsg.add("No car brands selected.");
        }
        if (errMsg.size()
                == 0) {
            request.setAttribute("successMsg", "Workshop successfully added!");
            RequestDispatcher view = request.getRequestDispatcher("AddWorkshop.jsp");
            view.forward(request, response);
        } else {
            request.setAttribute("errMsg", errMsg);
            RequestDispatcher view = request.getRequestDispatcher("AddWorkshop.jsp");
            view.forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(AddWorkshopServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(AddWorkshopServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
