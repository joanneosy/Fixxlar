/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ForgotPassword {
    
    //if hashCode is expired or does not exist in database, return null
    //else return email
    public String verifyHashCode(String givenHashCode) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String email = null;
        Timestamp expireDate = null;
        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            rs = null;
            pstmt = conn.prepareStatement("SELECT email, expireDate FROM `pwHashCode` WHERE hashCode =\"" + givenHashCode + "\"");
            System.out.println("pstmt: " + pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                email = rs.getString("email");
                expireDate = rs.getTimestamp("expireDate");
            }
            
            if (email != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, 5);
                String newTime = dateFormat.format(cal.getTime());
                Timestamp now = Timestamp.valueOf(newTime);
                if (now.after(expireDate)) {
                    return null;
                } else {
                    return email;
                }
            } else {
                return null;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt, rs);
            return email;
        }
    }
    
    public void storeHashCode(String givenEmail, String hashCode) throws SQLException {
        
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement pStatement = null;
        pStatement = connection.prepareStatement("INSERT INTO pwHashCode VALUES (?,?,?)");

        try {
            pStatement.setString(1, hashCode);
            pStatement.setString(2, givenEmail);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, 24);
            String newTime = dateFormat.format(cal.getTime());
            Timestamp ts = Timestamp.valueOf(newTime);
            pStatement.setTimestamp(3, ts);
            pStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(connection, pStatement);
        }
    }
    

}