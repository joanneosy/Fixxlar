<%-- 
    Document   : workshop
    Created on : May 5, 2016, 10:00:14 AM
    Author     : joanne.ong.2014
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="protectWorkshop.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hello workshop</title>
    </head>
    <body>
        <h1>Welcome <%=((User)session.getAttribute("loggedInUser")).getName()%></h1>
        <a href="logout.jsp">Logout</a>
    </body>
</html>
