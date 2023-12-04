/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.Shopping.Cart;
import sample.Shopping.Product;
import sample.Shopping.CartDAO;
import sample.user.UserDTO;

/**
 *
 * @author Admin
 */
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "checkout.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        boolean check = false;
        String quantityError = "";
        Product laptop = new Product();
        String url = ERROR;
        try {
            double total = Double.parseDouble(request.getParameter("total"));
            Cart cart = (Cart) session.getAttribute("CART");
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            CartDAO orderDAO = new CartDAO();
            for (Map.Entry<String, Product> laptopEntry : cart.getCart().entrySet()) {
                laptop = laptopEntry.getValue();
                if (!orderDAO.checkAvailableQuantity(laptop)) {
                    quantityError += laptop.getName() + " only have " + orderDAO.getQuantityInStock(laptop.getId()) + " left\n";
                }
            }
            if (!"".equals(quantityError)) {
                request.setAttribute("QUANTITY_ERROR", quantityError);
                url = ERROR;
            } else {
                check = orderDAO.addOrderDetail(loginUser.getUserID(), total, cart.getCart());
                if (check) {
                    session.setAttribute("CART", null);
                    request.setAttribute("SUCCESS", "SUCCESS");
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Unkown Error");
                }
            }
        } catch (Exception e) {
            log(e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
