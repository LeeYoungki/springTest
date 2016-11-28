<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Main Manager</title>
</head>
<body>
<h2>비디오 등록</h2>
<form action="joinVideo.do" method="post">
    <table>
        <tr>
            <th>제목</th>
            <td>
                <input type="text" name="title" required="required">
            </td>
        </tr>
        <tr>
            <th>종류</th>
            <td>
                <select name="type" size="4">
                    <option>Bluelay</option>
                    <option>DVD</option>
                    <option>CD</option>
                    <option>VHS</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>VID(비디오 고유번호)</th>
            <td>
                <input type="text" name="vid" required="required">
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="확인"></td>
        </tr>
    </table>
</form>
</body>
</html>
