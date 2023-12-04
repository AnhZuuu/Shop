<%-- Document : shopping Created on : Jun 16, 2023, 10:29:49 AM Author : Admin --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SOUNDFIN</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/userStyle.css" type="text/css" />
    </head>

    <body>
        <header>
            <div class="navbar">
                <div class="left-links">
                    <div class="logo"><img src="images/logo.png" class="logoimg"></div>
                    <a href="shopping.jsp" class="active">Shop</a>
                    <a href="userOrders.jsp">Your Order</a>
                </div>
                <div class="right-links">
                    <form action="MainController" method="post" class="formsearch">
                        <input type="text" name="search" value="${param.search}">
                        <select name="searchby">
                            <option value="byname">By Name</option>
                            <option value="bymanufacturer">By brand</option>
                        </select>
                            <input hidden="" value="searchproduct" name="action">
                            <input type="submit" value="search">
                    </form>
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
            <section class="shop">
            <c:if test="${(param.search == null)}">
                <jsp:useBean id="productDAO" class="sample.Shopping.ProductDAO"></jsp:useBean>
                <c:set var="PRODUCT_LIST" scope="request" value='${productDAO.searchProduct("", "")}'></c:set>
            </c:if>
            <c:forEach items="${requestScope.PRODUCT_LIST}" var="product" varStatus="counter">
                <table class="product">
                    <tr>
                        <td class="productimg"><img src="${product.imageSrc}" class="img"></td>

                        <td><label id="bold">Name: </label>${product.name} <br>
                            <label id="bold">Warranty: </label>${product.warranty} months <br>
                            <label id="bold">Type: </label>${product.type} <br>
                            <label id="bold">Color: </label>${product.color} <br>
                            <label id="bold">Price: </label>${product.price} USD <br>
                            <form action="MainController" method="POST">
                                <input name="productid" value="${product.id}" hidden="">
                                <div id="buy">
                                    <input type="number" name="quantity" placeholder="quantity" required=""
                                           min="1" max="10">
                                    <input type="submit" name="action" value="add">
                                </div>
                            </form>
                        </td>
                    </tr>
                </table>
                <form action='MainController' method="POST">
                </form>
            </c:forEach>
            <form action='MainController' method="POST">
            </form>
        </section>
        <footer>

        </footer>
    </body>

</html>