<%-- 
    Document   : order
    Created on : Jun 17, 2023, 2:18:14 PM
    Author     : Admin
--%>

<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Orders Page</title>
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
        <jsp:useBean id="orderDAO" class="sample.Shopping.OrderDAO" ></jsp:useBean>
        <c:set var="orderList" scope="request" value='${orderDAO.getAllOrders()}'></c:set>
        <c:if test="${not empty requestScope.orderList}">
            <table>
                <thead>
                    <tr>
                        <th>NO</th>
                        <th>ID</th>
                        <th>USER</th>
                        <th>DATE</th>
                        <th>TOTAL</th>
                        <th>STATUS</th>
                        <th>VIEW</th>
                        <th colspan="3" id="changeStatus">CHANGE STATUS</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" varStatus="counter" items="${requestScope.orderList}">
                        <tr>
                    <form action="MainController" method="POST">
                        <td>${counter.count}</td>
                        <td><input type="text" name="id" value="${order.getId()}" readonly=""></td>
                        <td>${order.getUserId()}</td>
                        <td>${order.getDate()}</td>
                        <td>${order.getTotal()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${order.getStatus()==-1}">
                                    canceled
                                </c:when>
                                <c:when test="${order.getStatus()==0}">
                                    waiting
                                </c:when>
                                <c:when test="${order.getStatus()==1}">
                                    Shipped
                                </c:when>
                            </c:choose>
                        </td>
                        <td><input type="submit" name="action" value="viewDetail"></td>
                        <td>
                            <c:if test="${order.getStatus()!=-1}">
                                <input type="submit" name="action" value="cancel">
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${order.getStatus()!=0}">
                                <input type="submit" name="action" value="waiting">
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${order.getStatus()!=1}">
                                <input type="submit" name="action" value="shipped">
                            </c:if>
                        </td>
                    </form>
                </tr>
        </body>
    </c:forEach>
</table>
            <c:if test="${not empty requestScope.ERROR}">
                <h3 class="tableFooter">${requestScope.ERROR}</h3>
            </c:if>
</c:if>
</body>
</html>
