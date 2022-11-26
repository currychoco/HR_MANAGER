<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: currychoco
  Date: 2022-11-16
  Time: 오후 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <c:import url="/WEB-INF/views/layout/head.jsp"/>
  <script type="text/javascript">
    function search(){

      const data = $("#nameOrDeptCode").val();

      if(!data) {
        alert("사원명이나 사번을 입력해 주세요");
        return;
      }

      $.ajax({
        url : "/department/search",
        type : "GET",
        contentType : "application/json",
        data : {
          data : data
        }
      }).done(function(response){

        console.log(response);

        const list = response;
        let htmlText = "";
        if(response.length === 0) {
          htmlText = `<tr><td colspan="2" class="text-center">검색된 부서가 없습니다</td></tr>`;
        } else {

          list.forEach(e=>{
            htmlText += `
              <tr>
                  <td><a href="/manager/department/detail?deptCode=\${e.deptCode}">\${e.deptCode}</a></td>
                  <td>\${e.deptName}</td>
              </tr>
          `;
          });
        }

        $('#deptInfoBody').html(htmlText);
      }).fail(function(err){
        err.responseJSON && alert(err.responseJSON.message);
      });
    }

    $(document).ready(function (){
      $("#nameOrDeptCode").keyup(function(key) {
        if(key.keyCode === 13) {
          search();
        }
      });
    });
  </script>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp"/>
<div class="container text-center">
  <form class="form-inline">
    <input type="text" class="form-control" id = "nameOrDeptCode" name="nameOrDeptCode" placeholder="부서명 or 부서코드" />
    <button type="button" class="btn btn-primary" onclick="search()">검색</button>
  </form>
  <div>
    <table class="table table-hover">
      <colgroup>
        <col style="width:50%" />
        <col style="width:50%" />

      </colgroup>
      <thead>
      <tr>
        <th>부서코드</th>
        <th>부서명</th>
      </tr>
      </thead>
      <tbody id="deptInfoBody">
      <!-- search() 의 데이터가 들어갑니다 -->
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
