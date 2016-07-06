<%-- 
    Document   : protectAdmin
    Created on : May 6, 2016, 4:29:42 PM
    Author     : joanne.ong.2014
--%>

<%@page import="entity.WebUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    // check if user is authenticated
    WebUser user = (WebUser) session.getAttribute("loggedInUser");

    if (user == null) {
        response.sendRedirect("Login.jsp");
        return;
    } else if (user.getUserType().equals("Workshop")) {
        response.sendRedirect("Workshop.jsp");
    }

%>
