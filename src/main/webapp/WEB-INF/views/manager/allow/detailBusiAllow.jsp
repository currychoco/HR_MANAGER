<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: currychoco
  Date: 2022-11-19
  Time: 오후 3:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="/WEB-INF/views/layout/head.jsp"/>
    <script type="text/javascript">
        function busiCardAllow(){
            const no = '<c:out value = "${busiCard.no}"/>';
            const empNo = '<c:out value = "${busiCard.empNo}"/>';
            const empName = '<c:out value = "${busiCard.empName}"/>';
            const empNameEn = '<c:out value = "${busiCard.empNameEn}"/>';
            const deptName = '<c:out value = "${busiCard.deptName}"/>';
            const deptNameEn = '<c:out value = "${busiCard.deptNameEn}"/>';
            const jobName = '<c:out value = "${busiCard.jobName}"/>';
            const jobNameEn = '<c:out value = "${busiCard.jobNameEn}"/>';
            const positionName = '<c:out value = "${busiCard.positionName}"/>';
            const positionNameEn = '<c:out value = "${busiCard.positionNameEn}"/>';
            const email = '<c:out value = "${busiCard.email}"/>';
            const phone = '<c:out value = "${busiCard.phone}"/>';

            $.ajax({
                url : "/allow/busi-card",
                type : "POST",
                contentType : "application/json",
                data : JSON.stringify({
                    no : no,
                    empNo : empNo,
                    empName : empName,
                    empNameEn : empNameEn,
                    deptName : deptName,
                    deptNameEn : deptNameEn,
                    jobName : jobName,
                    jobNameEn : jobNameEn,
                    positionName : positionName,
                    positionNameEn : positionNameEn,
                    email : email,
                    phone : phone,
                })
            }).done(function(response){
                alert("명함 신청 승인 완료");
                location.href="/manager/busi-card/allow";
            }).fail(function(err){
                err.responseJSON && alert(err.responseJSON.message);
            });
        }
    </script>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp"/>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>목록</th>
            <th>명함 신청 정보</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>한글명</td>
            <td>${busiCard.empName}</td>
        </tr>
        <tr>
            <td>영문명</td>
            <td>${busiCard.empNameEn}</td>
        </tr>
        <tr>
            <td>부서한글명</td>
            <td>${busiCard.deptName}</td>
        </tr>
        <tr>
            <td>부서영문명</td>
            <td>${busiCard.deptNameEn}</td>
        </tr>
        <tr>
            <td>직책한글명</td>
            <td>${busiCard.jobName}</td>
        </tr>
        <tr>
            <td>직책영문명</td>
            <td>${busiCard.jobNameEn}</td>
        </tr>
        <tr>
            <td>직급한글명</td>
            <td>${busiCard.positionName}</td>
        </tr>
        <tr>
            <td>직급영문명</td>
            <td>${busiCard.positionNameEn}</td>
        </tr><tr>
            <td>이메일</td>
            <td>${busiCard.email}</td>
        </tr>
        <tr>
            <td>휴대폰번호</td>
            <td>${busiCard.phone}</td>
        </tr>
        </tbody>
    </table>
    <div>
        <button class="btn btn-primary btn-block" onclick="busiCardAllow()">명함 신청 승인</button>
    </div>
</div>
</body>
</html>
