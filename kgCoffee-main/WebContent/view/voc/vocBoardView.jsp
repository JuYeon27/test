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
		
		
		<div class="board_view_wrap">
			<div class="board_view">
				<div class="title">
					 ${ssv.jemok}
				</div>
				<div class="info">
					<dl>
						<dt>번호</dt>
						<dd>${ssv.bunho} </dd>
					</dl>
					<dl>
						<dt>글쓴이</dt>
						<dd>${ssv.writer}</dd>
					</dl>
					<dl>
						<dt>작성일</dt>
						<dd>${ssv.date}</dd>
					</dl>
					<dl>
						<dt>조회</dt>
						<dd>${ssv.count}</dd>
					</dl>
				</div>
				
				<div class="cont">
					
					<!-- <dd id="imgview">
						<img id="imgId">
					</dd> -->
					
					<dd>
						<textarea readonly name="content">${ssv.content}</textarea>
					</dd>
				</div>
					
			</div>`
			
			<div class="bt_wrap">
				<a href="/kgCoffee/voc/getAll.do" class="on">목록</a>
				
				<c:choose>
					<c:when test="${result==1}">
						<a href="/kgCoffee/voc/edit.do?bunho=${ssv.bunho}">수정</a>
						<a href="/kgCoffee/voc/delete.do?bunho=${ssv.bunho}">삭제</a>
						<a href="/kgCoffee/view/voc/vocBoardReWrite.jsp?=&bunho=${ssv.bunho}=&ref=${ssv.ref}&re_step=${ssv.re_step}&re_level=${ssv.re_level}">답글쓰기</a>
					</c:when>
					<c:when test="${result==2}">
						<a href="/kgCoffee/voc/edit.do?bunho=${ssv.bunho}">수정</a>
						<a href="/kgCoffee/voc/delete.do?bunho=${ssv.bunho}">삭제</a>
						<a href="/kgCoffee/view/voc/vocBoardReWrite.jsp?=&bunho=${ssv.bunho}=&ref=${ssv.ref}&re_step=${ssv.re_step}&re_level=${ssv.re_level}">답글쓰기</a>
					</c:when>
					<c:when test="${result==3}">
						<a href="/kgCoffee/view/voc/vocBoardReWrite.jsp?=&bunho=${ssv.bunho}=&ref=${ssv.ref}&re_step=${ssv.re_step}&re_level=${ssv.re_level}">답글쓰기</a>
					</c:when>
				</c:choose>
				
			</div>
		</div>
	</div>


<script>
		
	console.log("ref:::"+${ssv.ref});
	console.log("ref:::"+${result});
	
	
	 	const filename = '${ssv.filename}';
	 	
	 	console.log(filename); //확인용
	 	
	 	if(filename){
	 		
	 		const $cont = document.querySelector(".cont");
			
	 		var newEl = document.createElement('img');
	 		
	 		newEl.id = 'imgId';
	 		
	 		$cont.prepend(newEl);
	 		
	 		console.log("태그생성");
	 		
	 	 	document.getElementById('imgId').src = 
			 	'http://localhost:8080/kgCoffee/file_upload/' + filename;
	 	 	/* 경로명 주의 http://localhost:8080/프로젝트명/이미지저장폴더명/  */
	 	 	
	 	}	
	 		
</script>

<%@include file="/include/footer.jsp" %>

</body>
</html>