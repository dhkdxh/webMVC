<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Post List</title>
    <style>
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            font-family: "Segoe UI", Arial, sans-serif;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px 15px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
            transition: 0.2s ease-in;
        }

        a {
            color: #2a7ae2;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

        caption {
            caption-side: top;
            font-size: 1.3em;
            font-weight: bold;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <script>

    </script>
    <table>
        <caption>Post List</caption>
        <thead>
        <tr>
            <th>No</th>
            <th>Title</th>
            <th>CreatedAt</th>
            <th>Writer</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${posts}" var="post">
            <tr>
                <!-- 자동 글번호 (1부터 시작) -->
                <td>${post.postId}</td>

                <!-- 제목에 링크 걸기 -->
                <td>
                    <a href="/posts/view?id=${post.postId}">${post.title}</a>
                </td>

                <td>${post.createdAt}</td>

                <td>
                    ${post.writer}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
