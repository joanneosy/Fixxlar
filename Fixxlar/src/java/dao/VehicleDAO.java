/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Customer;
import entity.QuotationRequest;
import entity.Vehicle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import util.ConnectionManager;

/**
 *
 * @author Joanne
 */
public class VehicleDAO {

    private final String USER_AGENT = "Mozilla/5.0";

    public Vehicle getVehicle(int givenId) throws UnsupportedEncodingException, IOException {

        /* For web services
        //Add URL here
        String url = "";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        
        //Add parameters here
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("", ""));
        urlParameters.add(new BasicNameValuePair("", ""));

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
        JsonObject jobj = element.getAsJsonObject().getAsJsonObject("payload");
        
        JsonElement attElement = jobj.get("id");
        int id = attElement.getAsInt();
        attElement = jobj.get("make");
        String make = attElement.getAsString();
        attElement = jobj.get("model");
        String model = attElement.getAsString();
        attElement = jobj.get("year");
        int year = attElement.getAsInt();
        attElement = jobj.get("plate_number");
        String plateNumber = attElement.getAsString();
        attElement = jobj.get("user_id");
        int customerID = attElement.getAsInt();
        
        Vehicle vehicle = new Vehicle(id, make, model, year, plateNumber, customerID);

        return vehicle;
         */
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vehicle vehicle = null;
        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            rs = null;
            pstmt = conn.prepareStatement("select * from vehicles where id = " + givenId + ";");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int requestId = rs.getInt("id");
                String make = rs.getString("make");
                String model = rs.getString("model");
                int year = rs.getInt("year");
                String plateNumber = rs.getString("plate_number");
                int customerId = rs.getInt("user_id");
                vehicle = new Vehicle(requestId, make, model, year, plateNumber, customerId);
            }

            return vehicle;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt, rs);
            return vehicle;
        }
    }
}
