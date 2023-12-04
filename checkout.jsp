<%-- 
    Document   : checkout
    Created on : Jun 16, 2023, 3:49:48 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check out page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/orderStyle.css" type="text/css" />
    </head>
    <body>
        <div class="navbar">
                <div class="left-links">
                    <div class="logo"><img src="images/logo.png" class="logoimg"></div>
                    <a href="shopping.jsp" >Shop</a>
                    <a href="userOrders.jsp">Your Order</a>
                </div>
                <div class="right-links">
                    <c:choose>
                        <c:when test="${empty sessionScope.CART or sessionScope.CART.getCart().size()==0}">
                            <a href="viewCart.jsp" class="cart" id="active"> <img src="images/cartEmptyBlack.png"></a>
                            </c:when>
                            <c:otherwise>
                            <a href="viewCart.jsp" class="cart" id="active"> <img src="images/cartBlack.png"></a>
                            </c:otherwise> 
                        </c:choose>
                        <c:choose>
                            <c:when test="${sessionScope.LOGIN_USER!=null}">
                            <a href="user.jsp">${sessionScope.LOGIN_USER.fullName}</a>
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
        <c:if test="${empty requestScope.SUCCESS}">
            <c:redirect url="shopping.jsp" ></c:redirect>
        </c:if>
        <c:set var="SUCCESS" scope="request" value='${null}'></c:set>
        <h2 class="tableFooter">Your order is comfirmed</h2>
    </body>
</html>
