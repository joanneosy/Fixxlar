<%-- 
    Document   : ProtectUsers
    Created on : Jun 18, 2016, 11:57:44 PM
    Author     : Joanne
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    // check if user is authenticated
    User user = (User) session.getAttribute("loggedInUser");

    if (user == null) {
        response.sendRedirect("Login.jsp");
        return;
    } 

%>
