<%-- 
    Document   : AddNormalWorkshopStaff
    Created on : Aug 1, 2016, 8:02:42 PM
    Author     : Joanne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Workshop Staff</title>
    </head>
    <body>
        <%
            String errMsg = (String) request.getAttribute("errMsg");
            if (errMsg != null) {
                out.println(errMsg + "<br/>");
            }
        %>
        <form action = "AddNormalWorkshopStaff" method= "post">

            <h3>Add Workshop Staff</h3>
            Name: <input type="text" name="staffName" required/><br/> 
            Email: <input type="text" name="staffEmail" required/><br/> 
            Password: <input type="password" name="password" required/><br/>
            Confirm Password: <input type="password" name="confirmPassword" required/><br/>
            Handphone number: <input type="number" name="staffHpNo" required/><br/> 
            <input type="submit" value="Add Workshop Staff"><br/><br/>
            <a href="<%=(String) session.getAttribute("loggedInUserType")%>.jsp">Home</a>
        </form><br/>
    </body>
</html>
