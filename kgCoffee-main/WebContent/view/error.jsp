<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
  margin: 0;
  padding: 20px;
  background-color: #ffc900;
  font-family: arial;
  height: 700px;
}

.info {
  position: absolute;
  top: 0;
  left: 0;
  padding: 10px 0;
  font-size: 110%;
  text-transform: capitalize;
  color: #ffc900;
  font-weight: 700;
  background-color: #fff;
  width: 100%;
  text-align: center;
}

.info a {
  text-decoration: none;
  color: #333;
}
.info a:after {
  content: " | ";
  color: #ffc900;
}

.info a:last-of-type:after {
  content: "";
}

.box {
  text-align: center;
  position: relative;
}

.box div {
  width: 250px;
  height: 80px;
  line-height: 80px;
  color: #ffc900;
  background-color: #fff;
  font-size: 280%;
  position: absolute;
  top: 490px;
  left: 40%;
  text-transform: capitalize;
  animation: moving 8s linear infinite;
  -webkit-animation: moving 8s linear infinite;
  -moz-animation: moving 8s linear infinite;
  -o-animation: moving 8s linear infinite;

  transform-origin: 50% -400%;
  -webkittransform-origin: 50% -400%;
  -moz-transform-origin: 50% -400%;
  -o-transform-origin: 50% -400%;
}

.box div:before {
  content: "";
  width: 25px;
  height: 25px;
  background-color: #fff;
  border-radius: 50%;
  display: block;
  position: absolute;
  left: 45%;
  top: -350px;
}

.box div:after {
  content: "";
  width: 3px;
  height: 335px;
  background-color: #fff;
  display: block;
  position: absolute;
  left: 50%;
  top: -330px;
}

.box p {
  position: absolute;
  top: 560px;
  left: 38%;
  font-weight: 700;
  text-transform: uppercase;
  color: #fff;
  width: 300px;
}

.box p span {
  display: block;
  font-size: 300%;
}

@keyframes moving {
  0%,
  100% {
    transform: rotate(0);
  }
  25% {
    transform: rotate(3deg);
  }
  50% {
    transform: rotate(-3deg);
  }
}

@-webkit-keyframes moving {
  0%,
  100% {
    transform: rotate(0);
  }
  25% {
    transform: rotate(3deg);
  }
  50% {
    transform: rotate(-3deg);
  }
}

@-moz-keyframes moving {
  0%,
  100% {
    transform: rotate(0);
  }
  25% {
    transform: rotate(3deg);
  }
  50% {
    transform: rotate(-3deg);
  }
}

@-o-keyframes moving {
  0%,
  100% {
    transform: rotate(0);
  }
  25% {
    transform: rotate(3deg);
  }
  50% {
    transform: rotate(-3deg);
  }
}

</style>
</head>

<body>


<div class="info">돌아가기 <a href="/kgCoffee/view/user/index.jsp" target="_blank">KG COFFEE</a>
</div>

<div class="box">
            <div>
                close !
            </div>
            <p><span>error 404 !</span> 죄송합니다. 관리자에게 문의해주세요.  </p>
        </div>
  
</body>
</html>