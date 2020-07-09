<%--
  Created by IntelliJ IDEA.
  User: Taogen
  Date: 7/9/2020
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Hello! <%=session.getAttribute("user")%>

<br>
<br>

<a href="logout">Logout</a>
</body>
</html>
