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
  <script type="text/javascript">
  function searchEmployee() {
    const data = $("#empName").val();

    $.ajax({
      url: "/get/name/empno",
      type: "GET",
      contentType: "application/json",
      data: {
        data: data
      }
    }).done(function (response) {
      const list = response;
      let htmlText = "";
      if (response.length === 0) {
        htmlText = `<tr><td colspan="5" class="text-center">검색된 사원이 없습니다</td></tr>`;
      } else {

        list.forEach(e => {
          htmlText += `
                <tr>
                    <td>\${e.empNo}</td>
                    <td>\${e.empName}</td>
                    <td>\${e.gender == 'M' ? "남" : "여"}</td>
                    <td>\${e.deptName}</td>
                    <td>\${e.positionName}</td>
                    <td><button type="button" class="btn btn-basic btn-authority" data-emp-no="\${e.empNo}" onclick="selectEmployee(\${e.empNo})">선택</button></td>
                </tr>
            `;
        });
      }

      $('#empInfoBody').html(htmlText);
    }).fail(function (err) {
      console.log(err);
    });
  }

  function grantAuthority(){

    const empNo = $("#empNo").val();
    const authCode = $("#authority option:selected").val();
    const empName = $("#empName").val();
    const authName = $("#authority option:selected").text();

    console.log(empNo);

    if(confirm(empName + "(" + empNo + ")에게 " + authName + "권한을 부여하겠습니까?")){
      $.ajax({
        url : "/account-authority/add",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
          empNo : empNo,
          authCode : authCode
        })
      }).done(function(response){
        alert("권한부여 완료:)");
      }).fail(function(err){
        err.responseJSON && alert(err.responseJSON.message);
      });
    }
  }

  function selectEmployee(empNo){

    $.ajax({
      url : "/account/check-is-id",
      type: "GET",
      contentType: "application/json",
      data: {
        empNo : empNo,
      }
    }).done(function(response){
      if(response){
        $("#empNo").val(empNo);
        $("#employee").val(empNo);

        $(".btn-authority").removeClass("btn-primary").addClass("btn-basic");
        $("button[data-emp-no=" + empNo + "]").removeClass("btn-basic").addClass("btn-primary");
      }else{
        alert("회원가입이 안 된 사원입니다");
      }
    }).fail(function(err){
      err.responseJSON && alert(err.responseJSON.message);
    });
  }

  $(document).ready(function() {
    $("#addForm").submit(function(e) {
      e.preventDefault();
      grantAuthority();
    });

  });

  </script>
</head>
<body>
  <c:import url="/WEB-INF/views/header.jsp"/>

  <div class="container body-container">
    <h3 class="title">권한 관리</h3>
    <form id="addForm">
      <div class="form-inline">
        <div class="form-group">
          <input type="text" class="form-control" id="empName" name="empName" placeholder="사번 or 사원명" required>
          <button type="button" class="btn btn-primary" onclick="searchEmployee()">검색</button>
        </div>
      </div>
      <div class="form-group">
        <table class="table">
          <thead id="empInfoHead">
          <tr>
            <th>사번</th>
            <th>이름</th>
            <th>성별</th>
            <th>부서</th>
            <th>직책</th>
          </tr>
          </thead>
          <tbody id="empInfoBody">
          <!--검색된 사원 리스트 출력-->
          </tbody>
        </table>
      </div>
      <div class="form-group">
        <select class="form-control" id="authority" name="authority">
          <c:forEach items="${authList}" var="item">
            <option value="${item.authCode}">${item.authName}</option>
          </c:forEach>
        </select>
        <input type="hidden" id="empNo" value="">
      </div>
      <div class="form-group">
        <button type="submit" class="btn btn-primary btn-block">권한부여</button>
      </div>
    </form>
  </div>
  <c:import url="/WEB-INF/views/footer.jsp"/>
</body>
</html>
