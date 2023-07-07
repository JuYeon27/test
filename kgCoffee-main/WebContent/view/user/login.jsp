<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<!-- 구글폰트 -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<!-- css 파일 링크 -->
<link rel="stylesheet" type="text/css" href="/kgCoffee/css/login_style.css">
<link rel="stylesheet" type="text/css" href="/kgCoffee/css/header_style.css">

</head>
<body>

	<%@include file="/include/header.jsp" %>
	
	<!-- login section start ---------------------------------------------------------------- -->
	<section class="login">
	
        <div class="form-box">
        
            <div class="form-value">
            
                <form action="/kgCoffee/user/login.do" method="post">
                
                    <h2>Login</h2>
                    
                    <div class="inputbox">
                        <ion-icon name="mail-outline"></ion-icon>
                        <input type="text" name="user_id" required>
                        <label>아이디</label>
                    </div>
                    
                    <div class="inputbox">
                        <ion-icon name="lock-closed-outline"></ion-icon>
                        <input type="password" name="user_pw" required>
                        <label>비밀번호</label>
                    </div>
                    
                    <div class="button">
						<input type="submit" class="btn" value="로그인">
					</div>
                    
                </form>
                <button onclick="location.href='/kgCoffee/view/user/join.jsp'">회원가입</button> <!-- form 밖으로 뺀 이유는 안빼면 회원가입 페이지로 이동을 안함 -->
                <button onclick="location.href='/kgCoffee/view/user/findAccount.jsp'">아이디/비밀번호 찾기</button>
            </div>
            
        </div>
        
    </section> <!-- login section end -->

	<!-- ionicons에서 아이콘 가져오기 -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	
	<script src="/kgCoffee/js/user_script.js"></script> <!-- 유효성검사 -->
	
	
 	<script>
	 	const msg = '${msg}';
	 	
	    if (msg === 'join-success') {
	        alert('축하합니다. 회원가입에 성공했습니다.');
	    } else if(msg === 'join-failed') {
	    	alert('존재하는 아이디 입니다 다시 입력해주세요.');
	    	history.back();
	    }
	    
	    
		const update_msg = '${update_msg}';
	 	
	    if (update_msg === 'update-success') {
	        alert('비밀번호 수정이 완료되었습니다.');
	    } else if(update_msg === 'update-failed') {
	    	alert('수정이 되지 않았습니다. 다시 입력해주세요.');
	    	history.back();
	    }
	    
	</script>
	
</body>
</html>