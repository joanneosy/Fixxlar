/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.QuotationRequest;
import entity.Workshop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import util.ConnectionManager;

/**
 *
 * @author Joanne
 */
public class QuotationRequestDAO {

    public QuotationRequest retrieveQuotationRequest(int givenID) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        QuotationRequest qr = null;
        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            rs = null;
            pstmt = conn.prepareStatement("select s.id, name, details, description, vehicle_id, mileage, urgency, amenities,"
                    + " latitude, longitude, address, photos, make, model, year, plate_number, s.created, status, workshop_id "
                    + "from vehicles as v, services as s where v.id = s.vehicle_id and s.id =" + givenID + "");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int requestID = rs.getInt("id");
                String details = rs.getString("details");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int vehicleID = rs.getInt("vehicle_id");
                String mileage = rs.getString("mileage");
                String urgency = rs.getString("urgency");
                String amenities = rs.getString("amenities");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                String address = rs.getString("address");
                String photos = rs.getString("photos");
                int status = rs.getInt("status");
                int workshopID = rs.getInt("workshop_id");
                Timestamp date = rs.getTimestamp("created");
                String carMake = rs.getString("make");
                String carModel = rs.getString("model");
                int carYear = rs.getInt("year");
                String plateNumber = rs.getString("plate_number");

                qr = new QuotationRequest(requestID, name, details, description, vehicleID, mileage, urgency,
                        amenities, latitude, longitude, address, photos, date, status, workshopID);
            }

            return qr;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt, rs);
            return qr;
        }
    }
    //Assuming get new requests first
    //Default: 1-4 status
    //Workshop: won't show cancelled request at all
    //Will only show cancelled request to Fixir admin
    public ArrayList<QuotationRequest> retrieveAllQuotationRequests(int workshopID, int givenStatus, String orderBy, String order, String givenCarModel, String serviceType) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        QuotationRequest qr = null;
        ArrayList<QuotationRequest> qrs = new ArrayList<QuotationRequest>();
        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            rs = null;
            String stmt = "select s.id, name, details, description, vehicle_id, mileage, urgency, amenities,"
                    + " latitude, longitude, address, photos, make, model, year, plate_number, s.created, status, workshop_id "
                    + "from vehicles as v, services as s where v.id = s.vehicle_id";
            
            if (givenStatus != 0 || !givenCarModel.equals("")) {
                stmt += "where ";
                if (givenStatus != 0) {
                    stmt += "status = " + givenStatus;
                    if (!givenCarModel.equals("")) {
                        stmt += "and model = " + givenCarModel;
                    }
                } else if (!givenCarModel.equals("")) {
                    stmt += "model = " + givenCarModel;
                }
            }
            pstmt = conn.prepareStatement(stmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int requestID = rs.getInt("id");
                String details = rs.getString("details");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int vehicleID = rs.getInt("vehicle_id");
                String mileage = rs.getString("mileage");
                String urgency = rs.getString("urgency");
                String amenities = rs.getString("amenities");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                String address = rs.getString("address");
                String photos = rs.getString("photos");
                int status = rs.getInt("status");
                Timestamp date = rs.getTimestamp("created");
                String carMake = rs.getString("make");
                String carModel = rs.getString("model");
                int carYear = rs.getInt("year");
                String plateNumber = rs.getString("plate_number");

                qr = new QuotationRequest(requestID, name, details, description, vehicleID, mileage, urgency,
                        amenities, latitude, longitude, address, photos, date, status, 0 /*workshopID*/);
                qrs.add(qr);
            }

            return qrs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt, rs);
            return qrs;
        }
    }
    
    public void updateStatus (int requestID, int givenStatus) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            pstmt = conn.prepareStatement("UPDATE SERVICES SET STATUS = " + givenStatus + " WHERE ID = " + requestID + ";");
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt);
        }
    }
    
    public void addOffer (int requestID, int workshopID, double price, String description) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            pstmt = null;
            pstmt = conn.prepareStatement("INSERT INTO OFFERS VALUES (NULL, " + requestID + ", " + workshopID + ", '$" + price 
                    + "', '" + description + "', NOW());");
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pstmt);
        }
    }
}
