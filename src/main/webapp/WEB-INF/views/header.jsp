<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: currychoco
  Date: 2022-11-15
  Time: 오전 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String id = (String)session.getAttribute("id");
    List<String> grant = (List<String>) session.getAttribute("grant");
%>

<!--TODO : EL태그로 모두 변환시키기!-->

<header>
    <a href="/"><h1>회사명</h1></a>
    <c:choose>
        <c:when test="${id == null}">
            <a href="/login">로그인</a>
            <a href="/join">회원가입</a>
        </c:when>
        <c:otherwise>
            <p><b><%=id%></b>님 안녕하세요!</p>
            <button onclick="logout()">로그아웃</button>
        </c:otherwise>
    </c:choose>
</header>
<nav>
    <ul>
        <li><a href="/englishname">영문명 추천</a></li>
        <li><a href="/businesscard">명함 신청</a></li>
        <li><a href="/empinfo">회원 정보</a></li>
        <c:if test="${grant.size() != 0 && grant!=null}">
            <li><a href="/manager">관리자 페이지</a></li>
        </c:if>
    </ul>
</nav>