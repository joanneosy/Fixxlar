<%-- 
    Document   : ChangePassword
    Created on : May 17, 2016, 5:17:18 PM
    Author     : Joanne
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
    </head>
    <body>
        <h1>Change Password</h1>
        <form action = "ChangePassword" method= "post">
            Old Password: <input type="password" name="oldPassword" required/><br/>
            New Password: <input type="password" name="newPassword" required/><br/>
            Confirm New Password: <input type="password" name="confirmNewPassword" required/><br/>
            <input type="submit" value="Reset Password"/>
        </form><br/>

        <%
            String errMsg = (String) request.getAttribute("errMsg");
            if (errMsg != null) {
                out.println(errMsg);
            }
        %>
    </body>
</html>
