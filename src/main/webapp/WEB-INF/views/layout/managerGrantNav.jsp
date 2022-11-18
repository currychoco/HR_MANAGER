<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: currychoco
  Date: 2022-11-18
  Time: 오후 5:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="grantNav">
  <ul>
    <li><a href="/manager">사원 검색</a></li>
    <c:forEach items="${sessionScope.grant}" var="item">
      <tr>
        <c:choose>
          <c:when test="${item.equals('g000000')}">
            <li><a href="/add/employee">신규 사원 추가</a></li>
          </c:when>
          <c:when test="${item.equals('g000001')}">
            <li>사원 정보 수정</li>
          </c:when>
        </c:choose>
      </tr>
    </c:forEach>
  </ul>
</div>

