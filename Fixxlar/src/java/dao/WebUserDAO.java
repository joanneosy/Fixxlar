package dao;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.WebUser;
import entity.Workshop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class WebUserDAO {

    private static final String USER_AGENT = "Mozilla/5.0";

    /**
     * Retrieve user
     *
     * @param givenEmail email of the user
     * @return a user
     * @throws SQLException if an SQL error occurs
     */
    public WebUser retrieveUser(String givenEmail) {
        return null;
    }

    public WebUser authenticateUser(String email, String password) throws UnsupportedEncodingException, IOException {
        WebUser webUser = null;
        String url = "http://119.81.43.85/erp/user/login_web_app_user";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("email", email));
        urlParameters.add(new BasicNameValuePair("password", password));

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
            return webUser;
        } else {
            JsonElement userElement = jobj.get("payload");
            JsonObject user = userElement.getAsJsonObject();
            JsonElement attElement = user.get("staff_id");
            int staffId = 0;
            if (!attElement.isJsonNull()) {
                staffId = attElement.getAsInt();
            }
            
            int userType = 0;
            attElement = user.get("user_type");
            if (!attElement.isJsonNull()) {
                userType = attElement.getAsInt();
            }
            
            int refStaffId = 0;
            attElement = user.get("workshop_staff_id");
            if (attElement != null && !attElement.isJsonNull()) {
                refStaffId = attElement.getAsInt();
            }
            
            attElement = user.get("web_admin_id");
            if (attElement != null && !attElement.isJsonNull()) {
                refStaffId = attElement.getAsInt();
            }
            
            String token = "";
            attElement = user.get("token");
            if (!attElement.isJsonNull()) {
                token = attElement.getAsString();
            }
            
            int shopId = 0;
            attElement = user.get("shop_id");
            if (attElement != null || !attElement.isJsonNull()) {
                shopId = attElement.getAsInt();
            }
            webUser = new WebUser(staffId, email, userType, refStaffId, token, shopId);
        }
        return webUser;
    }

    // Update user's password with the new pasword hash 
    public boolean updateUserPassword(int staffId, String token, String currentPassword, String newPassword) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/user/change_password";
        System.out.println(staffId + " " + token + " " + currentPassword + " " + newPassword);
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("currentPassword", currentPassword));
        urlParameters.add(new BasicNameValuePair("nPassword", newPassword));

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
        System.out.println(str);
        if (isSuccess.getAsString().equals("false")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addMasterUser(int staffId, String token, String name, String email, String hpNo, int shopId, String password) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/user/add_new_master_workshop_staff";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("staff_name", name));
        urlParameters.add(new BasicNameValuePair("email", email));
        urlParameters.add(new BasicNameValuePair("handphone", hpNo));
        urlParameters.add(new BasicNameValuePair("shop_id", shopId + ""));
        urlParameters.add(new BasicNameValuePair("password", password));


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
    
    public boolean addNormalUser(int staffId, String token, String name, String email, String hpNo, int shopId, String password) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/user/add_new_master_workshop_staff";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("staff_name", name));
        urlParameters.add(new BasicNameValuePair("email", email));
        urlParameters.add(new BasicNameValuePair("handphone", hpNo));
        urlParameters.add(new BasicNameValuePair("shop_id", shopId + ""));
        urlParameters.add(new BasicNameValuePair("password", password));


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
}
