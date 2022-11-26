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
    <c:import url="/WEB-INF/views/layout/head.jsp"/>
    <style>
        tbody th,td {
            padding: 20px 0px !important;
        }
    </style>
    <script type="text/javascript">
        let empNo;

        $.ajax({
            url : "/get/employee/all/info",
            type : "GET",
            dataType : "json",
        }).done(function(response){
            const emp = response;
            empNo = emp.empNo;
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
                        <td>\${emp.startDateToString}</td>
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

        function modifyOwnInfo(){
            location.href="/home/employee/modify?empNo=${empNo}";
        }
    </script>
</head>
<body>
    <c:import url="/WEB-INF/views/header.jsp"/>
    <div class="container body-container">
        <h3 class="title">나의인사정보</h3>
        <div class="form-group">
            <table class="table table-hover">
                <tbody id="empInfo"></tbody>
            </table>
        </div>
        <div class="form-group">
            <button class="btn btn-primary btn-block" onclick="modifyOwnInfo()">수정요청</button>
        </div>
    </div>
    <c:import url="/WEB-INF/views/footer.jsp"/>
</body>
</html>
