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
        function modifyDepartment(){
            const deptCode = $("#deptCode").val();
            const upperDeptCode = $("#upperDeptCode").val();
            const headEmpNo = $("#headEmpNo").val();
            const deptName = $("#deptName").val();
            const deptNameEn = $("#deptNameEn").val();
            const deptLevel = $("#deptLevel").val();

            if(!deptCode || !deptName || !deptNameEn || !deptLevel) {
                alert("누락된 정보가 있습니다.");
                return;
            }

            $.ajax({
                url : "/department/modify",
                type : "POST",
                contentType : "application/json",
                data : JSON.stringify({
                    deptCode : deptCode,
                    upperDeptCode : upperDeptCode,
                    headEmpNo : headEmpNo,
                    deptName : deptName,
                    deptNameEn : deptNameEn,
                    deptLevel : deptLevel
                })
            }).done(function(response){
                alert("부서 정보 수정이 완료되었습니다 :)");
            }).fail(function(err){
                err.responseJSON && alert(err.responseJSON.message);
            });
        }

        $(document).ready(function() {
            $("#updateForm").submit(function(e) {
                e.preventDefault();

                if(confirm("수정하시겠습니까?")){
                    modifyDepartment();
                }

            });
        });
    </script>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp"/>

<div class="container body-container">
    <form id="updateForm">
        <div class="form-group">
            <input type="text" class="form-control" id="deptCode" name="deptCode" placeholder="부서코드" value="${department.deptCode}" readonly>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="deptName" name="deptName" placeholder="부서한글명" value="${department.deptName}" required>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="deptNameEn" name="deptNameEn" placeholder="부서영문명" value="${department.deptNameEn}" required>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="upperDeptCode" name="upperDeptCode" placeholder="상위부서" value="${department.upperDeptCode}">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="headEmpNo" name="headEmpNo" placeholder="부서장사번" value="${department.headEmpNo}">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="deptLevel" name="deptLevel" placeholder="부서레벨" value="${department.deptLevel}" required>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-lg btn-primary btn-block">수정</button>
        </div>
    </form>
</div>
<c:import url="/WEB-INF/views/footer.jsp"/>
</body>
</html>
