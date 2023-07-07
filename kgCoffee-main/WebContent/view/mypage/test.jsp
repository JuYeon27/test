<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage</title>

<link rel="stylesheet" href="/kgCoffee/css/header_style.css"
	type="text/css">
<link rel="stylesheet" href="/kgCoffee/css/userOrderList.css?after"
	type="text/css">
<link rel="stylesheet" href="/kgCoffee/css/userOrderView.css?after"
	type="text/css">

<script src="/kgCoffee/js/jquery-3.7.0.min.js"></script>
<!-- jquery -->

</head>
<body>

	<%@include file="../../include/header.jsp"%>

	<section class="mypage" id="mypage">

		<div class="payment">

			<ul class="paymentList">
				<li class="select_btn_list">
					<div class="select_btn">
						<button type="button" class="btn_select_list"
							onclick="location.href='#'">주문 상세 리스트</button>
					</div>
					<div class="select_btn">
						<button type="button" class="btn_select_list"
							onclick="location.href='/kgCoffee/view/mypage/userModifyCk.jsp'">개인정보
							확인/수정</button>
					</div>
				</li>
			</ul>


				<!-- --------------------------------------------------- -->
	<ul class="paymentList">
		<% int i = 0; %>
			<% for(i=1; i>100; i++) { %>
				<!-- --------------------------------------------------- -->
					
					<li class="paymentItem">
						<div class="item">

							<div class="order_date">
								<strong>2023-06-27 주문</strong>
								<!-- 주문일 -->
							</div>

							<div class="product_content">
								<div class="product_content_list">
									<img alt="상품1" src="/kgCoffee/img/menuImg/ame.jpg">
									<!-- 첫번째 menu_imgurl -->
								</div>

								<div class="product_content_list">
									<div class="content_list_box">
										<h3 class="menu_name">아메리카노 0</h3>

											 <!-- 지점명 자르기 위해서 -->
                                   	 		 <!-- 지점명 자르기 위해서 -->
										<a class="place_name">장한평역점</a>

										<a class="order_total">총 금액 : 196 원</a>
									</div>
								</div>
							</div>

							<div>
								<button type="button" class="btn_order_list" onclick="arccodionMenu(4)">주문 상세보기</button>
							</div>


						</div> 
						
						
						<div id="content4" class="content show">
							<ul class="paymentList">
								

								<!-- --------------------------------------------------- -->
								

									<li class="orderOneView">
										<div class="itme-one">

											<!-- 구입한 상품정보들 -->
											<div class="product_content">
												<div class="product_content_list">
													<img alt="상품1" src="/kgCoffee/img/menuImg/ame.jpg">
												</div>

												<div class="product_content_list">
													<div class="content_list_box">
														<h3 class="menu_name">아메리카노 0</h3>
														<!-- 각상품들의 이름 -->
														<a class="order_total">0 원</a>
														<!-- 각 상품들의 가격 -->
														<a class="order_total">2 개</a>
														<!-- 각상품들의 수량 -->
													</div>
												</div>
											</div>

										</div>
									</li>
									

								

									<li class="orderOneView">
										<div class="itme-one">

											<!-- 구입한 상품정보들 -->
											<div class="product_content">
												<div class="product_content_list">
													<img alt="상품1" src="/kgCoffee/img/menuImg/ame.jpg">
												</div>

												<div class="product_content_list">
													<div class="content_list_box">
														<h3 class="menu_name">아메리카노 1</h3>
														<!-- 각상품들의 이름 -->
														<a class="order_total">1 원</a>
														<!-- 각 상품들의 가격 -->
														<a class="order_total">3 개</a>
														<!-- 각상품들의 수량 -->
													</div>
												</div>
											</div>

										</div>
									</li>
									

								

									<li class="orderOneView">
										<div class="itme-one">

											<!-- 구입한 상품정보들 -->
											<div class="product_content">
												<div class="product_content_list">
													<img alt="상품1" src="/kgCoffee/img/menuImg/ame.jpg">
												</div>

												<div class="product_content_list">
													<div class="content_list_box">
														<h3 class="menu_name">아메리카노 2</h3>
														<!-- 각상품들의 이름 -->
														<a class="order_total">2 원</a>
														<!-- 각 상품들의 가격 -->
														<a class="order_total">4 개</a>
														<!-- 각상품들의 수량 -->
													</div>
												</div>
											</div>

										</div>
									</li>
									

								

									<li class="orderOneView">
										<div class="itme-one">

											<!-- 구입한 상품정보들 -->
											<div class="product_content">
												<div class="product_content_list">
													<img alt="상품1" src="/kgCoffee/img/menuImg/ame.jpg">
												</div>

												<div class="product_content_list">
													<div class="content_list_box">
														<h3 class="menu_name">아메리카노 3</h3>
														<!-- 각상품들의 이름 -->
														<a class="order_total">3 원</a>
														<!-- 각 상품들의 가격 -->
														<a class="order_total">5 개</a>
														<!-- 각상품들의 수량 -->
													</div>
												</div>
											</div>

										</div>
									</li>
									

								

									<li class="orderOneView">
										<div class="itme-one">

											<!-- 구입한 상품정보들 -->
											<div class="product_content">
												<div class="product_content_list">
													<img alt="상품1" src="/kgCoffee/img/menuImg/ame.jpg">
												</div>

												<div class="product_content_list">
													<div class="content_list_box">
														<h3 class="menu_name">아메리카노 4</h3>
														<!-- 각상품들의 이름 -->
														<a class="order_total">4 원</a>
														<!-- 각 상품들의 가격 -->
														<a class="order_total">6 개</a>
														<!-- 각상품들의 수량 -->
													</div>
												</div>
											</div>

										</div>
									</li>
									

								

									<li class="orderOneView">
										<div class="itme-one">

											<!-- 구입한 상품정보들 -->
											<div class="product_content">
												<div class="product_content_list">
													<img alt="상품1" src="/kgCoffee/img/menuImg/ame.jpg">
												</div>

												<div class="product_content_list">
													<div class="content_list_box">
														<h3 class="menu_name">아메리카노 5</h3>
														<!-- 각상품들의 이름 -->
														<a class="order_total">5 원</a>
														<!-- 각 상품들의 가격 -->
														<a class="order_total">7 개</a>
														<!-- 각상품들의 수량 -->
													</div>
												</div>
											</div>

										</div>
									</li>
									

								

									<li class="orderOneView">
										<div class="itme-one">

											<!-- 구입한 상품정보들 -->
											<div class="product_content">
												<div class="product_content_list">
													<img alt="상품1" src="/kgCoffee/img/menuImg/ame.jpg">
												</div>

												<div class="product_content_list">
													<div class="content_list_box">
														<h3 class="menu_name">아메리카노 6</h3>
														<!-- 각상품들의 이름 -->
														<a class="order_total">6 원</a>
														<!-- 각 상품들의 가격 -->
														<a class="order_total">8 개</a>
														<!-- 각상품들의 수량 -->
													</div>
												</div>
											</div>

										</div>
									</li>
									

								

									<li class="orderOneView">
										<div class="itme-one">

											<!-- 구입한 상품정보들 -->
											<div class="product_content">
												<div class="product_content_list">
													<img alt="상품1" src="/kgCoffee/img/menuImg/ame.jpg">
												</div>

												<div class="product_content_list">
													<div class="content_list_box">
														<h3 class="menu_name">아메리카노 7</h3>
														<!-- 각상품들의 이름 -->
														<a class="order_total">7 원</a>
														<!-- 각 상품들의 가격 -->
														<a class="order_total">9 개</a>
														<!-- 각상품들의 수량 -->
													</div>
												</div>
											</div>

										</div>
									</li>
									

								
								<!-- <hr class="hr_view"> -->
								<div class="order_total_view">
									<strong>[ 결제 정보 ]</strong><br>
									<p>지점명 : 장한평역점</p>
									<p>총 수량 : 44 개</p>
									<p>총 금액 : 196 원</p>
								</div>
							</ul>


						</div>
						

					</li>
					
					
					<% } %>
				<!-- --------------------------------------------------- -->

			</ul>
							


				<!-- ---------------------------------------------------------------- -->
			

		</div>
		<!-- payment div end -->

	</section>
	<!-- mypage section end -->

	<jsp:include page="../../paging/pagingOrderList.jsp">
		<jsp:param value="${paging.page}" name="page" />
		<jsp:param value="${paging.beginPage}" name="beginPage" />
		<jsp:param value="${paging.endPage}" name="endPage" />
		<jsp:param value="${paging.prev}" name="prev" />
		<jsp:param value="${paging.next}" name="next" />
	</jsp:include>


	<%@include file="../../include/footer.jsp"%>

	<script>
	
		var temp = "${dtoList}";
		console.log(temp);

		
		//아코디언 메뉴 클릭 이벤트
		function arccodionMenu(orderId){
		    $("#content"+orderId).toggleClass("show");
		}
		
		
		//회원 수정 후 메세지
		console.log('${update_msg}');
		const update_msg = '${update_msg}';

		if (update_msg === 'update-success') {
			alert('${loginUser.user_name}' + '님 수정 되었습니다.');
		} else if (update_msg === 'update-failed') {
			alert('${loginUser.user_name}' + '님 수정을 실패했습니다. 다시 입력해주세요.');
			history.back();
		}
		
	</script>

</body>
</html>