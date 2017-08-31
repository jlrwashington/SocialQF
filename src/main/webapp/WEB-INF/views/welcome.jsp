<%--
  Created by IntelliJ IDEA.
  User: Grand Circus Student
  Date: 8/24/2017
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <head>
        <style>
            body {
                background-color: lightblue;
            }
        </style>
    </head>
</head>
<body>
<h1 align="center"> Detroit SocialQ </h1>
${thank}<br/>
<br/>

${inst}

<form action="/response" method="post">
    First Name:<input type="text" name="fName">
    Zodiac Sign:<select name="sign">
        <option value="Aquarius">01/20-02/18 Aquarius</option>
        <option value="Aries">02/19-03/20 Aries</option>
        <option value="Cancer">06/21-07/22 Cancer</option>
        <option value="Capricorn">12/22-01/19 Capricorn</option>
        <option value="Gemini">05/21-06/20 Gemini</option>
        <option value="Leo">07/23-08/22 Leo</option>
        <option value="Libra">09/23-10/22 Libra</option>
        <option value="Pisces">02/19-03/20 Pisces</option>
        <option value="Sagittarius">11/22-12/21 Sagittarius</option>
        <option value="Scorpio">10/23-11/21 Scorpio</option>
        <option value="Taurus">04/20-05/20 Taurus</option>
        <option value="Virgo">08/23-09/22 Virgo</option>
        </select>
    <input type="submit" name="submit" value="Search">
</form>

</body>
</html>