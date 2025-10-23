<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2025-10-22
  Time: 오후 3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/calc/makeResult" method="POST">
        <input type="number" name="num1">
        <input type="number" name="num2">

        <button type="submit">전송</button>
    </form>
</body>
</html>
