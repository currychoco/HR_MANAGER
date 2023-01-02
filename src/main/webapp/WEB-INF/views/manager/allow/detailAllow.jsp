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
    <c:import url="/WEB-INF/views/layout/head.jsp"/>
    <script type="text/javascript">
        function modifyAllow(){
            const no = '<c:out value = "${myInformation.no}"/>';
            const empNo = '<c:out value = "${employee.empNo}"/>';
            const empName = '<c:out value = "${myInformation.empName}"/>';
            const empNameEn = '<c:out value = "${myInformation.empNameEn}"/>';
            const gender = '<c:out value = "${myInformation.gender}"/>';
            const email = '<c:out value = "${myInformation.email}"/>';
            const phone = '<c:out value = "${myInformation.phone}"/>';
            const address = '<c:out value = "${myInformation.address}"/>';

            $.ajax({
                url : "/allow/update",
                type : "POST",
                contentType : "application/json",
                data : JSON.stringify({
                    no : no,
                    empNo : empNo,
                    empName : empName,
                    empNameEn : empNameEn,
                    gender : gender,
                    email : email,
                    phone : phone,
                    address : address
                })
            }).done(function(response){
                alert("승인완료");
                location.href="/manager/allow/list";
            }).fail(function(err){
                err.responseJSON && alert(err.responseJSON.message);
            });
        }
    </script>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp"/>

<div class="container body-container">
    <h3 class="title">인사정보 수정신청 관리</h3>
    <table class="table">
        <thead>
            <tr>
                <th>목록</th>
                <th>변경 전</th>
                <th>변경 후</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>한글명</td>
                <td>${employee.empName}</td>
                <td>${myInformation.empName}</td>
            </tr>
            <tr>
                <td>영문명</td>
                <td>${employee.empNameEn}</td>
                <td>${myInformation.empNameEn}</td>
            </tr>
            <tr>
                <td>성별</td>
                <td>${employee.gender}</td>
                <td>${myInformation.gender}</td>
            </tr>
            <tr>
                <td>이메일</td>
                <td>${employee.email}</td>
                <td>${myInformation.email}</td>
            </tr>
            <tr>
                <td>휴대폰번호</td>
                <td>${employee.phone}</td>
                <td>${myInformation.phone}</td>
            </tr>
            <tr>
                <td>주소</td>
                <td>${employee.address}</td>
                <td>${myInformation.address}</td>
            </tr>
        </tbody>
    </table>
    <div>
        <button class="btn btn-primary btn-block" onclick="modifyAllow()">승인</button>
    </div>
</div>
<c:import url="/WEB-INF/views/footer.jsp"/>
</body>
</html>
