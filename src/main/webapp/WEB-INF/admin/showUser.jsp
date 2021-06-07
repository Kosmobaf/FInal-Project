<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <title>Create User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>
<h3>
    <c:choose>
        <c:when test="${userOrderList.size() > 0}">
            <fmt:message key="userBasis_jsp.header.ordered"/><br/>
        </c:when>
        <c:otherwise>
            <fmt:message key="userBasis_jsp.header.unordered"/>
        </c:otherwise>
    </c:choose>
</h3>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="showUser_jsp.table.service"/></th>
        <th scope="col"><fmt:message key="showUser_jsp.table.tariff"/></th>
        <th scope="col"><fmt:message key="showUser_jsp.table.status"/></th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="userOrderList" type="java.util.List"--%>
    <c:forEach var="tariff" items="${userOrderList}">
        <tr>
            <th scope="row"><c:out value="${ tariff .nameService}"/></th>
            <td><c:out value="${ tariff .nameTariff}"/></td>
            <td><c:out value="${ tariff .status}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<%--@elvariable id="user" type="com.model.entity.User"--%>
<fmt:message key="showUser_jsp.header.status"/> - ${user.status.name()}
<br/>

<c:choose>
    <c:when test="${user.status.name() == 'blocked'}">
        <form action="${pageContext.request.contextPath}/changeStatusUser" method="post">
            <input type="number" hidden name="idUser" value="${user.id}">
            <input type="submit" value="<fmt:message key="showUser_jsp.button.activate"/>">
        </form>
    </c:when>
    <c:otherwise>
        <form action="${pageContext.request.contextPath}/changeStatusUser" method="post">
            <input type="number" hidden name="idUser" value="${user.id}">
            <input type="submit" value="<fmt:message key="showUser_jsp.button.block"/>">
        </form>
    </c:otherwise>
</c:choose>
<br/>

<p><a href="${pageContext.request.contextPath}/adminBasis"><fmt:message key="admin_main"/></a></p>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="logout"/></a>

</body>
</html>