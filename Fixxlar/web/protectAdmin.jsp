<%-- 
    Document   : protectAdmin
    Created on : May 6, 2016, 4:29:42 PM
    Author     : joanne.ong.2014
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    // check if user is authenticated
    User user = (User) session.getAttribute("loggedInUser");

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    } else if (user.getUserType().equals("workshop")) {
        response.sendRedirect("workshop.jsp");
    }

%>
