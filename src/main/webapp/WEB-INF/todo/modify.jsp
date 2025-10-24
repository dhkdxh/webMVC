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
    <title>Todo Modify/Remove</title>
</head>
<body>
    <script>
        function modifyOperation() {
            let formName = document.modify_remove;

            formName.action = '/todo/modify';
            formName.method = 'post';
            formName.submit();
        }
        function removeOperation() {
            let formName = document.modify_remove;

            formName.action = '/todo/remove';
            formName.method = 'post';
            formName.submit();
        }
    </script>
    <form name="modify_remove">
        <input type="number" name="tno" placeholder="tno" required><br/>
        <input type="text" name="title" placeholder="title" required><br/>
        <input type="date" name="dueDate" required><br/>
<%--        <input type="hidden" name="finished" value="false">--%>
        <input type="checkbox" name="finished" value="true">


        <button type="submit" id="modify" onclick="modifyOperation()">Modify</button>
        <button type="submit" id="remove" onclick="removeOperation()">Remove</button>
    </form>
</body>
</html>
