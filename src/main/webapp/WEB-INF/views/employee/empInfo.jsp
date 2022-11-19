<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: currychoco
  Date: 2022-11-17
  Time: 오후 3:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="/WEB-INF/views/layout/head.jsp"/>
    <script type="text/javascript">
        $.ajax({
            url : "/get/employee/all/info",
            type : "GET",
            dataType : "json",
        }).done(function(response){
            const emp = response;
            let htmlText=`
                    <tr>
                        <th>사번</th>
                        <td>\${emp.empNo}</td>
                    </tr>
                    <tr>
                        <th>이름</th>
                         <td>\${emp.empName}</td>
                    </tr>
                    <tr>
                        <th>영문명</th>
                         <td>\${emp.empNameEn}</td>
                    </tr>
                    <tr>
                        <th>부서</th>
                        <td>\${emp.deptName} </td>
                    </tr>
                    <tr>
                        <th>직책</th>
                          <td>\${emp.jobName}</td>
                    </tr>
                    <tr>
                        <th>직위</th>
                         <td>\${emp.positionName}</td>
                    </tr>
                    <tr>
                        <th>성별</th>
                        <td>\${emp.gender}</td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td>\${emp.email}</td>
                    </tr>
                    <tr>
                        <th>휴대폰 번호</th>
                         <td>\${emp.phone}</td>
                    </tr>
                    <tr>
                        <th>입사일</th>
                        <td>\${emp.startDate}</td>
                    </tr>
                    <tr>
                        <th>아이디</th>
                        <td>\${emp.id}</td>
                    </tr>
            `;

            $('#empInfo').append(htmlText);

        }).fail(function(err){
            console.log(err);
        });
    </script>
</head>
<body>
    <c:import url="/WEB-INF/views/header.jsp"/>
    <div class="container">
        <table class="table table-hover">
            <tbody id="empInfo"></tbody>
        </table>
    </div>
</body>
</html>
