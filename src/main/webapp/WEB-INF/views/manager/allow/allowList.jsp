<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: currychoco
  Date: 2022-11-17
  Time: 오전 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <c:import url="/WEB-INF/views/layout/head.jsp"/>
  <script type="text/javascript">
    function getAllowList() {

      $.ajax({
        type : "GET",
        url : "/allow/list",
        contentType : "application/json",
      }).done(function(response){
        const list = response;
        let htmlText = "";

        list.forEach(e => {
          htmlText += `
              <tr>
                  <td>\${e.no}</td>
                  <td><a href="/manager/allow/detail?no=\${e.no}&empNo=\${e.empNo}">\${e.empNo}</a></td>
                  <td>\${e.empName}</td>
                  <td>\${e.allow}</td>
                  <td>\${e.requestDateToString}</td>
                  <td>\${e.allowDateToString}</td>
              </tr>
          `;
        });

        $('#tableBody').html(htmlText);
      }).fail(function(err){
        err.responseJSON && alert(err.responseJSON.message);
      });
    }

    $(document).ready(function (){
      getAllowList();
    });
  </script>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp"/>
<div class="container body-container text-center">
  <form class="form-inline">
    <input type="text" class="form-control" id="name" name = "name" placeholder="이름" />
    <button type="button" class="btn btn-primary" onclick="recommend()">추천</button>
  </form>
  <div>
    <table class="table table-hover">
      <thead>
      <tr>
        <th>번호</th>
        <th>사번</th>
        <th>한글명</th>
        <th>상태</th>
        <th>신청일</th>
        <th>승인일</th>
      </tr>
      </thead>
      <tbody id="tableBody">

      </tbody>
    </table>
  </div>
</div>
<c:import url="/WEB-INF/views/footer.jsp"/>
</body>
</html>
