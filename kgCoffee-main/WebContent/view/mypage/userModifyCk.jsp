<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify</title>

<link rel="stylesheet" href="/kgCoffee/css/header_style.css" type="text/css">
<link rel="stylesheet" href="/kgCoffee/css/userModify.css?after" type="text/css">

</head>
<body>
	
	<%@include file="../../include/header.jsp" %>
	
	<section class="modify" id="modify">
		
		<div class="modify-mg">
		
			<ul class="modifyList">
				<li class="modify_name">
					<h3> 비밀번호 체크 </h3>
				</li>
			</ul>
			
			
			<ul class="modifyList">
			
			
				<li class="modifyView">
					
					<!-- ---------------------------------------------- -->
					
					<div class="modify-form">
					<form id="f1" action="/kgCoffee/mypage/modifyck.do?user_id=${loginUser.user_id}" method="post" >
					
						<p class="modify-msg">
							<strong><c:out value="${loginUser.user_name}" /></strong>님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인 합니다.
						</p>
						
						<table class="modify-table">
							
							<tr>
								<th>아이디</th>
								<td><c:out value="${loginUser.user_id}" /></td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td><input type="password" name="user_pw" id="user_pw" required></td>
							</tr>
							
						</table>
						
						<div class="modify-button">
							<button type="submit" class="btn_design">확인</button> &nbsp;&nbsp;&nbsp;
							<button type="button" class="btn_design" id="go-back">취소</button>
						</div>
						
					</form>
					</div>
					
					<!-- ---------------------------------------------- -->
					
				</li> <!-- modifyView end -->
			
			</ul> <!-- modifyList end -->
			
		</div> <!-- modify-mg div end -->
		
	</section>
	
	<%@include file="../../include/footer.jsp" %>
	
	<script>
		document.getElementById('go-back').addEventListener('click', () => {
			window.history.back();
		});
	</script>
	
</body>
</html>