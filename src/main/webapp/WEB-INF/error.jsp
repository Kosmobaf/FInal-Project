<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" isErrorPage="true"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error Page</title>
</head>
<body>
<h2>
    Error Page<br/>
    <i>Error <%= exception %></i>

</h2>
<c:set var="message" value="${sessionScope.errorMessage}"/>
<h3>Message: ${message}</h3>
<br>
<a href="${pageContext.request.contextPath}/index.jsp">Index</a>


</body>
</html>