<%-- 
    Document   : EditWorkshop
    Created on : Jun 3, 2016, 3:39:11 PM
    Author     : Joanne
--%>

<%@page import="entity.CarBrand"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.TreeMap"%>
<%@page import="dao.WorkshopDAO"%>
<%@page import="entity.Workshop"%>
<%@include file="ProtectAdmin.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Workshop</title>
    </head>
    <body>
        <%
            int wsId = Integer.parseInt(request.getParameter("id"));
            WorkshopDAO wDAO = new WorkshopDAO();
            Workshop ws = wDAO.retrieveWorkshop(wsId, user.getStaffId(), user.getToken());
            String website = ws.getWebsite();
            if (website.equals("null")) { website = ""; }
            String description = ws.getDescription();
            if (description.equals("null")) { description = ""; }
            String openingHourFormat = ws.getOpeningHourFormat();
            if (openingHourFormat.equals("null")) { openingHourFormat = ""; }
            String contact2 = ws.getContact2();
            if (contact2.equals("null")) { contact2 = ""; }
            String location = ws.getLocation();
            if (location.equals("null")) { location = ""; }
            String brandsCarried = ws.getBrandsCarried();
            if (brandsCarried.equals("null")) { brandsCarried = ""; }
            String category = ws.getCategory();
            if (category.equals("null")) { category = ""; }
            String remark = ws.getRemark();
            if (remark.equals("null")) { remark = ""; }
            int status = ws.getStatus();
        %>
        <h1>Edit <%=ws.getName()%></h1>
        <form action = "EditWorkshop" method= "post">
            ID: <input type="text" name="id" value="<%=ws.getId()%>" readonly/><br/>
            Email: <input type="email" name="email" value="<%=ws.getEmail()%>" required/><br/>
            Company Name: <input type="text" name="name" value="<%=ws.getName()%>" required/><br/>
            Address <input type="text" name="address" value="<%=ws.getAddress()%>" required/><br/>
            Website: <input type="text" name="website" value="<%=website%>" /><br/>
            Description: <textarea name= 'description' cols="50" rows="5" ><%=description%></textarea><br/>
            Opening Hours <input type="text" name="openingHour" value="<%=ws.getOpeningHour()%>" required/><br/>
            Opening Hours Format: <input type="text" name="openingHourFormat" value="<%=openingHourFormat%>"/><br/>
            Contact Number: <input type="text" name="contact" value="<%=ws.getContact()%>" required/><br/>
            Another Contact Number: <input type="text" name="contact2" value="<%=contact2%>"/><br/>
            Location: <input type="text" name="location" value="<%=location%>"/><br/>
            Brands Carried <input type="text" name="brandsCarried" value="<%=brandsCarried%>"/><br/>
            Category: <input type="text" name="category" value="<%=category%>"/><br/>
            Remark: <input type="text" name="remark" value="<%=remark%>"/><br/>
            Active: 
            <%
                if (status == 1) {
                    out.println("<input type=\"checkbox\" name=\"isActive\" value=\"1\" checked>Active<br>");
                } else {
                    out.println("<input type=\"checkbox\" name=\"isActive\" value=\"1\" >Active<br>");
                }
            %>

            Specialize: <br/>
            <%
                String[] carBrandsID = ws.getSpecialize().split(",");
                ArrayList<String> carBrands = wDAO.retrieveAllCarBrands(user.getStaffId(), user.getToken());
                for (String s : carBrands) {
                    if (Arrays.asList(carBrandsID).contains(s)) {
                        out.println("<input type=\"checkbox\" name=\"specialize\" value=\"" + s + "\" checked>" + s + "<br>");
                    } else {
                        out.println("<input type=\"checkbox\" name=\"specialize\" value=\"" + s + "\">" + s + "<br>");
                    }
                }
            %>
            <input type="submit" value="Edit Workshop"><br/><br/>
        </form><br/>
        <%
            ArrayList<String> errMsg = (ArrayList<String>) request.getAttribute("errMsg");
            if (errMsg != null) {
                for (String s : errMsg) {
                    out.println(s + "<br/>");
                }
            }
        %>
        <br/>
        <a href="ViewWorkshop.jsp">Go Back</a>

    </body>
</html>
