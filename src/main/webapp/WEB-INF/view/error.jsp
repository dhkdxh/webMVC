<%--
  Created by IntelliJ IDEA.
  User: j
  Date: 2025-10-25
  Time: 오후 3:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
    <h1>Error Page</h1>
    <h2>Exception Occurs!</h2>
    <p>${error}</p>

    <form action="/posts" method="get">
        <button type="submit" onclick="history.back()">이전 페이지로</button>
    </form>
</body>
</html>
