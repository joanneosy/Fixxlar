/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import entity.Workshop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TreeMap;
import util.ConnectionManager;

/**
 *
 * @author Fixxlar
 */
public class WorkshopDAO {
    
    public Workshop retrieveWorkshop(int givenId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Workshop workshop = null;
        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            rs = null;
            pstmt = conn.prepareStatement("SELECT * FROM `Workshop` WHERE UserID =" + givenId + "");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                workshop = new Workshop(id, name, email, address);
            }
            //Return null if email does not exist in database
            return workshop;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt, rs);
            return workshop;
        }
    }
    
    public void addWorkshop(int userID, String email, String name, String address, String[] carBrandsID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            pstmt = conn.prepareStatement("INSERT INTO Workshop VALUES (" + userID + ",'" + email + "','" + name + "','" + address + "');");
            pstmt.executeUpdate();
            
            for (int i = 0; i<carBrandsID.length; i++) {
                pstmt = conn.prepareStatement("INSERT INTO WorkshopBrandsProficiency VALUES (" + userID + ",'" + carBrandsID[i] + "');");
                pstmt.executeUpdate();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt);
        }
    }
    
    public TreeMap<Integer, String> retrieveAllCarBrands() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TreeMap<Integer, String> carBrands = new TreeMap<Integer, String>();
        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            rs = null;
            pstmt = conn.prepareStatement("SELECT * FROM CarBrands");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int brandID = rs.getInt("BrandID");
                String brandName = rs.getString("BrandName");
                carBrands.put(brandID, brandName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt, rs);
            return carBrands;
        }
    }
}
