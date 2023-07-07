function addModal(){
	  $(".box").click(function (event) {

         event.stopPropagation();  
         
		
         $(this).children(".inner_modal").addClass("active")


      });

      $(".inner_close").click(function (event) {

         event.stopPropagation();
         event.preventDefault();
         var $modal = $(this).parent().parent();



         $modal.removeClass("active");


      });
}


addModal();