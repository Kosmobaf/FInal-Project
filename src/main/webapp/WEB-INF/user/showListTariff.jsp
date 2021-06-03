<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>

</head>
<body>

<h1>Список тарифів</h1>

    <c:forEach var="tariff" items="${sessionScope.tariffListForService}">
        <ul>
            <li>
                <form action="${pageContext.request.contextPath}/addTariffWithService" method="post">
                    <input type="number" hidden name="idTariff" value="${tariff.id}">
                    <input type="submit" name="tariff" value="<c:out value="${tariff.nameTariff}"/>">
                </form>
            </li>
        </ul>
        <hr/>
    </c:forEach>

<a href="${pageContext.request.contextPath}/userBasis "> На головну сторінку користувача</a>
<a href="${pageContext.request.contextPath}/logout "> Logout</a>
</body>
</html>