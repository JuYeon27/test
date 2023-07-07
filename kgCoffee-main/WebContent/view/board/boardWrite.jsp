<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>

<script type="text/javascript" src="/kgCoffee/js/boardSubmit.js" defer></script>

<link rel="stylesheet" href="/kgCoffee/css/board.css">
<link rel="stylesheet" href="/kgCoffee/css/header_style.css" type="text/css">
</head>
<body>
	<%@include file="../../include/header.jsp" %>
	
	<%@include file="../../include/sub_header_news.jsp" %>
			
	
	<div class="board_wrap">
		<div class="board_title">
			<strong><h3 align="center">종로 KG커피 공지사항</h3></strong>
			<p align="center">종로 KG커피 소식을 알려드립니다.</p>
		</div>
		
		
		<form id="frm1" action="/kgCoffee/board/insert.do" method="post" enctype="multipart/form-data">
		
		<div class="board_write_wrap">
			<div class="board_write">
				<div class="title">
					<dl>
						<dt>제목</dt>
						<dd><input type="text" name="jemok" placeholder="제목 입력"></dd>
					</dl>
				</div>
				<div class="info">
					<dl>
						<dt>글쓴이</dt>
						<dd><input readonly type="text" name="writer" value="<c:out value='${loginUser.user_name}'/>"></dd>
						<!-- <dd><input type="text" name="writer" placeholder="글쓴이 입력"></dd> -->
					</dl>
					<dl>
						<dt>첨부파일</dt>
						<dd><input type="file" name="filename"></dd>
					</dl>
				</div>
				<div class="cont">
					<textarea name="content" placeholder="내용 입력"></textarea>
				</div>	
			</div>
			
			<div class="bt_wrap">
				<a href=# onclick="formSubmit();" class="on">등록</a>
				<a href="/kgCoffee/board/getAll.do">취소</a>
			</div>
		</div>
	</div>
	
	</form>

	<%@include file="../../include/footer.jsp" %>
	
</body>
</html>