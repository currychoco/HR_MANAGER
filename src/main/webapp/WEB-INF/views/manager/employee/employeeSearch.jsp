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

            const data = $("#nameOrEmpNo").val();

            if(!data) {
                alert("사원명이나 사번을 입력해 주세요");
                return;
            }

            $.ajax({
                url : "/get/name/empno",
                type : "GET",
                contentType : "application/json",
                data : {
                    data : data
                }
            }).done(function(response){
                const list = response;
                let htmlText = "";
                if(response.length === 0) {
                    htmlText = `<tr><td colspan="5" class="text-center">검색된 사원이 없습니다</td></tr>`;
                } else {

                    list.forEach(e=>{
                        htmlText += `
                            <tr>
                                <td><a href="/manager/employee/detail?empNo=\${e.empNo}">\${e.empNo}</a></td>
                                <td>\${e.empName}</td>
                                <td>\${e.gender == 'M' ? "남" : "여"}</td>
                                <td>\${e.deptName}</td>
                                <td>\${e.positionName}</td>
                            </tr>
                        `;
                    });
                }

                $('#empInfoBody').html(htmlText);
            }).fail(function(err){
                err.responseJSON && alert(err.responseJSON.message);
            });
        }

        $(document).ready(function (){
            $("#nameOrEmpNo").keyup(function(key) {
                if(key.keyCode === 13) {
                    search();
                }
            });
        });
    </script>
</head>
<body>
    <c:import url="/WEB-INF/views/header.jsp"/>
    <div class="container body-container">
        <h3 class="title">사원 관리</h3>
        <div class="text-center">
            <form class="form-inline">
                <input type="text" class="form-control" id = "nameOrEmpNo" name="nameOrEmpNo" placeholder="이름 or 사번" />
                <button type="button" class="btn btn-primary" onclick="search()">검색</button>
            </form>
        </div>
        <div>
            <table class="table table-hover">
                <colgroup>
                    <col style="width:10%" />
                    <col style="width:20%" />
                    <col style="width:10%" />
                    <col style="width:40%" />
                    <col style="width:20%" />
                </colgroup>
                <thead>
                    <tr>
                        <th>사번</th>
                        <th>이름</th>
                        <th>성별</th>
                        <th>부서</th>
                        <th>직책</th>
                    </tr>
                </thead>
                <tbody id="empInfoBody">
                    <!-- search() 의 데이터가 들어갑니다 -->
                </tbody>
            </table>
        </div>
    </div>
    <c:import url="/WEB-INF/views/footer.jsp"/>
</body>
</html>
