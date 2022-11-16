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
</head>
<body>
    <c:import url="/WEB-INF/views/header.jsp"/>
    <section>

        매니저 페이지에는 기본 정보만 출력하고, -> 처음 페이지 이동하면 보인 개인정보 보이게
        인사 정보 수정권한 있는 사람만 수정 버튼 눌러서 수정할 수 있게, -> 인사정보는 뭘 수정? 부서변경, 직책변경, 직급변경??? 각 정보마다 따로 권한을 둘까, 아니면 부서, 직책, 직급은 같은 권한으로 처리할까...
         ㄴ 이름 개명했을 때에는? 영어명 바꾸고 싶다고 하면?? -> 기본정보수정은 신청을 받아서 관리자가 수정하는 걸로...계속 개인이 바꾸면 문제 생길 수도 있으니까...
        연봉 정보는 열람 권한이 있는 사람만 볼 수 있게,
         ㄴ 연봉 수정은 수정 권한이 있는 사람만 수정할 수 있게,
        사원 추가 버튼은 해당 권한이 있는 사람만 볼 수 있게,

    </section>
</body>
</html>
