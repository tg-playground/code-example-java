<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Taogen
  Date: 2/17/2020
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Success Page</title>
</head>
<body>

<h3>Login successful.</h3>
<br>

Add jsessionid method 1: <a href="<c:url value="/userInfo" />">Go to User Info page</a>
<br>

Add jsessionid method 2: <a href='<%=response.encodeURL(request.getContextPath()+"/userInfo")%>'>Go to User Info page</a>
<br>

<a href="<c:url value="/" />">Go to Index Page</a>
<br>

<a href="<c:url value="/logout" />">Logout</a>
<br>


</body>
</html>
