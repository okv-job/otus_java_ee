<%--
  Created by IntelliJ IDEA.
  User: okv
  Date: 17.01.18
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
    <form method="post" action="/adduser">
        <input type="text" name="firstName" placeholder="Фамилия"><br/>
        <input type="text" name="secondName" placeholder="Имя"><br/>
        <input type="text" name="address" placeholder="Адрес"><br/>
        <input type="submit" value="Add user">
    </form>
</body>
</html>
