<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
 response.setHeader("Cache-Control","no-cache");
 response.setHeader("Pragma","no-cache");
 response.setDateHeader("Expires",0);
%>

	<!DOCTYPE html>
	<html>

	<head>
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
			integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<link rel="stylesheet"
			href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
			rel="stylesheet"> 


		<!-- 아이콘 가져오기 -->
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css"/>
		<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">

		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4053743493582ba95036931859f08a59&libraries=services"></script>
		<link rel="stylesheet" href="/kgCoffee/css/kakaoMap.css?ver=220610" type="text/css">
		<link rel="stylesheet" type="text/css" href="/kgCoffee/css/header_style.css">
		<link rel="stylesheet" href="/kgCoffee/css/sub_header.css?ver=1" type="text/css">
		<script src="/kgCoffee/js/main_script.js" defer></script>
		<script type="text/javascript" src="/kgCoffee/js/kakakoMap.js" defer></script>
	</head>
 

	<body>

		<%@include file="../../include/header.jsp" %>

			<div class="sub_header_wrap">


				<div class="sub_header">
					<div class="sub_header_title_wrap">
						<div class="sub_header_title_info">
							STORE </div>
						<div class="sub_header_title robo color">
							<h2>KG STORE</h2>
						</div>
					</div>
					<div class="sub_header_text_wrap">
						<div class="sub_header_text sub_header_list_map">
							<ul>
								<li>
									홈
								</li>
								<li>
									매장 </li>
								<li>
									매장찾기 </li>
							</ul>
						</div>
					</div>
				</div>
   

			</div>
			<div class="map_wrap">
				<section class="find_store_wrap">

					<div class="find_store_cont" class="bg_white">
						<div id="menu_wrap">
							<div class="find_store_header">
								<ul>
									<li class="btn_find_store menu_toggle active">
										매장찾기
									</li>
									<li class="btn_find_location menu_toggle">
										지역검색
									</li>

								</ul>

							</div>
							<article class="search bg_white menu_toggle active">
								<div class="quick_search">
									<div class="quick_search_title">
										<b>매장찾기</b>
									</div>
									<div class="quick_search_content">
										함께하는 KG커피
									</div>
									<div class="quick_search_inner_wrap">
										<div class="quick_search_inner">
											<input placeholder="지점명 또는 주소를 입력하세요" title="퀵 검색" type="text"
												name="keyword" id="keyword"> <a href="javascript:void(0);"
												class="quickSearchBtn"><i class="bi bi-search"></i></a>
										</div>
									</div>
								</div>


								<div class="search_count">검색결과 없음</div>
								<article class="search_result">
									<ul id="placesList"></ul>
									<div id="pagination"></div>
								</article>
							</article>
							<article class="location menu_toggle bg_white">
								<div class="location_title_wrap">
									<div class="location_title">
										<b>지역검색</b>
									</div>
									<div class="location_content">
										함께하는 KG커피
									</div>
								</div>
								<div class="location_list_wrap">
									<div class="location_list">
										<ul class="store_area_search_list area_list">
											<li data-store-area-name="서울"> 서울 </li>
											<li data-store-area-name="경기"> 경기 </li>
											<li data-store-area-name="인천"> 인천 </li>
											<li data-store-area-name="강원"> 강원 </li>
											<li data-store-area-name="광주"> 광주 </li>
											<li data-store-area-name="대전"> 대전 </li>
											<li data-store-area-name="대구"> 대구 </li>
											<li data-store-area-name="부산"> 부산 </li>
											<li data-store-area-name="울산"> 울산 </li>
											<li data-store-area-name="세종"> 세종 </li>
											<li data-store-area-name="경남"> 경남 </li>
											<li data-store-area-name="경북"> 경북 </li>
											<li data-store-area-name="전남"> 전남 </li>
											<li data-store-area-name="전북"> 전북 </li>
											<li data-store-area-name="충남"> 충남 </li>
											<li data-store-area-name="충북"> 충북 </li>
											<li data-store-area-name="제주"> 제주 </li>
										</ul>
										<ul class="store_area_search_list_result area_list off">
											<li class="clear_btn" data-area="clear">뒤로가기</li>
										</ul>
									</div>

								</div>

							</article>




						</div>


					</div>

				</section>
				<!-- 지도를 표시할 div 입니다 -->
				<div id="map"></div>







			</div>


			<%@include file="../../include/footer.jsp" %>

	</body>

	</html>