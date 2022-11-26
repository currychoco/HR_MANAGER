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
    <c:import url="/WEB-INF/views/layout/head.jsp"/>
    <script src="/js/join.js"></script>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp"/>
<div class="container">
    <div class="form-group">
        <input type="text" class="form-control" id="empNo" name="empNo" placeholder="사번" required>
        <button type="button" class="btn btn-primary">이메일 인증 번호 발송</button>
    </div>
    <div class="form-group">
        <input type="text" class="form-control" id="id" name="id" placeholder="아이디" required>
    </div>
    <div class="form-group">
        <input type="password" class="form-control" id="password" name="password" placeholder="패스워드" required>
    </div>
    <div class="form-group">
        <button class="btn btn-primary btn-block" onclick="join()">회원가입</button>
    </div>

</div>
</body>
</html>
