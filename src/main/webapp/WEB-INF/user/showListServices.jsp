<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>

<h1>Список послуг</h1>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Назва послуги</th>
        <th scope="col">Вибрати</th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="serviceList" type="java.util.List"--%>
    <c:forEach var="service" items="${serviceList}">
        <tr>
            <th scope="row"><c:out value="${service.nameService}"/></th>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/getAllTariffList">
                    <input type="number" hidden name="idService" value="${service.id}">
                    <input type="submit" name="service" value="Вибрати">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br/>
<a href="${pageContext.request.contextPath}/userBasis "> На головну сторінку користувача</a><br/>
<a href="${pageContext.request.contextPath}/logout "> Вихід</a>
</body>
</html>