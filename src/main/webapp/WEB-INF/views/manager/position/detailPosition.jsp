<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: currychoco
  Date: 2022-11-19
  Time: 오후 3:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="/WEB-INF/views/layout/head.jsp"/>
    <script type="text/javascript">
        function modifyPosition(){
            const positionCode = $("#positionCode").val();
            const positionName = $("#positionName").val();
            const positionNameEn = $("#positionNameEn").val();
            const positionLevel = $("#positionLevel").val();

            if(!positionCode || !positionName || !positionNameEn || !positionLevel) {
                alert("누락된 정보가 있습니다.");
                return;
            }

            $.ajax({
                url : "/position/modify",
                type : "POST",
                contentType : "application/json",
                data : JSON.stringify({
                    positionCode : positionCode,
                    positionName : positionName,
                    positionNameEn : positionNameEn,
                    positionLevel : positionLevel
                })
            }).done(function(response){
                alert("직급 정보 수정이 완료되었습니다 :)");
            }).fail(function(err){
                err.responseJSON && alert(err.responseJSON.message);
            });
        }

        $(document).ready(function() {
            $("#updateForm").submit(function(e) {
                e.preventDefault();

                if(confirm("수정하시겠습니까?")){
                    modifyPosition();
                }

            });
        });
    </script>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp"/>

<div class="container">
    <form id="updateForm">
        <div class="form-group">
            <input type="text" class="form-control" id="positionCode" name="positionCode" placeholder="직급코드" value="${position.positionCode}" readonly>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="positionName" name="positionName" placeholder="직급한글명" value="${position.positionName}" required>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="positionNameEn" name="positionNameEn" placeholder="직급영문명" value="${position.positionNameEn}" required>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="positionLevel" name="positionLevel" placeholder="직급레벨" value="${position.positionLevel}">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-lg btn-primary btn-block">수정</button>
        </div>
    </form>
</div>
</body>
</html>
