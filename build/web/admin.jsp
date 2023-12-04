<%-- Document : admin Created on : May 30, 2023, 11:07:25 AM Author : Admin --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.user.UserDAO" %>
<%@page import="java.util.List" %>
<%@page import="sample.user.UserDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Page</title>
        <link rel="stylesheet" href="styles/adminStyle.css" type="text/css" />
    </head>

    <body>
        <header>
            <div class="navbar">
                <div class="left-links">
                    <div class="logo"><img src="images/logo.png" class="logoimg"></div>
                    <a href="admin.jsp" class="active">User</a>
                    <a href="order.jsp">Orders</a>
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

        <section>
            <c:if test="${param.search==null}">
                <jsp:useBean id="userDAO" class="sample.user.UserDAO"></jsp:useBean>
                <c:set var="USER_LIST" scope="request" value='${userDAO.getAllUsers()}'></c:set>
            </c:if>
            <form action="MainController" method="POST" class="search">
                <input type="text" name="search" value="${param.search}" />
                <input type="submit" name="action" value="search" /></br>
            </form>
            <div>
                <c:if
                    test="${requestScope.USER_LIST != null}">
                    <table>
                        <tr>
                            <th>No</th>
                            <th>User ID</th>
                            <th>Full Name</th>
                            <th>Role ID</th>
                            <th>Password</th>
                            <th>Delete</th>
                        </tr>
                        <c:forEach var="user" varStatus="counter" items="${requestScope.USER_LIST}">
                            <tr>
                            <form action="MainController" method="POST">
                                <td>${counter.count}</td>
                                <td><input type="text" name="userID" value="${user.userID}"
                                           readonly=""></td>
                                <td><input type="text" name="fullName" value="${user.fullName}"
                                           required=""></td>
                                <td><input type="text" name="roleID" value="${user.roleID}"
                                           required=""></td>
                                <td>${user.passWord}</td>
                                <td class="button">
                                    <input type="submit" name="action" value="update">
                                    <input type="submit" name="action" value="delete">
                                </td>
                                <input type="text" name="search" value="${param.search}"
                                       hidden="" readonly="">
                            </form>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
            <h3 style="color: red;">${requestScope.ERROR}</h3>
        </section>
    </body>

</html>