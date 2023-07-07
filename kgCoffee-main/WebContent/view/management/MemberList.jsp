<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원리스트</h2>
	<table border="1">
	<tr>
		<td width="150" align="center">아이디</td>
		<td width="150" align="center">이름</td>
		<td width="150" align="center">생일</td>
		<td width="150" align="center">전화번호</td>		
	</tr>

	<c:forEach var="list" items="${list}">
	<tr>
		<td width="150" align="center">${list.user_id}</td>
		<td width="150" align="center">${list.user_name}</td>
		<td width="150" align="center">${list.birthday}</td>
		<td width="150" align="center">${list.tel}</td>
	</tr>
	</c:forEach>
	</table>

</body>
</html>