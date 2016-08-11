<%-- 
    Document   : EditNormalWorkshopStaff
    Created on : Aug 10, 2016, 2:15:12 PM
    Author     : Joanne
--%>

<%@page import="dao.WebUserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="ProtectWorkshop.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Staff</title>
    </head>
    <body>
        <h1>Edit Staff</h1>

        <%
            String errMsg = (String) request.getAttribute("errMsg");
            if (errMsg != null) {
                out.println(errMsg);
            }

            int id = Integer.parseInt(request.getParameter("id"));
            WebUserDAO uDAO = new WebUserDAO();
            WebUser userToEdit = uDAO.retrieveUser(user.getStaffId(), user.getToken(), id);

            String email = userToEdit.getEmail();
            if (email.equals("null")) {
                email = "";
            }

            String name = userToEdit.getName();
            if (name.equals("null")) {
                name = "";
            }

            String handphone = userToEdit.getHandphone();
            if (handphone.equals("null")) {
                handphone = "";
            }

        %>

        <form action = "EditStaff" method= "post">
            Name: <input type="text" name="name" value=<%=name%> required/><br/>
            Email: <input type="text" name="email" value=<%=email%> required/><br/>
            Handphone: <input type="text" name="handphone" value=<%=handphone%> required/><br/>
            <input type="hidden" name="id" value="<%=id%>"><br/>
            <input type="submit" value="Edit Employee"><br/><br/>
        </form><br/>


    </body>
</html>
