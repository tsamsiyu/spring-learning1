<%--
  Created by IntelliJ IDEA.
  User: tsamsiyu
  Date: 24.02.16
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form:form action="check-user" method="POST" commandName="user">
        <fieldset>
            <form:label path="name">Name: </form:label>
            <form:input path="name" />

            <form:label path="password">Password:</form:label>
            <form:input path="password" />
        </fieldset>
    </form:form>
</body>
</html>
