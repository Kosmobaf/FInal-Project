<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>

</head>
<body>

<h1>Список послуг</h1>
<c:forEach var="service" items="${sessionScope.serviceList}">
    <ul>
        <li>
            <form action="${pageContext.request.contextPath}/getTariffList" method="get">
                <label>
                    <input type="number" hidden name="idService" value="${service.id}">
                </label>
                <input type="submit" name="service" value="<c:out value="${service.nameService}"/>">
            </form>
        </li>
    </ul>
    <hr/>
</c:forEach>
<a href="${pageContext.request.contextPath}/logout ">  Logout</a>
</body>
</html>