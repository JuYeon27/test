<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="/kgCoffee/css/board.css">
<link rel="stylesheet" href="/kgCoffee/css/header_style.css" type="text/css">
<!--서브밋해주는 자바스크립트 -->
<script type="text/javascript" src="/kgCoffee/js/boardSubmit.js" defer></script>
</head>
<body>

	<%@include file="/include/header.jsp"%>
	
	<%@include file="../../include/sub_header_voc.jsp" %>

	<div class="board_wrap">
	
		<div class="board_title">
			<strong><h3 align="center">종로 KG커피 고객의 소리</h3></strong>
			<p align="center">종로 KG 커피 고객의 소리 페이지 입니다.</p>
		</div>
		
		<!----------------------------------- 고객문의 검색창 ------------------------------------>

		<form id="frm1" action="/kgCoffee/voc/vocSearchboard.do" method="get" enctype="multipart/form-data">
		<ul>
			<div class="board_search_wrap">
					<ul class="board_ul">
						<li class="board_search_select">
							<div class="input_select_wrap input_wrap2">
								<select name="searchKeywordType">
								<c:choose>
									<c:when test="${param.searchKeywordType == 'title'}">
										<option value="jemok" selected>제목</option>
										<option value="writer">글쓴이</option>
										<option value="content">내용</option>
									</c:when>
									<c:when test="${param.searchKeywordType == 'writer'}">
										<option value="jemok">제목</option>
										<option value="writer" selected>글쓴이</option>
										<option value="content">내용</option>
									</c:when>
									<c:when test="${param.searchKeywordType == 'content'}">
										<option value="jemok">제목</option>
										<option value="writer">글쓴이</option>
										<option value="content" selected>내용</option>
									</c:when>
									<c:otherwise>
										<option value="jemok" selected>제목</option>
										<option value="writer">글쓴이</option>
										<option value="content">내용</option>
									</c:otherwise>
								</c:choose>
								</select> 
							</div>
							<div class="input_text_wrap input_wrap2">
								<label> <input type="text" name="searchKeyword" value="${param.searchKeyword}" placeholder="검색어를 입력하세요" required="required">
								</label>
							</div>
						</li>
						<li class=bt_wrap>
						<a href=# onclick="formSubmit();" class="on">검색</a></li>
						
						<div class="bt_wrap">
					<a href="/kgCoffee/view/voc/vocBoardWrite.jsp" class="on">글쓰기</a>
				</div>	
					</ul>
				</form>
				
			</div>
		</ul>
		
	<!----------------------------------- 고객문의 검색창 ------------------------------------>		
	
		
		
		<div class="board_list_wrap">
			<div class="board_list">
				<div class="top">
					<div class="num">번호</div>
					<div class="title">제목</div>
					<div class="writer">글쓴이</div>
					<div class="date">작성일</div>
					<div class="count">조회</div>
				</div>

<%-- 
				<c:forEach var="v1" items="${alist1}">
					<div>
						<div class="num">${v1.bunho}</div>
						<div class="title"><a href="boardView.jsp"> ${v1.jemok}</a></div>
						<div class="title">
							<a href="searchone.do?bunho=${v1.bunho}">${v1.jemok}</a>
						</div>
						<div class="writer">${v1.writer}</div>
						<div class="date">${v1.date}</div>
						<div class="date"><fmt:formatDate value="${v1.date}" pattern="yyyy-MM-dd" /></div>
						<div class="count">${v1.count}</div>
					</div>
				</c:forEach>
			</div> --%>
			
			
			
				<c:forEach var="v1" items="${alist1}">
					<div class="han_jul">
						<div class="num">${v1.bunho}</div>
						<%-- <div class="title"><a href="boardView.jsp"> ${v1.jemok}</a></div> --%>
						<div class="title">
						<c:if test="${v1.re_step>1}">
						<c:forEach var="j" begin="1" end="${(v1.re_step-1)*5}">
							&nbsp;
						</c:forEach>
						</c:if>
							<a class ="jemok_a" href="/kgCoffee/voc/searchone.do?bunho=${v1.bunho}&voc_id=${v1.user_id}">${v1.jemok}</a>
						</div>
						<div class="writer">${v1.writer}</div>
						<%-- <div class="date">${v1.date}</div> --%>
						<div class="date"><fmt:formatDate value="${v1.date}" pattern="yyyy-MM-dd" /></div>
						<div class="count">${v1.count}</div>
					</div>
				</c:forEach>
			</div>
				
				<jsp:include page="../../paging/paging2.jsp">
				    <jsp:param value="${paging.page}" name="page"/>
				    <jsp:param value="${paging.beginPage}" name="beginPage"/>
				    <jsp:param value="${paging.endPage}" name="endPage"/>
				    <jsp:param value="${paging.prev}" name="prev"/>
				    <jsp:param value="${paging.next}" name="next"/>
				</jsp:include>
					
				
			</div>
		</div>
		
		
		</form>
		
		
		<%@include file="/include/footer.jsp" %>
		
</body>
</html>