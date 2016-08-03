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
        <form action="ViewWorkshop" method="post">
            Sort by:
            <select name="orderBy">
                <option value="">None</option>
                <option value="name">Name A-Z</option>
                <option value="name desc">Name Z-A</option>
                <option value="location">Location A-Z</option>
                <option value="location desc">Location Z-A</option>
            </select>  
            <select name="isActive">
                <option value="">All</option>
                <option value=1>Active</option>
                <option value=0>Inactive</option>
            </select>   
            <input type="submit" value="Go!">
        </form>

        <form action="SearchWorkshop" method="post">
            Search:
            <input type="text" name="search"/>
            <input type="submit" value="Go!">
        </form>


        <%
            ArrayList<Workshop> allWorkshops = (ArrayList<Workshop>) request.getAttribute("workshops");
            if (allWorkshops == null) {
                WorkshopDAO wDAO = new WorkshopDAO();
                allWorkshops = wDAO.retrieveAllWorkshops(user.getStaffId(), user.getToken());
            }
            if (allWorkshops.size() == 0) {
                out.println("No workshop found. Try again.<br/>");
            } else {
        %>
        <table style=\"width:100%\" border=\"1\">
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>Name</th>
                <th>Address</th>
                <th>Description</th>
                <th>Website</th>
                <th>Opening Hours</th>
                <th>Contact Number</th>
                <th>Location</th>
                <th>Brands Carried</th>
                <th>Category</th>
                <th>Specialize</th>
                <th>Remark</th>
                <th>Active</th>
                <th>Edit</th>
            </tr>
            <%
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
                        out.println("<td>" + w.getBrandsCarried() + "</td>");
                        out.println("<td>");
                        String[] category = w.getCategory().split(",");
                        for (String s : category) {
                            out.println(s + "<br/>");
                        }
                        out.println("</td>");
                        out.println("<td>");
                        String[] carBrands = w.getSpecialize().split(",");
                        for (String s : carBrands) {
                            out.println(s + "<br/>");
                        }
                        out.println("</td>");
                        out.println("<td>" + w.getRemark() + "</td>");
                        int status = w.getStatus();
                        if (status == 0) {
                            out.println("<td>Inactive</td>");
                        } else {
                            out.println("<td>Active</td>");
                        }
                        out.println("<td><a href = \"EditWorkshop.jsp?id=" + w.getId() + "\">Edit</a>");
                        out.println("</tr>");
                    }
                    out.println("</table>");

                }
            %>

            <a href="<%=(String) session.getAttribute("loggedInUserType")%>.jsp">Home</a>
    </body>
</html>
