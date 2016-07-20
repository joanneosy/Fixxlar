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

    private static final String USER_AGENT = "Mozilla/5.0";

    public static QuotationRequest retrieveQuotationRequest(int staffId, String token, int givenID) throws SQLException, ParseException, UnsupportedEncodingException, IOException {
        QuotationRequest qr = null;
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
        JsonElement qrElement = jobj.get("payload");
        JsonObject qrObj = null;
        if (qrElement.isJsonNull()) {
            return qr;
        }
        qrObj = qrElement.getAsJsonObject().getAsJsonObject("quotation_request_info");
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
        if (!attElement.isJsonNull()) {
            offerId = attElement.getAsInt();
        }

        attElement = qrObj.get("service_final_price");
        double finalQuotationPrice = 0.0;
        if (!attElement.isJsonNull()) {
            finalQuotationPrice = attElement.getAsDouble();
        }

        //Change to timestamp if needed
        attElement = qrObj.get("requested_datetime");
        Timestamp requestDateTime = null;
        String dateTimeString = "1990-01-01 00:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDate = dateFormat.parse(dateTimeString);
        requestDateTime = new java.sql.Timestamp(parsedDate.getTime());
        if (!attElement.isJsonNull()) {
            dateTimeString = attElement.getAsString();
            dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            parsedDate = dateFormat.parse(dateTimeString);
            requestDateTime = new java.sql.Timestamp(parsedDate.getTime());
        }

        attElement = qrObj.get("shop_id");
        int wsId = 0;
        if (!attElement.isJsonNull()) {
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

        attElement = qrObj.get("service_status");
        int status = 0;
        if (!attElement.isJsonNull()) {
            status = attElement.getAsInt();
        }

        Vehicle vehicle = new Vehicle(vehicleId, carMake, carModel, carYear, carPlate, customerId, carColor, carControl);
        Customer customer = new Customer(customerId, customerEmail, customerName, customerHpNo);
        qr = new QuotationRequest(id, name, details, description, vehicle, mileage, urgency, amenities, latitude, longitude, address, photos,
                requestDateTime, status, wsId, finalQuotationPrice, offerId, customer);

        return qr;
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
            if (!attElement.isJsonNull()) {
                offerId = attElement.getAsInt();
            }

            attElement = qrObj.get("service_final_price");
            double finalQuotationPrice = 0.0;
            if (!attElement.isJsonNull()) {
                finalQuotationPrice = attElement.getAsDouble();
            }

            attElement = qrObj.get("requested_datetime");
            Timestamp requestDateTime = null;
            String dateTimeString = "1990-01-01 00:00:00";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(dateTimeString);
            requestDateTime = new java.sql.Timestamp(parsedDate.getTime());
            if (!attElement.isJsonNull()) {
                dateTimeString = attElement.getAsString();
                dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                parsedDate = dateFormat.parse(dateTimeString);
                requestDateTime = new java.sql.Timestamp(parsedDate.getTime());
            }

            attElement = qrObj.get("shop_id");
            int wsId = 0;
            if (!attElement.isJsonNull()) {
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

            attElement = qrObj.get("service_status");
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

    public boolean completeService(int staffId, String token, int serviceId) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/quotation_request/complete_service";
 
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("service_id", serviceId + ""));

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

    public static boolean addInitialQuotation(int staffId, String token, int quotationRequestId, int workshopId, double minPrice, double maxPrice, String description) throws UnsupportedEncodingException, IOException {
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
    
    public static boolean addDiagnosticPrice(int staffId, String token, int quotationRequestId, int workshopId, double price, String description) throws UnsupportedEncodingException, IOException {
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

    public static boolean addFinalQuotation(int staffId, String token, int quotationRequestId, double price) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/quotation_request/add_final_quotation";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("final_quotation", price + ""));
        urlParameters.add(new BasicNameValuePair("service_id", quotationRequestId + ""));

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

    public boolean addEstimatedCompletionTime(int staffId, String token, int quotationRequestId, int workshopId, String time) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/quotation_request/update_estimated_completion_time";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("service_id", quotationRequestId + ""));
        urlParameters.add(new BasicNameValuePair("estimated_completion_time", time));
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

    public static void main(String[] args) throws Exception {
        //HashMap<Integer, QuotationRequest> qList = retrieveAllQuotationRequests(3, "57cd00bbfaf886f8fe9fc98e15ee3bb0b31663a18f752de76cd998d71fe983be2b2f2a3de0f41ab8f69f3d19ab29b78c1b957578f5e7679e112de86fd1841da08c48d0a674fa001c3a2fe1e161c76edc6ce950b080c9a876034b3dfeabf826efacc3fde2d5542e8d4ccb90fd0163cb76371b7159546fd7d652d31abd73c235b290d4d62e2c31a8a00704432fae9a60e9f04eb4338698e0b2d5183d8cd4a4671ac1bc1d0ae79792bc21c8a1cd902d841a8fb6e8d0197cbccb3fd6f92456d9a361b940c0ff1affe7149e458fac8d7b0783e095d0dc4c82b86930e9fa378b4ffe1f15ce963d80291a7bc1c69ee0d63fb8b1d613d5feb3820b6c1a86cf7ab95d1340d8974f4acdf8e6ce9b426565d4f14b348c80787b4f24e95f9cd625f2a50e28d5118b364c362ae096623208881a9cc9f2161a422bac6d4d8e968f7b26093837d95b17352f417d0a061138438242a2cf6dd32e736bad691912e3d2701fc17a84a855a3aeb57969ad875a656e80206f10677818967ecd6942ea5463d1e3f188b84bc54c781ca85a3dceb76da8a17d9728fb0eb94b51ecd96787f11abefc7fb0e6f80307402b8d8e00920d8a48b566dbf1ad84b0487bb67863a31d122c86084ae08b891252fad4fe7df17c76cf419f33e2fb760968ac9e112567188c0b163961ef708e1fbb9059de5d7fa1f7f20da40123d5311fd308fcee95f36a3a4991b814ef3b", 0, 0, null, "requested_datetime", "desc");
       System.out.println(addFinalQuotation(6, "01947fca992fe00c370eda74df80c4a871432aedb9f34419a3d3e3b7abb01f2d061f52dcb7f999ac1267e9b8b5dd431b66cec22d718f4208488744f8436cd29e84c5ec710db29c42293c3cad743d7e1b078fb8af5b929bcf7aca36a168113aab6899c53b764eb66b1a2f9db401bf7ad034fbbf4a363889c2e063117959e9a51beeba8e7a8194d1beb03adef6ee3af38ef724f172b34558a5b1d9039f1cdfb52045dfc2a0d03f9b5c5c32504a2aa38bb8e8dbcc72b91ecbc718a007d7df1521650fd600a9a07039fbbd201da015e81f74f927751a90af2ef28ab4871df588e3c58e55fc69ca9c1b6f36f76f29e4a8069eb7ef42b4dca6dc5b2fcd405ab4d1c7fb0fef5d270e23b74dee56ba34c522ca242e539cf884acc2bee028d2f33bbe669a9acdac570b77cfaa33ac7ddf5b8edfc74bb7147130388f11ef5c9d399b399d08c7bacfd6537e356ef7673c27e9034ffa39ec16bddc7e399aa8d1d64774d9e0f0395e70a7be4f3b5f1f75c6e37601414a89f45b34f501110c277a8f58d14503b645f6dc75c17d8591f36d8b7f7188110fc59b2b471b19e4b0ac440f02cf876d0fe45bbc25f9591153250af976e3afc21fa86a0ad7178f379a98db6f06431632e7a1da6e0970733c4c6cc123d53225a011939f775008adb8c864b5252ca761149921b0cd20f7ac610be7c896834df008354b9d5bd523d11f8ab91d493baeba67e1", 480, 10));
       
    }
}
