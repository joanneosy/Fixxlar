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
        <form action = "authenticate.jsp" method= "post">
            Email: <input type="text" name="email"/><br/>
            Password: <input type="password" name="password"/><br/>
            <input type="submit" value="Log In"/>
        </form>
        
         <%
            String errMsg = (String) request.getAttribute("errMsg");
            if (errMsg != null) {
                out.println(errMsg);
            }
        %>
    </body>
</html>
