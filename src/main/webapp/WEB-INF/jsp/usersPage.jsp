<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Змей
  Date: 16.09.2019
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
</head>
<body>
    <h2>User Page</h2>

    <section>
        <h2>Create User</h2>
        <c:url value="/createUser" var="var"/>
        <form action="${var}" method="post">
            <input name="name" placeholder="Name">
            <input name="login" placeholder="Login">
            <input name="password" placeholder="password">
            <select name="role">
                <option name="user" selected >User</option>
                <option name="admin">Admin</option>
            </select>
            <input type="submit" value="Создать"/>
        </form>
    </section>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Login</th>
            <th>Password</th>
            <th>Role</th>
            <th>Action</th>
        </tr>
        <c:forEach var="user" items="${usersList}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>
                    <form method="get" action="/updateUser/${user.id}">
                        <input type="submit" value="Изменить">
                    </form>
                </td>
                <td>
                    <form method="get" action="/deleteUser/${user.id}">
<%--                        <input type="hidden" name="id" value="${user.id}">--%>
<%--                        <input type="hidden" name="name" value="${user.name}">--%>
<%--                        <input type="hidden" name="login" value="${user.login}">--%>
<%--                        <input type="hidden" name="password" value="${user.password}">--%>
<%--                        <input type="hidden" name="role" value="${user.role}">--%>
                        <input type="submit" value="Удалить">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
