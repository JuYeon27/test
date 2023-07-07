
/* join 유효성 검사 */

function check() {

	var pw = $("#pw").val();
	var id = $("#id").val();
	var name = $("#name").val();
	var tel = $("#tel").val();
	
 	var num = pw.search(/[0-9]/g);
	var eng = pw.search(/[a-z]/ig);
	var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	
	var checkNumber = id.search(/[0-9]/g);
	var checkEnglish = id.search(/[a-z]/ig);
	
	var nameReg = /^[ㄱ-ㅎ|가-힣]+$/;
	
	var telReg = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;

	/* 아이디 */
	/* 영문,숫자 혼합 */
	if(id.length < 5 || id.length > 10) {
		alert("아이디를 5자리 ~ 10자리 이내로 입력해주세요.");
		return false;
	} else if(checkNumber < 0 || checkEnglish < 0){
		alert("아이디에 숫자와 영문자를 혼합하여 입력해주세요.");
		return false;
	}
	
	
	/* 비밀번호 */
	/* 영문,숫자,특수문자 중 2가지 혼합 (영문,숫자 = 통과) (특문,숫자 = 통과) */
	if(pw.length < 5 || pw.length > 10) {
		alert("비밀번호를 5자리 ~ 10자리 이내로 입력해주세요.");
		return false;
	} else if(pw.search(id) > -1) {
		alert("비밀번호에 아이디가 포함되었습니다.");
		return false;
	} else if( (num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0) ){
		alert("비밀번호에 영문, 숫자, 특수문자 중 2가지 이상을 혼합하여 입력해주세요.");
		return false;
	}
	
	
	/* 이름 */
	if(!nameReg.test(name)) {
		alert("이름은 한글로 입력해주세요.");
		return false;
	}
	
	
	/* 전화번호 */
	if(!telReg.test(tel)) {
		alert("전화번호 형식으로 입력해주세요.");
		return false;
	} else {
		return true;
	}
	
}
