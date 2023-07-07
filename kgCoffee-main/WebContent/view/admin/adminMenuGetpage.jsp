<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>

		<head>
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
				integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
				crossorigin="anonymous">

			<!--================= 메뉴 리스트 페이지 =======================-->

			<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		</head>
		<div class="box-container">

			<c:forEach var='vo' items="${alist}">
				<div class="box">
					<span class="discount">${vo.menuType }</span>
					<div class="image">
						<img src="http://localhost:8080/kgCoffee/img/menuImg/${vo.fileName }" alt="" />
					</div>
					<div class="menu_content">
						<h3>${vo.menuName }</h3>
						<div class="price">${vo.menuPrice }</div>
					</div>


					<div class="inner_modal">
						<div class="close_wrap">
							<div class="inner_close">&times;</div>
						</div>
						<div class="inner_modal_text1">
							<b>${vo.menuName }</b>
						</div>
						<div class="inner_modal_text2">가격: ${vo.menuPrice }</div>
						<div class="inner_modal_text3">${vo.menuExplain }</div>
						<div class="inner_modal_basket" data-menu-id="${vo.menuId}">
							<input type="button" value="수정" class="update-btn" onclick="location.href='adminMenuGetpage.do?menuId=${vo.menuId}';"/>
							<input type="button" value="삭제" class="delete-btn" onclick="location.href='adminMenuDelete.do?menuId=${vo.menuId}';"/>
						</div>
					</div>

				</div>


			</c:forEach>
		</div>

		<!-- ======= 페이징 ================= -->

		<nav aria-label="Page navigation example">
			<ul class="pagination" id="pagination_ul">
				<c:if test="${paging.prev}">
					<li class="page-item prev" onclick=addPageEvent(${paging.beginPage-1})><a class="page-link"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
				</c:if>


				<c:forEach var="n" begin="${paging.beginPage}" end="${paging.endPage}" step="1">
					

					<li data-page-num="${n}" onclick=addPageEvent(${n}) class="page-item page${n} <c:if test='${n eq 1}'>active-li</c:if>"><a class="page-link">${n}</a>
					</li>
				</c:forEach>
				<c:if test="${paging.next}">
					<li class="page-item next" onclick=addPageEvent(${paging.endPage+1})><a class="page-link"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
				</c:if>

			</ul>
		</nav>

		<script>
			function addPageEvent(n) {

				var menuName = $(".menuName").val();
				
				searchEvent(n,menuName);
				

	
			}
			
			var loginUser = "${loginUser}"; 
			console.log(loginUser);

			function update(menuId){
				
				location.href='adminMenuGetpage.do?menuId='+menuId;
				
			}
			
			
			

		</script>