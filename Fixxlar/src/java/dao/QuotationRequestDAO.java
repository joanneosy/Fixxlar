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
import entity.Customer;
import entity.QuotationRequest;
import entity.Vehicle;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

    public QuotationRequest retrieveQuotationRequest(int staffId, String token, int givenID) throws SQLException {
        return null;
    }

    //Assuming get new requests first
    //Default: 1-4 status
    //Workshop: won't show cancelled request at all
    //Will only show cancelled request to Fixir admin
    public HashMap<Integer, QuotationRequest> retrieveAllQuotationRequests(int staffId, String token, int givenWsId, int givenStatus,
            String givenCarModel, String orderBy, String order) throws SQLException, UnsupportedEncodingException, IOException, ParseException {

        HashMap<Integer, QuotationRequest> allQuotationRequests = new HashMap<>();
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
        urlParameters.add(new BasicNameValuePair("car_model", givenCarModel));

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
        JsonArray arr = jobj.getAsJsonObject("payload").getAsJsonArray("quotation_request_info");
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

            attElement = shop.get("service_urgency");
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

            attElement = shop.get("service_final_price");
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

            attElement = shop.get("car_make");
            String carMake = "";
            if (!attElement.isJsonNull()) {
                carMake = attElement.getAsString();
            }

            attElement = shop.get("car_model");
            String carModel = "";
            if (!attElement.isJsonNull()) {
                carModel = attElement.getAsString();
            }

            attElement = shop.get("car_year_manufactured");
            int carYear = 0;
            if (!attElement.isJsonNull()) {
                carYear = attElement.getAsInt();
            }

            attElement = shop.get("car_plate_number");
            String carPlate = "";
            if (!attElement.isJsonNull()) {
                carPlate = attElement.getAsString();
            }

            attElement = shop.get("car_color");
            String carColor = "";
            if (!attElement.isJsonNull()) {
                carColor = attElement.getAsString();
            }

            attElement = shop.get("car_type_of_control_of_car");
            String carControl = "";
            if (!attElement.isJsonNull()) {
                carControl = attElement.getAsString();
            }

            attElement = shop.get("customer_id");
            int customerId = 0;
            if (!attElement.isJsonNull()) {
                customerId = attElement.getAsInt();
            }

            attElement = shop.get("customer_name");
            String customerName = "";
            if (!attElement.isJsonNull()) {
                customerName = attElement.getAsString();
            }

            attElement = shop.get("customer_email");
            String customerEmail = "";
            if (!attElement.isJsonNull()) {
                customerEmail = attElement.getAsString();
            }

            attElement = shop.get("customer_mobile_number");
            String customerHpNo = "";
            if (!attElement.isJsonNull()) {
                customerHpNo = attElement.getAsString();
            }

            attElement = shop.get("service_status");
            int status = 0;
            if (!attElement.isJsonNull()) {
                status = attElement.getAsInt();
            }

            Vehicle vehicle = new Vehicle(vehicleId, carMake, carModel, carYear, carPlate, customerId, carColor, carControl);
            Customer customer = new Customer(customerId, customerEmail, customerName, customerHpNo);
            QuotationRequest qr = new QuotationRequest(id, name, details, description, vehicle, mileage, urgency, amenities, latitude, longitude, address, photos,
                    requestDateTime, status, wsId, finalQuotationPrice, offerId, customer);

            allQuotationRequests.put(i, qr);
        }
        return allQuotationRequests;
    }

    public boolean updateStatus(int staffId, String token, int serviceId, int givenStatus) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/quotation_request/update_request_status";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("service_id", serviceId + ""));
        urlParameters.add(new BasicNameValuePair("status", givenStatus + ""));

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
        JsonElement isSuccess = jobj.get("is_success");
        if (isSuccess.getAsString().equals("false")) {
            return false;
        } else {
            return true;
        }
    }

    //Add initial quotation (isQuotation = 1) or diagnostic price (isQuotation = 2)
    public boolean addInitialQuotation(int staffId, String token, int quotationRequestId, int workshopId, double price, String description, int isQuotation) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/quotation_request/save_offer";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("service_id", quotationRequestId + ""));
        urlParameters.add(new BasicNameValuePair("price", price + ""));
        urlParameters.add(new BasicNameValuePair("description", description));
        urlParameters.add(new BasicNameValuePair("shop_id", workshopId + ""));
        urlParameters.add(new BasicNameValuePair("isQuotation", isQuotation + ""));

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
        JsonElement isSuccess = jobj.get("is_success");
        if (isSuccess.getAsString().equals("false")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addFinalQuotation(int staffId, String token, int quotationRequestId, int workshopId, double price) throws UnsupportedEncodingException, IOException {
        String url = "";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("service_id", quotationRequestId + ""));
        urlParameters.add(new BasicNameValuePair("price", price + ""));
        urlParameters.add(new BasicNameValuePair("shop_id", workshopId + ""));

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
        JsonElement isSuccess = jobj.get("is_success");
        if (isSuccess.getAsString().equals("false")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addEstimatedCompletionTime(int staffId, String token, int quotationRequestId, int workshopId, double time) throws UnsupportedEncodingException, IOException {
        String url = "";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("service_id", quotationRequestId + ""));
        urlParameters.add(new BasicNameValuePair("time", time + ""));
        urlParameters.add(new BasicNameValuePair("shop_id", workshopId + ""));

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
        JsonElement isSuccess = jobj.get("is_success");
        if (isSuccess.getAsString().equals("false")) {
            return false;
        } else {
            return true;
        }
    }

    /*public static void main(String[] args) throws Exception {
        //HashMap<Integer, QuotationRequest> qList = retrieveAllQuotationRequests(3, "57cd00bbfaf886f8fe9fc98e15ee3bb0b31663a18f752de76cd998d71fe983be2b2f2a3de0f41ab8f69f3d19ab29b78c1b957578f5e7679e112de86fd1841da08c48d0a674fa001c3a2fe1e161c76edc6ce950b080c9a876034b3dfeabf826efacc3fde2d5542e8d4ccb90fd0163cb76371b7159546fd7d652d31abd73c235b290d4d62e2c31a8a00704432fae9a60e9f04eb4338698e0b2d5183d8cd4a4671ac1bc1d0ae79792bc21c8a1cd902d841a8fb6e8d0197cbccb3fd6f92456d9a361b940c0ff1affe7149e458fac8d7b0783e095d0dc4c82b86930e9fa378b4ffe1f15ce963d80291a7bc1c69ee0d63fb8b1d613d5feb3820b6c1a86cf7ab95d1340d8974f4acdf8e6ce9b426565d4f14b348c80787b4f24e95f9cd625f2a50e28d5118b364c362ae096623208881a9cc9f2161a422bac6d4d8e968f7b26093837d95b17352f417d0a061138438242a2cf6dd32e736bad691912e3d2701fc17a84a855a3aeb57969ad875a656e80206f10677818967ecd6942ea5463d1e3f188b84bc54c781ca85a3dceb76da8a17d9728fb0eb94b51ecd96787f11abefc7fb0e6f80307402b8d8e00920d8a48b566dbf1ad84b0487bb67863a31d122c86084ae08b891252fad4fe7df17c76cf419f33e2fb760968ac9e112567188c0b163961ef708e1fbb9059de5d7fa1f7f20da40123d5311fd308fcee95f36a3a4991b814ef3b", 0, 0, null, "requested_datetime", "desc");
        HashMap<Integer, Integer> qList = new HashMap<Integer, Integer>();
        qList.put(1, 1);
        qList.put(2, 2);
        Iterator it = qList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            int qr = (Integer) pair.getValue();
            System.out.println(qr);
        }
    }*/
}
