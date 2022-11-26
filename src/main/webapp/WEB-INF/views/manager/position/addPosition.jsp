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
    <script>
        function createPosition(){
            const positionCode = $("#positionCode").val();
            const positionName = $("#positionName").val();
            const positionNameEn = $("#positionNameEn").val();
            const positionLevel = $("#positionLevel").val();

            if(!positionCode || !positionName || !positionNameEn || !positionLevel){
                alert("누락된 내용이 있습니다.");
                return;
            }

            $.ajax({
                url : "/position/add",
                type : "POST",
                contentType : "application/json",
                data : JSON.stringify({
                    positionCode : positionCode,
                    positionName : positionName,
                    positionNameEn : positionNameEn,
                    positionLevel : positionLevel
                })
            }).done(function(response){
                alert("직급 생성 성공")
                location.reload();
            }).fail(function(err){
                console.log(err);
                err.responseJSON && alert(err.responseJSON.message);
            });
        }

        $(document).ready(function() {
            $("#addForm").submit(function(e) {
                e.preventDefault();
                createPosition();
            });
        });
    </script>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp"/>
<div>
    <form class="container body-container" id="addForm">
        <div class="form-group">
            <input type="text" class="form-control" id="positionCode" name="positionCode" placeholder="직급코드" required>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="positionName" name="positionName" placeholder="직급한글명" required>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="positionNameEn" name="positionNameEn" placeholder="직급영문명">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="positionLevel" name="positionLevel" placeholder="직급레벨" required>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-lg btn-primary btn-block">직급생성</button>
        </div>
    </form>
</div>
<c:import url="/WEB-INF/views/footer.jsp"/>
</body>
</html>
