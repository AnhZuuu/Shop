/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.Shopping;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class OrderDAO {
    private static final String SEARCH_ALL = "SELECT orderID,userID,date,total,status FROM tblOrders";
    private static final String SEARCH_ORDER_BY_ID = "SELECT orderID,userID,date,total,status FROM tblOrders WHERE orderID = ?";
    private static final String SEARCH_ALL_BY_USER_ID = "SELECT orderID,userID,date,total,status FROM tblOrders WHERE userID = ?";
    private static final String UPDATE_STATUS = "UPDATE tblOrders "
            + "SET status=? "
            + "WHERE orderID=?";
    
    public List<Order> getAllOrders() throws SQLException{
        List<Order> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_ALL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String userID = rs.getString("userID");
                    Date date = rs.getDate("date");
                    double total = rs.getDouble("total");
                    int status = rs.getInt("status");
                    orderList.add(new Order(orderID, userID, date, total, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return orderList;
    }
    public List<Order> getOrdersByUserId(String userID) throws SQLException{
        List<Order> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_ALL_BY_USER_ID);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    Date date = rs.getDate("date");
                    double total = rs.getDouble("total");
                    int status = rs.getInt("status");
                    orderList.add(new Order(orderID, userID, date, total, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return orderList;
    }
    
    public Order getOrderById(int orderID) throws SQLException{
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_ORDER_BY_ID);
                ptm.setInt(1, orderID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    Date date = rs.getDate("date");
                    double total = rs.getDouble("total");
                    int status = rs.getInt("status");
                    return (new Order(orderID, userID, date, total, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }
    public boolean updateStatus(int status, int orderID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STATUS);
                ptm.setInt(1, status);
                ptm.setInt(2, orderID);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
