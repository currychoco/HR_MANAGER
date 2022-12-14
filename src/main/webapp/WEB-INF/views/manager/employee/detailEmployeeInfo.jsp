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
            const address1 = $("#address1").val();
            const address2 = $("#address2").val();
            const zipCode = $("#zipCode").val();

            if(!dept || !job || !position || !empName || !empNameEn || !gender || !email || !phone || !address1 || !address2 || !zipCode) {
                alert("누락된 정보가 있습니다.");
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
                    phone : phone,
                    address1 : address1,
                    address2 : address2,
                    zipCode : zipCode
                })
            }).done(function(response){
                alert("회원 정보 수정이 완료되었습니다 :)");
            }).fail(function(err){
                err.responseJSON && alert(err.responseJSON.message);
            });
        }

        function openSalaryPopup() {
            window.open("/manager/salary/detail?empNo=${employee.empNo}", "salary", "width=600, height=400");
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

    <div class="container body-container">
        <h3 class="title">사원 관리</h3>
        <form id="updateForm">
            <div class="form-group">
                <input type="text" class="form-control" id="empNo" name="empNo" placeholder="사번" value="${employee.empNo}" readonly>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="empName" name="empName" placeholder="이름" value="${employee.empName}" required <c:if test="${!sessionScope.grant.contains('g000001')}">readonly</c:if>>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="empNameEn" name="empNameEn" placeholder="영문명" value="${employee.empNameEn}" required <c:if test="${!sessionScope.grant.contains('g000001')}">readonly</c:if>>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="id" name="id" placeholder="아이디" value="${employee.id != null ? employee.id : '-'}" required readonly>
            </div>
            <div class="form-group">
                <select class="form-control" id="gender" name="gender" required <c:if test="${!sessionScope.grant.contains('g000001')}">readonly</c:if>>
                    <option value="M" <c:if test="${employee.gender == 'M'}">selected</c:if>>남성</option>
                    <option value="F" <c:if test="${employee.gender == 'F'}">selected</c:if>>여성</option>
                </select>
            </div>
            <div class="form-group">
                <select class="form-control" id="dept" name="dept" required <c:if test="${!sessionScope.grant.contains('g000001')}">readonly</c:if>>
                    <c:forEach var="dept" items="${departments}">
                        <option value="${dept.deptCode}" ${dept.deptCode == employee.deptCode ? 'selected' : ''}>${dept.deptName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <select class="form-control" id="job" name="job" required <c:if test="${!sessionScope.grant.contains('g000001')}">readonly</c:if>>
                    <c:forEach var="job" items="${jobs}">
                        <option value="${job.jobCode}" ${job.jobCode == employee.jobCode ? 'selected' : ''}>${job.jobName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <select class="form-control" id="position" name="position" required <c:if test="${!sessionScope.grant.contains('g000001')}">readonly</c:if>>
                    <c:forEach var="position" items="${positions}">
                        <option value="${position.positionCode}" ${position.positionCode == employee.positionCode ? 'selected' : ''}>${position.positionName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <input type="email" class="form-control" id="email" name="email" placeholder="이메일" value="${employee.email}" required <c:if test="${!sessionScope.grant.contains('g000001')}">readonly</c:if>>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="phone" name="phone" placeholder="휴대폰" value="${employee.phone}" required <c:if test="${!sessionScope.grant.contains('g000001')}">readonly</c:if>>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="zipCode" name="zipCode" placeholder="우편번호" value="${employee.zipCode}" required <c:if test="${!sessionScope.grant.contains('g000001')}">readonly</c:if>>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="address1" name="address1" placeholder="주소" value="${employee.address1}" required <c:if test="${!sessionScope.grant.contains('g000001')}">readonly</c:if>>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="address2" name="address2" placeholder="상세 주소" value="${employee.address2}" required <c:if test="${!sessionScope.grant.contains('g000001')}">readonly</c:if>>
            </div>
            <c:if test="${sessionScope.grant.contains('g000013')}">
                <div class="form-group" id="salary">
                    <button type="button" class="btn btn-basic btn-block" onclick="openSalaryPopup()">연봉정보 조회</button>
                </div>
            </c:if>
            <div class="form-group">
                <input type="hidden" id="salaryAmount" value="">
            </div>
            <c:if test="${sessionScope.grant.contains('g000001')}">
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">수정</button>
                </div>
            </c:if>
        </form>
    </div>
    <c:import url="/WEB-INF/views/footer.jsp"/>
</body>
</html>
