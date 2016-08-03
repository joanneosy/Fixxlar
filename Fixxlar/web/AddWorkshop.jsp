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
            Address: <input type="text" name="address" required/><br/>
            Postal Code: <input type="text" name="postalCode" required/><br/>
            Website: https://<input type="text" name="website" required/><br/>
            Description: <textarea name= 'description' cols="50" rows="5"></textarea><br/>
            <br/>
            Opening Hours: <br/>
            Monday: 
            <select name="mondayOpen">
                <option value="Closed">Closed</option>
                <%                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select>

            <select name="mondayClose">
                <option value="Closed">Closed</option>
                <%
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select><br/>

            Tuesday: 
            <select name="tuesdayOpen">
                <option value="Closed">Closed</option>
                <%
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select>

            <select name="tuesdayClose">
                <option value="Closed">Closed</option>
                <%
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select><br/>

            Wednesday: 
            <select name="wednesdayOpen">
                <option value="Closed">Closed</option>
                <%
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select>

            <select name="wednesdayClose">
                <option value="Closed">Closed</option>
                <%
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select><br/>

            Thursday 
            <select name="thursdayOpen">
                <option value="Closed">Closed</option>
                <%
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select>

            <select name="thursdayClose">
                <option value="Closed">Closed</option>
                <%
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select><br/>

            Friday: 
            <select name="fridayOpen">
                <option value="Closed">Closed</option>
                <%
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select>

            <select name="fridayClose">
                <option value="Closed">Closed</option>
                <%
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select><br/>

            Saturday: 
            <select name="saturdayOpen">
                <option value="Closed">Closed</option>
                <%
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select>

            <select name="saturdayClose">
                <option value="Closed">Closed</option>
                <%
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select><br/>

            Sunday: 
            <select name="sundayOpen">
                <option value="Closed">Closed</option>
                <%
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select>

            <select name="sundayClose">
                <option value="Closed">Closed</option>
                <%
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select><br/>

            Public Holidays: 
            <select name="phOpen">
                <option value="Closed">Closed</option>
                <%
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select>

            <select name="phClose">
                <option value="Closed">Closed</option>
                <%
                    for (int i = 0; i < 2400; i += 100) {
                        String str = i + "";
                        if (str.length() == 3) {
                            str = "0" + str;
                        } else if (str.length() == 1) {
                            str = "0000";
                        }
                        out.println("<option value=\"" + str + "\">" + str + "</option>");
                    }
                %>
            </select><br/><br/>
            Opening Hours Format: <input type="text" name="openingHourFormat" /><br/>
            Contact Number: <input type="text" name="contact" required/><br/>
            Another Contact Number: <input type="text" name="contact2" /><br/>
            Location: 
            <select name="location">
                <option value="West">West</option>
                <option value="North">North</option>
                <option value="South">South</option>
                <option value="East">East</option>
                <option value="Central">Central</option>
            </select><br/>
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
