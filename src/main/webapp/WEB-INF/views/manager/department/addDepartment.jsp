<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: currychoco
  Date: 2022-11-20
  Time: 오후 4:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <c:import url="/WEB-INF/views/layout/head.jsp"/>
  <script>
    function createDepartment(){
      const deptCode = $("#deptCode").val();
      const upperDeptCode = $("#upperDeptCode").val();
      const headEmpNo = $("#headEmpNo").val();
      const deptName = $("#deptName").val();
      const deptNameEn = $("#deptNameEn").val();
      const deptLevel = $("#deptLevel").val();

      if(!deptCode || !upperDeptCode || !deptName || !deptNameEn || !deptLevel){
        alert("누락된 내용이 있습니다.");
        return;
      }

      $.ajax({
        url : "/department/add",
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
        alert("부서 생성 성공")
        location.reload();
      }).fail(function(err){
        console.log(err);
        err.responseJSON && alert(err.responseJSON.message);
      });
    }

    $(document).ready(function() {
      $("#addForm").submit(function(e) {
        e.preventDefault();
        createDepartment();
      });
    });
  </script>
</head>
<body>
  <c:import url="/WEB-INF/views/header.jsp"/>
  <div>
    <form class="container" id="addForm">
      <div class="form-group">
        <input type="text" class="form-control" id="deptCode" name="deptCode" placeholder="부서코드" required>
      </div>
      <div class="form-group">
        <input type="text" class="form-control" id="upperDeptCode" name="upperDeptCode" placeholder="상위부서코드" required>
      </div>
      <div class="form-group">
        <input type="text" class="form-control" id="headEmpNo" name="headEmpNo" placeholder="부서장사번">
      </div>
      <div class="form-group">
        <input type="text" class="form-control" id="deptName" name="deptName" placeholder="부서한글명" required>
      </div>
      <div class="form-group">
        <input type="text" class="form-control" id="deptNameEn" name="deptNameEn" placeholder="부서영문명" required>
      </div>
      <div class="form-group">
        <input type="text" class="form-control" id="deptLevel" name="deptLevel" placeholder="부서레벨" required>
      </div>
      <div class="form-group">
        <button type="submit" class="btn btn-lg btn-primary btn-block">부서생성</button>
      </div>
    </form>
  </div>
</body>
</html>
