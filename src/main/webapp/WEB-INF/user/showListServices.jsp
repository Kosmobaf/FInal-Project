<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>

</head>
<body>

<h1>Список послуг</h1>
<h2>
<c:forEach var="tariff" items="${sessionScope.serviceList}">
    <ul>
        <li>
            <form method="post" action="${pageContext.request.contextPath}/getTariffList" >
                <label>
                    <input type="number" hidden name="idService" value="${tariff.id}">
                </label>
                <input type="submit" name="service" value="<c:out value="${tariff.nameService}"/>">
            </form>
        </li>
    </ul>
    <hr/>
</c:forEach>
</h2>
<a href="${pageContext.request.contextPath}/logout "> Вихід</a>
</body>
</html>