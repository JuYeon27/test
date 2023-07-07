


function formSubmit(){
	document.getElementById("frm1").submit();
}


function formSubmit2(){

	var temp=confirm("정말 삭제하시겠습니까?");

	if(temp){
	document.getElementById("frm2").submit();
	}
	
	
}


