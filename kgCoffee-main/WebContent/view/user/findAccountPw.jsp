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
					<form id="f1" action="/kgCoffee/user/findPw.do" method="post" >
					
						<p class="modify-msg">
							회원님의 아이디와 이름과 전화번호로 본인확인을 진행합니다.<br>
							본인 확인을 통해 비밀번호를 재설정 하실 수 있습니다.
						</p>
						
						<table class="modify-table">
							
							<tr>
								<th>아이디</th>
								<td><input type="text" name="user_id" id="user_id" required></td>
							</tr>
							
							<tr>
								<th>이름</th>
								<td><input type="text" name="user_name" id="user_name" required></td>
							</tr>
							
							<tr>
								<th>전화번호</th>
								<td><input type="text" name="tel" id="tel" required></td>
							</tr>
							
						</table>
						
						<div class="modify-button">
							<button type="submit" class="btn_design">비밀번호찾기</button>
							<button type="button" class="btn_design" id="go-back">취소</button>
						</div>
						
					</form>
					</div>
					
				</li>
			
			</ul>
			
		</div>
		
	</section>
	
	<%@include file="../../include/footer.jsp" %>
	
	<script>
		document.getElementById('go-back').addEventListener('click', () => {
			window.history.back();
		});
		
	</script>
	
</body>
</html>