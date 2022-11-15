<%--
  Created by IntelliJ IDEA.
  User: currychoco
  Date: 2022-11-15
  Time: 오전 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<%
    String id = (String)session.getAttribute("id");
%>
    <header>
        <%if(id != null){%>
            <a href="/"><h1>회사명</h1></a>
            <a href="/login">로그인</a>
            <a href="/join">회원가입</a>
        <%}else{%>
            <a href="/logout">로그아웃</a>
        <%}%>
    </header>
    <nav>
        <ul>
            <li><a href="/">영문명 추천</a></li>
            <li><a href="/businesscard">명함 신청</a></li>
            <li><a href="/">회원 정보</a></li>

        </ul>
    </nav>
</body>
</html>
