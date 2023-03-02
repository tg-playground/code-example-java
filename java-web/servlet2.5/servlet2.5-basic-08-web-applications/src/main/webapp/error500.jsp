<%--
  Created by IntelliJ IDEA.
  User: Taogen
  Date: 2/20/2020
  Time: 2:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error 500 Page</title>
</head>
<body>
<h3>Error 500: Server internal error!</h3>
<ul>
    <li>Status Code: <%=request.getAttribute("javax.servlet.error.status_code")%></li>
    <li>Exception Type: <%=request.getAttribute("javax.servlet.error.exception_type")%></li>
    <li>Message: <%=request.getAttribute("javax.servlet.error.message")%></li>
    <li>Exception: <%=request.getAttribute("javax.servlet.error.exception")%></li>
    <li>Request Uri: <%=request.getAttribute("javax.servlet.error.request_uri")%></li>
    <li>Servlet Name: <%=request.getAttribute("javax.servlet.error.servlet_name")%></li>
</ul>
</body>
</html>
