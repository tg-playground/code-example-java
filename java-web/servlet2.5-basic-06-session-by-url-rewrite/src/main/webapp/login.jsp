<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Taogen
  Date: 2/19/2020
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

Error Message: <%=request.getAttribute("errorMessage")%>
<br>

<form action="<c:url value="/login" />" method="post">
    Username: <input type="text" name="username" />
    <br>
    Password: <input type="password" name="password" />
    <br>
    <input type="submit" value="Login">
</form>
</body>
</html>
