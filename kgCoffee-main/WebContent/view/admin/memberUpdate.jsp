<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify</title>
<link rel="stylesheet" href="/kgCoffee/css/board.css">
<link rel="stylesheet" href="/kgCoffee/css/header_style.css"
	type="text/css">
<link rel="stylesheet" href="/kgCoffee/css/userModify.css?after"
	type="text/css">

<script src="/kgCoffee/js/jquery-3.7.0.min.js"></script>
<!-- jquery -->

</head>
<body>

	<%@include file="/view/admin/adminSide.jsp"%>


	<section class="content_section">






		<div class="content_hedear_wrap ">
			<ul class="modify-mg">
				<li class="modify_name">
					<h2 class="h2">회원 관리 페이지</h2>
				</li>
			</ul>
		</div>

		<div class="content_wrap">
			<div class="content">
				<section class="modify" id="modify">
					<ul class="modifyList">

						<li class="modifyView">

							<div class="modify-form">
								<form id="f1"
									action="/kgCoffee/admin/update.do?user_id=${uvo.user_id}"
									method="post">

									<table class="modify-table">

										<tr>
											<th>아이디</th>
											<td><input type="text" name="user_id"
												value="${uvo.user_id}" disabled></td>
											<%-- <td><c:out value="${uvo.user_id}" /></td> --%>
										</tr>
										<tr>
											<th>비밀번호</th>
											<td><input type="password" name="user_pw"
												value="${uvo.user_pw}" disabled></td>
										</tr>
										<tr>
											<th>이름</th>
											<td><input type="text" name="user_name"
												value="${uvo.user_name}" required></td>
										</tr>
										<tr>
											<th>생년월일</th>
											<td><input type="date" name="birthday"
												value="${uvo.birthday}" required></td>
										</tr>
										<tr>
											<th>전화번호</th>
											<td><input type="tel" name="tel" value="${uvo.tel}"
												required></td>
										</tr>

									</table>

									<div class="modify-button">
										<button type="submit" class="btn_design"
											onClick="return check();">수정</button>
										&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_design" id="go-back">취소</button>
									</div>

								</form>
							</div>


						</li>
						<!-- modifyView end -->

					</ul>
					<!-- modifyList end -->

					<!-- modify-mg div end -->
				</section>
			</div>
		</div>
	</section>


	<script>
	$("#user_management").toggleClass("active");
    
		document.getElementById('go-back').addEventListener('click', () => {
			window.history.back();
		});
	</script>


	<script src="/kgCoffee/js/user_script.js"></script>

</body>
</html>