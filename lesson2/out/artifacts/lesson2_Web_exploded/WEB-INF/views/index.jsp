<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: okv
  Date: 17.01.18
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JDBC</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <td>ID</td>
        <td>Фамилия</td>
        <td>Имя</td>
        <td>Адрес</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="item">
        <tr>
            <td><c:out value="${item.id}" /></td>
            <td><c:out value="${item.firstName}" /></td>
            <td><c:out value="${item.secondName}" /></td>
            <td><c:out value="${item.address}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<button onclick="window.location.href='/adduser'">Add user</button>
<br/>
<button id="removeButton" onclick="document.getElementById('remove').style.display='block';this.style.display='none'">Delete user</button>
<form method="post" action="deleteuser" id="remove" style="display: none;">
    <input type="text" name="id" placeholder="Введите ID">
    <input type="submit" value="delete">
    <button onclick="document.getElementById('removeButton').style.display='block';this.parentElement.style.display='none';return false;">cancel</button>
</form>
</body>
</html>
