<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Utwórz personę</title>
</head>
<body>
<form:form method="post" action="" modelAttribute="song">
    <div>
        <label>Login:
            <form:input type="text" path="songName"/>
        </label>
    </div>

    <div>
        <label>Email:
            <form:input type="text" path="songAuthor"/>
        </label>
    </div>
    <div>
        <label>Password:
            <form:input type="text" path="songText"/>
        </label>
    </div>
    <div>
        <button type="submit">Dodaj</button>
    </div>
</form:form>
</body>
</html>