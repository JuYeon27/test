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

	<%@include file="/include/header.jsp" %>
	
	<%@include file="../../include/sub_header_voc.jsp" %>

	<div class="board_wrap">
		<div class="board_title">
			<strong><h3 align="center">종로 KG커피 고객의 소리</h3></strong>
			<p align="center">종로 KG 커피 고객의 소리 페이지 입니다.</p>
		</div>
		
		
		<form id="frm1" action="/kgCoffee/voc/Reinsert.do" method="post" enctype="multipart/form-data">
		
		<div class="board_write_wrap">
			<div class="board_write">
				<div class="title">
					<dl>
						<dt>제목</dt>
						<dd><input type="text" name="jemok" value =" [답변]" placeholder="제목 입력"></dd>
					</dl>
				</div>
				<div class="info">
					<dl>
						<dt>글쓴이</dt>
						<dd><input type="text" name="writer" value="<c:out value='${loginUser.user_name}' />"></dd>
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
			
			<input type="hidden" name="ref" value="${param.ref}">
			<input type="hidden" name="re_step" value="${param.re_step}">
			<input type="hidden" name="re_level" value="${param.re_level}">
			<div class="bt_wrap">
				<a href="/kgCoffee/voc/getAll.do">취소</a>
				<a href=# onclick="formSubmit();" class="on">답글쓰기</a>
			</div>
		</div>
	</div>
	
	</form>


	<%@include file="/include/footer.jsp" %>
	




 <script>
 
 console.log("ref:::"+"${ref}");
 
 </script>
</body>
</html>