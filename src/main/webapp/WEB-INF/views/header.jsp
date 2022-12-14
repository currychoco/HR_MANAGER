<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: currychoco
  Date: 2022-11-15
  Time: 오전 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    .dropdown-header {
        color: #337ab7;
    }
</style>

<header class="body-header">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/" style="padding: 0px 10px;">
                    <img src="/favicon.png" width="50" height="50" />
                </a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="/empinfo">내 정보</a></li>
                <li><a href="/business-card">명함 신청</a></li>
                <c:if test="${grant.size() != 0 && grant!=null}">
                <li class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                        관리자 페이지<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="dropdown-header">사원</li>
                        <c:if test="${sessionScope.grant.contains('g000002')}">
                            <li><a href="/manager/employee/search">사원 검색</a></li>
                        </c:if>
                        <c:if test="${sessionScope.grant.contains('g000000')}">
                            <li><a href="/manager/employee/add">사원 추가</a></li>
                        </c:if>
                        <li class="dropdown-header">부서</li>
                        <c:if test="${sessionScope.grant.contains('g000010')}">
                            <li><a href="/manager/department/search">부서 검색</a></li>
                        </c:if>
                        <c:if test="${sessionScope.grant.contains('g000003')}">
                            <li><a href="/manager/department/create">부서 생성</a></li>
                        </c:if>
                        <li class="dropdown-header">직책</li>
                        <c:if test="${sessionScope.grant.contains('g000011')}">
                            <li><a href="/manager/job/search">직책 검색</a></li>
                        </c:if>
                        <c:if test="${sessionScope.grant.contains('g000004')}">
                            <li><a href="/manager/job/create">직책 생성</a></li>
                        </c:if>
                        <li class="dropdown-header">직급</li>
                        <c:if test="${sessionScope.grant.contains('g000012')}">
                            <li><a href="/manager/position/search">직급 검색</a></li>
                        </c:if>
                        <c:if test="${sessionScope.grant.contains('g000005')}">
                            <li><a href="/manager/position/create">직급 생성</a></li>
                        </c:if>
                        <li class="dropdown-header">권한</li>
                        <c:if test="${sessionScope.grant.contains('g000009')}">
                            <li><a href="/manager/authority/grant">권한 부여</a></li>
                        </c:if>
                        <li class="dropdown-header">신청 승인</li>
                        <c:if test="${sessionScope.grant.contains('g000015')}">
                            <li><a href="/manager/allow/list">수정 신청 허가</a></li>
                        </c:if>
                        <c:if test="${sessionScope.grant.contains('g000016')}">
                            <li><a href="/manager/busi-card/allow">명함 신청 허가</a></li>
                        </c:if>
                    </ul>
                </li>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${sessionScope.id == null}">
                        <li><a href="/join"><span class="glyphicon glyphicon-user"></span> 회원가입</a></li>
                        <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> 로그인</a></li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <p style="margin-top:13px;">Hello, <b>${sessionScope.id}</b></p>
                        </li>
                        <li><a href="javascript:logout()"><span class="glyphicon glyphicon-log-out"></span> 로그아웃</a></li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </nav>

</header>