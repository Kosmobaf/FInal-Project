<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <title>USER</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>

<h1>
    <fmt:message key="userBasis_jsp.header.mainPage"/> <br/>
</h1>
<br/>
<h2>
    <%--@elvariable id="cash" type="java.math.BigDecimal"--%>
        <fmt:message key="userBasis_jsp.header.balance"/>: ${cash} <fmt:message key="currency"/> <br/>
</h2>
<br/>
<h3>
    <c:choose>
        <%--@elvariable id="userOrderList" type="java.util.List"--%>
        <c:when test="${userOrderList.size() > 0}">
            <fmt:message key="userBasis_jsp.header.ordered"/>:<br/>
        </c:when>
        <c:otherwise>
            <fmt:message key="userBasis_jsp.header.unordered"/>:<br/>
        </c:otherwise>
    </c:choose>
    <br/>
</h3>
<br/>
<c:choose>
    <%--@elvariable id="userOrderList" type="java.util.List"--%>
    <c:when test="${userOrderList.size() > 0}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="userBasis_jsp.table.service"/></th>
                <th scope="col"><fmt:message key="userBasis_jsp.table.tariff"/></th>
                <th scope="col"><fmt:message key="userBasis_jsp.table.status"/></th>
                <th scope="col"><fmt:message key="userBasis_jsp.table.act_tariff"/></th>
                <th scope="col"><fmt:message key="userBasis_jsp.table.rem_tariff"/></th>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="userOrderList" type="java.util.List"--%>
            <c:forEach var="user_order" items="${userOrderList}">
                <tr>
                    <th scope="row"><c:out value="${user_order.nameService}"/></th>
                    <td><c:out value="${user_order.nameTariff}"/></td>
                    <td><c:out value="${user_order.status}"/></td>
                    <td>
                        <c:set var="blocked" scope="session" value="blocked"/>
                        <c:if test="${user_order.status.toString() == blocked}">
                            <form action="${pageContext.request.contextPath}/activateTariff" method="post">
                                <input type="number" hidden name="idTariff" value="${user_order.tariffId}">
                                <input type="submit" value="<fmt:message key="userBasis_jsp.button.pay"/>">
                            </form>
                        </c:if>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/deleteTariffFromUser" method="post">
                            <input type="number" hidden name="idOrder" value="${user_order.id}">
                            <input type="submit" value="<fmt:message key="userBasis_jsp.button.remove"/>">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
</c:choose>
<a href="${pageContext.request.contextPath}/getAllService "><fmt:message key="userBasis_jsp.href.choose_service"/></a><br/>
<a href="${pageContext.request.contextPath}/addCash"><fmt:message key="userBasis_jsp.href.add_money"/></a><br/>
<a href="${pageContext.request.contextPath}/getFileServices "><fmt:message key="userBasis_jsp.href.download"/></a><br/>
<a href="${pageContext.request.contextPath}/logout "><fmt:message key="logout"/></a>
</body>
</html>