<%-- 
    Document   : create
    Created on : Jun 9, 2023, 10:15:28 AM
    Author     : Admin
--%>

<%@page import="sample.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/loginStyle.css" type="text/css" />
    </head>
    <body>
        <jsp:useBean id="userEror" class="sample.user.UserError"></jsp:useBean>
        <div class="center">
        <div>
            <div class="logo"></div>
            <div class="login-block">
                <h1>Register</h1>
                <form action="MainController" method="POST">
                    <input type="text" name="userID" placeholder="User ID" required="" id="userid">${userError.getUserIDError()}<br>
                    <input type="text" name="fullName" required="" placeholder="Full Name" id="fullName">${userError.getFullNameError()}<br>
                    <input type="password" name="password" required="" placeholder="Password" id="password"><br>
                    <input type="password" name="confirm" required="" placeholder="Confirm password" id="password">${userError.getConfirmError()}<br>
                    <input type="submit" name="action" value="Create" class="submit-button">
                    ${userError.getError()}
                </form>
                <div class="link"><a href="login.html" class="link">back to login</a></div>
            </div>
        </div>
    </div>
    </body>
</html>
