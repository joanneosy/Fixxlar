<%-- 
    Document   : ViewWorkshop
    Created on : Jun 3, 2016, 2:47:17 PM
    Author     : Joanne
--%>

<%@page import="entity.Workshop"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.WorkshopDAO"%>
<%@include file="ProtectAdmin.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View All Workshop</title>
    </head>
    <body>
        <h1>All workshops</h1>
        <%            String successMsg = (String) request.getAttribute("successMsg");
            if (successMsg != null) {
                out.println(successMsg + "<br/>");
            }
        %>
        <br/>
        <table style="width:100%" border="1">
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>Name</th>
                <th>Description</th>
                <th>Website</th>
                <th>Address</th>
                <th>Opening Hours</th>
                <th>Contact Number</th>
                <th>Location</th>
                <th>Specialize</th>
                <th>Category</th>
                <th>Car Brands</th>
                <th>Remark</th>
                <th>Active</th>
                <th>Edit</th>
            </tr>
            <%
                WorkshopDAO wDAO = new WorkshopDAO();
                ArrayList<Workshop> allWorkshops = wDAO.retrieveAllWorkshops();
                for (Workshop w : allWorkshops) {
                    out.println("<tr>");
                    out.println("<td align=\"center\"> " + w.getId() + "</td>");
                    out.println("<td>" + w.getEmail() + "</td>");
                    out.println("<td>" + w.getName() + "</td>");
                    out.println("<td>" + w.getAddress() + "</td>");
                    out.println("<td>" + w.getDescription() + "</td>");
                    out.println("<td>" + w.getWebsite() + "</td>");
                    out.println("<td>" + w.getOpeningHour() + "</td>");
                    out.println("<td>" + w.getContact());
                    String contact2 = w.getContact2();
                    
                    if (!(contact2.equals(""))) {
                        out.println(" / " + w.getContact2());
                    }
                    out.println("</td>");
                    out.println("<td>" + w.getLocation() + "</td>");
                    out.println("<td>" + w.getSpecialize() + "</td>");
                    out.println("<td>" + w.getCategory() + "</td>");
                    out.println("<td>" + w.getCarBrands() + "</td>");
                    out.println("<td>" + w.getRemark() + "</td>");
                    byte isActive = w.getIsActive();
                    if (isActive == 0) {
                        out.println("<td>Inactive</td>");
                    } else {
                        out.println("<td>Active</td>");
                    }
                    out.println("<td><a href = \"EditWorkshop.jsp?id=" + w.getId() + "\">Edit</a>");
                    out.println("</tr>");
                }
            %>
        </table>
        <a href="<%=(String) session.getAttribute("loggedInUserType")%>.jsp">Home</a>
    </body>
</html>
