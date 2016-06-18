package dao;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ConnectionManager;

public class UserDAO {

    /**
     * Retrieve user
     *
     * @param givenEmail email of the user
     * @return a user
     * @throws SQLException if an SQL error occurs
     */
    public User retrieveUser(String givenEmail) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            rs = null;
            pstmt = conn.prepareStatement("SELECT * FROM `webapp_users` WHERE email =\"" + givenEmail + "\"");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String userType = rs.getString("userType");
                user = new User(id, email, password, userType);
            }
            //Return null if email does not exist in database
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt, rs);
            return user;
        }
    }

    // Update user's password with the new pasword hash 
    public void updateUserPassword(String email, String passwordHash) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            pstmt = conn.prepareStatement("UPDATE webapp_users SET password = '" + passwordHash + "' WHERE email = '" + email + "'");
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt);
        }
    }

    public void addUser(int id, String email, String password, String userType) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            pstmt = conn.prepareStatement("INSERT INTO webapp_users VALUES (" + id + ",'" + email + "','" + password + "','" + userType + "');");
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt);
        }
    }

}
