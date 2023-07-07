<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 구글폰트 -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<!-- css 파일 링크 -->
<link rel="stylesheet" type="text/css" href="/kgCoffee/css/header_style.css">
<!-- 아이콘 가져오기 -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">

</head>
<body>
	<div class="header_wrap">
	<header>
		
		<a href="/kgCoffee/view/user/index.jsp" class="logo"><img src="/kgCoffee/img/logo.png" width="180"></a><!-- 로고 이미지 -->
		
		<nav class="navbar">
			<a href="/kgCoffee/view/user/index.jsp">HOME</a>
			<a href="/kgCoffee/menu/getAllmenu.do">MENU</a>
			<a href="/kgCoffee/store/find.map">STORE</a>
			<a href="/kgCoffee/board/getAll.do">NEWS</a>
			<a href="/kgCoffee/order/cart">ORDER</a>
		</nav> 
		
		
		<div class="icons">
		
			<i class='fa fa-bars' id="menu-bar"></i> <!-- 창이 작아지면 메뉴바 생김 -->
			
			<c:choose>
				 <c:when test="${sessionScope.result==1}">
					<a href="/kgCoffee/admin/adminMenu.do" id="admin_page" class='fas fa-user-cog'></a> <!-- 관리자페이지 -->
					<a href="/kgCoffee/user/logout.do" id="logout" class='fas fa-user-slash'></a> <!-- 로그아웃 -->
					<a href="/kgCoffee/voc/getAll.do" id="ask" class='fas fa-headphones-alt'></a> <!-- 고객센터(문의) -->
				</c:when>
				<c:when test="${sessionScope.result==2}">
					<a href="/kgCoffee/mypage/findOrder.do" id="user_page" class='fa fa-user'></a> <!-- 마이페이지 -->
					<a href="/kgCoffee/user/logout.do" id="logout" class='fas fa-user-slash'></a> <!-- 로그아웃 -->
					<a href="/kgCoffee/voc/getAll.do" id="ask" class='fas fa-headphones-alt'></a> <!-- 고객센터(문의) -->
				</c:when>
				<c:otherwise> 
					<a href="/kgCoffee/view/user/login.jsp" id="login" class='fa fa-unlock'></a> <!-- 로그인 --> 
				</c:otherwise>
			</c:choose>
			
		</div>
		
	</header>
	</div>
	
	<!-- 자바스크립트 file link -->
	<script src="/kgCoffee/js/header_script.js"></script>
	

</body>
</html>