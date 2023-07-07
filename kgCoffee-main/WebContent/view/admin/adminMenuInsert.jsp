<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴추가</title>

<link rel="stylesheet" href="/kgCoffee/css/header_style.css" type="text/css">
<link rel="stylesheet" href="/kgCoffee/css/adminMenuInsert.css" type="text/css">

<script src="/kgCoffee/js/jquery-3.7.0.min.js"></script> <!-- jquery -->

</head>
<body>
	
<%-- 	<%@include file="../../include/header.jsp" %> --%>
	
	<section class="menuInsert" id="menuInsert">
		
		<div class="menu-mg">
		
			<ul class="menuList">
				<li class="menu_name">
					<h3> 메뉴 추가 </h3>
				</li>
			</ul>
			
			
			<ul class="menuList">
			
				<li class="menuView">
					
					<div class="menu-form">
					<form id="f1" action="/kgCoffee/admin/adminMenuInsert.do?" method="post" enctype="multipart/form-data">
						
						<table class="menu-table">
							
							<tr>
								<th>메뉴 사진</th>
								<td><input type="file" name="img" /></td>
							</tr>
							<tr>
								<th>카페인 타입</th>
								<td><select name="caffeinType" id="caffeinType">
										<option value="none">none</option>
										<option value="decaffeine">decaffeine</option>
										<option value="caffeine">caffeine</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>메뉴 이름</th>
								<td><input type="text" name="menuName"></td>
							</tr>
							<tr>
								<th>메뉴 가격</th>
								<td><input type="text" name="menuPrice"></td>
							</tr>
							<tr>
								<th>메뉴 설명</th>
								<td><input type="text" name="menuExplain"></td>
							</tr>
							<tr>
								<th>메뉴 타입</th>
								<td><select name="menuType" id="menuType">
										<option value="Hot">Hot</option>
										<option value="Ice">Ice</option>
										<option value="Food">Food</option>
									</select>
								</td>
							</tr>
							
						</table>
						
						<div class="menu-button">
							<button type="submit" class="btn_design">등록</button> &nbsp;&nbsp;&nbsp;
							<button type="reset" class="btn_design" id="go-back">취소</button>
						</div>
						
					</form>
					</div>
					
					
				</li> <!-- modifyView end -->
			
			</ul> <!-- modifyList end -->
			
		</div> <!-- modify-mg div end -->
		
	</section>
	
	<%-- <%@include file="../../include/footer.jsp" %> --%>
	
	
	
	<script src="/kgCoffee/js/user_script.js"></script>
	
</body>
</html>