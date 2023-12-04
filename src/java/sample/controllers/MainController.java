/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {
    
    private static final String WELCOME_PAGE = "login.html";
    
    private static final String LOGIN = "login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT = "logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String SEARCH  = "search";
    private static final String SEARCH_CONTROLLER = "SearchController";
    private static final String DELETE  = "delete";
    private static final String DELETE_CONTROLLER = "DeleteController";
    private static final String UPDATE  = "update";
    private static final String UPDATE_CONTROLLER = "UpdateController";
    private static final String CREATE_PAGE = "CreatePage";
    private static final String CREATE_PAGE_VIEW= "create.html";
    private static final String CREATE  = "Create";
    private static final String CREATE_CONTROLLER = "CreateController";
    private static final String SHOPPING_PAGE  = "ShoppingPage";
    private static final String SHOPPING_PAGE_VIEW = "shopping.html";
    private static final String ADD  = "add";
    private static final String ADD_CONTROLLER = "AddController";
    private static final String VIEW_PAGE  = "view";
    private static final String VIEW_PAGE_VIEW = "viewCart.jsp";
    private static final String REMOVE  = "remove";
    private static final String REMOVE_CONTROLLER = "RemoveController";
    private static final String EDIT = "edit";
    private static final String EDIT_CONTROLLER = "EditController";
    private static final String CHECKOUT = "checkout";
    private static final String CHECKOUT_CONTROLLER = "CheckOutController";
    private static final String SEARCH_PRODUCT = "searchproduct";
    private static final String SEARCH_PRODUCT_CONTROLLER = "SearchProductController";
    private static final String VIEW_DETAIL = "viewDetail";
    private static final String VIEW_DETAIL_CONTROLLER = "ViewDetailController";
    private static final String CANCEL_STATUS = "cancel";
    private static final String WAITING_STATUS = "waiting";
    private static final String SHIPPED_STATUS = "shipped";
    private static final String CHANGE_STATUS_CONTROLLER = "ChangeStatusController";
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
        String url = WELCOME_PAGE;
        try {
            String action = request.getParameter("action");
            switch (action){
                case LOGIN:
                    url = LOGIN_CONTROLLER;
                    break;
                case LOGOUT:
                    url = LOGOUT_CONTROLLER;
                    break;
                case SEARCH:
                    url = SEARCH_CONTROLLER;
                    break;
                case DELETE:
                    url = DELETE_CONTROLLER;
                    break;
                case UPDATE:
                    url = UPDATE_CONTROLLER;
                    break;
                case CREATE_PAGE:
                    url = CREATE_PAGE_VIEW;
                    break;
                case CREATE:
                    url = CREATE_CONTROLLER;
                    break;
                case SHOPPING_PAGE:
                    url = SHOPPING_PAGE_VIEW;
                    break;
                case ADD:
                    url = ADD_CONTROLLER;
                    break;
                case VIEW_PAGE:
                    url = VIEW_PAGE_VIEW;
                    break; 
                case REMOVE:
                    url = REMOVE_CONTROLLER;
                    break;
                case EDIT:
                    url = EDIT_CONTROLLER;
                    break;
                case CHECKOUT:
                    url = CHECKOUT_CONTROLLER;
                    break;
                case SEARCH_PRODUCT:
                    url = SEARCH_PRODUCT_CONTROLLER;
                    break;
                case VIEW_DETAIL:
                    url = VIEW_DETAIL_CONTROLLER;
                    break;
                case CANCEL_STATUS:
                case WAITING_STATUS:
                case SHIPPED_STATUS:
                    url = CHANGE_STATUS_CONTROLLER;
                    break;
                default:
                    url = WELCOME_PAGE;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally{
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
