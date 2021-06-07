<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <title>ADMIN THE BASIS</title>

</head>
<body>

<h1><fmt:message key="adminBasis_jsp.header.main_page"/></h1>

<p> <a href="${pageContext.request.contextPath}/getAllUser"><fmt:message key="adminBasis_jsp.ref.show_all_user"/></a></p>
<p> <a href="${pageContext.request.contextPath}/showAllTariff"><fmt:message key="adminBasis_jsp.ref.show_all_tariff"/></a></p>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="logout"/></a>
</body>
</html>