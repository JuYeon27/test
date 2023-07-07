<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>관리자 페이지</title>

<link rel="stylesheet" href="/kgCoffee/css/adminMain.css?after">
<link rel="stylesheet" href="/kgCoffee/css/adminNav.css?after">

<link rel="stylesheet" href="/kgCoffee/css/adminMenu.css">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<script src="/kgCoffee/js/menuModal.js" defer></script>
<script src="/kgCoffee/js/adminMenuType.js" defer></script>
<script src="/kgCoffee/js/menuBasket.js" defer></script>



<style type="text/css">


.content_section {

	height: auto;
}



.heading{


	width:900px;

	

}

</style>
</head>
<body>

	<div class="wrap">

		<!-- nav-start ------------------------------------------------------- -->
		<%@ include file="adminSide.jsp" %>
		<!-- nav-end -------------------------------------------------------- -->


		<section class="content_section">

			<div class="content_hedear_wrap">
				<div class="content_header">
					<div class="header_title">메뉴 관리 페이지
					<input type="button" value="메뉴추가" class="menuInsert-btn" onclick="location.href='/kgCoffee/admin/menuInsertForm.do'" />
					
					</div>
				</div>
			</div>

			<div class="content_wrap">
				<div class="content">
					<%-- <%@include file="/view/menu/adminMenu.jsp"%>--%>
		<div class="heading">
			<h1>KG MENU</h1>
			<div class="text">
			<div  class="menu-inset-serch">
				<form action="/kgCoffee/admin/adminSearchMenu.do">
					<input type="text" placeholder="메뉴검색" class="menuName" name="menuName" value="${menuName}"/>
					<input type="submit" value="검색" class="serch-btn" />
				</form>
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
				</div>
			</div>
		</section>
	</div>
	
	
	<script>
	
	//아코디언 메뉴 클릭 이벤트
	function arccodionMenu(){
		
		console.log($(".select-chart"));
	    $(".select-chart").toggleClass("show");
	    
	    
	}
	
	$(".nav_menu li").click(function(){
		event.stopPropagation();
		
		$(".active").removeClass("active");
		
		$(this).toggleClass("active");
		
		
		
	});
	
		</script>






</body>
</html>