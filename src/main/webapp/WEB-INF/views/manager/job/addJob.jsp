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
    function createJob(){
      const jobCode = $("#jobCode").val();
      const jobName = $("#jobName").val();
      const jobNameEn = $("#jobNameEn").val();
      const jobLevel = $("#jobLevel").val();

      if(!jobCode || !jobNameEn || !jobName || !jobLevel){
        alert("누락된 내용이 있습니다.");
        return;
      }

      $.ajax({
        url : "/job/add",
        type : "POST",
        contentType : "application/json",
        data : JSON.stringify({
          jobCode : jobCode,
          jobName : jobName,
          jobNameEn : jobNameEn,
          jobLevel : jobLevel
        })
      }).done(function(response){
        alert("직책 생성 성공")
        location.reload();
      }).fail(function(err){
        err.responseJSON && alert(err.responseJSON.message);
      });
    }

    $(document).ready(function() {
      $("#addForm").submit(function(e) {
        e.preventDefault();
        createJob();
      });
    });
  </script>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp"/>
<div>
  <form class="container" id="addForm">
    <div class="form-group">
      <input type="text" class="form-control" id="jobCode" name="jobCode" placeholder="직책코드" required>
    </div>
    <div class="form-group">
      <input type="text" class="form-control" id="jobName" name="jobName" placeholder="직책한글명" required>
    </div>
    <div class="form-group">
      <input type="text" class="form-control" id="jobNameEn" name="jobNameEn" placeholder="직책영문명">
    </div>
    <div class="form-group">
      <input type="text" class="form-control" id="jobLevel" name="jobLevel" placeholder="직책레벨" required>
    </div>
    <div class="form-group">
      <button type="submit" class="btn btn-lg btn-primary btn-block">직책생성</button>
    </div>
  </form>
</div>
</body>
</html>
