<%-- 
    Document   : AddWorkshop
    Created on : May 19, 2016, 12:39:53 PM
    Author     : Joanne
--%>

<%@page import="entity.CarBrand"%>
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
            Email: <input type="email" name="email" required/><br/>
            Address <input type="text" name="address" required/><br/>
            Website: <input type="text" name="website" required/><br/>
            Description: <textarea name= 'description' cols="50" rows="5"></textarea><br/>
            Opening Hours <input type="text" name="openingHour" required/><br/>
            Opening Hours Format: <input type="text" name="openingHourFormat" /><br/>
            Contact Number: <input type="text" name="contact" required/><br/>
            Another Contact Number: <input type="text" name="contact2" /><br/>
            Location: <input type="text" name="location" /><br/>
            Brands Carried <input type="text" name="brandsCarried" /><br/>
            Category: 
            <input type="checkbox" name="category" value="Maintenance1"/>Maintenance1<br/>
            <input type="checkbox" name="category" value="Car Grooming1"/>Car Grooming1<br/>
            <input type="checkbox" name="category" value="Tyre/Wheel Service1"/>Tyre/Wheel Service1<br/>
            <input type="checkbox" name="category" value="Air Conditioning1"/>Air Conditioning1<br/>
            <input type="checkbox" name="category" value="Battery1"/>Battery1<br/>
            <input type="checkbox" name="category" value="Others1"/>Others1<br/>
            Remark: <input type="text" name="remark" /><br/>

            <br/>
            Specialize: <br/>
            <%                WorkshopDAO wDAO = new WorkshopDAO();
                ArrayList<String> carBrands = wDAO.retrieveAllCarBrands(user.getStaffId(), user.getToken());
                for (String cb : carBrands) {
                    out.println("<input type=\"checkbox\" name=\"specialize\" value=\"" + cb + "\">" + cb + "<br>");
                }
            %>
            <input type="submit" value="Add Workshop"><br/><br/>
            <a href="<%=(String) session.getAttribute("loggedInUserType")%>.jsp">Home</a>

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
