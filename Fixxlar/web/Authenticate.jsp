<%-- 
    Document   : authenticate
    Created on : May 4, 2016, 6:11:29 PM
    Author     : joanne.ong.2014
--%>

<%@page import="entity.HashCode"%>
<%@page import="entity.User"%>
<%@page import="dao.UserDAO"%>
<%
    String email = request.getParameter("email");
    String passwordEntered = request.getParameter("password");

    UserDAO dao = new UserDAO();
    User user = dao.retrieveUser(email);
    HashCode hc = new HashCode();
    if (user != null) {
        String passwordHash = user.getPassword();
        //check password
        
        if (hc.check(passwordEntered,passwordHash)) {
            session.setAttribute("loggedInUser", user);
            String userType = user.getUserType();
            session.setAttribute("loggedInUserType", userType);
            if (userType.equals("admin")) {
                response.sendRedirect("Admin.jsp");
            } else {
                response.sendRedirect("Workshop.jsp");
                return;
            }
        } else {
            request.setAttribute("errMsg", "Invalid Email/Password");
            RequestDispatcher view = request.getRequestDispatcher("Login.jsp");
            view.forward(request, response);
            return;
        }
    } else {
        request.setAttribute("errMsg", "Invalid Email/Password");
        RequestDispatcher view = request.getRequestDispatcher("Login.jsp");
        view.forward(request, response);
        return;
    }
%>
