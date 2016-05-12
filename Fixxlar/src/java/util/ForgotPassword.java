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

public class ForgotPassword {
    
    public String retrieveEmail(String givenToken) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String email = null;
        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            rs = null;
            pstmt = conn.prepareStatement("SELECT email, expireDate FROM `token` WHERE token =\"" + givenToken + "\"");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                email = rs.getString("email");
                Timestamp expireDate = rs.getTimestamp("expireDate");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt, rs);
            return email;
        }
    }
}
