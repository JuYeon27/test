
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<!-- CSS only -->
<link rel="stylesheet" type="text/css" href="/kgCoffee/css/menu.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="/kgCoffee/css/header_style.css" type="text/css">
<script src="/kgCoffee/js/menuModal.js" defer></script>
 <script src="/kgCoffee/js/menuType.js" defer></script>
<script src="/kgCoffee/js/menuBasket.js" defer></script>
</head>
<body>
	<%@include file="/include/header.jsp" %>

	<!-- ==============서브 헤더====================== -->
	<section class="about" id="about">
		<h1 class="heading"></h1>
		<div class="row">
			<div class="video-container">
				<video src="http://localhost:8080/kgCoffee/img/menuImg/coffee.mp4"
					loop autoplay muted></video>
				<h3>Best Coffee</h3>
			</div>

			<div class="content">
				<h3>KG 커피는</h3>
				<p>저희 KG커피는 자연산 콜롬비아 원두를 사용하고 있으며 전통방법으로 원두를 조리하고 있습니다</p>
			</div>
		</div>

	</section>

	<!-- ==============타이틀================= -->
	<section class="products" id="products">
		<div class="heading">
			<h1>KG MENU</h1>
			<div class="text">
				<input type="checkbox" data-type="menuAll" data-value="All" checked/> 전체메뉴 &nbsp; <input
					type="checkbox" data-type="caffeineType" data-value="decaffeine" /> 디카페인
				&nbsp; <input data-type="menuType" type="checkbox" data-value="Ice" />
				아이스 &nbsp; <input type="checkbox" data-type="menuType" data-value="Hot" /> 핫
				&nbsp; <input type="checkbox" data-type="menuType" data-value="Food"/> 디저트
			</div>
		</div>
		
	<!-- ===== 메뉴 목록 ===== -->
		<div class="page_wrap">
			<%@include file="/view/menu/menuGetpage.jsp"%>
		</div>

		
		
	</section>
	<script>
	
		const loginUser = "${loginUser}"
		console.log(loginUser)
	</script>

	<%@include file="/include/footer.jsp"%>

</body>
</html>