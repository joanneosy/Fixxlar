<%-- 
    Document   : AddWorkshop
    Created on : May 19, 2016, 12:39:53 PM
    Author     : Joanne
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.TreeMap"%>
<%@page import="dao.WorkshopDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="ProtectAdmin.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Workshop</title>
    </head>
    <body>
        <h1>Add Workshop</h1>
        <form action = "AddWorkshop" method= "post">
            Company Name: <input type="text" name="name" required/><br/>
            Address <input type="text" name="address" required/><br/>
            Email: <input type="email" name="email" required/><br/>
            Password: <input type="password" name="password" required/><br/>
            Confirm Password: <input type="password" name="confirmPassword" required/><br/>
            <br/>
            Car brands: <br/>
            <%                
                WorkshopDAO wDAO = new WorkshopDAO();
                TreeMap<Integer, String> carBrands = wDAO.retrieveAllCarBrands();
                Iterator it = carBrands.entrySet().iterator();
                int brandID;
                String brandName;
                while (it.hasNext()) {
                    Map.Entry<Integer, String> pair = (Map.Entry) it.next();
                    brandID = pair.getKey();
                    brandName = pair.getValue();
                    out.println("<input type=\"checkbox\" name=\"carBrands\" value=\"" + brandID + "\">" + brandName + "<br>");
                }
            %>
            <input type="submit" value="Add Workshop"><br/><br/>
            <a href="<%=(String)session.getAttribute("loggedInUserType")%>.jsp">Home</a>

        <%
            ArrayList<String> errMsg = (ArrayList<String>) request.getAttribute("errMsg");
            if (errMsg != null) {
                for (String s : errMsg) { 
                    out.println(s + "<br/>");
                }
            }

            String successMsg = (String) request.getAttribute("successMsg");
            if (successMsg != null) {
                out.println(successMsg + "<br/>");
            }
        %>
        </form><br/>
    </body>
</html>
