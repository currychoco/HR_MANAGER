<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: currychoco
  Date: 2022-11-18
  Time: 오후 4:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="/WEB-INF/views/layout/head.jsp"/>

    <script type="text/javascript">
        function createNewEmployee(){
            const dept = $("#dept option:selected").val();
            const job = $("#job option:selected").val();
            const position = $("#position option:selected").val();
            const empName = $("#empName").val();
            const empNameEn = $("#empNameEn").val();
            const gender = $("#gender option:selected").val();
            const email = $("#email").val();
            const phone = $("#phone").val();

            if(!dept || !job || !position || !empName || !empNameEn || !gender || !email || !phone) {
                alert("누락된 내용이 있습니다.");
                return;
            }

            $.ajax({
                url : "/employee/add",
                type : "POST",
                contentType : "application/json",
                data : JSON.stringify({
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
                if(response){
                    alert("생성완료");
                    location.reload();
                }else{
                    alert("생성실패");
                }
            }).fail(function(err){
                err.responseJSON && alert(err.responseJSON.message);
            });
        }

        $(document).ready(function() {
            $("#addForm").submit(function(e) {
                e.preventDefault();
                createNewEmployee();
            });
        });
    </script>
</head>
<body>
    <c:import url="/WEB-INF/views/header.jsp"/>
    <div class="container">
        <form id="addForm">
            <div class="form-group">
                <input type="text" class="form-control" id="empName" name="empName" placeholder="이름" required>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="empNameEn" name="empNameEn" placeholder="영문명" required>
            </div>
            <div class="form-group">
                <select class="form-control" id="gender" name="gender" required>
                    <option value="">성별</option>
                    <option value="M">남성</option>
                    <option value="F">여성</option>
                </select>
            </div>
            <div class="form-group">
                <select class="form-control" id="dept" name="dept" required>
                    <c:forEach var="dept" items="${departments}">
                        <option value="${dept.deptCode}">${dept.deptName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <select class="form-control" id="job" name="job" required>
                    <c:forEach var="job" items="${jobs}">
                        <option value="${job.jobCode}">${job.jobName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <select class="form-control" id="position" name="position" required>
                    <c:forEach var="position" items="${positions}">
                        <option value="${position.positionCode}">${position.positionName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <input type="email" class="form-control" id="email" name="email" placeholder="이메일" required>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="phone" name="phone" placeholder="휴대폰" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block">생성</button>
            </div>
        </form>
    </div>
</body>
</html>
