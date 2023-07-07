<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴수정</title>

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
					<h3> 메뉴 수정 </h3>
				</li>
			</ul>
			
			
			<ul class="menuList">
			
				<li class="menuView">
					
					<div class="menu-form">
					<form id="f1" action="adminMenuUpdate.do?" method="post" enctype="multipart/form-data">
						
						<table class="menu-table">
							
							<tr>
								<th>메뉴 아이디</th>
								<td><input type="hidden" name="menuId" value="${vo.menuId }"></td>
							</tr>
							<tr>
								<th>메뉴 사진</th>
								<td><label for=file_input style="display: inline-block;">
									<div class="img-btn">첨부</div>
									</label>
									<input type="text" name="img2" value="${vo.fileName }" />
									<input type="file" name="img" id ="file_input" style="display:none;"/>
								</td>
							</tr>
							<tr>
								<th>카페인 타입</th>
								<td><select name="caffeineType" id="caffeineType" class="caffeineType">
      								   <option class="none" value="none" ${vo.caffeineType == 'none' ? 'selected' : ''}>
      									none</option>
     								   <option class="decaffeine" value="decaffeine" ${vo.caffeineType == 'decaffeine' ? 'selected' : ''}>
     									decaffeine</option>
      								   <option class="caffeine" value="caffeine" ${vo.caffeineType == 'caffeine' ? 'selected' : ''}>
      									caffeine</option>
   									 </select>
								</td>
							</tr>
							<tr>
								<th>메뉴 이름</th>
								<td><input type="text" name="menuName" value="${vo.menuName }"></td>
							</tr>
							<tr>
								<th>메뉴 가격</th>
								<td><input type="text" name="menuPrice" value="${vo.menuPrice }"></td>
							</tr>
							<tr>
								<th>메뉴 설명</th>
								<td><input type="text" name="menuExplain" value="${vo.menuExplain }"></td>
							</tr>  
							<tr>
								<th>메뉴 타입</th> 	
								<td><select name="menuType" id="menuType" class="menuType">
     								   <option class="Hot" value="Hot" ${vo.menuType == 'Hot' ? 'selected' : ''}>
     								   Hot</option>
     								   <option class="Ice" value="Ice" ${vo.menuType == 'Ice' ? 'selected' : ''}>
     								   Ice</option>
     								   <option class="Food" value="Food" ${vo.menuType == 'Food' ? 'selected' : ''}>
     								   Food</option>
    								</select>
								</td>
							</tr>
							
						</table>
						
						<div class="menu-button">
							<button type="submit" class="btn_design">수정</button> &nbsp;&nbsp;&nbsp;
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
	<script>
	
	
	  $("#file_input").change(function() {
		  
		  
    	var fileName = $(this).val().split("\\").pop(); // 파일 이름 추출
   		 $("input[name='img2']").val(fileName); // 입력 필드에 파일 이름 설정
  		});
	  
	  
</script>
	
	
</body>
</html>