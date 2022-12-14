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
    <c:import url="/WEB-INF/views/layout/head.jsp"/>

    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <script type="text/javascript">
        function search(){
            new daum.Postcode({
                oncomplete: function(data) {
                    console.log(data);
                    $("#address1").val(data.address);
                    $("#zipCode").val(data.zonecode);
                }
            }).open();
        }


        function createNewEmployee(){
            const dept = $("#dept option:selected").val();
            const job = $("#job option:selected").val();
            const position = $("#position option:selected").val();
            const empName = $("#empName").val();
            const empNameEn = $("#empNameEn").val();
            const gender = $("#gender option:selected").val();
            const email = $("#email").val();
            const phone = $("#phone").val();
            const address1 = $("#address1").val();
            const address2 = $("#address2").val();
            const zipCode = $("#zipCode").val();

            if(!dept || !job || !position || !empName || !empNameEn || !gender || !email || !phone || !address1 || !address2 || !zipCode) {
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
                    phone : phone,
                    address1 : address1,
                    address2 : address2,
                    zipCode : zipCode
                })
            }).done(function(response){
                alert("생성완료");
                location.reload();
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
    <div class="container body-container">
        <h3 class="title">사원 관리</h3>
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
            <div class="form-inline" style="margin-bottom: 15px">
                <input type="text" class="form-control" id="zipCode" name="zipCode" placeholder="우편번호" required readonly>
                <button type="button" class="btn" onclick="search()">주소검색</button>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="address1" name="address1" placeholder="주소" required readonly>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="address2" name="address2" placeholder="상세주소" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block">생성</button>
            </div>
        </form>
    </div>
    <c:import url="/WEB-INF/views/footer.jsp"/>
</body>
</html>
