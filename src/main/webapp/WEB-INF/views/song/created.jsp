<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Utworzony song</title>
</head>
<body>
<table>
    <tr>
        <th>Lp.</th>
        <th>Id</th>
        <th>SongName</th>
        <th>SongAurhor</th>
        <th>SongText</th>
    </tr>
    <tr>
        <td>1.</td>
        <td>${song.id}</td>
        <td>${song.songName}</td>
        <td>${song.songAuthor}</td>
        <td>${song.songText}</td>
    </tr>
</table>
</body>
</html>