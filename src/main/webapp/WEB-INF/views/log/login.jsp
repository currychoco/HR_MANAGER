<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: currychoco
  Date: 2022-11-15
  Time: 오전 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/login.js"></script>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp"/>
<section>
    <div>
        <input type="text" id="id" name="id" placeholder="아이디" required/>
        <input type="password" id="password" name="password" placeholder="패스워드" required/>
        <button onclick="login()">로그인</button>
    </div>
</section>
</body>
</html>
