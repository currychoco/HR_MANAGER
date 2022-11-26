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
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script type="text/javascript">

        function modifySalary(){
            const empNo = ${salary.empNo};
            const amount = $("#amount").val();

            if(!amount) {
                alert("누락된 정보가 있습니다.");
                return;
            }

            $.ajax({
                url : "/salary/modify",
                type : "POST",
                contentType : "application/json",
                data : JSON.stringify({
                    empNo : empNo,
                    amount : amount
                })
            }).done(function(response){
                alert("연봉 정보 수정이 완료되었습니다 :)");
            }).fail(function(err){
                err.responseJSON && alert(err.responseJSON.message);
            });
        }

        $(document).ready(function() {
            $("#updateForm").submit(function(e) {
                e.preventDefault();

                if(confirm("수정하시겠습니까?")){
                    modifySalary();
                }

            });
        });
    </script>
</head>
<body>

<div class="container" style="margin-top:30px;">
    <form id="updateForm">
        <div class="form-group">
            <input type="text" class="form-control" id="empNo" name="empNo" placeholder="사번" value="${salary.empNo}" readonly>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="empName" name="empName" placeholder="사원명" value="${employee.empName}" required>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="amount" name="amount" placeholder="연봉" value="${salary.amount}" required>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">수정</button>
        </div>
    </form>
</div>
</body>
</html>
