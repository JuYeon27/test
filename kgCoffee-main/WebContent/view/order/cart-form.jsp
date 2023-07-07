
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <!--    <meta name="viewport" content="width=device-width, initial-scale=1.0">-->
    <title>장바구니</title>
<link rel="stylesheet" href="/kgCoffee/css/header_style.css" type="text/css">
    <style>
        /* 테이블 가운데 정렬 */
        table {
            margin-left: auto;
            margin-right: auto;
            width: 80%; /* 테이블 너비 조정 */
        }
        table td {
            text-align: center;
        }
        /* 가운데 정렬 */
        .center-align {
            text-align: center;
        }

        .buttons .form_wrap{

            margin: 0 auto;
        }

        
        .buttons .form_wrap form{

            display: inline-block;
        }

    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="/kgCoffee/js/cart-form.js" defer></script>
    <script src="https://kit.fontawesome.com/efb53b9ad1.js" defer crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="/kgCoffee/css/cart-form.css">
</head>
<body>
    <%@include file="/include/header.jsp" %>
<div class="container" style="justify-content: center">
    <h1 style="text-align: center">장바구니</h1>
    <label class="font">주문 상품</label>
    <div class="form-group">
        <c:forEach var="item" items="${cartList}" varStatus="status">
            <div class="product-info" id="ix${status.count}">
                <div class="product-info-first">
                    <img src="/kgCoffee/img/menuImg/${item.fileName}" width="70">
                </div>
                <div class ="product-info-middle" style="flex-grow: 1; width: max-content">
                    <div>
                        <div class="product-info-name">${item.menuName}</div>
                        <div class="product-info-count">
                            <span onclick="javascript:basket.changePNum(${status.count}, ${item.cartId}, ${item.menuPrice})" style="margin-right: 15px"><i class="fa-solid fa-minus minus"></i></span>
                            <input type="text" name="p_num${status.count}" id="p_num${status.count}" 
                            size="2" maxlength="4" class="p_num" value="${item.menuAmount}" 
                            onkeyup="javascript:basket.changePNum(${status.count});">
                            <span onclick="javascript:basket.changePNum(${status.count}, ${item.cartId}, ${item.menuPrice})" style="margin-left: 15px"><i class="fa-solid fa-plus plus"></i></span>
                        </div>
                    </div>
                </div>
                <div class="product-info-last">
                    <button class="delete_button fa-solid fa-xmark" onclick="javascript:basket.delItem(${status.count}, ${item.cartId})"></button>
                    <div class="product-info-fee" id="amount${status.count}" style="margin-top: 40px">${item.menuPrice * item.menuAmount}원</div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div style="display:flex; margin-top: 50px">
        <p class="font" style="width: 50%">상품 금액</p>
        <p style="width: 50%" class="red" id="sum_p_price">${totalPrice}원</p>
    </div>
</div>
<div class="buttons" style="display: flex">
    <div class="form_wrap">

        <form action="/kgCoffee/order/order" method="GET">
            <input type="hidden" name="cartList" value=${cartList}>
            <input type="hidden" name="totalPrice" id="totalPrice" value=${totalPrice}>
            <button type="submit"  onclick="javascript:basket.go_pay_form()">결제하기</button>
        </form>
        <form action="" method="GET">
            <button type="button" onclick="history.back()">뒤로가기</button>
        </form>
    </div>
</div>
<%@include file="/include/footer.jsp"%>

</body>
</html>
