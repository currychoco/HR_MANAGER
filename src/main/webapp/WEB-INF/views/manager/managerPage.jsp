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
            $('.empInfo').empty(); // 지우기
            const data = $("#nameOrEmpNo").val();

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
                if(response.length === 0){
                    htmlText=`해당 사원이 존재하지 않습니다.`;
                }else if(list.length === 1){
                    list.forEach(emp=>{
                        htmlText+=`
                            <table>
                                <tr>
                                    <th>사번</th>
                                    <td>\${emp.empNo}</td>
                                </tr>
                                    <th>이름</th>
                                     <td>\${emp.empName}</td>
                                </tr>
                                    <th>영문명</th>
                                     <td>\${emp.empNameEn}</td>
                                </tr>
                                    <th>부서</th>
                                    <td>\${emp.deptName} </td>
                                </tr>
                                    <th>직책</th>
                                      <td>\${emp.jobName}</td>
                                </tr>
                                    <th>직위</th>
                                     <td>\${emp.positionName}</td>
                                </tr>
                                    <th>성별</th>
                                    <td>\${emp.gender}</td>
                                </tr>
                                    <th>이메일</th>
                                    <td>\${emp.email}</td>
                                </tr>
                                    <th>휴대폰 번호</th>
                                     <td>\${emp.phone}</td>
                                </tr>
                                    <th>입사일</th>
                                    <td>\${emp.startDate}</td>
                                </tr>
                                    <th>아이디</th>
                                    <td>\${emp.id}</td>
                                </tr>
                            </table>
                        `;
                    })
                }else{
                    htmlText +=`
                        <table>
                            <thead>
                                <tr>
                                    <th>사번</th>
                                    <th>이름</th>
                                    <th>성별</th>
                                    <th>부서</th>
                                    <th>직책</th>
                                </tr>
                            </thead>
                             <tbody>
                    `;

                    list.forEach(e=>{
                        htmlText += `
                            <tr>
                                <td>\${e.empNo}</td>
                                <td>\${e.empName}</td>
                                <td>\${e.gender}</td>
                                <td>\${e.deptName}</td>
                                <td>\${e.positionName}</td>
                            </tr>
                        `;
                    });

                    htmlText +=`
                            </tbody>
                        </table>
                    `;
                }

                $('.empInfo').append(htmlText);
            }).fail(function(err){
                console.log(err);
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
    <section>
        <c:import url="/WEB-INF/views/layout/managerGrantNav.jsp"/>
        <div class="searchForm">
            <input type="text" id = "nameOrEmpNo" name="nameOrEmpNo" required>
            <button onclick="search()">검색</button>
        </div>
        <div class = "empInfo"></div>

    </section>
</body>
</html>
