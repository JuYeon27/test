/**
 * 
 */

function addBasketEvent(){
	
//장바구니 클릭 이벤트
$(".basket_btn").click(menuBasket);

}
  
function menuBasket() {

	
	
	if(!(loginUser)){
		
		alert("로그인이 필요합니다");
		return;
	
	}



	var reqUrl = "/kgCoffee/order/cart/insert";
	var menuId = $(this).parent().data("menu-id");
	var amount = $(this).prev().val();



	let param = "menuId="+menuId+"&menuAmount="+amount;

	$.ajax({
		url: reqUrl,
		type: "POST",
		
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		data:param,
		success: function(data) {

			
			var msg = data.trim();
	
			if(!(msg===null)){

				if(msg==="update-success"){
					alert("장바구니에 추가되었습니다.");

				}else if(msg==="create-success"){

					alert("새상품이 장바구니에 추가되었습니다.");

				}else if(msg==="empty_amount"){
					alert("수량을 입력해주세요");

				}

			}

		}


	})
}


addBasketEvent();