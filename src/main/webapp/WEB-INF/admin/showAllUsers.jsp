<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <title>ADMIN</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>

<h1><fmt:message key="showAllUsers_jsp.header.list_users"/></h1>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col"><fmt:message key="showAllUsers_jsp.table.login"/></th>
        <th scope="col"><fmt:message key="showAllUsers_jsp.table.role"/></th>
        <th scope="col"><fmt:message key="showAllUsers_jsp.table.cash"/></th>
        <th scope="col"><fmt:message key="showAllUsers_jsp.button.show"/></th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="userList" type="java.util.List"--%>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.role.toString()}"/></td>
            <td><c:out value="${user.cash}"/> <fmt:message key="currency"/></td>
            <td>
                <form action="${pageContext.request.contextPath}/showUser" method="get">
                    <input type="number" hidden name="idUser" value="${user.id}">
                    <input type="submit" value="<fmt:message key="showAllUsers_jsp.button.show"/>">
                </form>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/createUser"><fmt:message key="showAllUsers_jsp.href.create_user"/></a>

<p><a href="${pageContext.request.contextPath}/adminBasis"><fmt:message key="admin_main"/></a></p>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="logout"/></a>
</body>
</html>