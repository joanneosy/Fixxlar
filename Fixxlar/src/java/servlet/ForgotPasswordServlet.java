/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.EmailDAO;
import dao.UserDAO;
import entity.Email;
import entity.HashCode;
import entity.User;
import is203.JWTUtility;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ForgotPassword;

/**
 *
 * @author Fixxlar
 */
@WebServlet(name = "ForgotPassword", urlPatterns = {"/ForgotPassword"})
public class ForgotPasswordServlet extends HttpServlet {

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

        String emailTo = request.getParameter("email");
        UserDAO uDao = new UserDAO();
        User user = uDao.retrieveUser(emailTo);
        if (user != null) {
            HashCode hc = new HashCode();
            String saltedHash = hc.getSaltedHash(emailTo);
            ForgotPassword fp = new ForgotPassword();
            fp.storeHashCode(emailTo, saltedHash);
            //SEND EMAIL
            EmailDAO eDAO = new EmailDAO();
            Email email = new Email(emailTo, "fixxlar@gmail.com", "fixxlar123", "Reset Passord for Fixir",
                    "<h3>Reset Password</h3>\n"
                    + "Use the following link to reset your password! <br/>\n"
                    + "<a href = \"http://localhost:8084/Fixxlar/ResetPassword?hashCode=" + saltedHash + "\">Reset your password</a><br/><br/>\n"
                    + "If the link above does not work, click this: <br/>"
                    + "http://localhost:8084/Fixxlar/ResetPassword?hashCode=" + saltedHash + "<br/><br/>"
                    + "If you don’t use this link within 24 hours, it will expire. To get a new password reset link, "
                    + "visit <a href = \"http://www.google.com\">here</a>\n" + "");

            eDAO.SendEmail(email);
            request.setAttribute("successResetPasswordMsg", "You will receive a password reset email soon.");
            RequestDispatcher view = request.getRequestDispatcher("Login.jsp");
            view.forward(request, response);
            return;
        } else {
            request.setAttribute("errMsg", "Invalid Email");
            RequestDispatcher view = request.getRequestDispatcher("ForgotPassword.jsp");
            view.forward(request, response);
            return;
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
            Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
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