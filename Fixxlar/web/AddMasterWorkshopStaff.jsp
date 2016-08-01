<%-- 
    Document   : AddMasterWorkshopStaff
    Created on : Jul 29, 2016, 5:31:48 PM
    Author     : Joanne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String errMsg = (String) request.getAttribute("errMsg");
            if (errMsg != null) {
                out.println(errMsg + "<br/>");
            }
        %>
        <form action = "AddMasterWorkshopStaff" method= "post">

            <h3>Register Master Workshop Admin User</h3>
            Name: <input type="text" name="staffName" required/><br/> 
            Email: <input type="text" name="staffEmail" required/><br/> 
            Password: <input type="password" name="password" required/><br/>
            Confirm Password: <input type="password" name="confirmPassword" required/><br/>
            Handphone number: <input type="number" name="staffHpNo" required/><br/> 
            <input type="hidden" name="workshopId" value="<%=request.getParameter("workshopId")%>"/>
            <input type="submit" value="Add Master Workshop Staff"><br/><br/>
            <a href="<%=(String) session.getAttribute("loggedInUserType")%>.jsp">Home</a>
        </form><br/>
    </body>
</html>
