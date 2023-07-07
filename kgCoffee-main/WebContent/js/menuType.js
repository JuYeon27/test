/**
 * 
 */

	/*메뉴리스트 비동기처리*/

$(".text > input").change(searchEvent);
	
	
	function searchEvent(n){
		if(!(n>0)){
			n=1;
		}






		var reqUrl = "/kgCoffee/menu/getSerchmenu.do?page="+n+"&amount=12&displayPage=10";
		
		$("input:checkbox").each(function(index) {
			if ($(this).is(":checked") == true) {

				
				
				
			
				 reqUrl += "&"+$(this).data("type") + "=" + $(this).data("value");
				 
				 
				
			}

		})
		
		

				console.log(reqUrl);
				$.ajax({
					url : reqUrl,
					type : "GET",
					data : 'JSON',
					success : function(data) {
						
						
						
						
						
						
						var newEl = data;
						
						
						
						$("div.page_wrap").html(data.toString());
							
						$(".active-li").removeClass("active-li");
						$(".page"+n).addClass("active-li");

						addModal();
						addBasketEvent();
					
						

			}})


	} 