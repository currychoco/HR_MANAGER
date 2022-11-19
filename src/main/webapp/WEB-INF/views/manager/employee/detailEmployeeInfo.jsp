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
        function updateEmployee(){
            const empNo = $("#empNo").val();
            const dept = $("#dept option:selected").val();
            const job = $("#job option:selected").val();
            const position = $("#position option:selected").val();
            const empName = $("#empName").val();
            const empNameEn = $("#empNameEn").val();
            const gender = $("#gender option:selected").val();
            const email = $("#email").val();
            const phone = $("#phone").val();

            if(!dept || !job || !position || !empName || !empNameEn || !gender || !email || !phone) {
                alert("빠진거 있음");
                return;
            }

            $.ajax({
                url : "/employee/update",
                type : "POST",
                contentType : "application/json",
                data : JSON.stringify({
                    empNo : empNo,
                    deptCode : dept,
                    jobCode : job,
                    positionCode : position,
                    empName : empName,
                    empNameEn : empNameEn,
                    gender : gender,
                    email : email,
                    phone : phone
                })
            }).done(function(response){
                alert("회원 정보 수정이 완료되었습니다 :)");
            }).fail(function(err){
                alert("회원 정보 수정에 실패했습니다 :(");
            });
        }

        $(document).ready(function() {
            $("#updateForm").submit(function(e) {
                e.preventDefault();
                updateEmployee();
            });
        });
    </script>
</head>
<body>
    <c:import url="/WEB-INF/views/header.jsp"/>

    <div>
        <form id="updateForm">
            <div class="form-group">
                <input type="text" class="form-control" id="empNo" name="empNo" placeholder="사번" value="${employee.empNo}" readonly>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="empName" name="empName" placeholder="이름" value="${employee.empName}" required>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="empNameEn" name="empNameEn" placeholder="영문명" value="${employee.empNameEn}" required>
            </div>
            <div class="form-group">
                <select class="form-control" id="gender" name="gender" required>
                    <option value="M" ${employee.gender == 'M'}>남성</option>
                    <option value="F" ${employee.gender == 'M'}>여성</option>
                </select>
            </div>
            <div class="form-group">
                <select class="form-control" id="dept" name="dept" required>
                    <c:forEach var="dept" items="${departments}">
                        <option value="${dept.deptCode}" ${dept.deptCode == employee.deptCode ? 'selected' : ''}>${dept.deptName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <select class="form-control" id="job" name="job" required>
                    <c:forEach var="job" items="${jobs}">
                        <option value="${job.jobCode}" ${job.jobCode == employee.jobCode ? 'selected' : ''}>${job.jobName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <select class="form-control" id="position" name="position" required>
                    <c:forEach var="position" items="${positions}">
                        <option value="${position.positionCode}" ${position.positionCode == employee.positionCode ? 'selected' : ''}>${position.positionName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <input type="email" class="form-control" id="email" name="email" placeholder="이메일" value="${employee.email}" required>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="phone" name="phone" placeholder="휴대폰" value="${employee.phone}" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-lg btn-primary btn-block">수정</button>
            </div>
        </form>
    </div>
</body>
</html>
