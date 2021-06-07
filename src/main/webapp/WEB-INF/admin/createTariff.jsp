<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<html>
<head>
    <title>Admin</title>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/addTariff">
    <label> <fmt:message key="showAllTariff_jsp.table.id_service"/>
        <input type="text" name="id_service">
    </label>
    <label> <fmt:message key="table.name"/>
        <input type="text" name="nameTariff">
    </label>
    <label> <fmt:message key="showListTariff_jsp.table.cost"/>
        <input type="number" min="0.01" step="0.01" name="cost">
    </label>
    <input class="button" type="submit" value="<fmt:message key="create"/>">
</form>


<p><a href="${pageContext.request.contextPath}/adminBasis"><fmt:message key="admin_main"/></a></p>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="logout"/></a>

</body>
</html>