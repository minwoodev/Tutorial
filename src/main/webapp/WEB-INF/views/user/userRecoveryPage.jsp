<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
   <meta charset="UTF-8">
    <!-- bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <!-- my css -->
    <link rel="stylesheet" type="text/css" href="../resources/css/frameCss.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/commonCss.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/commonFormCss.css">

    <!-- font css -->
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">

    <!-- script -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript"></script>

    <script type="text/javascript" src="../resources/js/user/userRecoveryPage.js"></script>
    <script type="text/javascript" src="../resources/js/frame/dropdown.js"></script>
    <script type="text/javascript" src="../resources/js/frame/jquery-3.6.0.min.js"></script>
</head>

<body>

    <jsp:include page="../commons/mainNav.jsp"></jsp:include>

    <!-- header part end-->

    <!-- body part start -->

    <section class="container-fluid">

        <div class="row mt-4">

            <div class="col">
                 <!-- page Title -->
                <div class="row mt-1 conTitleArea">
                    <h3 class="conTItle"><i class="bi bi-list"></i>계정 활성화</h3>
                </div>

                <!-- 페이지별 내용 시작-->
                <div class="col" style=""> <!-- 각 페이지 내용 여기에 들어가면 됩니다  -->														
					<div class="row mt-2" id="submitOptionBox"> <!-- 옵션박스 -->
						<div class="col">
						<div class="row mt-3">
							<div class="col-3 fs-5"><input class="form-control" id="userId" type="text" placeholder="아이디를 입력해 주세요." aria-label="default input example"></div>												
							<div class="col-3 fs-5"><input class="form-control" id="userNickName"  type="text" placeholder="이름을 입력해 주세요." aria-label="default input example"></div>
							<div class="col"></div>
						</div>					
						<div class="row mt-3">
						    <div class="col-3 fs-5"><input class="form-control" id="userEmail"  type="password" placeholder="이메일을 입력해 주세요." aria-label="default input example"></div>							
						</div>	
						<div class="row mt-3 text-center">
							<div class="inputTitle"></div>
							<div class="col bi bi-exclamation-square-fill deepblue">&nbsp;돌아오신 것을 환영합니다! 정보를 정확히 기입하셔야 계정활성화가 가능합니다.</div>
							<div class="col-2"></div>
						</div>													
						<div class="row mt-1">
							<div class="inputTitle"></div>
							<div class="col d-grid"><button type="button" id="recoveryButton" class="btnBasic bi bi-emoji-laughing">&nbsp;계정활성화</button></div>
							<div class="col-2"></div>
						</div>
					</div>
					</div>					
				</div>
                <!-- 페이지별 내용 끝 -->
            </div>
        </div>
    </section>

</body>

</html>