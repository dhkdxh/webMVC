<%--
  Created by IntelliJ IDEA.
  User: j
  Date: 2025-10-25
  Time: 오후 3:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Post Form</title>
</head>
<body>
    <script>
        function save_func(){
            let formName = document.postForm;

            formName.action = '/posts/save';
            formName.method = 'post';
            formName.submit();
        }

        function update_func(){
            let formName = document.postForm;
            let writer = formName.writer.value;

            if(writer !== "${post.writer}"){
                alert("작성자는 변경할 수 없습니다.");
            }else{
                formName.action = '/posts/update';
                formName.method = 'post';
                formName.submit();
            }
        }
    </script>
    <form name="postForm">
        <input type="text" name="title" value="${post.title}" placeholder="title" required><br/>
        <input type="text" name="content" value="${post.content}" placeholder="content" required><br/>
        <input type="text" name="writer" value="${post.writer}" placeholder="name" required><br/>
        <input type="password" name="passphrase" placeholder="password" required><br/>


        <c:choose>
            <c:when test="${post.postId != null}">
                <button type="submit" onclick="update_func()" id="updatebtn">수정하기</button>
                <input type="hidden" name="postId" value="${post.postId}">
            </c:when>
            <c:otherwise>
                <button type="submit" onclick="save_func()" id="savebtn">등록하기</button>
            </c:otherwise>
        </c:choose>
    </form>
</body>
</html>
