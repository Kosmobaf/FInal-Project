<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tld/custom.tld" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error Page</title>
</head>
<body>
<h2>
    <ex:ErrorPage/>
    <br/>
</h2>
<h3>
    <c:choose>
        <%--@elvariable id="errorMessage" type="com.controller.MyException"--%>
        <c:when test="${errorMessage != null}">
            Message: ${errorMessage}
        </c:when>
        <c:otherwise>
            Message: Something went wrong
        </c:otherwise>
    </c:choose>
</h3>
<br>
<a href="${pageContext.request.contextPath}/index.jsp">Index</a>


</body>
</html>