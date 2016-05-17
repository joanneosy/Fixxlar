<%-- 
    Document   : ForgotPassword
    Created on : May 13, 2016, 9:12:35 AM
    Author     : Joanne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <form action = "ForgotPassword" method= "post">
            Email: <input type="email" name="email" required/><br/>
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
