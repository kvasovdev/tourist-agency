<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Список пользователей</title>
</head>
<body>
<table border="2">
    <tr>
        <td>ID</td>
        <td>Полное имя</td>
        <td>Номер телефона</td>
    </tr>
    <c:forEach items="${clients}" var = "client">
    <tr>
        <td>${client.getId()}</td>
        <td>${client.getName()}</td>
        <td>${client.getAge()}</td>
        <td>
            <form action = "updateClient.jsp" method="post">
                <input type="hidden" name="id" value="${client.getId()}">
                <input type="hidden" name="name" value="${client.getName()}">
                <input type="hidden" name="age" value="${client.getAge()}">
                <input type="submit" value="Изменить" style="float:left">
            </form>
            <form action="deleteClient.jsp" method="post">
                <input type="hidden" name="id" value="${client.getId()}">
                <input type="submit" value="Удалить" style="float:left">
            </form></td>
    </tr>
    </c:forEach>
</table>

<form action = "createClient.jsp">
    <input type="submit" value="Добавить нового пользователя">
</form>
</body>
</html>