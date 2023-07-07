
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지</title>
    <link rel="stylesheet" type="text/css" href="/kgCoffee/css/menu.css">
    <script src="/kgCoffee/js/menuModal.js" defer></script>
 	<script src="/kgCoffee/adminjs/adminMenuType.js" defer></script>
	<script src="/kgCoffee/js/menuBasket.js" defer></script>
</head>
<body>
   <section class="products" id="products">
		<div class="heading">
			<h1>KG MENU</h1>
			<div class="text">
			<div  class="menu-inset-serch">
				<input type="button" value="메뉴추가" class="menuInsert-btn" onclick="location.href='/kgCoffee/view/admin/adminMenuInsert.jsp'" />
				<input type="text" placeholder="메뉴검색" class="menuSerch" value=""/>
				<input type="button" value="검색" class="serch-btn" />
			</div>
				<input type="checkbox" data-type="menuAll" data-value="All" checked/> 전체메뉴 &nbsp; 
				<input type="checkbox" data-type="caffeineType" data-value="decaffeine" /> 디카페인 &nbsp; 
				<input data-type="menuType" type="checkbox" data-value="Ice" />아이스 &nbsp; 
				<input type="checkbox" data-type="menuType" data-value="Hot" /> 핫 &nbsp; 
				<input type="checkbox" data-type="menuType" data-value="Food"/> 디저트
			</div>
		</div>
		
	<!-- ===== 메뉴 목록 ===== -->
		<div class="page_wrap">
			<%@include file="adminMenuGetpage.jsp"%>
		</div>

		
		
	</section> 
   
</body>
</html>



