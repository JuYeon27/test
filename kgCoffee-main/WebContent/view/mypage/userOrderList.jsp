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

<style>

</style>
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


			<ul class="paymentList">

				<!-- --------------------------------------------------- -->
				<c:forEach var="dto" items="${dtoList}">
					<li class="paymentItem">
						<div class="item">

							<div class="order_date">
								<strong>${dto.order.orderDate} 주문</strong>
								<!-- 주문일 -->
							</div>

							<div class="product_content">
								<div class="product_content_list">
									<img alt="상품1"
										src="/kgCoffee/img/menuImg/${dto.paymentsList[0].fileName}">
									<!-- 첫번째 menu_imgurl -->
								</div>

								<div class="product_content_list">
									<div class="content_list_box">
										<h3 class="menu_name">${dto.paymentsList[0].menuName}</h3>

											<c:set var="placeName" value="${dto.order.placeName}" /> <!-- 지점명 자르기 위해서 -->
                                   	 		<c:set var="pLen" value="${fn:length(placeName)}" /> <!-- 지점명 자르기 위해서 -->
										<a class="place_name">${fn:substring(placeName,8,pLen)}</a>

										<a class="order_total">총 금액 : ${dto.order.totalPrice} 원</a>
										<button type="button" class="btn_refund_order" data-total-price="${dto.order.totalPrice}" data-imp-uid="${dto.order.impUid}">환불하기</button>
									</div>	
								</div>
							</div>

							<div>
								<button type="button" class="btn_order_list" onclick="arccodionMenu(${dto.order.orderId})">주문 상세보기</button>
							</div>


						</div> 
						
						
						<div id="content${dto.order.orderId}" class="content">
							<ul class="paymentList">
								<c:set var="totalAmount" value="0" />

								<!-- --------------------------------------------------- -->
								<c:forEach var="payments" items="${dto.paymentsList}">

									<li class="orderOneView">
										<div class="itme-one">

											<!-- 구입한 상품정보들 -->
											<div class="product_content">
												<div class="product_content_list">
													<img alt="상품1"
														src="/kgCoffee/img/menuImg/${payments.fileName}">
												</div>

												<div class="product_content_list">
													<div class="content_list_box">
														<h3 class="menu_name">${payments.menuName}</h3>
														<!-- 각상품들의 이름 -->
														<a class="order_total">${payments.menuPrice} 원</a>
														<!-- 각 상품들의 가격 -->
														<a class="order_total">${payments.menuAmount} 개</a>
														<!-- 각상품들의 수량 -->
													</div>
												</div>
											</div>

										</div>
									</li>
									<c:set var="totalAmount"
										value="${totalAmount + payments.menuAmount }" />

								</c:forEach>
								<!-- <hr class="hr_view"> -->
								<div class="order_total_view">
									<strong>[ 결제 정보 ]</strong><br>
									<p>지점명 : ${fn:substring(placeName,8,pLen)}</p>
									<p>총 수량 : ${totalAmount} 개</p>
									<p>총 금액 : ${dto.order.totalPrice} 원</p>
								</div>
							</ul>


						</div>
						

					</li>
				</c:forEach>
				<!-- --------------------------------------------------- -->

			</ul>
			
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
	
		var temp = "${paging}";
		console.log(temp);
		
		$(".btn_refund_order").click(function refundOrder(){

			if(confirm("해당 주문을 환불하시겠습니까?")){

				let imp_uid = $(this).data("imp-uid");
				let total_price= $(this).data("total-price");

				let reqUrl = "/kgCoffee/order/api/refund.do";

				console.log(imp_uid)

				
				console.log(total_price)

				$.ajax({
                    type : "POST",
                    url : reqUrl,
                    data : {
                        // 저장할 pay 정보 넣어서 서버 보내서 DB 테이블 저장
                        imp_uid : imp_uid,
                        total_price: total_price,
 

                    },
                    success:function(res_data) {

                    	
                        var res = JSON.parse(res_data);
                        
                        console.log(res);
						
						var msg = res.msg;
						console.log(msg);
                        if(!(msg===null)){
    
                            if(msg==="refund-success"){
                                alert("환불이 완료되었습니다.");
								location.reload(true);
    
                            }else if(msg==="delete-faild"){
    
                                alert("결제 실패.");
    
                            }else if(msg==="refund-faild"){
                                alert("refund-faild");
    
                            }
    
                        }
    
                    }
                })

				


			}
			
			

		})

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