package dao;

import com.google.gson.JsonArray;
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
import java.util.HashMap;
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
    public WebUser retrieveUser(int staffId, String token, int givenId) throws IOException {
        WebUser user = null;
        String url = "http://119.81.43.85/erp/user/get_staff_info";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("id", givenId + ""));

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
        JsonObject userObj = null;
        if (shopElement.isJsonNull()) {
            return user;
        } else {
            userObj = shopElement.getAsJsonObject().getAsJsonObject("staff");
            JsonElement attElement = userObj.get("id");
            int id = 0;
            if (attElement != null && !attElement.isJsonNull()) {
                id = attElement.getAsInt();
            }
            attElement = userObj.get("name");
            String name = "";
            if (attElement != null && !attElement.isJsonNull()) {
                name = attElement.getAsString();
            }
            attElement = userObj.get("email");
            String email = "";
            if (attElement != null && !attElement.isJsonNull()) {
                email = attElement.getAsString();
            }
            attElement = userObj.get("handphone");
            String handphone = "";
            if (attElement != null && !attElement.isJsonNull()) {
                handphone = attElement.getAsString();
            }
            
            attElement = userObj.get("user_type");
            int userType = 0;
            if (attElement != null && !attElement.isJsonNull()) {
                userType = attElement.getAsInt();
            }
            
            int refStaffId = 0;
            attElement = userObj.get("workshop_staff_id");
            if (attElement != null && !attElement.isJsonNull()) {
                refStaffId = attElement.getAsInt();
            }

            attElement = userObj.get("web_admin_id");
            if (attElement != null && !attElement.isJsonNull()) {
                refStaffId = attElement.getAsInt();
            }
            
            int shopId = 0;
            attElement = userObj.get("shop_id");
            if (attElement != null && !attElement.isJsonNull()) {
                shopId = attElement.getAsInt();
            }
            
            int staffType = 0;
            attElement = userObj.get("staff_type");
            if (attElement != null && !attElement.isJsonNull()) {
                staffType = attElement.getAsInt();
            }
      
            user = new WebUser(id, email, userType, refStaffId, "", shopId, name, handphone, staffType);
        }
        return user;
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
            if (attElement != null && !attElement.isJsonNull()) {
                shopId = attElement.getAsInt();
            }

            String handphone = "";
            attElement = user.get("handphone");
            if (attElement != null && !attElement.isJsonNull()) {
                handphone = attElement.getAsString();
            }

            String name = "";
            attElement = user.get("name");
            if (attElement != null && !attElement.isJsonNull()) {
                name = attElement.getAsString();
            }
            
            int staffType = 0;
            attElement = user.get("staff_type");
            if (attElement != null && !attElement.isJsonNull()) {
                staffType = attElement.getAsInt();
            }
            webUser = new WebUser(staffId, email, userType, refStaffId, token, shopId, name, handphone, staffType);
        }
        return webUser;
    }

    // Update user's password with the new pasword hash 
    public boolean updateUserPassword(int staffId, String token, String currentPassword, String newPassword) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/user/change_password";
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
        if (isSuccess.getAsString().equals("false")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addMasterWorkshopStaff(int staffId, String token, String name, String email, String hpNo, int shopId, String password) throws UnsupportedEncodingException, IOException {
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

    public boolean addNormalWorkshopStaff(int staffId, String token, String name, String email, String hpNo, int shopId, String password) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/user/add_new_normal_workshop_staff";

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
        System.out.println(str);
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

    public boolean addMasterAdmin(int staffId, String token, String name, String email, String hpNo, String password) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/user/add_new_master_admin";

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

    public boolean addNormalAdmin(int staffId, String token, String name, String email, String hpNo, String password) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/user/add_new_normal_admin";

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

    public String updateMasterWorkshopStaff(int staffId, String token, String name, String email, String handphone, int id) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/user/update_master_workshop_staff";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("id", id + ""));
        urlParameters.add(new BasicNameValuePair("staff_name", name));
        urlParameters.add(new BasicNameValuePair("email", email));
        urlParameters.add(new BasicNameValuePair("handphone", handphone));

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

    public String updateNormalWorkshopStaff(int staffId, String token, String name, String email, String handphone, int id) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/user/update_normal_workshop_staff";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("id", id + ""));
        urlParameters.add(new BasicNameValuePair("staff_name", name));
        urlParameters.add(new BasicNameValuePair("email", email));
        urlParameters.add(new BasicNameValuePair("handphone", handphone));

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

    public String updateMasterAdmin(int staffId, String token, String name, String email, String handphone, int id) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/user/update_master_admin";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("id", id + ""));
        urlParameters.add(new BasicNameValuePair("staff_name", name));
        urlParameters.add(new BasicNameValuePair("email", email));
        urlParameters.add(new BasicNameValuePair("handphone", handphone));

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

    public String updateNormalAdmin(int staffId, String token, String name, String email, String handphone, int id) throws UnsupportedEncodingException, IOException {
        String url = "http://119.81.43.85/erp/user/update_normal_admin";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("id", id + ""));
        urlParameters.add(new BasicNameValuePair("staff_name", name));
        urlParameters.add(new BasicNameValuePair("email", email));
        urlParameters.add(new BasicNameValuePair("handphone", handphone));

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

    //View employees for workshop master staff
    public HashMap<Integer, WebUser> retrieveNormalWorkshopStaff(int staffId, String token, int wsId) throws UnsupportedEncodingException, IOException {
        HashMap<Integer, WebUser> allStaff = new HashMap<>();
        String url = "http://119.81.43.85/erp/user/get_normal_workshop_staff_info";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("staff_id", staffId + ""));
        urlParameters.add(new BasicNameValuePair("token", token));
        urlParameters.add(new BasicNameValuePair("shop_id", wsId + ""));

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
        JsonArray arr = (jobj.getAsJsonObject("payload")).getAsJsonArray("staff");
        for (int i = 0; i < arr.size(); i++) {
            JsonElement userEle = arr.get(i);
            JsonObject user = userEle.getAsJsonObject();

            JsonElement attElement = user.get("id");
            int indivStaffId = 0;
            if (attElement != null && !attElement.isJsonNull()) {
                indivStaffId = attElement.getAsInt();
            }

            int userType = 0;
            attElement = user.get("user_type");
            if (attElement != null && !attElement.isJsonNull()) {
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

            String indivToken = "";
            attElement = user.get("token");
            if (attElement != null && !attElement.isJsonNull()) {
                indivToken = attElement.getAsString();
            }

            int shopId = 0;
            attElement = user.get("shop_id");
            if (attElement != null && !attElement.isJsonNull()) {
                shopId = attElement.getAsInt();
            }

            String email = "";
            attElement = user.get("email");
            if (attElement != null && !attElement.isJsonNull()) {
                email = attElement.getAsString();
            }

            String handphone = "";
            attElement = user.get("handphone");
            if (attElement != null && !attElement.isJsonNull()) {
                handphone = attElement.getAsString();
            }

            String name = "";
            attElement = user.get("name");
            if (attElement != null && !attElement.isJsonNull()) {
                name = attElement.getAsString();
            }
            
            int staffType = 0;
            attElement = user.get("staff_type");
            if (attElement != null && !attElement.isJsonNull()) {
                staffType = attElement.getAsInt();
            }
            
            System.out.println(name);
            WebUser staff = new WebUser(indivStaffId, email, userType, refStaffId, indivToken, shopId, name, handphone, staffType);
            allStaff.put(i, staff);
        }
        return allStaff;
    }
    
    public static HashMap<Integer, WebUser> retrieveAllStaff(int staffId, String token) throws UnsupportedEncodingException, IOException {
        HashMap<Integer, WebUser> allStaff = new HashMap<>();
        String url = "http://119.81.43.85/erp/user/get_all_staff";
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
        JsonElement ele = element.getAsJsonObject().getAsJsonObject("payload").get("staff");
        if (ele.isJsonNull()) {
            return allStaff;
        }
        
        JsonArray arr = ele.getAsJsonArray();
        for (int i = 0; i < arr.size(); i++) {
            JsonElement userEle = arr.get(i);
            JsonObject user = userEle.getAsJsonObject();

            JsonElement attElement = user.get("id");
            int indivStaffId = 0;
            if (attElement != null && !attElement.isJsonNull()) {
                indivStaffId = attElement.getAsInt();
            }

            int userType = 0;
            attElement = user.get("user_type");
            if (attElement != null && !attElement.isJsonNull()) {
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

            String indivToken = "";
            attElement = user.get("token");
            if (attElement != null && !attElement.isJsonNull()) {
                indivToken = attElement.getAsString();
            }

            int shopId = 0;
            attElement = user.get("shop_id");
            if (attElement != null && !attElement.isJsonNull()) {
                shopId = attElement.getAsInt();
            }

            String email = "";
            attElement = user.get("email");
            if (attElement != null && !attElement.isJsonNull()) {
                email = attElement.getAsString();
            }

            String handphone = "";
            attElement = user.get("handphone");
            if (attElement != null && !attElement.isJsonNull()) {
                handphone = attElement.getAsString();
            }

            String name = "";
            attElement = user.get("name");
            if (attElement != null && !attElement.isJsonNull()) {
                name = attElement.getAsString();
            }
    
            int staffType = 0;
            attElement = user.get("staff_type");
            if (attElement != null && !attElement.isJsonNull()) {
                staffType = attElement.getAsInt();
            }
            System.out.println(name);
            WebUser staff = new WebUser(indivStaffId, email, userType, refStaffId, indivToken, shopId, name, handphone, staffType);
            allStaff.put(i, staff);
        }
        return allStaff;
    }
    
    public static void main (String[] args) throws IOException {
        //retrieveNormalWorkshopStaff(31,"24a76c100537f25a2c92788297c7d836e9dc09712b20d36c5e7db5c36d35b26178ed27855177f7aa965daaf02bf178a2333a5fbde5707e9a1df80e899ea88f7b648ec3c645970583d2a7614232d75cd1211542eccf9f3ad0aeea39514e6a694392cb377ba32bab68d43b23f19f25db128fdab9b1d3e49a69c55e6e6104aca06de73540466cc37d48e001c56819f38fa974fe199f1a1a2d66460d9977a9508c96035c3302796e0e213aaaa0a9adae6a6b4a6cf5da71f51bda275358ae930c94a3587b57acecc88b555b94cc12434055e3a417e6385a09f00983e0529f0f7089d56286cb0aa33d81b30b58df85f367a4ccc2d9521478f609d054aebdaf2359545b7013de846ff37ff98ee1e55f14bcb089d02ce4b13f8686d8c0cefbfccb0c63340b4689e086891bb0f33747ad35810b52cddefb3b73b8de82d7db1cce0bbcb251e1fc1f12ba761a43602569cfedd5faf7d53994cb278201a7c37a9ea37dfdf3c3664ebe57320cb307ce662b2a037682dd2c6d8f5ff3591b29443ea669fb54001155ad21964573413d448a0da93ae410aeac029aca93fca1b877fc84a0e51732e564ea25d194ef5a2eb9723781780daa3e0b1d8e91616496650d87ff6877f7e031b1b6bb69c32733137bbc0841c5080efff7d47f7fcd7b0c6b2aa2c3aeff2ed7ff0baa38c954243f51e8a2d17f84530174ca74e8d78c1fb17e926e038e5ba0a5d8",1);
        retrieveAllStaff(29, "b2397f111518e143d1875ee5f8f5a4fd7d47383aeecddf3b3c43ff28d9bf54ac0ba628c2abcffe4b25226f1e5c9f68852c63ae941579665da5e6bcbeabb593f123210000747c7b22e959a940d29f1ee0a3623a7f17259223fe67d8a281400edb305090339efa8cdb611bb4b6aea366e1ff6a55127a3a468278841db2b104c7dbca6aebe9a2e5dcab697eeeb0259e8b298609c3300d3c96af0e3bc72b68e9f4e1d7ffcfcbe87a0c3b37ebc6fe0b48609bdee4e052e8839ea4a265b91ffafda9d160b88234f2608bab79c39b6b09cf498db2dda5c579fb2b853333d1d77d0a9ebd9b32a67ac143b2660cf42a4630223e88fd141aaaf218664164d5a6e6d9b234f0c5979c394c3e3b2e3795928b9a86243852fd1777909246374c7696aa3233ef53f526f85b02ff9bf7b6fc0ddd30508b05b775ffa576be425924dcc048211a9fa247b0b18eac39096bc10c68cb08b6d47fd6e71fa2e1534a94c35f833d8b6c55002494332f38dfebd4d4d10f1011793f64c76a7b46d2e3ddf8a1bc1593e6ec1e95cf0a175a434a6a69d25fe8abd7b682198b438d38e62f5b7a67c2f1fafb77d83a8b7c3ee2711821b62de37f68efff029c42a72ebdbf016cea8ad020a5cfa95e638791f4286a6f117f03c1d1e72a760d5de37b581c73063c9f2b7d0bb99efde8e29cc7886dacac160fb1252ae48da48d54c29eef3c8a75992b8f495d1219af452a");
    }
}
