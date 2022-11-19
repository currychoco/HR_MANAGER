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
    <c:forEach items="${sessionScope.grant}" var="item">
      <tr>

        <c:if test="${item.equals('g000000')}">
          <li><a href="/manager/employee/add">신규 사원 추가</a></li>
        </c:if>
        <c:if test="${item.equals('g000002')}">
          <li><a href="/manager/employee/search">사원 검색</a></li>
        </c:if>

      </tr>
    </c:forEach>
  </ul>
</div>

