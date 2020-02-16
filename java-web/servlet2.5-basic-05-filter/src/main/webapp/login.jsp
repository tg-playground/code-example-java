<%--
  Created by IntelliJ IDEA.
  User: Taogen
  Date: 2/16/2020
  Time: 5:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String contextPath = request.getContextPath(); %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

ErrorMessage: <%= request.getAttribute("errorMessage") %>

<form action="<%=contextPath%>/login" method="post">
    Username:<input type="text" name="username"/>
    <br>
    Password: <input type="password" name="password" />
    <br>
    <input type="submit" value="Login">
</form>
</body>
</html>
