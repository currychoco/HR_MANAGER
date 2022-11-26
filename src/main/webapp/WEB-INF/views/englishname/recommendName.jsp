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
        recommend()

        function recommend() {
            const name = '<c:out value="${param.empName}"/>';

            $.ajax({
                type : "GET",
                url : "/englishname/recommand",
                contentType : "application/json",
                data : {
                    korName : name
                }
            }).done(function(response){
                console.log(response);
                let list = response.aItems;
                let htmlText = "";

                list.forEach(e => {
                    htmlText += `
                        <tr>
                            <td><a href="javascript:select('\${e.name}')">\${e.name}</a></td>
                            <td>\${e.score}</td>
                        </tr>
                    `;
                });

                $('#tableBody').html(htmlText);
            }).fail(function(err){
                console.log(err);
            });
        }

        function select(name){
            window.opener.document.getElementById("empNameEn").value= name;
            close();
        }

        $(document).ready(function (){
            $("#name").keyup(function(key) {
                if(key.keyCode === 13) {
                    recommend();
                }
            });
        });
    </script>
</head>
<body>
    <div class="container text-center">
        <div>
            <table class="table table-hover">
                <colgroup>
                    <col style="width:70%" />
                    <col style="width:30%" />
                </colgroup>
                <thead>
                    <tr>
                        <th>영문명</th>
                        <th>사용 빈도</th>
                    </tr>
                </thead>
                <tbody id="tableBody">
                    <!-- recommend() 의 데이터가 추가됩니다 -->
                </tbody>
            </table>
        </div>
        <div class = "recommendedEnglishName"></div>
    </div>
</body>
</html>
