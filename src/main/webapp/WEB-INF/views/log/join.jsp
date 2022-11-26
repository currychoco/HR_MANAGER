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
    <c:import url="/WEB-INF/views/layout/head.jsp"/>
    <script src="/js/join.js"></script>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp"/>
<div class="container body-container">
    <h3 class="title">회원가입</h3>
    <div class="row form-group">
        <div class="col-sm-10">
            <input type="text" class="form-control" id="empNo" name="empNo" placeholder="사번" required>
        </div>
        <div class="col-sm-2">
            <button type="button" id="sendEmailButton" class="btn btn-primary" style="float:right" onclick="sendKeyByEmail()">이메일 인증 번호 발송</button>
        </div>
    </div>
    <div class="row form-group" id="checkKeyForm" style="display:none">
        <div class="col-sm-10">
            <input type="text" class="form-control" id="key" name="key" placeholder="인증 번호" required>
        </div>
        <div class="col-sm-2">
            <button type="button" id="keyCheckButton" class="btn btn-primary" style="float:right" onclick="checkKey()">인증 번호 확인 버튼</button>
        </div>
    </div>
    <div class="form-group">
        <input type="text" class="form-control" id="id" name="id" placeholder="아이디" required>
        <span id="duplicateMsg"></span>
    </div>
    <div class="form-group">
        <input type="password" class="form-control" id="password" name="password" placeholder="패스워드" required>
    </div>
    <div class="form-group">
        <button class="btn btn-primary btn-block" onclick="join()">회원가입</button>
    </div>
</div>
<c:import url="/WEB-INF/views/footer.jsp"/>
</body>
</html>
