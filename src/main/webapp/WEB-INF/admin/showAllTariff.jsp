<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <title>Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>
<h1>Список тарифів</h1>

<table class="table table-striped" border="1" cellpadding="5" cellspacing="5">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col"><fmt:message key="table.name"/></th>
        <th scope="col"><fmt:message key="showAllTariff_jsp.table.id_service"/></th>
        <th scope="col"><fmt:message key="showListTariff_jsp.table.cost"/></th>
        <th scope="col"><fmt:message key="showAllTariff_jsp.table.id_service"/></th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="tariffList" type="java.util.List"--%>
    <c:forEach var="tariff" items="${tariffList}">
        <tr>
            <th scope="row"><c:out value="${tariff.id}"/></th>
            <td><c:out value="${tariff.nameTariff}"/></td>
            <td><c:out value="${tariff.idServices}"/></td>
            <td><c:out value="${tariff.cost}"/> <fmt:message key="currency"/></td>
            <td>
                <form action="${pageContext.request.contextPath}/deleteTariff" method="post">
                    <input type="number" hidden name="idTariff" value="${tariff.id}">
                    <input type="submit" value="<fmt:message key="showAllTariff_jsp.button.del_tariff"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>

<%--@elvariable id="currentPage" type="java.lang.Integer"--%>
<c:if test="${currentPage != 1}">
    <td><a href="${pageContext.request.contextPath}/showAllTariff?page=${currentPage - 1}"><fmt:message key="previous"/></a>
    </td>
</c:if>

<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <%--@elvariable id="noOfPages" type="java.lang.Integer"--%>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="${pageContext.request.contextPath}/showAllTariff?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="${pageContext.request.contextPath}/showAllTariff?page=${currentPage + 1}"><fmt:message key="next"/></a></td>
</c:if>

<br/>
<form action="${pageContext.request.contextPath}/addTariff" method="post">
    <input type="submit" value="<fmt:message key="showAllTariff_jsp.button.add_tariff"/>">
</form>
<br/>
<p><a href="${pageContext.request.contextPath}/adminBasis"><fmt:message key="admin_main"/></a></p>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="logout"/></a>

</body>
</html>