<%-- 
    Document   : orderDetails
    Created on : Jul 5, 2023, 4:44:16 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order Details Page</title>
        <link rel="stylesheet" href="styles/adminStyle.css" type="text/css" />
    </head>
    <body>
        <header>
            <div class="navbar">
                <div class="left-links">
                    <div class="logo"><img src="images/logo.png" class="logoimg"></div>
                    <a href="admin.jsp">User</a>
                    <a href="order.jsp" class="active">Orders</a>
                </div>
                <div class="right-links">
                    <c:choose>
                        <c:when test="${sessionScope.LOGIN_USER!=null}">
                            <p>welcome, ${sessionScope.LOGIN_USER.fullName}</p>
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

        <c:if test="${requestScope.ORDER_DETAIL_LIST!=null and requestScope.ORDER_DETAIL_LIST.size()>0}">
            <table>
                <thead>
                    <tr>
                        <th>NO</th>
                        <th>ID</th>
                        <th>ORDER ID</th>
                        <th>PRODUCT NAME</th>
                        <th>QUANTITY</th>
                        <th>TOTAL</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="total" scope="request" value="${0}"/>
                    <jsp:useBean id="productDAO" class="sample.Shopping.ProductDAO"></jsp:useBean>
                    <c:forEach var="product" varStatus="counter" items="${requestScope.ORDER_DETAIL_LIST}">
                        <c:set var="productName" scope="request" value="${productDAO.getProductById(product.getProductId()).getName()}"/>
                        <tr>
                    <form action="MainController">
                        <td>${counter.count}</td>
                        <td><input type="text" name="id" value="${product.getOrderDetailId()}" readonly=""></td>
                        <td>${product.getOrderId()}</td>
                        <td>${requestScope.productName}</td>
                        <td>
                            <input type="number" name="oldQuantity" value="${product.getQuantity()}" min="1" hidden="">
                            <input type="number" name="quantity" value="${product.getQuantity()}" min="1" required="">
                        </td>
                        <td>${product.getPrice() * product.getQuantity()}</td>
                    </form>
                </tr>
            </c:forEach>
        </table>
        <div class="tableFooter">
            <h3>SUM: ${requestScope.TOTAL}</h3>
        </div>
    </c:if>
</body>
</html>
