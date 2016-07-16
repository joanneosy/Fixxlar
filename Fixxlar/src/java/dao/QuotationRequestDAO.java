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
import entity.QuotationRequest;
import entity.Workshop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class QuotationRequestDAO {

    private final String USER_AGENT = "Mozilla/5.0";

    public QuotationRequest retrieveQuotationRequest(int givenID) throws SQLException {
        return null;
    }

    //Assuming get new requests first
    //Default: 1-4 status
    //Workshop: won't show cancelled request at all
    //Will only show cancelled request to Fixir admin
    public ArrayList<QuotationRequest> retrieveAllQuotationRequests(int staffId, String token, int givenWsId, int givenStatus, 
            String carModel, String orderBy, String order) throws SQLException, UnsupportedEncodingException, IOException, ParseException {
       
        ArrayList<QuotationRequest> allQuotationRequests = new ArrayList<QuotationRequest>();
        String url = "http://119.81.43.85/erp/quotation_request/get_quotation_request_info";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("workshop_id", givenWsId + ""));
        urlParameters.add(new BasicNameValuePair("status", givenStatus + ""));
        urlParameters.add(new BasicNameValuePair("order_by", orderBy));
        urlParameters.add(new BasicNameValuePair("asc_of_desc", order));
        urlParameters.add(new BasicNameValuePair("car_model", carModel));

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
        JsonArray arr = (jobj.getAsJsonObject("payload")).getAsJsonArray("quotation_request_info");
        for (int i = 0; i < arr.size(); i++) {
            JsonElement workshop = arr.get(i);
            JsonObject shop = workshop.getAsJsonObject();
            JsonElement attElement = shop.get("service_id");
            int id = 0;
            if (!attElement.isJsonNull()) {
                id = attElement.getAsInt();
            }
            attElement = shop.get("service_name");
            String name = "";
            if (!attElement.isJsonNull()) {
                name = attElement.getAsString();
            }
            attElement = shop.get("service_details");
            String details = "";
            if (!attElement.isJsonNull()) {
                details = attElement.getAsString();
            }
            attElement = shop.get("service_description");
            String description = "";
            if (!attElement.isJsonNull()) {
                description = attElement.getAsString();
            }
            attElement = shop.get("service_mileage");
            String mileage = "";
            if (!attElement.isJsonNull()) {
                mileage = attElement.getAsString();
            }

            attElement = shop.get("as");
            String urgency = "";
            if (!attElement.isJsonNull()) {
                urgency = attElement.getAsString();
            }

            attElement = shop.get("service_address");
            String address = "";
            if (!attElement.isJsonNull()) {
                address = attElement.getAsString();
            }

            attElement = shop.get("service_longitude");
            double longitude = 0.0;
            if (!attElement.isJsonNull()) {
                longitude = attElement.getAsDouble();
            }

            attElement = shop.get("service_latitude");
            double latitude = 0.0;
            if (!attElement.isJsonNull()) {
                latitude = attElement.getAsDouble();
            }

            attElement = shop.get("service_amenities");
            String amenities = "";
            if (!attElement.isJsonNull()) {
                amenities = attElement.getAsString();
            }

            attElement = shop.get("service_photos");
            String photos = "";
            if (!attElement.isJsonNull()) {
                photos = attElement.getAsString();
            }

            attElement = shop.get("offer_id");
            int offerId = 0;
            if (!attElement.isJsonNull()) {
                offerId = attElement.getAsInt();
            }

            attElement = shop.get("quotation_price");
            double finalQuotationPrice = 0.0;
            if (!attElement.isJsonNull()) {
                finalQuotationPrice = attElement.getAsDouble();
            }

            //Change to timestamp if needed
            attElement = shop.get("requested_datetime");
            Timestamp requestDateTime = null;
            String dateTimeString = "";
            if (!attElement.isJsonNull()) {
                dateTimeString = attElement.getAsString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date parsedDate = dateFormat.parse(dateTimeString);
                requestDateTime = new java.sql.Timestamp(parsedDate.getTime());
            }
            
            attElement = shop.get("shop_id");
            int wsId = 0;
            if (!attElement.isJsonNull()) {
                wsId = attElement.getAsInt();
            }
            attElement = shop.get("vehicle_id");
            int vehicleId = 0;
            if (!attElement.isJsonNull()) {
                vehicleId = attElement.getAsInt();
            }
            attElement = shop.get("customer_id");
            int customerId = 0;
            if (!attElement.isJsonNull()) {
                customerId = attElement.getAsInt();
            }

            attElement = shop.get("service_status");
            int status = 0;
            if (!attElement.isJsonNull()) {
                status = attElement.getAsInt();
            }

            QuotationRequest qr = new QuotationRequest(id, name, details, description, vehicleId, mileage, urgency, amenities, latitude, longitude, address, photos,
                    requestDateTime, status, wsId, finalQuotationPrice, offerId, customerId);
            allQuotationRequests.add(qr);
        }
        return allQuotationRequests;
    }

    public void updateStatus(int requestID, int givenStatus) {

    }
    
    public void addOffer (int staffId, String token, int quotationRequestId, int workshopId, double price, String description) {
        
    }
            
            
    /*public static void main (String[] args) throws Exception {
        retrieveAllQuotationRequests(18, "cb2341be42e49a320f0dbba633e242254956ca9bb800485c757a6e37284fc9693c28a333b39df2791c5a8f88fe136c4060fb65814c807c7cc7acc897a529fc6d22ca19d35ee58820a3571eda94eae9c7c8ca3d76e7501e7df79840f3ede675f0b042cf09ca4e0dfe3ef7a21a4ea49bb0ae14225354831375b78acc64b0bdb6088b9693747d3e145715caa1f3e0dac23bf5190c37ef119f300a3ca1ac0ab18dd9a39c244e1fe7aeab8ad409e365d35a95a01ed3f2467b94fc97aadc2e4cb75482c517edb9e542387fa205b5549d89cae8463bf446cbb4c92b725cd99da45109badf09f2abd13c0d54143f3071186640a7fb1f100b849e5f6c6e1fbcbfa91a1ccec982b106d80b3d21a011f75e82ca16cb7f5d820374e1fd074b5373a367d1cf4c49668b790c3b761df624862302c78acd282c1f3d36eedb98e3d33bcf0b2ed2285490f953e0c588f65a893f07dbd49fbbe4211f898c23b3713358bbe00c0d0574a95256a5bfec7ae42a12f4df75a359fd7dd44d2c72430bb0426e1429fc5e9dad491e8cdb520d0f61b271efde9fe74a24baf208c542bdd49d9eab9a6d3eb836468b7c295d3d9792398b1287c86dd5fb59427cf8e038f1b2643e1dda27b9f4ac99fddf0af3b942d34b2f3b8d36c07b2552fdba09c535162e7eabfb80291f5b6e0087dfe5fcabf9a1384ca93d81923773ce6fd28e1efec778c656e7f379af9b994f", 
                8, 0, null, "requested_datetime", "desc");
     
    }*/
}
