<%-- 
    Document   : ViewRequest
    Created on : Jun 9, 2016, 3:12:40 PM
    Author     : Joanne
--%>

<%@page import="entity.WebUser"%>
<%@page import="dao.CustomerDAO"%>
<%@page import="dao.VehicleDAO"%>
<%@page import="entity.Workshop"%>
<%@page import="dao.WorkshopDAO"%>
<%@page import="dao.QuotationRequestDAO"%>
<%@page import="entity.QuotationRequest"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Requests</title>
    </head>
    <body>
        <h1>All requests</h1>
        <%
            WorkshopDAO wDAO = new WorkshopDAO();
            String email = ((WebUser) session.getAttribute("loggedInUser")).getEmail();
            String userType = (String) session.getAttribute("loggedInUserType");
            Workshop ws = wDAO.retrieveWorkshop(email);
            int id = 0;
            if (userType.equals("Workshop")) {
                id = ws.getId();
            }
            QuotationRequestDAO qrDAO = new QuotationRequestDAO();
            ArrayList<QuotationRequest> allQuotationRequests = qrDAO.retrieveAllQuotationRequests(id, 0, "", "", "", "");
            VehicleDAO vDAO = new VehicleDAO();
            CustomerDAO cDAO = new CustomerDAO();
        %>
        <table style="width:100%" border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Details</th>
                <th>Description</th>
                <th>Vehicle ID</th>
                <th>Mileage</th>
                <th>Urgency</th>
                <th>Amenities</th>
                <th>Address</th>
                <th>Photos</th>
                <th>Car Make</th>
                <th>Car Model</th>
                <th>Year of Manufacture</th>
                <th>Plate Number</th>
                <th>Date</th>
                <th>Status</th>
                <th>Workshop ID</th>
                <th>Change</th>
            </tr>

            <%                
                for (QuotationRequest qr : allQuotationRequests) {
                    out.println("<tr>");
                    out.println("<td align=\"center\"> " + qr.getId() + "</td>");
                    out.println("<td>" + qr.getName() + "</td>");
                    out.println("<td>" + qr.getDetails() + "</td>");
                    out.println("<td>" + qr.getDescription() + "</td>");
                    out.println("<td>" + qr.getVehicle().getId() + "</td>");
                    out.println("<td>" + qr.getMileage() + "</td>");
                    out.println("<td>" + qr.getUrgency() + "</td>");
                    out.println("<td>" + qr.getAmenities());
                    out.println("<td>" + qr.getAddress());
                    out.println("<td>" + qr.getPhotos() + "</td>");
                    out.println("<td>" + qr.getVehicle().getMake() + "</td>");
                    out.println("<td>" + qr.getVehicle().getModel() + "</td>");
                    out.println("<td>" + qr.getVehicle().getYear() + "</td>");
                    out.println("<td>" + qr.getVehicle().getPlateNumber() + "</td>");
                    out.println("<td>" + qr.getDate() + "</td>");
                    int status = qr.getStatus();
                    if (status == 1) {
                        out.println("<td>New</td>");
                    } else if (status == 2) {
                        out.println("<td>Ongoing</td>");
                    } else if (status == 3) {
                        out.println("<td>Completed</td>");
                    } else if (status == 4) {
                        out.println("<td>Cancelled</td>");
                    }
                    out.println("<td>" + qr.getWorkshopId() + "</td>");
                    if (userType.equals("Admin")) {
                        out.println("<td><a href = \"EditQuotationRequest.jsp?id=" + qr.getId() + "\">Edit</a></td>");
                    } else if (userType.equals("Workshop") && status == 1) {
                        out.println("<td><a href = \"AddOffer.jsp?id=" + qr.getId() + "\">Give Quotation</a></td>");
                    } else if (userType.equals("Workshop") && status == 2) {
                        out.println("<td><a href = \"EditQuotation?status=2&id=" + qr.getId() + "\">Complete</a></td>");
                    } else if (userType.equals("Workshop") && status == 3) {
                        out.println("<td><a href = \"ViewOneQuotationRequest.jsp?&id=" + qr.getId() + "\">View Details</a></td>");
                    } 
                    out.println("</tr>");
                }
            %>
        </table>
        <a href="<%=(String) session.getAttribute("loggedInUserType")%>.jsp">Home</a>
    </body>
</html>
