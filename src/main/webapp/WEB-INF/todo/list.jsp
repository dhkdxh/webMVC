<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2025-10-23
  Time: 오후 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Todo List Page</title>
</head>
<body>
    <h1>List Page</h1>

<%--    <p>${list}</p>--%>
<%--    <h3>${1+2+3}</h3>--%>
<%--    <h3>${"aaa" += "BBB"}</h3>--%>
<%--    <h3>${"AAA".equals("bbb")}</h3>--%>
<%--    <h4>${list[0].tno}</h4>--%>
<%--    <h4>${list[0].getDueDate()}</h4>--%>

    <ul>
        <c:forEach var="dto" items="${list}">
            <li>${dto}</li>
        </c:forEach>
    </ul>
    <ul>
        <c:forEach var="form" begin="1" end="10">
            <li>${form}</li>
        </c:forEach>
    </ul>
</body>
</html>
