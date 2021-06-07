<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <title>USER</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>

<h1><fmt:message key="showListServices_jsp.header.list_services"/></h1>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="table.name"/></th>
        <th scope="col"><fmt:message key="choose"/></th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="serviceList" type="java.util.List"--%>
    <c:forEach var="service" items="${serviceList}">
        <tr>
            <th scope="row"><c:out value="${service.nameService}"/></th>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/getAllTariffList">
                    <input type="number" hidden name="idService" value="${service.id}">
                    <input type="submit" name="service" value="<fmt:message key="choose"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br/>
<a href="${pageContext.request.contextPath}/userBasis "><fmt:message key="user_main"/></a><br/>
<a href="${pageContext.request.contextPath}/logout "><fmt:message key="logout"/></a>
</body>
</html>