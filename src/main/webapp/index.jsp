<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Internet Provider</title>

</head>

<body>
<h1><fmt:message key="index_jsp.header.home_page"/></h1><br/>
<br/>
<h3><a href="${pageContext.request.contextPath}/login"><fmt:message key="index_jsp.header.login"/></a></h3>


<form id="settings_form" action="controller" method="get">
    <input type="hidden" name="command" value="updateSettings" />

    <div>
        <p>
            <fmt:message key="create"/>
        </p>
        <select name="localeToSet">
            <c:choose>
                <c:when test="${not empty defaultLocale}">
                    <option value="">${defaultLocale}[Default]</option>
                </c:when>
                <c:otherwise>
                    <option value=""/>
                </c:otherwise>
            </c:choose>

            <c:forEach var="localeName" items="${locales}">
                <option value="${localeName}">${localeName}</option>
            </c:forEach>
        </select>
    </div>
    <input type="submit" value='<fmt:message key="create"/>'><br/>
</form>
</body>
</html>
