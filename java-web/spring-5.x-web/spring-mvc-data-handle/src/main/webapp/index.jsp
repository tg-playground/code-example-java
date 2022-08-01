<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
<h2>Hello World!</h2>

<h3>User Form</h3>

<form action="/postUser" method="post">
    <form:errors path="*" cssClass="errorblock" element="div" />
    <input name="id" type="hidden" value="11"/>
    Name: <input name="name" /> <br>
    Age: <input name="age" /> <br>
    Addrsss: <input name="address" /> <br>
    <input type="submit"/>
</form>
</body>
</html>
