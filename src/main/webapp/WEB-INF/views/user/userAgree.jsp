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
                <div class="row mt-4 conTitleArea">
                    <h3 class="conTItle"><i class="bi bi-list"></i>사용자등록 진행 동의</h3>
                </div>
                <div class="row fs-6 green fw-bold ps-5">
                    <h5>◎ 이용약관</h5>
                </div>
 
                <form id="joinUserPage" action="./joinUserPage" method="get">
                    <div class="row formTable">
                        <div class="card">
                            <div class="card-body" style="overflow:auto; height:200px;">
                                <h6 class="text-primary">제 1 장 총 칙<br></h6>
                                <div class="green">제 1 조 (목적)<br></div>
                                <br>                            
                            </div>
                        </div>
 
                        <div class="row fs-6 green fw-bold ps-4 mt-3">
                            <h5>◎ 개인정보 수집 및 이용에 대한 동의</h5>
                        </div>
                        <div class="card mt-2">
                            <div class="card-body" style="overflow:auto; height:200px;">
                                <div class="green">가. 개인정보의 수집/이용 목적</div>
                            </div>
                        </div>
                        <div class="row text-end mt-1">
                            <div style="float:right;">
                                <input type="checkbox" name="agree" value="true" id="agreed">
                                <label>이용약관에 동의합니다. </label>
                            </div>
                        </div>
                        <div class="row formEndBorder mt-3">
                            <div class="col-1">
                                <button type="button" onclick="http://localhost:8181/index.jsp" class="btn btn-outline-success">취소</button>
                            </div>
                            <div class="col"></div>
                            <div class="col-2">
                                <input type="submit" class="btnBasic inputSubmit" value="제출">
                            </div>
                        </div>
                    </div>                
                </form>
                <!-- 페이지별 내용 끝 -->
            </div>
        </div>
    </section>
</body>

</html>