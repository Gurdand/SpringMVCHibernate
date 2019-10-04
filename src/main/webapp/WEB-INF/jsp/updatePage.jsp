<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Змей
  Date: 18.09.2019
  Time: 1:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Page</title>
</head>
<body>
    <h2>Update Page</h2>
    <c:url var="var" value="/admin/updateUser"/>
    <form method="post" action="${var}">
        <input type="hidden" name="id" value="${user.id}">
        <input name="name" value="${user.name}">
        <input name="login" value="${user.login}">
        <input name="password" value="${user.password}">
<%--        <c:forEach var="role" items="${rolesList}">--%>
<%--            <label for="${role.roleName}">${role.roleName}</label>--%>
<%--            <input id="${role.roleName}" type="checkbox" name="roleName" value="${role.roleName}"--%>
<%--                   <c:if test="${role.roleName.equals('USER')}" >checked</c:if> />--%>
<%--        </c:forEach>--%>
        <select multiple name="roles">
            <c:forEach var="role" items="${rolesList}">
                <option name="roleName" value="${role.roleId}_${role.roleName}">${role.roleName}</option>
            </c:forEach>
        </select>
<%--        <fieldset name="roles">--%>
<%--            <input type="checkbox" checked hidden name="user" value="USER" >--%>
<%--            <input type="checkbox" name="admin" value="ADMIN" <c:if test="${user.roles.contains('ADMIN')}">checked</c:if> >--%>
<%--        </fieldset>--%>
        <input type="submit" value="Обновить">
    </form>
</body>
</html>
