<%--
  Created by IntelliJ IDEA.
  User: okv
  Date: 15.02.18
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Old browser</title>
    <style>
        html, body {
            height: 100%;
        }

        body {
            background: azure;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .main-container {
            background: aquamarine;
            display: flex !important;
            width: 80%;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            margin: auto;
            -webkit-border-radius: 8px;
            -moz-border-radius: 8px;
            border-radius: 8px;
        }
    </style>
</head>
<body>
<div class="main-container">
    <h1>Ваши браузер <% out.println(request.getAttribute("BrowserName").toString());%>
        устарел, скачайте более свежую версию </h1>
    <p>Версия вашего браузера:<% out.println(request.getAttribute("Version").toString());%> Необходимо не менее:
        <% out.println(request.getAttribute("RequiredVersion").toString());%></p>
    <ul>
        <li><a href="http://www.opera.com/ru">Opera</a></li>
        <li><a href="https://www.google.ru/chrome/index.html">Chrome</a></li>
        <li><a href="https://www.mozilla.org/ru/firefox/">Firefox</a></li>
        <li><a href="https://www.microsoft.com/ru-ru/download/internet-explorer.aspx">Internet Explorer</a></li>
    </ul>
</div>
</body>
</html>
