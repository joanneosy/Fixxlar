/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import util.HashCode;
import dao.WebUserDAO;
import dao.WorkshopDAO;
import entity.WebUser;
import entity.Workshop;
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
import javax.servlet.http.HttpSession;

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
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String[] specializeArr = request.getParameterValues("specialize");
        String description = request.getParameter("description");
        String website = request.getParameter("website");
        String openingHour = request.getParameter("openingHour");
        String openingHourFormat = request.getParameter("openingHourFormat");
        double latitude = 0.0;
        double longitude = 0.0;
        String contact = request.getParameter("contact");
        String contact2 = request.getParameter("contact2");
        String location = request.getParameter("location");
        String brandsCarried = request.getParameter("brandsCarried");
        String category = request.getParameter("category");
        String remark = request.getParameter("remark");
        String wsStaffName = request.getParameter("staffName");
        String wsStaffHpNo = request.getParameter("staffHpNo");
        String specialize = "";

        ArrayList<String> errMsg = new ArrayList<String>();

        if (specializeArr == null) {
            errMsg.add("No car brands selected.");
        } else {
            specialize = specializeArr[0];
            for (int i = 1; i < specializeArr.length; i++) {
                specialize += "," + specializeArr[i];
            }
        }

        if (!password.equals(confirmPassword)) {
            errMsg.add("Passwords do not match.");
        } else {
            HashCode hc = new HashCode();
            password = hc.generateSaltedHash(password);
        }

        WebUserDAO uDAO = new WebUserDAO();
        WorkshopDAO wDAO = new WorkshopDAO();
        String[] latLong = wDAO.retrieveLatLong(address);
        if (latLong == null) {
            errMsg.add("Invalid address.");
        } else {
            latitude = Double.parseDouble(latLong[0]);
            longitude = Double.parseDouble(latLong[1]);
        }

        if (uDAO.retrieveUser(email) != null) {
            errMsg.add("User exists.");
        }

        if (errMsg.size() == 0) {
            HttpSession session = request.getSession(true);
            WebUser user = (WebUser) session.getAttribute("loggedInUser");
            int staffId = user.getStaffId();
            String token = user.getToken();
            ArrayList<String> addErrMsg = wDAO.addWorkshop(email, name, description, website, address, openingHour, openingHourFormat,
                    latitude, longitude, contact, contact2, location, specialize, category, brandsCarried, remark, staffId, token);
            if (addErrMsg.size() == 0) {
                Workshop ws = wDAO.retrieveWorkshop(email, user.getStaffId(), user.getToken());
                int wsId = ws.getId();
                uDAO.addMasterUser(staffId, token, wsStaffName, email, wsStaffHpNo, wsId, password);
                request.setAttribute("successMsg", "Workshop successfully added!");
                RequestDispatcher view = request.getRequestDispatcher("AddWorkshop.jsp");
                view.forward(request, response);
            } else {
                request.setAttribute("errMsg", addErrMsg);
                RequestDispatcher view = request.getRequestDispatcher("AddWorkshop.jsp");
                view.forward(request, response);
            }
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
