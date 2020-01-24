<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить данные пользователя</title>
</head>
<body>

Редактировать пользователя

<form action="/users/${param.id}" method="post">
    <input type="hidden" name = "id" value="${param.id}">
    <input type="text" name="fullName" value="${param.fullName}" placeholder=${param.fullName}>
    <input type="text" name="phoneNumber" value="${param.phoneNumber}" placeholder=${param.phoneNumber}>
    <input type="hidden" name="_method" value="put">
    <input type="submit" value="Обновить">
</form>

</body>
</html>