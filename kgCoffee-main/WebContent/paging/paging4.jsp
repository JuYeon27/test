<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="css/board.css">

</head>
<body>

    <div id="paging" class="board_page">
	<!-- 1~10까지 있는 페이지의 페이징 -->
	<c:url var="action" value="/voc/vocSearchboard.do?searchKeywordType=${searchKeywordType}&searchKeyword=${searchKeyword}"/>
	<c:if test="${param.prev}">
	    <a href="${action}?page=${param.beginPage-1}" class="bt prev"><</a>
	</c:if>
	<c:forEach begin="${param.beginPage}" end="${param.endPage}" step="1" var="index">
	    <c:choose>
	        <c:when test="${param.page==index}">
	           <a href="${action}&page=${index}" class="num on">${index}</a>
	        </c:when>
	        <c:otherwise>
	            <a href="${action}&page=${index}" class="num">${index}</a>
	        </c:otherwise>
	    </c:choose>
	</c:forEach>
	<c:if test="${param.next}">
	    <a href="${action}?page=${param.endPage+1}" class="bt next">></a>
	</c:if>
	</div>
	
</body>
</html>