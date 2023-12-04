<%-- 
    Document   : user
    Created on : May 30, 2023, 11:04:31 AM
    Author     : Admin
--%>

<%@page import="sample.user.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Information</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/userInformationStyle.css" type="text/css" />
    </head>

    <body>
        <header>
            <div class="navbar">
                <div class="left-links">
                    <div class="logo"><img src="images/logo.png" class="logoimg"></div>
                    <a href="shopping.jsp">Shop</a>
                    <a href="userOrders .jsp">Your Order</a>
                </div>
                <div class="right-links">
                        <c:choose>
                            <c:when test="${empty sessionScope.CART or sessionScope.CART.getCart().size()==0}">
                                <a href="viewCart.jsp" class="cart"> <img src="images/cartEmpty.png"></a>
                            </c:when>
                            <c:otherwise>
                            <a href="viewCart.jsp" class="cart"> <img src="images/cart.png"></a>
                        </c:otherwise> 
                        </c:choose>
                    <c:choose>
                        <c:when test="${sessionScope.LOGIN_USER!=null}">
                        <a href="user.jsp" class="active">${sessionScope.LOGIN_USER.fullName}</a>
                            <c:url var="logoutLink" value="MainController">
                                <c:param name="action" value="logout"></c:param>
                            </c:url>
                            <a href="${logoutLink}">Log out</a>
                        </c:when>
                        <c:otherwise>
                            <a href="create.html">Register</a>
                            <a href="login.html">Log In</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </header>

        <table>
            <tr>
                <th>User's Information</th>
                <th></th>
            </tr>
            <tr>
                <td>user ID:</td>
                <td>${sessionScope.LOGIN_USER.getUserID()}</td>
            </tr>
            <tr>
                <td>user's Name:</td>
                <td>${sessionScope.LOGIN_USER.getFullName()}</td>
            </tr>
            <tr>
                <td>user's Role:</td>
                <td>${sessionScope.LOGIN_USER.getRoleID()}</td>
            </tr>
            <tr>
                <td>user's Password:</td>
                <td>${sessionScope.LOGIN_USER.getPassWord()}</td>
            </tr>
        </table>
    </body>
</html>
