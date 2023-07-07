<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>테스트 입니다</h2>
	<form action="/kgCoffee/menu/insert.do" method="post" enctype="multipart/form-data">
	사진 <input type="file" name="img"/><br />
	카페인 유,무<input type="text" name="caffeinType"/><br />
	상품이름 <input type="text" name="menuName"/><br />
	상품가격 <input type="text" name="menuPrice"/><br />
	상품설명 <input type="text" name="menuExplain"/><br />
	상품타입 <input type="text" name="menuType"/><br />
	<input type="submit" value="전송"/>
	</form>
</body>
</html>