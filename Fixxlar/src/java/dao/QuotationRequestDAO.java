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
import entity.Offer;
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

    public HashMap<Integer, Integer> retrieveStatusSize(int staffId, String token, int givenWsId, int givenStatus,
            String givenCarModel, String orderBy, String order) throws SQLException, UnsupportedEncodingException, IOException, ParseException {
        HashMap<Integer, Integer> statusSize = new HashMap<Integer, Integer>();
        int statusArr[] = {1, 3, 5};
        for (int i = 0; i < 3; i++) {
            int sta = statusArr[i];
            String url = "http://119.81.43.85/erp/quotation_request/get_quotation_request_info";

            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
            urlParameters.add(new BasicNameValuePair("token", token));
            urlParameters.add(new BasicNameValuePair("workshop_id", givenWsId + ""));
            urlParameters.add(new BasicNameValuePair("status", sta + ""));
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
            int arrSize = arr.size();
            statusSize.put(i, arrSize);//To be changed to the status
        }
        return statusSize;
    }

    //Retrieve a service using the service ID. If called by admin, it can contain more than one if more than one workshops gave offers. 
    public HashMap<Integer, QuotationRequest> retrieveQuotationRequest(int staffId, String token, int givenID) throws SQLException, ParseException, UnsupportedEncodingException, IOException {
        HashMap<Integer, QuotationRequest> allQuotationRequests = new HashMap<Integer, QuotationRequest>();
        String url = "http://119.81.43.85/erp/quotation_request/get_quotation_request_info_by_service_id";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("service_id", givenID + ""));

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
        int arrSize = arr.size();
        if (arrSize > 20) {
            arrSize = 20;
        }
        for (int i = 0; i < arrSize; i++) {
//        for (int i = 0; i < arr.size(); i++) {
            JsonElement qrElement = arr.get(i);
            JsonObject qrObj = qrElement.getAsJsonObject();
            JsonElement attElement = qrObj.get("service_id");
            int id = 0;
            if (!attElement.isJsonNull()) {
                id = attElement.getAsInt();
            }
            attElement = qrObj.get("service_name");
            String name = "";
            if (!attElement.isJsonNull()) {
                name = attElement.getAsString();
            }
            attElement = qrObj.get("service_details");
            String details = "";
            if (!attElement.isJsonNull()) {
                details = attElement.getAsString();
            }
            attElement = qrObj.get("service_description");
            String description = "";
            if (!attElement.isJsonNull()) {
                description = attElement.getAsString();
            }
            attElement = qrObj.get("service_mileage");
            String mileage = "";
            if (!attElement.isJsonNull()) {
                mileage = attElement.getAsString();
            }

            attElement = qrObj.get("service_urgency");
            String urgency = "";
            if (!attElement.isJsonNull()) {
                urgency = attElement.getAsString();
            }

            attElement = qrObj.get("service_address");
            String address = "";
            if (!attElement.isJsonNull()) {
                address = attElement.getAsString();
            }

            attElement = qrObj.get("service_longitude");
            double longitude = 0.0;
            if (!attElement.isJsonNull()) {
                longitude = attElement.getAsDouble();
            }

            attElement = qrObj.get("service_latitude");
            double latitude = 0.0;
            if (!attElement.isJsonNull()) {
                latitude = attElement.getAsDouble();
            }

            attElement = qrObj.get("service_amenities");
            String amenities = "";
            if (!attElement.isJsonNull()) {
                amenities = attElement.getAsString();
            }

            attElement = qrObj.get("service_photos");
            String photos = "";
            if (!attElement.isJsonNull()) {
                photos = attElement.getAsString();
            }

            attElement = qrObj.get("offer_id");
            int offerId = 0;
            if (attElement != null && !attElement.isJsonNull()) {
                offerId = attElement.getAsInt();
            }

            attElement = qrObj.get("offer_status");
            int offerStatus = 0;
            if (attElement != null && !attElement.isJsonNull()) {
                offerStatus = attElement.getAsInt();
            }

            attElement = qrObj.get("quotation_min_price");
            double initialMinPrice = 0.0;
            if (attElement != null && !attElement.isJsonNull()) {
                initialMinPrice = attElement.getAsDouble();
            }

            attElement = qrObj.get("quotation_max_price");
            double initialMaxPrice = 0.0;
            if (attElement != null && !attElement.isJsonNull()) {
                initialMaxPrice = attElement.getAsDouble();
            }

            attElement = qrObj.get("diagnostic_price");
            double diagnosticPrice = 0.0;
            if (attElement != null && !attElement.isJsonNull()) {
                diagnosticPrice = attElement.getAsDouble();
            }

            attElement = qrObj.get("offer_final_price");
            double finalQuotationPrice = 0.0;
            if (attElement != null && !attElement.isJsonNull()) {
                finalQuotationPrice = attElement.getAsDouble();
            }

            attElement = qrObj.get("offer_est_complete_time");
            Timestamp estCompletionDateTime = null;
            String dateTimeString = "1990-01-01 00:00:00";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(dateTimeString);
            estCompletionDateTime = new java.sql.Timestamp(parsedDate.getTime());
            if (attElement != null && !attElement.isJsonNull()) {
                dateTimeString = attElement.getAsString();
                dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                parsedDate = dateFormat.parse(dateTimeString);
                estCompletionDateTime = new java.sql.Timestamp(parsedDate.getTime());
            }

            attElement = qrObj.get("requested_datetime");
            Timestamp requestDateTime = null;
            dateTimeString = "1990-01-01 00:00:00";
            dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            parsedDate = dateFormat.parse(dateTimeString);
            requestDateTime = new java.sql.Timestamp(parsedDate.getTime());
            if (!attElement.isJsonNull()) {
                dateTimeString = attElement.getAsString();
                dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                parsedDate = dateFormat.parse(dateTimeString);
                requestDateTime = new java.sql.Timestamp(parsedDate.getTime());
            }

            attElement = qrObj.get("shop_id");
            int wsId = 0;
            if (attElement != null && !attElement.isJsonNull()) {
                wsId = attElement.getAsInt();
            }

            attElement = qrObj.get("vehicle_id");
            int vehicleId = 0;
            if (!attElement.isJsonNull()) {
                vehicleId = attElement.getAsInt();
            }

            attElement = qrObj.get("car_make");
            String carMake = "";
            if (!attElement.isJsonNull()) {
                carMake = attElement.getAsString();
            }

            attElement = qrObj.get("car_model");
            String carModel = "";
            if (!attElement.isJsonNull()) {
                carModel = attElement.getAsString();
            }

            attElement = qrObj.get("car_year_manufactured");
            int carYear = 0;
            if (!attElement.isJsonNull()) {
                carYear = attElement.getAsInt();
            }

            attElement = qrObj.get("car_plate_number");
            String carPlate = "";
            if (!attElement.isJsonNull()) {
                carPlate = attElement.getAsString();
            }

            attElement = qrObj.get("car_color");
            String carColor = "";
            if (!attElement.isJsonNull()) {
                carColor = attElement.getAsString();
            }

            attElement = qrObj.get("car_type_of_control_of_car");
            String carControl = "";
            if (!attElement.isJsonNull()) {
                carControl = attElement.getAsString();
            }

            attElement = qrObj.get("customer_id");
            int customerId = 0;
            if (!attElement.isJsonNull()) {
                customerId = attElement.getAsInt();
            }

            attElement = qrObj.get("customer_name");
            String customerName = "";
            if (!attElement.isJsonNull()) {
                customerName = attElement.getAsString();
            }

            attElement = qrObj.get("customer_email");
            String customerEmail = "";
            if (!attElement.isJsonNull()) {
                customerEmail = attElement.getAsString();
            }

            attElement = qrObj.get("customer_mobile_number");
            String customerHpNo = "";
            if (!attElement.isJsonNull()) {
                customerHpNo = attElement.getAsString();
            }

            attElement = qrObj.get("service_category");
            String category = "";
            if (!attElement.isJsonNull()) {
                category = attElement.getAsString();
            }

            attElement = qrObj.get("service_rejection_times");
            int noOfRejections = 0;
            if (!attElement.isJsonNull()) {
                noOfRejections = attElement.getAsInt();
            }
            Vehicle vehicle = new Vehicle(vehicleId, carMake, carModel, carYear, carPlate, customerId, carColor, carControl);
            Customer customer = new Customer(customerId, customerEmail, customerName, customerHpNo);
            Offer offer = new Offer(offerId, id, wsId, offerStatus, initialMinPrice, initialMaxPrice, diagnosticPrice, finalQuotationPrice, estCompletionDateTime);

            QuotationRequest qr = new QuotationRequest(id, name, details, description, vehicle, mileage, urgency, amenities, latitude, longitude, address, photos,
                    requestDateTime, category, noOfRejections, wsId, customer, offer);
            allQuotationRequests.put(i, qr);
        }
        return allQuotationRequests;
    }

    //Assuming get new requests first
    //Default: 1-4 status
    //Workshop: won't show cancelled request at all
    //Will only show cancelled request to Fixir admin
    public HashMap<Integer, QuotationRequest> retrieveAllQuotationRequests(int staffId, String token, int givenWsId, int givenStatus, String orderBy, String order) throws SQLException, UnsupportedEncodingException, IOException, ParseException {

        HashMap<Integer, QuotationRequest> allQuotationRequests = new HashMap<Integer, QuotationRequest>();
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
        int arrSize = arr.size();
        if (arrSize > 20) {
            arrSize = 20;
        }
        for (int i = 0; i < arrSize; i++) {
//        for (int i = 0; i < arr.size(); i++) {
            JsonElement qrElement = arr.get(i);
            JsonObject qrObj = qrElement.getAsJsonObject();
            JsonElement attElement = qrObj.get("service_id");
            int id = 0;
            if (!attElement.isJsonNull()) {
                id = attElement.getAsInt();
            }
            attElement = qrObj.get("service_name");
            String name = "";
            if (!attElement.isJsonNull()) {
                name = attElement.getAsString();
            }
            attElement = qrObj.get("service_details");
            String details = "";
            if (!attElement.isJsonNull()) {
                details = attElement.getAsString();
            }
            attElement = qrObj.get("service_description");
            String description = "";
            if (!attElement.isJsonNull()) {
                description = attElement.getAsString();
            }
            attElement = qrObj.get("service_mileage");
            String mileage = "";
            if (!attElement.isJsonNull()) {
                mileage = attElement.getAsString();
            }

            attElement = qrObj.get("service_urgency");
            String urgency = "";
            if (!attElement.isJsonNull()) {
                urgency = attElement.getAsString();
            }

            attElement = qrObj.get("service_address");
            String address = "";
            if (!attElement.isJsonNull()) {
                address = attElement.getAsString();
            }

            attElement = qrObj.get("service_longitude");
            double longitude = 0.0;
            if (!attElement.isJsonNull()) {
                longitude = attElement.getAsDouble();
            }

            attElement = qrObj.get("service_latitude");
            double latitude = 0.0;
            if (!attElement.isJsonNull()) {
                latitude = attElement.getAsDouble();
            }

            attElement = qrObj.get("service_amenities");
            String amenities = "";
            if (!attElement.isJsonNull()) {
                amenities = attElement.getAsString();
            }

            attElement = qrObj.get("service_photos");
            String photos = "";
            if (!attElement.isJsonNull()) {
                photos = attElement.getAsString();
            }

            attElement = qrObj.get("offer_id");
            int offerId = 0;
            if (attElement != null && !attElement.isJsonNull()) {
                offerId = attElement.getAsInt();
            }

            attElement = qrObj.get("offer_status");
            int offerStatus = 0;
            if (attElement != null && !attElement.isJsonNull()) {
                offerStatus = attElement.getAsInt();
            }

            attElement = qrObj.get("quotation_min_price");
            double initialMinPrice = 0.0;
            if (attElement != null && !attElement.isJsonNull()) {
                initialMinPrice = attElement.getAsDouble();
            }

            attElement = qrObj.get("quotation_max_price");
            double initialMaxPrice = 0.0;
            if (attElement != null && !attElement.isJsonNull()) {
                initialMaxPrice = attElement.getAsDouble();
            }

            attElement = qrObj.get("diagnostic_price");
            double diagnosticPrice = 0.0;
            if (attElement != null && !attElement.isJsonNull()) {
                diagnosticPrice = attElement.getAsDouble();
            }

            attElement = qrObj.get("offer_final_price");
            double finalQuotationPrice = 0.0;
            if (attElement != null && !attElement.isJsonNull()) {
                finalQuotationPrice = attElement.getAsDouble();
            }

            attElement = qrObj.get("offer_est_complete_time");
            Timestamp estCompletionDateTime = null;
            String dateTimeString = "1990-01-01 00:00:00";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(dateTimeString);
            estCompletionDateTime = new java.sql.Timestamp(parsedDate.getTime());
            if (attElement != null && !attElement.isJsonNull()) {
                dateTimeString = attElement.getAsString();
                dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                parsedDate = dateFormat.parse(dateTimeString);
                estCompletionDateTime = new java.sql.Timestamp(parsedDate.getTime());
            }

            attElement = qrObj.get("requested_datetime");
            Timestamp requestDateTime = null;
            dateTimeString = "1990-01-01 00:00:00";
            dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            parsedDate = dateFormat.parse(dateTimeString);
            requestDateTime = new java.sql.Timestamp(parsedDate.getTime());
            if (!attElement.isJsonNull()) {
                dateTimeString = attElement.getAsString();
                dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                parsedDate = dateFormat.parse(dateTimeString);
                requestDateTime = new java.sql.Timestamp(parsedDate.getTime());
            }

            attElement = qrObj.get("shop_id");
            int wsId = 0;
            if (attElement != null && !attElement.isJsonNull()) {
                wsId = attElement.getAsInt();
            }

            attElement = qrObj.get("vehicle_id");
            int vehicleId = 0;
            if (!attElement.isJsonNull()) {
                vehicleId = attElement.getAsInt();
            }

            attElement = qrObj.get("car_make");
            String carMake = "";
            if (!attElement.isJsonNull()) {
                carMake = attElement.getAsString();
            }

            attElement = qrObj.get("car_model");
            String carModel = "";
            if (!attElement.isJsonNull()) {
                carModel = attElement.getAsString();
            }

            attElement = qrObj.get("car_year_manufactured");
            int carYear = 0;
            if (!attElement.isJsonNull()) {
                carYear = attElement.getAsInt();
            }

            attElement = qrObj.get("car_plate_number");
            String carPlate = "";
            if (!attElement.isJsonNull()) {
                carPlate = attElement.getAsString();
            }

            attElement = qrObj.get("car_color");
            String carColor = "";
            if (!attElement.isJsonNull()) {
                carColor = attElement.getAsString();
            }

            attElement = qrObj.get("car_type_of_control_of_car");
            String carControl = "";
            if (!attElement.isJsonNull()) {
                carControl = attElement.getAsString();
            }

            attElement = qrObj.get("customer_id");
            int customerId = 0;
            if (!attElement.isJsonNull()) {
                customerId = attElement.getAsInt();
            }

            attElement = qrObj.get("customer_name");
            String customerName = "";
            if (!attElement.isJsonNull()) {
                customerName = attElement.getAsString();
            }

            attElement = qrObj.get("customer_email");
            String customerEmail = "";
            if (!attElement.isJsonNull()) {
                customerEmail = attElement.getAsString();
            }

            attElement = qrObj.get("customer_mobile_number");
            String customerHpNo = "";
            if (!attElement.isJsonNull()) {
                customerHpNo = attElement.getAsString();
            }

            attElement = qrObj.get("service_category");
            String category = "";
            if (!attElement.isJsonNull()) {
                category = attElement.getAsString();
            }

            attElement = qrObj.get("service_rejection_times");
            int noOfRejections = 0;
            if (!attElement.isJsonNull()) {
                noOfRejections = attElement.getAsInt();
            }
            Vehicle vehicle = new Vehicle(vehicleId, carMake, carModel, carYear, carPlate, customerId, carColor, carControl);
            Customer customer = new Customer(customerId, customerEmail, customerName, customerHpNo);
            Offer offer = new Offer(offerId, id, wsId, offerStatus, initialMinPrice, initialMaxPrice, diagnosticPrice, finalQuotationPrice, estCompletionDateTime);

            QuotationRequest qr = new QuotationRequest(id, name, details, description, vehicle, mileage, urgency, amenities, latitude, longitude, address, photos,
                    requestDateTime, category, noOfRejections, wsId, customer, offer);
            allQuotationRequests.put(i, qr);
        }
        return allQuotationRequests;
    }

    public boolean completeService(int staffId, String token, int offerId) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/quotation_request/complete_service";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("offer_id", offerId + ""));

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

    public boolean addInitialQuotation(int staffId, String token, int quotationRequestId, int workshopId, double minPrice, double maxPrice, String description) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/quotation_request/save_offer";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("service_id", quotationRequestId + ""));
        urlParameters.add(new BasicNameValuePair("min_price", minPrice + ""));
        urlParameters.add(new BasicNameValuePair("max_price", maxPrice + ""));
        urlParameters.add(new BasicNameValuePair("description", description));
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

    public boolean addDiagnosticPrice(int staffId, String token, int quotationRequestId, int workshopId, double price, String description) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/quotation_request/save_offer";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("service_id", quotationRequestId + ""));
        urlParameters.add(new BasicNameValuePair("diagnostic_price", price + ""));
        urlParameters.add(new BasicNameValuePair("shop_id", workshopId + ""));
        urlParameters.add(new BasicNameValuePair("description", description));

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

    public boolean addFinalQuotation(int staffId, String token, int offerId, double price) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/quotation_request/add_final_quotation";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("final_quotation", price + ""));
        urlParameters.add(new BasicNameValuePair("offer_id", offerId + ""));

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

    public boolean addEstimatedCompletionTime(int staffId, String token, int offerId, String time) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/quotation_request/update_estimated_completion_time";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("offer_id", offerId + ""));
        urlParameters.add(new BasicNameValuePair("estimated_completion_time", time));

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

    public Offer retrieveOffer(int staffId, String token, int offerId) throws SQLException, ParseException, UnsupportedEncodingException, IOException {
        Offer offer = null;
        String url = "http://119.81.43.85/quotation_request/retrieve_min_max_quotation_or_diagnostic_price";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("offer_id", offerId + ""));

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
        JsonElement oElement = jobj.get("payload");
        JsonObject oObj = null;
        if (oElement.isJsonNull()) {
            return offer;
        }
        
        oObj = oElement.getAsJsonObject();
        JsonElement attElement = oObj.get("service_id");
        int serviceId = 0;
        if (!attElement.isJsonNull()) {
            serviceId = attElement.getAsInt();
        }
        
        attElement = oObj.get("est_complete_time");
        Timestamp estCompletionDateTime = null;
        String dateTimeString = "1990-01-01 00:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDate = dateFormat.parse(dateTimeString);
        estCompletionDateTime = new java.sql.Timestamp(parsedDate.getTime());
        if (attElement != null && !attElement.isJsonNull()) {
            dateTimeString = attElement.getAsString();
            dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            parsedDate = dateFormat.parse(dateTimeString);
            estCompletionDateTime = new java.sql.Timestamp(parsedDate.getTime());
        }
        
        attElement = oObj.get("final_price");
        double finalPrice = 0.0;
        if (!attElement.isJsonNull()) {
            finalPrice = attElement.getAsDouble();
        }
        
        attElement = oObj.get("offer_status");
        int status = 0;
        if (!attElement.isJsonNull()) {
            status = attElement.getAsInt();
        }
        
        attElement = oObj.get("shop_id");
        int wsId = 0;
        if (!attElement.isJsonNull()) {
            wsId = attElement.getAsInt();
        }

        attElement = oObj.get("min_price");
        double initialMinPrice = 0.0;
        if (attElement != null && !attElement.isJsonNull()) {
            initialMinPrice = attElement.getAsDouble();
        }
        
        attElement = oObj.get("max_price");
        double initialMaxPrice = 0.0;
        if (attElement != null && !attElement.isJsonNull()) {
            initialMaxPrice = attElement.getAsDouble();
        }
        
        attElement = oObj.get("diagnostic_price");
        double diagnosticPrice = 0.0;
        if (attElement != null && !attElement.isJsonNull()) {
            diagnosticPrice = attElement.getAsDouble();
        }

        offer = new Offer(offerId, serviceId, wsId, status, initialMinPrice, initialMaxPrice, diagnosticPrice, finalPrice, estCompletionDateTime);
        return offer;
        
    }
    
    public static void main(String[] args) throws Exception {
        //HashMap<Integer, QuotationRequest> qList = retrieveAllQuotationRequests(3, "57cd00bbfaf886f8fe9fc98e15ee3bb0b31663a18f752de76cd998d71fe983be2b2f2a3de0f41ab8f69f3d19ab29b78c1b957578f5e7679e112de86fd1841da08c48d0a674fa001c3a2fe1e161c76edc6ce950b080c9a876034b3dfeabf826efacc3fde2d5542e8d4ccb90fd0163cb76371b7159546fd7d652d31abd73c235b290d4d62e2c31a8a00704432fae9a60e9f04eb4338698e0b2d5183d8cd4a4671ac1bc1d0ae79792bc21c8a1cd902d841a8fb6e8d0197cbccb3fd6f92456d9a361b940c0ff1affe7149e458fac8d7b0783e095d0dc4c82b86930e9fa378b4ffe1f15ce963d80291a7bc1c69ee0d63fb8b1d613d5feb3820b6c1a86cf7ab95d1340d8974f4acdf8e6ce9b426565d4f14b348c80787b4f24e95f9cd625f2a50e28d5118b364c362ae096623208881a9cc9f2161a422bac6d4d8e968f7b26093837d95b17352f417d0a061138438242a2cf6dd32e736bad691912e3d2701fc17a84a855a3aeb57969ad875a656e80206f10677818967ecd6942ea5463d1e3f188b84bc54c781ca85a3dceb76da8a17d9728fb0eb94b51ecd96787f11abefc7fb0e6f80307402b8d8e00920d8a48b566dbf1ad84b0487bb67863a31d122c86084ae08b891252fad4fe7df17c76cf419f33e2fb760968ac9e112567188c0b163961ef708e1fbb9059de5d7fa1f7f20da40123d5311fd308fcee95f36a3a4991b814ef3b", 0, 0, null, "requested_datetime", "desc");
        //retrieveOffer(20, "bfa5334a57318d381e35ca9cd6c41f12cf504f6458e0f6799f62a946459def5f73f3490ba6577c61b4ea9bb69cc020ef8d37bc98ca12c57b757babf71b1d1b337729275e333b20f19a8738eca93bef992c49912d1943e599b5b5a1f33ad2d31dc6daa4f706d44760abd86764701d46b8c0e09ed0929977e41e41c62223c3267db510a9d8ff69994474d438dbee3d13d52bad3f65272b802f3cd54b9ef6da8d6c6d24a00cb263aa58e50fac2479d95f9664b1c31bfe1b0f640d3621f84f7642fefc28c0fe0c5069cbf37bf73e7e1a970a01374d3412cce1668e060af58fa02081a3ec9ba1bfe50d5a87ce82c5b30848b6c5026ba4839ab00e7fdd7e350691b323603ce4b511b5f4e7657361287d1a208ee8bf169b26db5a8419c54ca22428416ab28b95074192be54d4685b6acdaba8d1d8ceffff8c3afd565c929424faaca62f27d0e00acbd46cba910f274facfd511ed7acab33a5bb81dc622e10f43ce64f6394549535d58fb40271558f07c5b0e054121fa2382dafa2993ee393e6a2cc2693f5d11c88890bab3020f11a3e20c49fe040173b948d07cc0b76439a4a2966a59962268fb556a8deb4009333b4b0c327751430463097ca0d5231b0803c950bfb534291c57688306e4fc891b060b0a81bebc7261cf6618108f807a6685a785d72f13b086373d412218e4468bab6078c998d5f6ec3867330934c39e497d58850c5ec", 59);
        //retrieveQuotationRequest(16, "494ccf8d9c4367d5ea8de06645dfea172e50aeec2368bdcd7a2d9d3425b8dd9e6436719eb153df80789397279afa78acd5050cb05de7c0a7f993004646eceb089932a6d4d0755950e0ddeedd875590dd6be10c01c5cf2f569a3e9879e598a971dac1b94d210758f4cf7f9142d7ab2a1cc51b745bf9f3559731d7641650591b09e4c5a764b200a23609aceb382c2982e7f5ee2b5602c7f05714424805c355c364348486fb6b26f3c4621430e239bfa3c05943cbaba1492af684c81ad08e84bf410b3968568eb9c01fcfa507965c76d4b1806944ebea4b3bec84d788bbf5a950bc15bf101edbdb233c737fa3cea6ec3b6450822f1f663b13559c8369ccac2b15010f3cc519d342797db8d42ce3bf4e492ce6933165833b88b0275d08515c2c9bb0843b4e2ba178c83c4e5ce075d81329950fbc12002f7d2407fb00cc94ae029cdfa0ad6a91e0b20eb23dcb62dbec225849828bc0f86839090d0ec44361462d768e6f886438dcdb92494751cd94255147f183dc91256a8f150dd49f901a232bd3dcc6176483c587d79d23565419e7a62fbef62ea5cc4031b4173b42fa734419a8941fc52f753841cf58cb3431b20f53059185692e2b84fc6bdf85efdefdfa4248b8a8a410bb4b8e155e2e37caf4e4f66af9986846ec83d1c5079a01119d40559a37149c66b331cdd58798424dbbcdcc3d5cb503d7ec3ffd2756bb5e92e85625e1fd", 659);
    }
}
