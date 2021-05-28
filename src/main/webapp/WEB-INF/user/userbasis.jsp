<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>

</head>
<body>

<h1>Привіт користувач!</h1> <br/>
<h2>
    Твій список послуг:<br/>
    <c:forEach var="order" items="${sessionScope.userOrderList}">
        <ul>
            <li>
                Послуга <c:out value="${order.nameService} "/> по тарифу <c:out value="${order.nameTariff} "/>
                Статус <c:out value="${order.status}"/>
            </li>
        </ul>
        <hr/>
    </c:forEach>
</h2>
<a href="${pageContext.request.contextPath}/getAllService ">Вибрати послугу</a><br/>
<a href="${pageContext.request.contextPath}/getFileServices ">Скачати прайс лист на послуги</a><br/>
<a href="${pageContext.request.contextPath}/logout "> Logout</a>
</body>
</html>