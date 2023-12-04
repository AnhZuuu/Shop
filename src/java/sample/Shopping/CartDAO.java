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
import java.util.Map;
import javax.naming.NamingException;
import sample.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class CartDAO {

    private static final String ADD_NEW_ORDER = "INSERT INTO tblOrders (userID, date, total, status) "
            + "VALUES (?, ?, ?, ?)";
    private static final String GET_LAST_ORDER_ID = "SELECT MAX(orderID) AS orderID FROM tblOrders";
    private static final String ADD_ORDER_DETAIL = "INSERT INTO tblOrderDetails (orderID , productID, price, quantity) "
            + "VALUES (?, ?, ?, ?)";
    private static final String GET_PRODUCT_QUANTITY = "SELECT quantityInStock FROM tblProducts WHERE productID=?";
    private static final String UPDATE_PRODUCT = "UPDATE tblProducts "
            + "SET quantityInStock=? "
            + "WHERE productID=?";

    public int getQuantityInStock(String productID) throws SQLException{
        int quantityInStock = -1; //-1: error or invalid
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_QUANTITY);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                  quantityInStock = rs.getInt("quantityInStock");
                }
            }
        } catch (Exception e) {
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
        return quantityInStock;
    }
    
    public boolean checkAvailableQuantity(Product laptop) throws SQLException{
        int quantityInStock = getQuantityInStock(laptop.getId());
        if (quantityInStock < laptop.getQuantity()){
            return false;
        } else {return true;}
    }
    public boolean addOrderDetail(String userID, double total, Map<String, Product> cart) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                ptm = conn.prepareStatement(ADD_NEW_ORDER);
                Date date = new Date(System.currentTimeMillis());
                ptm.setString(1, userID);
                ptm.setDate(2, date);
                ptm.setDouble(3, total);
                ptm.setInt(4, 0);
                if (ptm.executeUpdate() > 0) {
                    ptm = conn.prepareStatement(GET_LAST_ORDER_ID);
                    rs = ptm.executeQuery();
                    int orderID = 0;
                    if (rs.next()) {
                        orderID = rs.getInt("orderID");
                    }
                    if (orderID > 0) {
                        for (Map.Entry<String, Product> laptopEntry : cart.entrySet()) {
                            Product laptop = laptopEntry.getValue();
                            ptm = conn.prepareStatement(ADD_ORDER_DETAIL);
                            ptm.setInt(1, orderID);
                            ptm.setString(2, laptop.getId());
                            ptm.setDouble(3, laptop.getPrice());
                            ptm.setInt(4, laptop.getQuantity());
                            ptm.executeUpdate();
                            updateProductQuantity(laptop);
                        }
                        conn.setAutoCommit(true);
                        check = true;
                    }
                }
            }
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
        return check;
    }
    public boolean updateProductQuantity(Product laptop) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                int quantityInStock = getQuantityInStock(laptop.getId());
                ptm = conn.prepareStatement(UPDATE_PRODUCT);
                ptm.setInt(1,(quantityInStock-laptop.getQuantity()) );
                ptm.setString(2, laptop.getId());
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
