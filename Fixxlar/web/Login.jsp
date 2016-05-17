<%-- 
    Document   : login
    Created on : May 4, 2016, 5:43:20 PM
    Author     : joanne.ong.2014
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log In</title>
    </head>
    <body>
        <h1>Hello</h1>
        
        <%
            String errMsg = (String) request.getAttribute("errMsg");
            if (errMsg != null) {
                out.println(errMsg + "<br/><br/>");
            }

            String successResetPasswordMsg = (String) request.getAttribute("successResetPasswordMsg");
            if (successResetPasswordMsg != null) {
                out.println(successResetPasswordMsg + "<br/><br/>");
            }
        %>
        <a href = "ForgotPassword.jsp">Forgot Password</a><br/><br/>
        <form action = "Authenticate.jsp" method= "post">
            Email: <input type="email" name="email" required/><br/>
            Password: <input type="password" name="password" required/><br/>
            <input type="submit" value="Log In"/>
        </form><br/>

    </body>
</html>
