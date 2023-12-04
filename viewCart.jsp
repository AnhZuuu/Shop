<%-- 
    Document   : viewCart
    Created on : Jun 16, 2023, 10:41:53 AM
    Author     : Admin
--%>

<%@page import="sample.Shopping.Product"%>
<%@page import="sample.Shopping.Cart"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SOUNDFIN CART</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/orderStyle.css" type="text/css" />
    </head>
    <body>
        <header>
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
        <c:if test="${sessionScope.CART != null and sessionScope.CART.getCart().size()>0}">
            <table>
                <thead>
                    <tr>
                        <th>NO</th>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>PRICE</th>
                        <th>QUANTITY</th>
                        <th>TOTAL</th>
                        <th>ACTION</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="total" scope="request" value="${0}"/>
                    <c:set var="counter" scope="request" value="${0}"/>
                    <c:forEach var="product" items="${sessionScope.CART.getCart()}">
                        <c:set var="total" scope="request" value="${requestScope.total + product.value.getPrice() * product.value.getQuantity()}"/>
                        <tr>
                    <form action="MainController">
                        <c:set var="counter" scope="request" value="${counter+1}"/>
                        <td>${counter}</td>
                        <td><input type="text" name="id" value="${product.value.getId()}" readonly=""></td>
                        <td>${product.value.getName()}</td>
                        <td>${product.value.getPrice()}</td>
                        <td><input type="number" name="quantity" value="${product.value.getQuantity()}" min="1" required=""></td>
                        <td>${product.value.getPrice() * product.value.getQuantity()}</td>
                        <td>
                            <input type="submit" name="action" value="remove">
                            <input type="submit" name="action" value="edit">
                        </td>
                    </form>
                </tr>
            </c:forEach>
        </table>
        <div class="tableFooter">
            <h3>Total: ${total}</h3>
        </div>
        <form action="MainController" method="POST">
            <input type="text" value="${total}" name="total" hidden="">
            <input type="submit" value="checkout" name="action">
        </form>
    </c:if>
    <c:if test="${sessionScope.CART == null or sessionScope.CART.getCart().size()==0}">
        <h2 class="tableFooter">Nothing in the cart! Please back to shop to add more</h2>
    </c:if>
    <p>${requestScope.ERROR}</p><br>
    <p>${requestScope.QUANTITY_ERROR}</p>
</body>
</html>
