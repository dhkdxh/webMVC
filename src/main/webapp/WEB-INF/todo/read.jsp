<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2025-10-23
  Time: 오후 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>todo의 read</title>
</head>
<body>
    <script>
        function modifyOperation() {
            let currentForm = document.modify_remove;

            currentForm.method = "get";
            currentForm.action = "/todo/modify";
            currentForm.submit();
        }
    </script>
    <p>tno: ${dto.tno}</p>
    <p>title: ${dto.title}</p>
    <p>dueDate: ${dto.dueDate}</p>
    <p>finished: ${dto.finished}</p>

    <form name="modify_remove">
        <button type="submit" onclick="modifyOperation()">Modify/Remove</button>
    </form>
</body>
</html>
