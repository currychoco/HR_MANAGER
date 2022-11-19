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

<!--TODO : java 에서 처리하기... ㅠ-->

<header>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">사과회사</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="/englishname">영문명 추천</a></li>
                <li><a href="/businesscard">명함 신청</a></li>
                <li><a href="/empinfo">회원 정보</a></li>
                <c:if test="${grant.size() != 0 && grant!=null}">
                <li class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                        관리자 페이지<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <c:forEach items="${sessionScope.grant}" var="item">
                            <c:if test="${item.equals('g000000')}">
                                <li><a href="/manager/employee/add">신규 사원 추가</a></li>
                            </c:if>
                            <c:if test="${item.equals('g000002')}">
                                <li><a href="/manager/employee/search">사원 검색</a></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </li>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${id == null}">
                        <li><a href="/join"><span class="glyphicon glyphicon-user"></span> 회원가입</a></li>
                        <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> 로그인</a></li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <p style="margin-top:13px;">Hello, <b><%=id%></b></p>
                        </li>
                        <li><a href="javascript:logout()"><span class="glyphicon glyphicon-log-out"></span> 로그아웃</a></li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </nav>

</header>