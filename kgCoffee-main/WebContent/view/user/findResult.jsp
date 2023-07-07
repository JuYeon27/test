<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계정정보 찾기</title>

<link rel="stylesheet" href="/kgCoffee/css/header_style.css" type="text/css">
<link rel="stylesheet" href="/kgCoffee/css/findAccount.css?after" type="text/css">

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
					
						<p class="find-id-p" align="center">
							${user_name} 회원님의 아이디입니다.
						</p>
						
						<p class="find-id-p" align="center" style="color: #f0932b">
							[  ${user_id}  ]
						</p>
						
						<div class="modify-button">
							<button class="btn_design"  onclick="location.href='/kgCoffee/view/user/login.jsp'">로그인</button>
						</div>
						
					</div>
					
				</li>
			
			</ul>
			
		</div>
		
	</section>
	
	<%@include file="../../include/footer.jsp" %>
	
	<script>
		
		const msg = '${msg}';
	 	
	    if (msg === 'find_id_fail') {
	        alert('이름과 전화번호를 다시 입력해주세요.');
	        history.back();
	    }
	    
	    document.getElementById('go-back').addEventListener('click', () => {
			window.history.back();
		});
		
	</script>
	
</body>
</html>