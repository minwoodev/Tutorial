<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">

	<!-- bootstarp css -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
	<!-- my css -->
	<link rel="stylesheet" type="text/css" href="../resources/css/frameCss.css">
	<link rel="stylesheet" type="text/css" href="../resources/css/commonCss.css">
	<link rel="stylesheet" type="text/css" href="../resources/css/commonFormCss.css">
	
	<!-- font css -->
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript"></script>
	<script type="text/javascript" src="../resources/js/user/loginPage.js"></script>
	<script type="text/javascript" src="../resources/js/frame/dropdown.js"></script>
	<script type="text/javascript" src="../resources/js/frame/jquery-3.6.0.min.js"></script>
</head>
<body>

	<section class="container-fluid">
		<div class="row mt-4">
			<div class="col">
				<div class="row">
					<div class="col">
						<!-- 글로벌 nav -->
						<jsp:include page="../commons/mainNav.jsp"></jsp:include>
					</div>
				</div>
				<!-- page Title -->
				<div class="row mt-1 conTitleArea">
					<h3 class="conTItle">
						<i class="bi bi-list"></i> 로그인 페이지
					</h3>
				</div>

				<!-- 페이지별 내용 시작-->
				<div class="row mt-1">
					<div class="col">
						<div class="row mt-1">
							<div class="col-4">
								<input type="text" id="inputId" class="form-control" placeholder="아이디를 입력해주세요" name="user_id">
							</div>
							<div class="col my-auto" id="alertId"></div>			
						</div>
						
						<div class="row mt-1">
							<div class="col-4">
								<input type="password" id="inputPw" class="form-control" placeholder="비밀번호를 입력해주세요" name="user_pw">
							</div>
							<div class="col my-auto" id="alertPw"></div>	
						</div>
						
						<div class="row mt-1">
							<div class="col-4">
								<div class="row">
									<div class="col">
										<input type="checkbox" id="saveIdBox" name="">ID저장
										<input type="checkbox" id="autoLoginBox" name="">자동로그인
									</div>
								</div>
								<div class="row mt-2">
									<a href="../user/findUserInfoPage">아이디찾기</a>
								</div>
							</div>
						</div>
						
						<div class="row mt-2">
							<div class="col-4">
								<button type="button" id="loginButton"
									class="btnBasic inputSubmit bi bi-check2-square">&nbsp;로그인</button>
							</div>
						</div>
					</div>
				</div>
				<!-- 페이지별 내용 끝 -->
			</div>
		</div>
	</section>


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" 
		crossorigin="anonymous">
	</script>
</body>
</html>