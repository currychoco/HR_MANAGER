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

      const data = $("#nameOrPositionCode").val();

      if(!data) {
        alert("직급명이나 직급코드를 입력해 주세요");
        return;
      }

      $.ajax({
        url : "/position/search",
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
          htmlText = `<tr><td colspan="2" class="text-center">검색된 직급이 없습니다</td></tr>`;
        } else {

          list.forEach(e=>{
            htmlText += `
              <tr>
                  <td><a href="/manager/position/detail?positionCode=\${e.positionCode}">\${e.positionCode}</a></td>
                  <td>\${e.positionName}</td>
              </tr>
          `;
          });
        }

        $('#positionInfoBody').html(htmlText);
      }).fail(function(err){
        console.log(err);
      });
    }

    $(document).ready(function (){
      $("#nameOrPositionCode").keyup(function(key) {
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
  <div class="form-inline">
    <input type="text" class="form-control" id="nameOrPositionCode" name="nameOrPositionCode" placeholder="직책명 or 직책코드" />
    <button type="button" class="btn btn-primary" onclick="search()">검색</button>
  </div>
  <div>
    <table class="table table-hover">
      <colgroup>
        <col style="width:50%" />
        <col style="width:50%" />
      </colgroup>
      <thead>
      <tr>
        <th>직책코드</th>
        <th>직책명</th>
      </tr>
      </thead>
      <tbody id="positionInfoBody">
      <!-- search() 의 데이터가 들어갑니다 -->
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
