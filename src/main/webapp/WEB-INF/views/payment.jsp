<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Main Manager</title>
</head>
<body>
<c:choose>
    <c:when test="${msg ne null}">
        <h2>${msg}</h2>
    </c:when>
    <c:otherwise>
        <form action="main.do">
            <h2>${userName}님 결제금액은 총 ${fee}입니다.</h2>
            <input type="submit" value="확인">
        </form>
    </c:otherwise>
</c:choose>

</body>
</html>
