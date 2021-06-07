<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <title>USER</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>

<h1><fmt:message key="showListTariff_jsp.header.list_tariffs"/></h1>

<div class="container">
    <div class="row">
        <div class="col-8"></div>
        <div class="col-4">
            <form action="${pageContext.request.contextPath}/getAllTariffList" method="get">
                <select name="sortCommand" class="form-select">
                    <option selected><fmt:message key="showListTariff_jsp.selected.sorting"/></option>
                    <option value="sortByName"><fmt:message key="showListTariff_jsp.selected.sortByName"/></option>
                    <option value="sortByNameReverse"><fmt:message key="showListTariff_jsp.selected.sortByNameReverse"/></option>
                    <option value="sortByCoast"><fmt:message key="showListTariff_jsp.selected.sortByCost"/></option>
                </select>
                <button type="submit" class="btn btn-primary mb-3"><fmt:message key="showListTariff_jsp.selected.sort"/></button>
            </form>
        </div>
    </div>
</div>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="table.name"/></th>
        <th scope="col"><fmt:message key="showListTariff_jsp.table.cost"/></th>
        <th scope="col"><fmt:message key="choose"/></th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="tariffListForService" type="java.util.List"--%>
    <c:forEach var="tariff" items="${tariffListForService}">
        <tr>
            <th scope="row"> <c:out value="${tariff.nameTariff}"/></th>
            <td><c:out value="${tariff.cost}"/> <fmt:message key="currency"/> </td>
            <td>
                <form action="${pageContext.request.contextPath}/addTariffWithService" method="post">
                    <input type="number" hidden name="idTariff" value="${tariff.id}">
                    <input type="submit" value="<fmt:message key="choose"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/userBasis "><fmt:message key="user_main"/></a><br/>
<a href="${pageContext.request.contextPath}/logout "> <fmt:message key="logout"/></a>
</body>
</html>