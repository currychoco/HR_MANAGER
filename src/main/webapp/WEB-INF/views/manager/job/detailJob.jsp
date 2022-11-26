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
    <c:import url="/WEB-INF/views/layout/head.jsp"/>
    <script type="text/javascript">
        function modifyJob(){
            const jobCode = $("#jobCode").val();
            const jobName = $("#jobName").val();
            const jobNameEn = $("#jobNameEn").val();
            const jobLevel = $("#jobLevel").val();

            if(!jobCode || !jobName || !jobNameEn || !jobLevel) {
                alert("누락된 정보가 있습니다.");
                return;
            }

            $.ajax({
                url : "/job/modify",
                type : "POST",
                contentType : "application/json",
                data : JSON.stringify({
                    jobCode : jobCode,
                    jobName : jobName,
                    jobNameEn : jobNameEn,
                    jobLevel : jobLevel
                })
            }).done(function(response){
                alert("직책 정보 수정이 완료되었습니다 :)");
            }).fail(function(err){
                err.responseJSON && alert(err.responseJSON.message);
            });
        }

        $(document).ready(function() {
            $("#updateForm").submit(function(e) {
                e.preventDefault();

                if(confirm("수정하시겠습니까?")){
                    modifyJob();
                }

            });
        });
    </script>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp"/>

<div class="container body-container">
    <form id="updateForm">
        <div class="form-group">
            <input type="text" class="form-control" id="jobCode" name="jobCode" placeholder="직책코드" value="${job.jobCode}" readonly>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="jobName" name="jobName" placeholder="직책한글명" value="${job.jobName}" required>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="jobNameEn" name="jobNameEn" placeholder="직책영문명" value="${job.jobNameEn}" required>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="jobLevel" name="jobLevel" placeholder="직책레벨" value="${job.jobLevel}">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-lg btn-primary btn-block">수정</button>
        </div>
    </form>
</div>
<c:import url="/WEB-INF/views/footer.jsp"/>
</body>
</html>
