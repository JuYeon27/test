<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리페이지</title>
<link rel="stylesheet" href="/kgCoffee/css/board.css">
<link rel="stylesheet" href="/kgCoffee/css/header_style.css"
	type="text/css">
<link rel="stylesheet" href="/kgCoffee/css/userModify.css?after"
	type="text/css">
<!--서브밋해주는 자바스크립트 -->
<script type="text/javascript" src="/kgCoffee/js/boardSubmit.js" defer></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<style>
body {
	background-color: #f4f6f8;
}
</style>
<body>

	<%@include file="/view/admin/adminSide.jsp"%>

	<br>


	<section class="content_section">



		<div class="content_hedear_wrap ">

			<ul class="modify-mg">
				<li class="modify_name">
					<h2 class="h2" align="center">회원 관리 페이지</h2>
				</li>
			</ul>
		</div>



		<!----------------------------------- 회원관리 멤버 검색창 ------------------------------------>

		<div class="content_wrap">
			<div class="content">
				<div class="board_wrap">
					<div>
						<form id="frm1" action="/kgCoffee/admin/searchMember.do"
							method="get" enctype="multipart/form-data">
							<div class="board_search_wrap">
								<ul class="board_ul">
									<li class="board_search_select">
										<div class="input_select_wrap input_wrap2">
											<select name="searchKeywordType">
												<c:choose>
													<c:when test="${param.searchKeywordType == 'user_id'}">
														<option value="user_id" selected>아이디</option>
														<option value="user_name">이름</option>
														<option value="tel">전화번호</option>
													</c:when>
													<c:when test="${param.searchKeywordType == 'user_name'}">
														<option value="user_id">아이디</option>
														<option value="user_name" selected>이름</option>
														<option value="tel">전화번호</option>
													</c:when>
													<c:when test="${param.searchKeywordType == 'tel' }">
														<option value="user_id">아이디</option>
														<option value="user_name">이름</option>
														<option value="tel" selected>전화번호</option>
													</c:when>
													<c:otherwise>
														<option value="selected" selected>선택</option>
														<option value="user_id">아이디</option>
														<option value="user_name">이름</option>
														<option value="tel">전화번호</option>
													</c:otherwise>
												</c:choose>
											</select>
										</div>
										<div class="input_text_wrap input_wrap2">
											<label> <input type="text" name="searchKeyword"
												value="${param.searchKeyword}" placeholder="검색어를 입력하세요"
												required="required">
											</label>
										</div>
									</li>
									<li class=bt_wrap><a href="#" onclick="formSubmit();"
										class="on">검색</a> <a href="#" onclick="formSubmit2();"
										class="on">삭제</a></li>
								</ul>
							</div>
						</form>
					</div>

					<!----------------------------------- 회원관리 멤버 검색창 ------------------------------------>

					<!------------------------------ 회원 멤버 리스트----------------------------- -->


					<form id="frm2" action="/kgCoffee/admin/deleteMember.do"
						method="get" enctype="multipart/form-data">

						<div class="board_list_wrap">
							<div class="board_list">
								<div class="top">
									<div class="check">
										<input type="checkbox" class="check" name="checkId"
											id="allCheck">
									</div>
									<div class="id">아이디</div>
									<div class="pass">패스워드</div>
									<div class="name">이름</div>
									<div class="birth">생일</div>
									<div class="tel">전화번호</div>
								</div>


								<div>
									<c:forEach var="li" items="${list}">
										<div class="check">
											<input type="checkbox" class="check" name="checkId"
												value="${li.user_id}">
										</div>
										<div class="id">
											<a href="/kgCoffee/admin/getOne.do?user_id=${li.user_id}">${li.user_id}</a>
										</div>
										<div class="pass">
											<input type="password" value="${li.user_pw}" readonly>
										</div>
										<div class="name">${li.user_name}</div>
										<div class="birth">${li.birthday}</div>
										<div class="tel">${li.tel}</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</form>

					<jsp:include page="../../paging/paging6.jsp">
						<jsp:param value="${paging.page}" name="page" />
						<jsp:param value="${paging.beginPage}" name="beginPage" />
						<jsp:param value="${paging.endPage}" name="endPage" />
						<jsp:param value="${paging.prev}" name="prev" />
						<jsp:param value="${paging.next}" name="next" />
					</jsp:include>



				</div>
			</div>
		</div>

	</section>


	<script>
	
	$("#user_management").toggleClass("active");
    
		$(document).ready(function() {
			$("#allCheck").change(function() {
				if (this.checked) {
					$(".check").prop("checked", true);
				} else {
					$(".check").prop("checked", false);
				}
			});
		});

		var msg = "${msg}";

		if (msg) {

			if (msg === "delete-success") {

				alert("삭제 되었습니다.")
			}

		}
	</script>

</body>
</html>