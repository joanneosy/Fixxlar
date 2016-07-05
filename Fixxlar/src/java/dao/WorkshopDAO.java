/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Workshop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import util.ConnectionManager;

/**
 *
 * @author Fixxlar
 */
public class WorkshopDAO {
    
    private final String USER_AGENT = "Mozilla/5.0";

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
                int status = rs.getByte("is_active");

                workshop = new Workshop(userID, email, name, description, website, address, openingHour, openingHourFormat,
                        latitude, longitude, contact, contact2, location, specialize, category, carBrands, remark, status);
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
                int status = rs.getInt("is_active");

                workshop = new Workshop(userID, email, name, description, website, address, openingHour, openingHourFormat,
                        latitude, longitude, contact, contact2, location, specialize, category, carBrands, remark, status);
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

    public ArrayList<Workshop> retrieveAllWorkshops(String orderBy, String givenIsActive) throws UnsupportedEncodingException, IOException {
        ArrayList<Workshop> allWorkshops = new ArrayList<Workshop>();
        String url = "http://119.81.43.85/erp/workshop/get_all_shop";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("user_id", "337"));
        urlParameters.add(new BasicNameValuePair("token", "1c21fa2b0e16cc95e4f3c837b36c812d9588e465a2d770c77b73c9e4744cb60fff61accec3ad0b37e649adf08fe551f4d4c70eff4e9233922ac4c256428589e696f055593e3393fbd2f335358985f815d7f984055b8ff2d977482dfc0bf4c915057b819fb6079edef02a88bde1d3241b7dfaaaf7bac9d4a74a3deb4ef838ac0529026ec97f88ab379a2e0c15340a857a775b0ac7f59b7a74586131084e1cbf66764f37a479e8621bd788d95c4d1f6d82a7ea2fa760a482cdf8b6f593f3d742a073b71a219197f49122fc1784fa4f4b7ed84371d33b4bf2e25a3dfa23a2b1501cf35b5434cb0a1678a7053efa43b0a533bcd288b3134cf0f81cae2f43e8ff14d72579f90a6ca86014ceab4992b5d352bb24bdc570ab8eabcfcd35b46a6c023df46bce56d51ea582d30da14bc84928d346346f1c93312fbd3ee784024f05da80a59ba8f9b733962b30165780af8697f3399a9994eac0c170029308efa00be5d0343e80c5390f3d91d82380003beea5d51b770e0a03c706c4f9ffec142c15f2a6de6cd392f3aa11b4cab14814205471e4abe371e3843aae412321fb8cde228eb66dfe812585f3324e192289a405e59297d7e1d9301bf49328f174a0e4b90df82064075b43d3c2539b3c09ff2f24d9dbbe00f913170696c912a84fef61563cc5f2b5e0a6b858441db2bb26b23ebeb1947ab235d9149e5eb46d09d154024d0c8a217a"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        String str = result.toString();
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(str);
        JsonObject jobj = element.getAsJsonObject();
        JsonArray arr = (jobj.getAsJsonObject("payload")).getAsJsonArray("shops");

        for (int i = 0; i < arr.size(); i++) {
            JsonElement workshop = arr.get(i);
            JsonObject workshopObj = workshop.getAsJsonObject();
            JsonElement attElement = workshopObj.get("id");
            int id = attElement.getAsInt();
            attElement = workshopObj.get("name");
            String name = attElement.getAsString();
            attElement = workshopObj.get("email");
            String email = "";
            //String email = attElement.getAsString();
            attElement = workshopObj.get("description");
            String description = attElement.getAsString();
            attElement = workshopObj.get("website");
            String website = attElement.getAsString();
            attElement = workshopObj.get("address");
            String address = attElement.getAsString();
            attElement = workshopObj.get("opening_hour_full");
            String openingHour = attElement.getAsString();
            attElement = workshopObj.get("opening_hour_format");
            String openingHourFormat = attElement.getAsString();
            attElement = workshopObj.get("latitude");
            double latitude = attElement.getAsDouble();
            attElement = workshopObj.get("longitude");
            double longitude = attElement.getAsDouble();
            attElement = workshopObj.get("contact");
            String contact = attElement.getAsString();
            attElement = workshopObj.get("contact2");
            String contact2 = attElement.getAsString();
            attElement = workshopObj.get("location");
            String location = attElement.getAsString();
            attElement = workshopObj.get("specialize");
            String specialize = attElement.getAsString();
            attElement = workshopObj.get("category");
            String category = attElement.getAsString();
            attElement = workshopObj.get("brand_carried");
            String carBrands = attElement.getAsString();
            attElement = workshopObj.get("remark");
            String remark = attElement.getAsString();
            attElement = workshopObj.get("status");
            int status = 1;
            //int status = attElement.getAsInt();
            Workshop ws = new Workshop(id, email, name, description, website, address, openingHour, openingHourFormat,
                    latitude, longitude, contact, contact2, location, specialize, category, carBrands, remark, status);

            allWorkshops.add(ws);
        }
        return allWorkshops;
    }

    public void updateWorkshop(int userID, String email, String name, String description, String website, String address, String openingHour, String openingHourFormat, double latitude,
            double longitude, String contact, String contact2, String location, String specialize, String category, String carBrands, String remark, int status) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            pstmt = conn.prepareStatement("UPDATE shops SET name = '" + name + "', email = '" + email + "', description = '" + description + "', website = '"
                    + website + "', address = '" + address + "', opening_hour_full = '" + openingHour + "', opening_hour_format = '"
                    + openingHourFormat + "', latitude = '" + latitude + "', longitude = '" + longitude + "', contact = '" + contact + "', contact2 = '"
                    + contact2 + "', location = '" + location + "', specialize = '" + specialize + "', category = '" + category + "', brand_carried = '"
                    + carBrands + "', remark = '" + remark + "', is_active = " + status + " WHERE id = " + userID + ";");
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
