<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <title>Create User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>
<h3>
    <c:choose>
        <c:when test="${userOrderList.size() > 0}">
            Замовлені послуги<br/>
        </c:when>
        <c:otherwise>
            Послуги ще не замовлені
        </c:otherwise>
    </c:choose>
</h3>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Послуга</th>
        <th scope="col">Тариф</th>
        <th scope="col">Статус</th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="userOrderList" type="java.util.List"--%>
    <c:forEach var="tariff" items="${userOrderList}">
        <tr>
            <th scope="row"><c:out value="${ tariff .nameService}"/></th>
            <td><c:out value="${ tariff .nameTariff}"/></td>
            <td><c:out value="${ tariff .status}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<%--@elvariable id="user" type="com.model.entity.User"--%>
Cтатус користувача - ${user.status.name()}
<br/>

<c:choose>
    <c:when test="${user.status.name() == 'blocked'}">
        <form action="${pageContext.request.contextPath}/changeStatusUser" method="post">
            <input type="number" hidden name="idUser" value="${user.id}">
            <input type="submit" value="Активувати">
        </form>
    </c:when>
    <c:otherwise>
        <form action="${pageContext.request.contextPath}/changeStatusUser" method="post">
            <input type="number" hidden name="idUser" value="${user.id}">
            <input type="submit" value="Заблокувати">
        </form>
    </c:otherwise>
</c:choose>
<br/>

<p><a href="${pageContext.request.contextPath}/adminBasis">Повернутись на головну сторінку</a></p>
<a href="${pageContext.request.contextPath}/logout">Вихід</a>

</body>
</html>