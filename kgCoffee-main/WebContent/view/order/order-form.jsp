
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>결제</title>
                <link rel="stylesheet" href="/kgCoffee/css/header_style.css" type="text/css">
                <link rel="stylesheet" type="text/css" href="/kgCoffee/css/order-form.css">
        
                <script src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
                <script type="text/javascript" src="/kgCoffee/js/order-form.js"></script>
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            </head>

            <body>
                <%@include file="/include/header.jsp" %>
                <div class="container" style="justify-content: center">
                    <h1>주문 상세</h1>
                    <div class="form-group menu-list-form">
                        <label class="font" for="menu-list-wrap">
                            <div class="order-title">
                                <div>주문 상품 </div>
                                <div id="orderName"> ${cartList[0].menuName}
                                    <c:if test="${fn:length(cartList)>1}"> 외 ${fn:length(cartList)-1}건 <div class="open-menu" onclick="arccodionMenu()"></div></c:if>
                                </div>
                            </div>
                        </label>
                        
                        <div class="menu-list-wrap">
                            
                            <c:forEach var="item" items="${cartList}" varStatus="status">
                                <div class="product-info" id="ix${status.count}">
                                    <div>
                                        <img src="/kgCoffee/img/menuImg/${item.fileName}" width="70">
                                    </div>
                                    <div class="product-info-content">
                                        <div>
                                            <div class="product-info-name">${item.menuName}</div>
                                            <div class="product-info-count">
                                                <div id="p_num${status.count}" class="p_num">
                                                    ${item.menuAmount}개
                                                </div>
                                            </div>
                                        </div>
                                        </div>
                                        <div>
                                           
                                            <div class="product-info-fee" id="amount${status.count}"
                                                style="margin-top: 40px">${item.menuPrice * item.menuAmount}원</div>
                                        </div>
                                    
                                </div>
                            </c:forEach>
                       
                        </div>

            

                    </div>


                    <div class="form-group store_list_form">
                        <label class="font" for="request">매장 선택</label>

                        <div style="display: flex" class="store_list_wrap">
                            <input id="store_list" name="store_list" list="store_list_data" type="text">
                            <datalist id="store_list_data" class="store_list_data">
                                <c:forEach var="store" items="${storeList}" varStatus="status">
                                    <c:set var="placeName" value="${store.placeName}" />
                                    <c:set var="pLen" value="${fn:length(placeName)}" />
                              
                                    <option data-val=${store.mapId} id="${fn:substring(placeName,8,pLen)}"
                                        value="${fn:substring(placeName,8,pLen)}" label="${store.roadAddressName }">
                                        </option>

                                </c:forEach>

                            </datalist>
                            </input>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="font" for="request">요청 사항</label>
                        <textarea id="request" rows="2" placeholder="요청 사항을 입력하세요"></textarea>
                    </div>




                    <div class="form-group">
                        <label class="font">매장 방문 여부</label>
                        <div style="display: flex">
                            <input type="radio" id="eat-in" name="visit-type" value="매장 먹고 가기" checked>
                            <p for="eat-in">매장에서 먹고 갈게요</p>
                        </div>
                        <div style="display: flex">
                            <input type="radio" id="take-out" name="visit-type" value="포장">
                            <p for="take-out">포장해주세요</p>
                        </div>
                    </div>
                    <div>

                        <div class="form-group" style="flex-wrap: wrap; display: flex;">
                            <label style="width: 100%;" class="font">결제 수단</label>


                            <div style="display: flex; width: 50%">
                                <input type="radio" id="kakaopay" name="payment-method" value="kakaopay" checked>
                                <p for="kakao-pay">카카오페이</p>
                            </div>
                            <div style="display: flex; width: 50%">
                                <input type="radio" id="kcp" name="payment-method" value="kcp">
                                <p for="credit-card">신용카드</p>
                            </div>
                            <div style="display: flex; width: 50%">
                                <input type="radio" id="payco" name="payment-method" value="payco">
                                <p for="payco">페이코</p>
                            </div>
                            <div style="display: flex; width: 50%">
                                <input type="radio" id="tosspay" name="payment-method" value="tosspay">
                                <p for="tosspay">토스페이</p>
                            </div>
                        </div>
                    </div>


                    <div class="total-price" style="display:flex; text-align: center; justify-content: center">
                        <p class="font">결제 금액: &nbsp</p>
                        <p class="font total-price-value">${totalPrice}</p><p class="font">원</p>
                    </div>
                </div>
                <div class="buttons">
                    <button id="pay-button"
                        onclick="javascript:order.go_pay('${userId}', '${totalPrice}', '${userId}')">결제하기</button>
                    <button id="back-button" onclick="history.back()">뒤로가기</button>
                </div>


<%@include file="/include/footer.jsp"%>
            </body>

            </html>