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
<form action="returnVideo.do">
    <table>
        <tr>
            <th>VID</th>
            <th>제목</th>
            <th>종류</th>
            <th>대여비</th>
            <th>연체료</th>
            <th>대여상태</th>
        </tr>
        <c:forEach var="video" items="${videos }">
            <tr>
                <td>${video.vid }</td>
                <td>${video.title }</td>
                <td>${video.type }</td>
                <td>${video.rentFee }</td>
                <td>${video.lateFee }</td>
                <td>${video.state }</td>
                <td><input type="radio" name="videoId" value="${video.id}"></td>
            </tr>
        </c:forEach>
        <tr>
            <th>userId</th>
            <td>
                <input type="text" name="userId" required="required">
            </td>
            <th>RentInfo id</th>
            <td>
                <input type="text" name="rentInfoId" required="required">
            </td>
            <td colspan="2">
                <input type="submit" name="select" value="반납">
            </td>
        </tr>

    </table>
</form>
</body>
</html>
