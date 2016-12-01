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
        <th>출납아이디</th>
        <th>회원명</th>
        <th>비디오 타이틀</th>
        <th>대여비</th>
        <th>연체료</th>
        <th>총합</th>
    </tr>
    <c:forEach var="cashBook" items="${cashBooks }">
        <tr>
            <td>${cashBook.user.name}</td>
            <td>${cashBook.video.title }</td>
            <td>${cashBook.rentFee }</td>
            <td>${cashBook.lateFee }</td>
            <td>${cashBook.total }</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="2"><input type="submit" value="확인"></td>
    </tr>
</table>
</form>
</body>
</html>
