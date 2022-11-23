<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: currychoco
  Date: 2022-11-15
  Time: 오후 4:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="/WEB-INF/views/layout/head.jsp"/>
    <style>
        img.busicard {
            width: 540px;
            height: 300px;
            border:solid black 1px;
        }
    </style>
</head>
<body>
    <c:import url="/WEB-INF/views/header.jsp"/>
    <div class="container">
        <div class="card form-group">
            <img src="/business-card/image?cardType=ko" class="busicard" />
            <img src="/business-card/image?cardType=en" class="busicard" />
        </div>
        <div class="form-group">
            <button type="button" class="btn btn-primary btn-block">신청</button>
        </div>
    </div>
</body>
</html>
