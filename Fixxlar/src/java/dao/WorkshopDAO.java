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
import entity.CarBrand;
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

    private static final String USER_AGENT = "Mozilla/5.0";

    public Workshop retrieveWorkshop(String givenEmail, int staffId, String token) throws UnsupportedEncodingException, IOException {
        Workshop ws = null;
        String url = "http://119.81.43.85/erp/workshop/get_shop_by_id_or_email";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("email", givenEmail));

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

        JsonElement shopElement = jobj.get("payload");
        JsonObject shop = null;
        if (shopElement.isJsonNull()) {
            return ws;
        } else {
            shop = shopElement.getAsJsonObject().getAsJsonObject("shop");
            JsonElement attElement = shop.get("id");
            int id = 0;
            if (!attElement.isJsonNull()) {
                id = attElement.getAsInt();
            }
            attElement = shop.get("name");
            String name = "";
            if (!attElement.isJsonNull()) {
                name = attElement.getAsString();
            }
            attElement = shop.get("email");
            String email = "";
            if (!attElement.isJsonNull()) {
                email = attElement.getAsString();
            }
            attElement = shop.get("description");
            String description = "";
            if (!attElement.isJsonNull()) {
                description = attElement.getAsString();
            }
            attElement = shop.get("website");
            String website = "";
            if (!attElement.isJsonNull()) {
                website = attElement.getAsString();
            }
            attElement = shop.get("address");
            String address = "";
            if (!attElement.isJsonNull()) {
                address = attElement.getAsString();
            }
            attElement = shop.get("opening_hour_full");
            String openingHour = "";
            if (!attElement.isJsonNull()) {
                openingHour = attElement.getAsString();
            }
            attElement = shop.get("opening_hour_format");
            String openingHourFormat = "";
            if (!attElement.isJsonNull()) {
                openingHourFormat = attElement.getAsString();
            }
            attElement = shop.get("latitude");
            double latitude = 0.0;
            if (!attElement.isJsonNull()) {
                latitude = attElement.getAsDouble();
            }
            attElement = shop.get("longitude");
            double longitude = 0.0;
            if (!attElement.isJsonNull()) {
                longitude = attElement.getAsDouble();
            }
            attElement = shop.get("contact");
            String contact = "";
            if (!attElement.isJsonNull()) {
                contact = attElement.getAsString();
            }
            attElement = shop.get("contact2");
            String contact2 = "";
            if (!attElement.isJsonNull()) {
                contact2 = attElement.getAsString();
            }
            attElement = shop.get("location");
            String location = "";
            if (!attElement.isJsonNull()) {
                location = attElement.getAsString();
            }
            attElement = shop.get("specialize");
            String specialize = "";
            if (!attElement.isJsonNull()) {
                specialize = attElement.getAsString();
            }
            attElement = shop.get("category");
            String category = "";
            if (!attElement.isJsonNull()) {
                category = attElement.getAsString();
            }
            attElement = shop.get("brand_carried");
            String brandsCarried = "";
            if (!attElement.isJsonNull()) {
                brandsCarried = attElement.getAsString();
            }
            attElement = shop.get("remark");
            String remark = "";
            if (!attElement.isJsonNull()) {
                remark = attElement.getAsString();
            }
            attElement = shop.get("status");
            int status = 0;
            if (!attElement.isJsonNull()) {
                status = attElement.getAsInt();
            }
            ws = new Workshop(id, email, name, description, website, address, openingHour, openingHourFormat,
                    latitude, longitude, contact, contact2, location, specialize, category, brandsCarried, remark, status);
        }
        return ws;
    }

    public Workshop retrieveWorkshop(int givenID, int staffId, String token) throws IOException {
        Workshop ws = null;
        String url = "http://119.81.43.85/erp/workshop/get_shop_by_id_or_email";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("id", givenID + ""));

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

        JsonElement shopElement = jobj.get("payload");
        JsonObject shop = null;
        if (shopElement.isJsonNull()) {
            return ws;
        } else {
            shop = shopElement.getAsJsonObject().getAsJsonObject("shop");
            JsonElement attElement = shop.get("id");
            int id = 0;
            if (!attElement.isJsonNull()) {
                id = attElement.getAsInt();
            }
            attElement = shop.get("name");
            String name = "";
            if (!attElement.isJsonNull()) {
                name = attElement.getAsString();
            }
            attElement = shop.get("email");
            String email = "";
            if (!attElement.isJsonNull()) {
                email = attElement.getAsString();
            }
            attElement = shop.get("description");
            String description = "";
            if (!attElement.isJsonNull()) {
                description = attElement.getAsString();
            }
            attElement = shop.get("website");
            String website = "";
            if (!attElement.isJsonNull()) {
                website = attElement.getAsString();
            }
            attElement = shop.get("address");
            String address = "";
            if (!attElement.isJsonNull()) {
                address = attElement.getAsString();
            }
            attElement = shop.get("opening_hour_full");
            String openingHour = "";
            if (!attElement.isJsonNull()) {
                openingHour = attElement.getAsString();
            }
            attElement = shop.get("opening_hour_format");
            String openingHourFormat = "";
            if (!attElement.isJsonNull()) {
                openingHourFormat = attElement.getAsString();
            }
            attElement = shop.get("latitude");
            double latitude = 0.0;
            if (!attElement.isJsonNull()) {
                latitude = attElement.getAsDouble();
            }
            attElement = shop.get("longitude");
            double longitude = 0.0;
            if (!attElement.isJsonNull()) {
                longitude = attElement.getAsDouble();
            }
            attElement = shop.get("contact");
            String contact = "";
            if (!attElement.isJsonNull()) {
                contact = attElement.getAsString();
            }
            attElement = shop.get("contact2");
            String contact2 = "";
            if (!attElement.isJsonNull()) {
                contact2 = attElement.getAsString();
            }
            attElement = shop.get("location");
            String location = "";
            if (!attElement.isJsonNull()) {
                location = attElement.getAsString();
            }
            attElement = shop.get("specialize");
            String specialize = "";
            if (!attElement.isJsonNull()) {
                specialize = attElement.getAsString();
            }
            attElement = shop.get("category");
            String category = "";
            if (!attElement.isJsonNull()) {
                category = attElement.getAsString();
            }
            attElement = shop.get("brand_carried");
            String brandsCarried = "";
            if (!attElement.isJsonNull()) {
                brandsCarried = attElement.getAsString();
            }
            attElement = shop.get("remark");
            String remark = "";
            if (!attElement.isJsonNull()) {
                remark = attElement.getAsString();
            }
            attElement = shop.get("status");
            int status = 0;
            if (!attElement.isJsonNull()) {
                status = attElement.getAsInt();
            }
            ws = new Workshop(id, email, name, description, website, address, openingHour, openingHourFormat,
                    latitude, longitude, contact, contact2, location, specialize, category, brandsCarried, remark, status);
        }
        return ws;
    }

    public ArrayList<String> addWorkshop(String email, String name, String description, String website, String address, String openingHour, String openingHourFormat, double latitude,
            double longitude, String contact, String contact2, String location, String specialize, String category, String brandsCarried,
            String remark, int staffId, String token) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/workshop/add_shop";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("name", name));
        urlParameters.add(new BasicNameValuePair("description", description));
        urlParameters.add(new BasicNameValuePair("website", website));
        urlParameters.add(new BasicNameValuePair("address", address));
        urlParameters.add(new BasicNameValuePair("opening_hour_full", openingHour));
        urlParameters.add(new BasicNameValuePair("opening_hour_format", openingHourFormat));
        urlParameters.add(new BasicNameValuePair("latitude", latitude + ""));
        urlParameters.add(new BasicNameValuePair("longitude", longitude + ""));
        urlParameters.add(new BasicNameValuePair("contact", contact));
        urlParameters.add(new BasicNameValuePair("contact2", contact2));
        urlParameters.add(new BasicNameValuePair("location", location));
        urlParameters.add(new BasicNameValuePair("specialize", specialize));
        urlParameters.add(new BasicNameValuePair("category", category));
        urlParameters.add(new BasicNameValuePair("brand_carried", brandsCarried));
        urlParameters.add(new BasicNameValuePair("remark", remark));
        urlParameters.add(new BasicNameValuePair("email", email));
        urlParameters.add(new BasicNameValuePair("status", 1 + ""));

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
        String errorMessage = null;
        ArrayList<String> errors = new ArrayList<String>();
        JsonElement isSuccess = jobj.get("is_success");
        if (isSuccess.getAsString().equals("false")) {
            errorMessage = jobj.get("error_message").getAsString();
            errors.add(errorMessage);
            JsonElement fields = jobj.get("payload");
            if (!fields.isJsonNull()) {
                JsonArray arr = fields.getAsJsonObject().get("error_field").getAsJsonArray();
            for (int i = 0; i < arr.size(); i++) {
                String f = arr.get(i).getAsString();
                errors.add(f);
            }
            }
        }

        return errors;

    }

    public ArrayList<Workshop> retrieveAllWorkshops(int staffId, String token) throws UnsupportedEncodingException, IOException {
        ArrayList<Workshop> allWorkshops = new ArrayList<Workshop>();
        String url = "http://119.81.43.85/erp/workshop/get_all_shop";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));

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
            JsonObject shop = workshop.getAsJsonObject();
            JsonElement attElement = shop.get("id");
            int id = 0;
            if (!attElement.isJsonNull()) {
                id = attElement.getAsInt();
            }
            attElement = shop.get("name");
            String name = "";
            if (!attElement.isJsonNull()) {
                name = attElement.getAsString();
            }
            attElement = shop.get("email");
            String email = "";
            if (!attElement.isJsonNull()) {
                email = attElement.getAsString();
            }
            attElement = shop.get("description");
            String description = "";
            if (!attElement.isJsonNull()) {
                description = attElement.getAsString();
            }
            attElement = shop.get("website");
            String website = "";
            if (!attElement.isJsonNull()) {
                website = attElement.getAsString();
            }
            attElement = shop.get("address");
            String address = "";
            if (!attElement.isJsonNull()) {
                address = attElement.getAsString();
            }
            attElement = shop.get("opening_hour_full");
            String openingHour = "";
            if (!attElement.isJsonNull()) {
                openingHour = attElement.getAsString();
            }
            attElement = shop.get("opening_hour_format");
            String openingHourFormat = "";
            if (!attElement.isJsonNull()) {
                openingHourFormat = attElement.getAsString();
            }
            attElement = shop.get("latitude");
            double latitude = 0.0;
            if (!attElement.isJsonNull()) {
                latitude = attElement.getAsDouble();
            }
            attElement = shop.get("longitude");
            double longitude = 0.0;
            if (!attElement.isJsonNull()) {
                longitude = attElement.getAsDouble();
            }
            attElement = shop.get("contact");
            String contact = "";
            if (!attElement.isJsonNull()) {
                contact = attElement.getAsString();
            }
            attElement = shop.get("contact2");
            String contact2 = "";
            if (!attElement.isJsonNull()) {
                contact2 = attElement.getAsString();
            }
            attElement = shop.get("location");
            String location = "";
            if (!attElement.isJsonNull()) {
                location = attElement.getAsString();
            }
            attElement = shop.get("specialize");
            String specialize = "";
            if (!attElement.isJsonNull()) {
                specialize = attElement.getAsString();
            }
            attElement = shop.get("category");
            String category = "";
            if (!attElement.isJsonNull()) {
                category = attElement.getAsString();
            }
            attElement = shop.get("brand_carried");
            String brandsCarried = "";
            if (!attElement.isJsonNull()) {
                brandsCarried = attElement.getAsString();
            }
            attElement = shop.get("remark");
            String remark = "";
            if (!attElement.isJsonNull()) {
                remark = attElement.getAsString();
            }
            attElement = shop.get("status");
            int status = 0;
            if (!attElement.isJsonNull()) {
                status = attElement.getAsInt();
            }
            //int status = attElement.getAsInt();
            Workshop ws = new Workshop(id, email, name, description, website, address, openingHour, openingHourFormat,
                    latitude, longitude, contact, contact2, location, specialize, category, brandsCarried, remark, status);

            allWorkshops.add(ws);
        }
        return allWorkshops;
    }

    public ArrayList<String> updateWorkshop(int id, String email, String name, String description, String website, String address, String openingHour, String openingHourFormat, double latitude,
            double longitude, String contact, String contact2, String location, String specialize, String category, String brandsCarried,
            String remark, int status, int staffId, String token) throws UnsupportedEncodingException, IOException {

        String url = "http://119.81.43.85/erp/workshop/edit_shop";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("id", id + ""));
        urlParameters.add(new BasicNameValuePair("name", name));
        urlParameters.add(new BasicNameValuePair("description", description));
        urlParameters.add(new BasicNameValuePair("website", website));
        urlParameters.add(new BasicNameValuePair("address", address));
        urlParameters.add(new BasicNameValuePair("opening_hour_full", openingHour));
        urlParameters.add(new BasicNameValuePair("opening_hour_format", openingHourFormat));
        urlParameters.add(new BasicNameValuePair("latitude", latitude + ""));
        urlParameters.add(new BasicNameValuePair("longitude", longitude + ""));
        urlParameters.add(new BasicNameValuePair("contact", contact));
        urlParameters.add(new BasicNameValuePair("contact2", contact2));
        urlParameters.add(new BasicNameValuePair("location", location));
        urlParameters.add(new BasicNameValuePair("specialize", specialize));
        urlParameters.add(new BasicNameValuePair("category", category));
        urlParameters.add(new BasicNameValuePair("brand_carried", brandsCarried));
        urlParameters.add(new BasicNameValuePair("remark", remark));
        urlParameters.add(new BasicNameValuePair("email", email));
        urlParameters.add(new BasicNameValuePair("status", 1 + ""));

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
        String errorMessage = null;
        ArrayList<String> errors = new ArrayList<String>();
        JsonElement isSuccess = jobj.get("is_success");
        if (isSuccess.getAsString().equals("false")) {
            errorMessage = jobj.get("error_message").getAsString();
            errors.add(errorMessage);
            JsonElement fields = jobj.get("payload");
            JsonArray arr;
            if (!fields.isJsonNull()) {
                arr = fields.getAsJsonObject().get("error_field").getAsJsonArray();

                for (int i = 0; i < arr.size(); i++) {
                    String f = arr.get(i).getAsString();
                    errors.add(f);
                }
            }
        }

        return errors;
    }

    public ArrayList<String> retrieveAllCarBrands(int staffId, String token) throws UnsupportedEncodingException, IOException {
        ArrayList<String> carBrands = new ArrayList<String>();
        String url = "http://119.81.43.85/erp/avalible_car/get_all_avalible_car";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));

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

        JsonElement brandsElement = jobj.get("payload");
        JsonObject brands = null;
        if (brandsElement.isJsonNull()) {
            return carBrands;
        } else {
            brands = brandsElement.getAsJsonObject();
            JsonArray brandsArr = brands.getAsJsonArray("available_cars");
            for (int i = 0; i < brandsArr.size(); i++) {
                JsonElement brandArr = brandsArr.get(i);
                JsonObject brandObj = brandArr.getAsJsonObject();
                JsonElement attElement = brandObj.get("car_brand");
                String brand = "";
                if (!attElement.isJsonNull()) {
                    brand = attElement.getAsString();
                    if (!carBrands.contains(brand)) {
                        carBrands.add(brand);
                    }
                }

            }
        }
        return carBrands;
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

    public static String deleteWorkshop(int staffId, String token, int id) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/workshop/delete_shop";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("id", id + ""));

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
        JsonElement errMsgEle = jobj.get("error_message");
        String errMsg = "";
        if (errMsgEle != null && !errMsgEle.isJsonNull()) {
            errMsg = errMsgEle.getAsString();
        }
        return errMsg;
    }
    /*public static void main(String[] args) throws IOException {
        System.out.println(deleteWorkshop(1, "be2d10f856cece23d68126cebe371907dc66d1eee8bb8f026a49f218d1af08796e54529b8dfe2a6d7494c7254b8da1f89c53e905a17e005225608765e46de12ef4d7eb2f6dfad4d0fdf26d3ecd67daa59ffa1c157df79106de8368e0366725d0815dd446a15b7427779d6b5a8f6eab7cbeeeddb43880804ed6f6d38362f2b2b0f12efa2d9c5b7477edb8de280116bd9aff4477c551bcc20acbec85e8d171e41c6a7270c993db1b568eda33b00ea32c443003cd600336b3d6d57e0e649fe9ed5720fe38635aa9cb82ea890c044f6d8060711515a9a33cb79e97e4716d7d3a7a6bf8d9df59b5ac05162dd719f682501a4b68d7db08a3e0398a07ce6cd60f5179b1d1d5defc2881c80e824338bd3ec56ebbdbd8e2734e1975414c8ff77d37516fcbe424078740c408c29e90634e3174c1fd886463cb1f053f8e686ce5e9be65018f732253ea284fd6b436e1eb8af78cc75194570aede63c0e712f5b3ed9846e09a2b9e5eb7b4cb9dd5d9bb85c9952795e01c449a7b0bb5f2c790e2dea668331a46aa1e920101224e948a46d7276540c856dc7616e5809c01efb3e152d5d882a3610749ac27333a091f3769bc3a3dcba064eefa53bab2a755490e77697538f623fe7d6aabe644dffc4318d7729858cc89f02ade151fb70a2b41b34390e8a4754512161d904a7e53eb08894c9be0ef1106212912ff1edf69fe7fbae05dcc51ccc0614", 5));
    }*/
}
