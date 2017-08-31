<%--
  Created by IntelliJ IDEA.
  User: Grand Circus Student
  Date: 8/11/2017
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Response</title>
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
<br/>
${fName}, as a ${sign} you are an ${trait} and very ${description} <br/>

Here are some events that may interest you! <br/>
<c:forEach items="${events}" var="event">
<h2> ${event.title}</h2>
    ${event.description}<br/>
    ${event.start_time}<br  />
    <a href="${event.url}" target="_blank">View Event</a> <br />

</c:forEach>

</body>
</html>