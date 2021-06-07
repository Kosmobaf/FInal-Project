<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <title>USER</title>
</head>
<body>
<h2><fmt:message key="addCash_jsp.header.refill"/></h2>
<h3>
    <form action="${pageContext.request.contextPath}/addCash" method="post">
        <label> <fmt:message key="addCash_jsp.label.input"/>
            <input type="number" min="0.01" step="0.01" name="inputCash">
        </label>
        <input type="submit" value="<fmt:message key="addCash_jsp.button.replenishment"/>"/>
    </form>
</h3>
<a href="${pageContext.request.contextPath}/userBasis "><fmt:message key="user_main"/></a><br/>
<a href="${pageContext.request.contextPath}/logout "><fmt:message key="logout"/></a>
</body>
</html>
