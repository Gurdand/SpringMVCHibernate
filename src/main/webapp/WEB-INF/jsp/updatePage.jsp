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
        <select name="role">
            <option name="user" value="user" <c:if test="${user.role.equals('user')}">selected</c:if> >User</option>
            <option name="admin" value="admin" <c:if test="${user.role.equals('admin')}">selected</c:if> >Admin</option>
        </select>
        <input type="submit" value="Обновить">
    </form>
</body>
</html>
