<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<form action="main.do">
<table>
    <tr>
        <th>아이디</th>
        <th>이름</th>
        <th>전화번호</th>
    </tr>
    <c:forEach var="user" items="${users }">
        <tr>
            <td>${user.userId }</td>
            <td>${user.name }</td>
            <td>${user.phone }</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="2"><input type="submit" value="확인"></td>
    </tr>
</table>
</form>
</body>
</html>
