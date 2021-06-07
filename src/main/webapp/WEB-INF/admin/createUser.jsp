<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/createUser">
    <label> <fmt:message key="login_jsp.label.login"/>
        <input type="text" name="login">
    </label>
    <label> <fmt:message key="login_jsp.label.password"/>
        <input type="password" name="password">
    </label>
    <input class="button" type="submit" value="<fmt:message key="create"/>">
</form>


<p><a href="${pageContext.request.contextPath}/adminBasis"><fmt:message key="admin_main"/></a></p>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="logout"/></a>

</body>
</html>