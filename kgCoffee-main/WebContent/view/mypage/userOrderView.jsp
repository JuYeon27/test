<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage</title>

<link rel="stylesheet" href="/kgCoffee/css/header_style.css" type="text/css">
<link rel="stylesheet" href="/kgCoffee/css/userOrderList.css?after" type="text/css">
<link rel="stylesheet" href="/kgCoffee/css/userOrderView.css?after" type="text/css">

</head>
<body>
	
	<%@include file="../../include/header.jsp" %>
	
	<section class="mypage" id="mypage">
		
		<div class="payment">
		
			<ul class="paymentList">
				<li class="page_name">
					<h3> 주문 상세 페이지 </h3>
				</li>
			</ul>
			
			
			<ul class="paymentList">
			
			<!-- --------------------------------------------------- -->
			<!-- <c/:forEach var="o1" items="$/{orderList1}"> -->
			
				<li class="orderOneView">
					<div class="itme-one">
						
						<div class="order_date_view">
							<strong>2023-06-22 주문</strong> <!-- 주문일 ${c1.order_date} -->
						</div>
						
						<!-- 구입한 상품정보들 -->
						
						<div class="product_content">
							<div class="product_content_list">
								<img alt="상품1" src="../../img/cafe-1.jpg" >
							</div>
							
							<div class="product_content_list">
								<div class="content_list_box">
									<h3 class="menu_name">상품이름</h3><!-- 각상품들의 이름 -->
									<a class="order_total">15,000원</a><!-- 각 상품들의 가격 -->
									<a class="order_total">1개</a><!-- 각상품들의 수량 -->
								</div>
							</div>
						</div>
						
						<!-- ----------------------------------------------- -->
						
						<hr class="hr_view">
						
						<!-- 결제정보 -->
						
						<div class="order_total_view">
							<strong>결제 정보</strong><br>
							<h2>지점명 : 종로점</h2>
							<h2>총 수량 : 1개</h2>
							<h2>총 금액 : 15,000원</h2>
						</div>
						
						<!-- ----------------------------------------------- -->
						
						<div>
							<button type="button" class="btn_order_list" id="go-back">뒤로 가기</button>
						</div>
						
					</div>
				</li>	
			
			</ul>
			
		</div> <!-- payment div end -->
		
	</section>
	
	<%@include file="../../include/footer.jsp" %>
	
	<script>
		document.getElementById('go-back').addEventListener('click', () => {
			window.history.back();
		});
	</script>
	
</body>
</html>