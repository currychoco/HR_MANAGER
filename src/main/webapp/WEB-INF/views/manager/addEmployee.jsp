<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: currychoco
  Date: 2022-11-18
  Time: 오후 4:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="/WEB-INF/views/layout/head.jsp"/>
    <script type="text/javascript">
        $.ajax({
            url : "/department/all",
            type : "GET",
            contentType : "application/json",
        }).done(function(response){
            console.log(response);
        }).fail(function(err){
            console.log(err);
        });
    </script>
</head>
<body>
    <c:import url="/WEB-INF/views/header.jsp"/>
    <c:import url="/WEB-INF/views/layout/managerGrantNav.jsp"/>
    <section>
        <div class="insertForm"></div>
    </section>
</body>
</html>
