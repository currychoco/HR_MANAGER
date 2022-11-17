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
    <title>Title</title>
    <c:import url="/WEB-INF/views/layout/head.jsp"/>
    <script type="text/javascript">
        function recommend() {
            const name = $("#name").val();
            $.ajax({
                type : "GET",
                url : "/englishname/recommand",
                contentType : "application/json",
                data : {
                    korName : name
                }
            }).done(function(response){
                console.log(response.aItems);
                let list = response.aItems;
                let htmlText = "";

                htmlText += `
                    <table>
                        <thead>
                            <tr>
                                <th>영문명</th>
                                <th>사용 빈도</th>
                            </tr>
                        </thead>
                        <tbody>
                `;

                list.forEach(e => {
                    htmlText += `
                        <tr>
                            <td>\${e.name}</td>
                            <td>\${e.score}</td>
                        </tr>
                    `;
                });

                htmlText += `
                        </tbody>
                    </table>
                `;

                $('.recommendedEnglishName').append(htmlText);
            }).fail(function(err){
                console.log(err);
            });
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
  <c:import url="/WEB-INF/views/header.jsp"/>
  <section>
      <div>
          <input type="text" id="name" name = "name" placeholder="이름"/>
          <button onclick="recommend()">추천</button>
      </div>
      <div class = "recommendedEnglishName"></div>

  </section>
</body>
</html>
