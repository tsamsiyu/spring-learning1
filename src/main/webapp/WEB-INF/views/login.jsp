<%--
  Created by IntelliJ IDEA.
  User: tsamsiyu
  Date: 24.02.16
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h3>${user.name}</h3>
    <h3>${errors}</h3>

    <span>
        <a href="?lang=en">En</a>
        <a href="?lang=ru">Ru</a>
    </span>

    <form:form action="check-user" method="POST" commandName="user">
        <fieldset>
            <div>
                <form:label path="name"><spring:message code="username" /></form:label>
                <br>
                <form:input path="name" />
                <form:errors path="name" />
            </div>
            <div>
                <form:label path="password"><spring:message code="password" /></form:label>
                <br>
                <form:input path="password" />
                <form:errors path="password" />
            </div>
        </fieldset>
        <form:button>Submit me</form:button>
    </form:form>
</body>
</html>
