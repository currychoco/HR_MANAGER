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
      const empName = $("#empName").val();
      const empNameEn = $("#empNameEn").val();
      const gender = $("#gender option:selected").val();
      const email = $("#email").val();
      const phone = $("#phone").val();

      if(!empName || !empNameEn || !gender || !email || !phone) {
        alert("누락된 정보가 있습니다.");
        return;
      }

      const originalEmpName = '<c:out value = "${employee.empName}"/>';
      const originalEmpNameEn = '<c:out value = "${employee.empNameEn}"/>';
      const originalGender ='<c:out value = "${employee.gender}"/>';
      const originalEmail = '<c:out value = "${employee.email}"/>';
      const originalPhone = '<c:out value = "${employee.phone}"/>';

      if(empName === originalEmpName && empNameEn === originalEmpNameEn && gender === originalGender
      && email === originalEmail && phone === originalPhone){
        alert("기존 정보와 같습니다.");
        return;
      }

      $.ajax({
        url : "/my-info/create",
        type : "POST",
        contentType : "application/json",
        data : JSON.stringify({
          empNo : empNo,
          empName : empName,
          empNameEn : empNameEn,
          gender : gender,
          email : email,
          phone : phone
        })
      }).done(function(response){
        console.log(response);
        alert("신청이 완료되었습니다")
      }).fail(function(err){
        console.log(err);
      });
    }

    function openEnNameRecommend() {
      window.name = "parentForm";
      window.open("/englishname?empName=${employee.empName}", "enName", "width=600, height=400");
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
  <form id="updateForm">
    <div class="form-group">
      <input type="hidden" id="empNo" name="empNo" value="${employee.empNo}">
      <input type="text" class="form-control" id="empName" name="empName" placeholder="이름" value="${employee.empName}" required >
    </div>
    <div class="form-group form-inline">
      <input type="text" class="form-control" id="empNameEn" name="empNameEn" placeholder="영문명" value="${employee.empNameEn}" required>
      <button type="button" class="btn btn-primary" onclick="openEnNameRecommend()">영문명 추천</button>
    </div>
    <div class="form-group">
      <select class="form-control" id="gender" name="gender" required>
        <option value="M" <c:if test="${employee.gender == 'M'}">selected</c:if>>남성</option>
        <option value="F" <c:if test="${employee.gender == 'F'}">selected</c:if>>여성</option>
      </select>
    </div>
    <div class="form-group">
      <input type="email" class="form-control" id="email" name="email" placeholder="이메일" value="${employee.email}" required>
    </div>
    <div class="form-group">
      <input type="text" class="form-control" id="phone" name="phone" placeholder="휴대폰" value="${employee.phone}" required>
    </div>
    <div class="form-group">
      <button type="submit" class="btn btn-primary btn-block">수정요청</button>
    </div>
  </form>
</div>
<c:import url="/WEB-INF/views/footer.jsp"/>
</body>
</html>
