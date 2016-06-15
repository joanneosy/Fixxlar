<%-- 
    Document   : admin
    Created on : May 5, 2016, 10:00:22 AM
    Author     : joanne.ong.2014
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="ProtectAdmin.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Admin</h1>
        <%            String successChangePasswordMsg = (String) request.getAttribute("successChangePasswordMsg");
            if (successChangePasswordMsg != null) {
                out.println(successChangePasswordMsg + "<br/><br/>");
            }
        %>
        <a href = "ChangePassword.jsp">Change Password</a><br/><br/>
        <a href = "AddWorkshop.jsp">Add Workshop</a><br/><br/>
        <a href = "ViewWorkshop.jsp">View all Workshop</a><br/><br/>
        <a href="Logout.jsp">Logout</a>
        
        <!--Start of Tawk.to Script-->
        <script type="text/javascript">
            var Tawk_API = Tawk_API || {}, Tawk_LoadStart = new Date();
            (function () {
                var s1 = document.createElement("script"), s0 = document.getElementsByTagName("script")[0];
                s1.async = true;
                s1.src = 'https://embed.tawk.to/575f763910253b870a73e7db/default';
                s1.charset = 'UTF-8';
                s1.setAttribute('crossorigin', '*');
                s0.parentNode.insertBefore(s1, s0);
            })();
        </script>
        <!--End of Tawk.to Script-->
        
    </body>
</html>
