<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>

<style>
.prev-next-a:hover {
	text-decoration: underline;
}

.prev-next-box li:first-child {
	border-top: 1px solid #000;
	padding-top: 8px;
}

.prev-next-box li {
	border-bottom: 1px solid #000;
}
</style>

<link rel="stylesheet" href="/kgCoffee/css/board.css">
<link rel="stylesheet" href="/kgCoffee/css/header_style.css"
	type="text/css">

</head>
<body>

	<%@include file="../../include/header.jsp"%>
	
	<%@include file="../../include/sub_header_news.jsp" %>


	<div class="board_wrap">

		<div class="board_title">
			<strong><h3 align="center">종로 KG커피 공지사항</h3></strong>
			<p align="center">종로 KG커피 소식을 알려드립니다.</p>


		</div>

		<div class="board_search_wrap-view">
			<ul class="board_ul_2">
				<li class="board_search_select"><c:choose>
						<c:when test="${sessionScope.result==1}">
							<div class="bt_wrap">
								<a href="/kgCoffee/board/getAll.do" class="on">목록</a> <a
									href="/kgCoffee/board/edit.do?bunho=${ssv.bunho}">수정</a> <a
									href="/kgCoffee/board/delete.do?bunho=${ssv.bunho}">삭제</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="bt_wrap">
								<a href="/kgCoffee/board/getAll.do" class="on">목록</a>
							</div>
						</c:otherwise>
					</c:choose></li>
			</ul>
		</div>

		<div class="board_view_wrap">
			<div class="board_view">
				<div class="title">${ssv.jemok}</div>
				<div class="info">
					<dl>
						<dt>번호</dt>
						<dd>${ssv.bunho}</dd>
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

					<!-- 이미지를 첨부한 게시글만 이미지가 보이도록 -->
					<!-- <img id="imgId"> -->

					<dd>
						<textarea readonly name="content">${ssv.content}</textarea>
					</dd>
				</div>
			</div>
		</div>

		<!-- 이전글과 다음글을 표시하는 부분 -->
		<div class="board_detail_prev_next">
			<ul class="prev-next-box">
				<li class="prev-next-li">
					<!-- 	<hr class="prev-next-hr"> --> <span>다음글</span> <span>
						<c:if test="${not empty nextPost}">
							<a class="prev-next-a"
								href="/kgCoffee/board/searchone.do?bunho=${nextPost.bunho}"
								class="underline-on-hover">${prePost.jemok}</a>
						</c:if> <c:if test="${empty nextPost}">
							<span class="prev-next-span"> 다음글이 없습니다.</span>
						</c:if>
				</span>
				</li>
				<li class="prev-next-li">
					<!-- 	<hr class="prev-next-hr"> --> <span> 이전글</span> <span>
						<c:if test="${not empty prePost}">
							<a class="prev-next-a"
								href="/kgCoffee/board/searchone.do?bunho=${prePost.bunho}"
								class="underline-on-hover">${prePost.jemok}</a>
						</c:if> <c:if test="${empty prePost}">
							<span class="prev-next-span"> 이전글이 없습니다.</span>
						</c:if>
				</span> <!-- 		<hr class="prev-next-hr"> -->
				</li>
			</ul>
		</div>



		<script>
			const filename = '${ssv.filename}';

			console.log(filename); //확인용

			if (filename) {

				const $cont = document.querySelector(".cont");

				var newEl = document.createElement('img');

				newEl.id = 'imgId';

				$cont.prepend(newEl);

				console.log("태그생성");

				document.getElementById('imgId').src = 'http://localhost:8080/kgCoffee/file_upload/'
						+ filename;
				/* 경로명 http://localhost:8080/프로젝트명/이미지저장폴더명/  */

			}
		</script>

	</div>



	<%@include file="../../include/footer.jsp"%>
</body>
</html>