<%-- 
    Document   : login
    Created on : May 30, 2023, 11:00:17 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/loginStyle.css" type="text/css"/>
        <title>Login Page</title>
    </head>
    <body>
        <<div class="center">
        <div>
            <div class="logo"></div>
            <div class="login-block">
                <h1>Login</h1>
                <form action="MainController" method="POST">
                    <input type="text" name="userID" required="" placeholder="user ID" id="userid" /></br>
                    <input type="password" name="password" required="" placeholder="password" id="password" /></br>
                    <p class="error">${requestScope.ERROR}</p>
                    <p class="forget-password">Forget password: <a href="">Click here!</a></p>
                    <input type="submit" name="action" value="login" class="submit-button" />
                </form>
                <div class="link"><a href="MainController?action=CreatePage" class="link">create new user</a></div>
                <div class="back">
                    <a href="shopping.jsp" title="Back to shopping page" ><img src="images/left.png"></a>
                </div>
            </div>
        </div>
    </div>

    </body>
</html>
