/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.Shopping;

import java.sql.Connection;
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
public class ProductDAO {

    private static final String SEARCH_ALL = "SELECT productID, productName, price, quantityInStock, "
            + "imageSrc, manufacturer, warranty, color, type FROM tblProducts";
    private static final String SEARCH_PRODUCT_BY_ID = "SELECT productID, productName, price, quantityInStock, "
            + "imageSrc, manufacturer, warranty, color, type FROM tblProducts WHERE productID = ?";
    private static final String SEARCH_PRODUCT_BY_NAME = "SELECT productID, productName, price, quantityInStock, "
            + "imageSrc, manufacturer, warranty, color, type FROM tblProducts WHERE productName LIKE ?";
    private static final String SEARCH_PRODUCT_BY_MANUFACTURER = "SELECT productID, productName, price, quantityInStock, "
            + "imageSrc, manufacturer, warranty, color, type FROM tblProducts WHERE manufacturer LIKE ?";

    public List<Product> getListProduct() throws SQLException {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_ALL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    double price = rs.getDouble("price");
                    int quantityInStock = rs.getInt("quantityInStock");
                    String imageSrc = rs.getString("imageSrc");
                    String manufacturer = rs.getString("manufacturer");
                    int warranty = rs.getInt("warranty");
                    String color = rs.getString("color");
                    String type = rs.getString("type");
                    listProduct.add(new Product(productID, productName, price, quantityInStock, imageSrc, warranty, color, type));
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
        return listProduct;
    }
    public Product getProductById(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_PRODUCT_BY_ID);
                ptm.setString(1, id);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    double price = rs.getDouble("price");
                    int quantityInStock = rs.getInt("quantityInStock");
                    String imageSrc = rs.getString("imageSrc");
                    String manufacturer = rs.getString("manufacturer");
                    int warranty = rs.getInt("warranty");
                    String color = rs.getString("color");
                    String type = rs.getString("type");
                    return (new Product(productID, productName, price, quantityInStock, imageSrc, warranty, color, type));
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
    public List<Product> searchProduct(String searchBy, String search) throws SQLException {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if ("".equals(search)) {
                    return getListProduct();
                } else {
                    if ("byname".equals(searchBy)) {
                        ptm = conn.prepareStatement(SEARCH_PRODUCT_BY_NAME);
                    } else if ("bymanufacturer".equals(searchBy)) {
                        ptm = conn.prepareStatement(SEARCH_PRODUCT_BY_MANUFACTURER);
                    } else {
                        return null;
                    }
                    ptm.setString(1, "%" + search + "%");
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        String productID = rs.getString("productID");
                        String productName = rs.getString("productName");
                        double price = rs.getDouble("price");
                        int quantityInStock = rs.getInt("quantityInStock");
                        String imageSrc = rs.getString("imageSrc");
                        String manufacturer = rs.getString("manufacturer");
                        int warranty = rs.getInt("warranty");
                        String color = rs.getString("color");
                        String type = rs.getString("type");
                        listProduct.add(new Product(productID, productName, price, quantityInStock, imageSrc, warranty, color, type));
                    }
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
        return listProduct;
    }
}
