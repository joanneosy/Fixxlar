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
            String email = ws.getEmail();
            if (email.equals("null")) {
                email = "";
            }
            String name = ws.getName();
            if (name.equals("null")) {
                name = "";
            }

            String address = ws.getAddress();
            String postalCode = "";
            if (address.equals("null")) {
                address = "";
            } else {
                postalCode = address.substring(address.lastIndexOf(" ") + 1);
                address = address.substring(0, address.lastIndexOf(" ", address.lastIndexOf(" ") - 1));
            }

            String website = ws.getWebsite();
            if (website.equals("null")) {
                website = "";
            }
            
            String description = ws.getDescription();
            if (description.equals("null")) {
                description = "";
            }
            String openingHour = ws.getOpeningHour();
            String[] daysAndTime = null;

            if (openingHour.equals("null")) {
                openingHour = "";
            } else {
                //Each string in this format: Monday-0900-1800
                daysAndTime = openingHour.split(",");
            }
            String openingHourFormat = ws.getOpeningHourFormat();
            if (openingHourFormat.equals("null")) {
                openingHourFormat = "";
            }
            String contact = ws.getContact();
            if (contact.equals("null")) {
                contact = "";
            }
            String contact2 = ws.getContact2();
            if (contact2.equals("null")) {
                contact2 = "";
            }
            String location = ws.getLocation();
            if (location.equals("null")) {
                location = "";
            }
            String brandsCarried = ws.getBrandsCarried();
            if (brandsCarried.equals("null")) {
                brandsCarried = "";
            }
            String category = ws.getCategory();
            if (category.equals("null")) {
                category = "";
            }
            String remark = ws.getRemark();
            if (remark.equals("null")) {
                remark = "";
            }
            int status = ws.getStatus();
        %>
        <h1>Edit <%=ws.getName()%></h1>
        <form action = "EditWorkshop" method= "post">
            ID: <input type="text" name="id" value="<%=ws.getId()%>" readonly/><br/>
            Email: <input type="email" name="email" value="<%=ws.getEmail()%>"/><br/>
            Company Name: <input type="text" name="name" value="<%=ws.getName()%>"/><br/>
            Address <input type="text" name="address" value="<%=ws.getAddress()%>"/><br/>
            Website: https://<input type="text" name="website" value="<%=website.substring(website.indexOf("/")+2)%>" /><br/>
            Description: <textarea name= 'description' cols="50" rows="5" ><%=description%></textarea><br/>
            Opening Hours: <br/>
            Monday: 
            <select name="mondayOpen">
                <%
                    String[] hours = daysAndTime[0].split("-");
                    if (hours[1].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    //hours[0] = Monday
                    //hours[1] = 0900
                    //hours[2] = 1800
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[1].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select>

            <select name="mondayClose">
                <%
                    if (hours[2].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[2].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select><br/>

            Tuesday: 
            <select name="tuesdayOpen">
                <%
                    hours = daysAndTime[1].split("-");
                    if (hours[1].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[1].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select>

            <select name="tuesdayClose">
                <%
                    if (hours[2].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[2].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select><br/>

            Wednesday: 
            <select name="wednesdayOpen">
                <%
                    hours = daysAndTime[2].split("-");
                    if (hours[1].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[1].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select>

            <select name="wednesdayClose">
                <option value="Closed">Closed</option>
                <%
                    if (hours[2].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[2].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select><br/>

            Thursday 
            <select name="thursdayOpen">
                <option value="Closed">Closed</option>
                <%
                    hours = daysAndTime[3].split("-");
                    if (hours[1].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[1].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select>

            <select name="thursdayClose">
                <option value="Closed">Closed</option>
                <%
                    if (hours[2].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[2].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select><br/>

            Friday: 
            <select name="fridayOpen">
                <option value="Closed">Closed</option>
                <%
                    hours = daysAndTime[4].split("-");
                    if (hours[1].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[1].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select>

            <select name="fridayClose">
                <option value="Closed">Closed</option>
                <%
                    if (hours[2].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[2].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select><br/>

            Saturday: 
            <select name="saturdayOpen">
                <option value="Closed">Closed</option>
                <%
                    hours = daysAndTime[5].split("-");
                    if (hours[1].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[1].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select>

            <select name="saturdayClose">
                <option value="Closed">Closed</option>
                <%
                    if (hours[2].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[2].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select><br/>

            Sunday: 
            <select name="sundayOpen">
                <option value="Closed">Closed</option>
                <%
                    hours = daysAndTime[6].split("-");
                    if (hours[1].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[2].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select>

            <select name="sundayClose">
                <option value="Closed">Closed</option>
                <%
                    if (hours[2].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[2].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select><br/>

            Public Holidays: 
            <select name="phOpen">
                <option value="Closed">Closed</option>
                <%
                    hours = daysAndTime[7].split("-");
                    if (hours[1].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[1].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select>

            <select name="phClose">
                <option value="Closed">Closed</option>
                <%
                    if (hours[2].equals("Closed")) {
                        out.println("<option value=\"Closed\" selected>Closed</option>");
                    } else {
                        out.println("<option value=\"Closed\">Closed</option>");
                    }
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        if (hours[2].equals(str)) {
                            out.println("<option value=\"" + str + "\" selected >" + str + "</option>");
                        } else {
                            out.println("<option value=\"" + str + "\" >" + str + "</option>");
                        }
                    }
                %>
            </select><br/><br/>            
            Opening Hours Format: <input type="text" name="openingHourFormat" value="<%=openingHourFormat%>"/><br/>
            Contact Number: <input type="text" name="contact" value="<%=ws.getContact()%>"/><br/>
            Another Contact Number: <input type="text" name="contact2" value="<%=contact2%>"/><br/>
            Location: 
            <select name="location">
                <%
                    if (location.equals("West")) {
                        out.println("<option value=\"West\" selected>West</option>");
                    } else {
                        out.println("<option value=\"West\">West</option>");
                    }

                    if (location.equals("North")) {
                        out.println("<option value=\"North\" selected>North</option>");
                    } else {
                        out.println("<option value=\"North\">North</option>");
                    }

                    if (location.equals("South")) {
                        out.println("<option value=\"South\" selected>South</option>");
                    } else {
                        out.println("<option value=\"South\">South</option>");
                    }

                    if (location.equals("Central")) {
                        out.println("<option value=\"Central\" selected>Central</option>");
                    } else {
                        out.println("<option value=\"Central\">Central</option>");
                    }

                    if (location.equals("East")) {
                        out.println("<option value=\"East\" selected>East</option>");
                    } else {
                        out.println("<option value=\"East\">East</option>");
                    }
                %> 
            </select><br/>
            Brands Carried <input type="text" name="brandsCarried" value="<%=brandsCarried%>"/><br/>
            Category: 
            <%
                String[] categories = category.split(",");
                if (Arrays.asList(categories).contains("Maintenance1")) {
                    out.println("<input type=\"checkbox\" name=\"category\" value=\"Maintenance1\" checked/>Maintenance1<br/>");
                } else {
                    out.println("<input type=\"checkbox\" name=\"category\" value=\"Maintenance1\"/>Maintenance1<br/>");
                }

                if (Arrays.asList(categories).contains("Car Grooming1")) {
                    out.println("<input type=\"checkbox\" name=\"category\" value=\"Car Grooming1\" checked/>Car Grooming1<br/>");
                } else {
                    out.println("<input type=\"checkbox\" name=\"category\" value=\"Car Grooming1\"/>Car Grooming1<br/>");
                }

                if (Arrays.asList(categories).contains("Tyre/Wheel Service1")) {
                    out.println("<input type=\"checkbox\" name=\"category\" value=\"Tyre/Wheel Service1\" checked/>Tyre/Wheel Service1<br/>");
                } else {
                    out.println("<input type=\"checkbox\" name=\"category\" value=\"Tyre/Wheel Service1\"/>Tyre/Wheel Service1<br/>");
                }

                if (Arrays.asList(categories).contains("Air Conditioning1")) {
                    out.println("<input type=\"checkbox\" name=\"category\" value=\"Air Conditioning1\" checked/>Air Conditioning1<br/>");
                } else {
                    out.println("<input type=\"checkbox\" name=\"category\" value=\"Air Conditioning1\"/>Air Conditioning1<br/>");
                }

                if (Arrays.asList(categories).contains("Battery1")) {
                    out.println("<input type=\"checkbox\" name=\"category\" value=\"Battery1\" checked/>Battery1<br/>");
                } else {
                    out.println("<input type=\"checkbox\" name=\"category\" value=\"Battery1\"/>Battery1<br/>");
                }

                if (Arrays.asList(categories).contains("Others1")) {
                    out.println("<input type=\"checkbox\" name=\"category\" value=\"Others1\" checked/>Others1<br/>");
                } else {
                    out.println("<input type=\"checkbox\" name=\"category\" value=\"Others1\"/>Others1<br/>");
                }
            %>
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
