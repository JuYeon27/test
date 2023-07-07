<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계정정보 찾기</title>

<link rel="stylesheet" href="/kgCoffee/css/header_style.css" type="text/css">
<link rel="stylesheet" href="/kgCoffee/css/findAccountPw.css?after" type="text/css">

<script src="/kgCoffee/js/jquery-3.7.0.min.js"></script> <!-- jquery -->

</head>
<body>
	
	<%@include file="../../include/header.jsp" %>
	
	<section class="modify" id="modify">
		
		<div class="modify-mg">
		
			<ul class="modifyList">
				<li class="modify_name">
					<h3> 계정정보 찾기 </h3>
				</li>
			</ul>
			
			
			<nav class="find-account-tab">
				<ul class="find-account-list">
					<li class="find-account-id">
						<a class="fa-id" href='/kgCoffee/view/user/findAccount.jsp'>아이디 찾기</a>
					</li>
				</ul>
				<ul class="find-account-list">
					<li class="find-account-pw">
						<a class="fa-pw" href='/kgCoffee/view/user/findAccountPw.jsp'>비밀번호 찾기</a>
					</li>
				</ul>
			</nav>
			
			<ul class="modifyList">
			
			
				<li class="modifyView">
					
					<div class="modify-form">
					<form id="f1" action="/kgCoffee/user/findUpdatePw.do" method="post" >
					
						<p class="modify-msg">
							${vo.user_name} 회원님 확인되었습니다.<br>
							새로운 비밀번호를 설정해주세요.
						</p>
						
						<table class="modify-table">
							
							<tr>
								<th>아이디</th>
								<td><c:out value="${vo.user_id}" /></td>
							</tr>
							
							<tr>
								<th>비밀번호</th>
								<td><input type="password" name="user_pw" class="user_pw" id="pw" placeholder="영문, 숫자, 특수문자 중 2가지를 혼합해주세요." required></td>
							</tr>
							
						</table>
						
						<input type="hidden" name="user_id" value="${vo.user_id}">
						<input type="hidden" name="user_name" value="${vo.user_name}">
						<input type="hidden" name="birthday" value="${vo.birthday}">
						<input type="hidden" name="tel" value="${vo.tel}">
						
						<div class="modify-button">
							<button type="submit" class="btn_design" onClick="return checkform();">확인</button>
						</div>
						
					</form>
					</div>
					
				</li>
			
			</ul>
			
		</div>
		
	</section>
	
	<%@include file="../../include/footer.jsp" %>
	
	<script>
	function checkform() {

		var pw = $("#pw").val();
		var id = "${vo.user_id}";
		
		console.log(pw);
		
	 	var num = pw.search(/[0-9]/g);
		var eng = pw.search(/[a-z]/ig);
		var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
		
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
		} else {
			return true;
		}

	}
	
	</script>
	
	<script>
	
		const findpwMag = '${findpwMag}';
	 	
	    if (findpwMag === 'find_pw_fail') {
	        alert('아이디, 이름, 전화번호를 다시 입력해주세요.');
	        history.back();
	    }
	    
	</script>
	
</body>
</html>