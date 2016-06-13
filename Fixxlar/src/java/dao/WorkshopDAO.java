/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import entity.Workshop;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import util.ConnectionManager;

/**
 *
 * @author Fixxlar
 */
public class WorkshopDAO {

    public Workshop retrieveWorkshop(String givenEmail) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Workshop workshop = null;
        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            rs = null;
            pstmt = conn.prepareStatement("SELECT * FROM `shops` WHERE email =\"" + givenEmail + "\"");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("id");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String website = rs.getString("website");
                String address = rs.getString("address");
                String openingHour = rs.getString("opening_hour_full");
                String openingHourFormat = rs.getString("opening_hour_format");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                String contact = rs.getString("contact");
                String contact2 = rs.getString("contact2");
                String location = rs.getString("location");
                String specialize = rs.getString("specialize");
                String category = rs.getString("category");
                String carBrands = rs.getString("brand_carried");
                String remark = rs.getString("remark");
                byte is_active = rs.getByte("is_active");

                workshop = new Workshop(userID, email, name, description, website, address, openingHour, openingHourFormat,
                        latitude, longitude, contact, contact2, location, specialize, category, carBrands, remark, is_active);
            }

            //Return null if email does not exist in database
            return workshop;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt, rs);
            return workshop;
        }
    }

    public Workshop retrieveWorkshop(int givenID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Workshop workshop = null;
        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            rs = null;
            pstmt = conn.prepareStatement("SELECT * FROM `shops` WHERE id =" + givenID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("id");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String website = rs.getString("website");
                String address = rs.getString("address");
                String openingHour = rs.getString("opening_hour_full");
                String openingHourFormat = rs.getString("opening_hour_format");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                String contact = rs.getString("contact");
                String contact2 = rs.getString("contact2");
                String location = rs.getString("location");
                String specialize = rs.getString("specialize");
                String category = rs.getString("category");
                String carBrands = rs.getString("brand_carried");
                String remark = rs.getString("remark");
                byte isActive = rs.getByte("is_active");

                workshop = new Workshop(userID, email, name, description, website, address, openingHour, openingHourFormat,
                        latitude, longitude, contact, contact2, location, specialize, category, carBrands, remark, isActive);
            }

            //Return null if email does not exist in database
            return workshop;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt, rs);
            return workshop;
        }
    }

    public void addWorkshop(String email, String name, String description, String website, String address, String openingHour, String openingHourFormat, double latitude,
            double longitude, String contact, String contact2, String location, String specialize, String category, String carBrands, String remark) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            pstmt = conn.prepareStatement("INSERT INTO shops VALUES (NULL,'" + email + "','" + name + "','" + description + "','" + website + "','"
                    + address + "','" + openingHour + "','" + openingHourFormat + "','" + latitude + "','" + longitude + "','" + contact + "','"
                    + contact2 + "','" + location + "','" + specialize + "','" + category + "','" + carBrands + "','" + remark + "'," + 1 + ");");
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt);
        }
    }

    public ArrayList<Workshop> retrieveAllWorkshops(String orderBy, String givenIsActive) {
        ArrayList<Workshop> allWorkshops = new ArrayList<Workshop>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs2 = null;
        String stmt = "SELECT * FROM shops";
        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            rs = null;
            
            if (!givenIsActive.equals("")) {
                stmt += " where is_active = " + givenIsActive;
            }
            if (!orderBy.equals("")) {
                stmt += " order by " + orderBy; 
            }
            
            pstmt = conn.prepareStatement(stmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("id");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String website = rs.getString("website");
                String address = rs.getString("address");
                String openingHour = rs.getString("opening_hour_full");
                String openingHourFormat = rs.getString("opening_hour_format");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                String contact = rs.getString("contact");
                String contact2 = rs.getString("contact2");
                String location = rs.getString("location");
                String specialize = rs.getString("specialize");
                String category = rs.getString("category");
                String carBrands = rs.getString("brand_carried");
                String remark = rs.getString("remark");
                byte isActive = rs.getByte("is_active");

                Workshop ws = new Workshop(userID, email, name, description, website, address, openingHour, openingHourFormat,
                        latitude, longitude, contact, contact2, location, specialize, category, carBrands, remark, isActive);
                allWorkshops.add(ws);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt, rs);
            ConnectionManager.close(conn, pstmt2, rs2);

            return allWorkshops;
        }
    }

    public void updateWorkshop(int userID, String email, String name, String description, String website, String address, String openingHour, String openingHourFormat, double latitude,
            double longitude, String contact, String contact2, String location, String specialize, String category, String carBrands, String remark, byte isActive) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            pstmt = conn.prepareStatement("UPDATE shops SET name = '" + name + "', email = '" + email + "', description = '" + description + "', website = '"
                    + website + "', address = '" + address + "', opening_hour_full = '" + openingHour + "', opening_hour_format = '"
                    + openingHourFormat + "', latitude = '" + latitude + "', longitude = '" + longitude + "', contact = '" + contact + "', contact2 = '"
                    + contact2 + "', location = '" + location + "', specialize = '" + specialize + "', category = '" + category + "', brand_carried = '"
                    + carBrands + "', remark = '" + remark + "', is_active = " + isActive + " WHERE id = " + userID + ";");
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt);
        }
    }

    public ArrayList<String> retrieveAllCarBrands() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<String> carBrands = new ArrayList<String>();
        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            rs = null;
            pstmt = conn.prepareStatement("SELECT * FROM car_brands");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String model = rs.getString("model");
                int year = rs.getInt("year");
                if (year == 0) {
                    carBrands.add(name + " " + model);
                } else {
                    carBrands.add(name + " " + model + " " + year);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt, rs);
            return carBrands;
        }
    }

    //Convert address to latitude and longitude 
    //Latitude = String[0]
    //Longitude = String[1]
    public String[] retrieveLatLong(String address) throws Exception {
        int responseCode = 0;
        String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
        URL url = new URL(api);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.connect();
        responseCode = httpConnection.getResponseCode();
        if (responseCode == 200) {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
            Document document = builder.parse(httpConnection.getInputStream());
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("/GeocodeResponse/status");
            String status = (String) expr.evaluate(document, XPathConstants.STRING);
            if (status.equals("OK")) {
                expr = xpath.compile("//geometry/location/lat");
                String latitude = (String) expr.evaluate(document, XPathConstants.STRING);
                expr = xpath.compile("//geometry/location/lng");
                String longitude = (String) expr.evaluate(document, XPathConstants.STRING);
                return new String[]{latitude, longitude};
            } else {
                return null;
            }
        }
        return null;
    }
}
