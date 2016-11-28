<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Main Manager</title>
</head>
<body>
<h2>회원가입</h2>
<form action="join.do" method="post">
    <table>
        <tr>
            <th>아이디</th>
            <td>
                <input type="text" name="user_id" required="required">
            </td>
        </tr>
        <tr>
            <th>이름</th>
            <td>
                <input type="text" name="name" required="required">
            </td>
        </tr>
        <tr>
            <th>전화번호</th>
            <td>
                <input type="text" name="phone" required="required">
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="확인"></td>
        </tr>
    </table>
</form>
</body>
</html>
