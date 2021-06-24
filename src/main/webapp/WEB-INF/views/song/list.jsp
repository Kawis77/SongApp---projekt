<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>List songs</title>

</head>
<body>
<div><a>Dodaj autora</a></div>
<div>
    <table>
        <tr>
            <th>Id</th>
            <th>songName</th>
            <th>songAurhor</th>
            <th>songText</th>
        </tr>
        <jsp:useBean id="songs" scope="request" type="java.util.List"/>
        <c:forEach items="${songs}" var="songs">
            <tr>
                <td>${songs.id}</td>
                <td>${songs.songName}</td>
                <td>${songs.songAuthor}</td>
                <td>${songs.songText}</td>

            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
