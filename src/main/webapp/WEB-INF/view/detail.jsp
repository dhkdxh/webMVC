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
    <title>Post Detail</title>
</head>
<body>
    <script>
        function update_page(){
            let formName = document.detailForm;

            formName.passphrase.value = "temp";
            formName.method = 'get';
            formName.action = '/posts/edit?id=${post.postId}';
            formName.submit();
        }

        function delete_func(){
            let formName = document.detailForm;

            if(!formName.passphrase.value){
                alert("비밀번호를 입력해주세요!");
                inputPwd.focus();
                return;
            }

            formName.method = 'post';
            formName.action = '/posts/delete';
            formName.submit();
        }
    </script>
    <h1>Detail Page</h1>

    <ul>
        <li>post_id : ${post.postId}</li>
        <li>title : ${post.title}</li>
        <li>content : ${post.content}</li>
        <li>writer : ${post.writer}</li>
        <li>createdAt : ${post.createdAt}</li>
        <li>updatedAt : ${post.updatedAt}</li>
    </ul>

    <form name="detailForm">
        <input type="password" name="passphrase" placeholder="비밀번호를 입력하세요.">
        <button type="button" onclick="update_page()">수정하기</button>
        <button type="button" onclick="delete_func()">삭제하기</button><br/>
        <input type="hidden" name="id" value="${post.postId}">
    </form>
</body>
</html>
