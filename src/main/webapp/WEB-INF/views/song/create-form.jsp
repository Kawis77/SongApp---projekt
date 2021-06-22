<%--
  Created by IntelliJ IDEA.
  User: krzys
  Date: 23.05.2021
  Time: 12:27
  To change this template use File | Settings | File Templates.

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Created song</title>
</head>
<body>
<form method="post" action="">
    <div>
        <label>Song Name
            <input type="text" name="songName"/>
        </label>
    </div>
    <div>
        <label>Email :

            <input type="text" name="songAurhor">
        </label>
        <div/>
        <div>
            <label>
                <input type="text" name="songText">
            </label>
        </div>
        <div>
            <label>
                <button type = "submit">Dodaj</button>
            </label>

        </div>


    </div>
</form>

</body>
</html>
