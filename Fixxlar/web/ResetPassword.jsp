<%-- 
    Document   : ResetPassword
    Created on : May 17, 2016, 3:12:43 PM
    Author     : Joanne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <form action = "ResetPassword" method= "post">
            New Password: <input type="password" name="password" required/><br/>
            Confirm New Password: <input type="password" name="confirmPassword" required/><br/>
            <input type="hidden" name="email" value="<%=request.getAttribute("resetPasswordEmail")%>">
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
