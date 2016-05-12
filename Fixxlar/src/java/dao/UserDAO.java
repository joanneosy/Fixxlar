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
            pstmt = conn.prepareStatement("SELECT * FROM `user` WHERE email =\"" + givenEmail + "\"");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String userType = rs.getString("userType");
                user = new User(email, name, password, userType);
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
}
