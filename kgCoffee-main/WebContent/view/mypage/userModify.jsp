<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify</title>

<link rel="stylesheet" href="/kgCoffee/css/header_style.css" type="text/css">
<link rel="stylesheet" href="/kgCoffee/css/userModify.css?after" type="text/css">

<script src="/kgCoffee/js/jquery-3.7.0.min.js"></script> <!-- jquery -->

</head>
<body>
	
	<%@include file="../../include/header.jsp" %>
	
	<section class="modify" id="modify">
		
		<div class="modify-mg">
		
			<ul class="modifyList">
				<li class="modify_name">
					<h3> 회원 정보 수정 </h3>
				</li>
			</ul>
			
			
			<ul class="modifyList">
			
				<li class="modifyView">
					
					<div class="modify-form">
					<form id="f1" action="/kgCoffee/mypage/userUpdate.do?user_id=${vo.user_id}" method="post" >
						
						<table class="modify-table">
							
							<tr>
								<th>아이디</th>
								<td><c:out value="${loginUser.user_id}" /></td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td><input type="password" name="user_pw" class="user_pw" placeholder="영문, 숫자, 특수문자 중 2가지를 혼합해주세요." required></td>
							</tr>
							<tr>
								<th>이름</th>
								<td><input type="text" name="user_name" value="${vo.user_name}" required></td>
							</tr>
							<tr>
								<th>생년월일</th>
								<td><input type="date" name="birthday"  value="${vo.birthday}"required></td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td><input type="text" name="tel" value="${vo.tel}" required></td>
							</tr>
							
						</table>
						
						<div class="modify-button">
							<button type="submit" class="btn_design" onClick="return check();">확인</button> &nbsp;&nbsp;&nbsp;
							<button type="button" class="btn_design" id="go-back">취소</button>
						</div>
						
					</form>
					</div>
					
					
				</li> <!-- modifyView end -->
			
			</ul> <!-- modifyList end -->
			
		</div> <!-- modify-mg div end -->
		
	</section>
	
	<%@include file="../../include/footer.jsp" %>
	
	<script>
		
		console.log('${msg}');
		
		const msg = '${msg}';
		
		if (msg === 'pw_success') {
		    
		} else if (msg === 'pw_fail') {
			alert('비밀번호가 틀렸습니다 다시 입력해주세요.');
			history.back();
		}
		
		
		document.getElementById('go-back').addEventListener('click', () => {
			window.history.back();
		});
		
	</script>
	
	<script src="/kgCoffee/js/user_script.js"></script>
	
</body>
</html>