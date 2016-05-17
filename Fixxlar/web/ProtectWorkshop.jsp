<%-- 
    Document   : protect
    Created on : May 5, 2016, 9:39:58 AM
    Author     : joanne.ong.2014
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    // check if user is authenticated
    User user = (User) session.getAttribute("loggedInUser");
    String userType = (String)session.getAttribute("loggedInUserType");

    if (user == null) {
        response.sendRedirect("Login.jsp");
        return;
    }  else if (user.getUserType().equals("Admin")) {
        response.sendRedirect("Admin.jsp");
    }

%>


