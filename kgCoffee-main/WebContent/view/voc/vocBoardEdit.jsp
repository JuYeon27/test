<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="/kgCoffee/css/board.css">
<link rel="stylesheet" href="/kgCoffee/css/header_style.css" type="text/css">

<script type="text/javascript" src="/kgCoffee/js/boardSubmit.js" defer></script>

</head>
<body>
	<%@include file="/include/header.jsp" %>
	
	<%@include file="../../include/sub_header_voc.jsp" %>

	<div class="board_wrap">
		<div class="board_title">
			<strong><h3 align="center">종로 KG커피 고객의 소리</h3></strong>
			<p align="center">종로 KG 커피 고객의 소리 페이지 입니다.</p>
		</div>
		
		
		<form id="frm1" action="/kgCoffee/voc/update.do?sbunho=${ssv.bunho}" method="post" enctype="multipart/form-data">
		

		
		<div class="board_write_wrap">
			<div class="board_write">
				<div class="title">
					<dl>
						<dt>제목</dt>
						<dd><input type="text" value="${ssv.jemok}" name="jemok"></dd>
					</dl>
				</div>
				<div class="info">
					<dl>
						<dt>글쓴이</dt>
						<dd><input type="text" name="writer" value="<c:out value='${loginUser.user_name}' />"></dd>
						<%-- <dd><input type="text" value="${ssv.writer}" name="writer"></dd> --%>
					</dl>
					<dl>
						<dt>첨부파일</dt>
						<dd><input type="file" name="filename"></dd>
					</dl>
				</div>
				<div class="cont">
					<textarea name="content">${ssv.content}</textarea>
				</div>	
			</div>
			
			<div class="bt_wrap">
				
				<a href=# onclick="formSubmit();" class="on">수정</a>
				<a href="getAll.do">취소</a>
			</div>
		</div>
	</div>
	
	<input type="hidden" value="${ssv.bunho}" name="bunho">
	
	</form>



<%@include file="/include/footer.jsp" %>

<script>
	
	function doSubmit() { 
		frm1.encoding = "application/x-www-form-urlencoded";
		frm1.action = "update.do?sbunho=${ssv.bunho}";
		frm1.submit(); 
	}
	
</script>


</body>
</html>