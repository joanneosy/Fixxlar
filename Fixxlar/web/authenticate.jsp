<%-- 
    Document   : authenticate
    Created on : May 4, 2016, 6:11:29 PM
    Author     : joanne.ong.2014
--%>

<%@page import="entity.PasswordHash"%>
<%@page import="entity.User"%>
<%@page import="dao.UserDAO"%>
<%
    String email = request.getParameter("email");
    String passwordEntered = request.getParameter("password");

    UserDAO dao = new UserDAO();
    User user = dao.retrieveUser(email);
    PasswordHash pwh = new PasswordHash();
    if (user != null) {
        String passwordHash = user.getPassword();
        //check password
        if (pwh.check(passwordEntered,passwordHash)) {
            session.setAttribute("loggedInUser", user);
            String userType = user.getUserType();
            session.setAttribute("loggedInUserType", userType);
            if (userType.equals("admin")) {
                response.sendRedirect("admin.jsp");
            } else {
                response.sendRedirect("workshop.jsp");
                return;
            }
        } else {
            request.setAttribute("errMsg", "Invalid Email/Password");
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
            return;
        }
    } else {
        request.setAttribute("errMsg", "Invalid Email/Password");
        RequestDispatcher view = request.getRequestDispatcher("login.jsp");
        view.forward(request, response);
        return;
    }
%>
