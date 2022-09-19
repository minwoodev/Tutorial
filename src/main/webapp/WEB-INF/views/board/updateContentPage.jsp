<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>        
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

    <script type="text/javascript" src="../resources/js/commons/loginBox.js"></script>
    <script type="text/javascript" src="../resources/js/frame/dropdown.js"></script>
    <script type="text/javascript" src="../resources/js/frame/jquery-3.6.0.min.js"></script></head>
    
	<style>
		#floatingContent {
			resize: none;
			line-height: 150%;
			width: 100%;
			overflow-y: hidden;
			height: 30px;
			border: 1px solid #ccc;
		}
	</style>

	<script>
		function checkRadioBox() {
			var checkVal = $('input[name=board_secret]:checked').val();
			if (!$('input:radio[name=board_secret]').is(":checked")) {
				alert("비밀글 여부를 선택해주세요");
				return;
			}
		}

		function cmaTextareaSize(obj, bsize) { // 객체명, 기본사이즈
			var sTextarea = document.getElementById(obj);
			var csize = (sTextarea.scrollHeight >= bsize) ? sTextarea.scrollHeight + "px" : bsize + "px";
			sTextarea.style.height = bsize + "px";
			sTextarea.style.height = csize;
		}
	</script>    
<body>
<section class="container-fluid">
        <div class="row mt-4">
            <div class="col">
            	<div class="row">
					<div class="col"><!-- 글로벌 nav -->
						<jsp:include page="../commons/mainNav.jsp"></jsp:include>
					</div>
				</div>
                <!-- page Title -->
                <div class="row mt-1 conTitleArea">
					<h3 class="conTItle">
						<i class="bi bi-list"></i> 글수정 페이지
					</h3>
                </div>

                <!-- 페이지별 내용 시작-->
                
                <div class="row mt-3">
                	<form:form action="./updateContentProcess" modelAttribute="boardVo" id="updateContentForm">
						<div class="col">
							<div class="row mt-3">
								<div class="col">
									작성자 : <input type="text" value="${data.userVo.user_nickname }" disabled="disabled">	
								</div>                		
							</div>
							
							<div class="row mt-3">
								<div class="col">
									제목 : <form:input type="text" class="from-control" value="${data.boardVo.board_title }" path="board_title"/>
								</div>
								<div class="col my-auto"><form:errors path="board_title" id="error_message" /></div>
							</div>
							
							<div class="row mt-3">
								<div class="col">
									<textarea onkeyup="cmaTextareaSize('floatingContent', 200);" class="form-control"
										id="floatingContent" name="board_content">${data.boardVo.board_content }
									</textarea> 																
								</div>
							</div>
							
							<div class="row mt-3">
								<div class="col">
									<div align="left">
										<input type="radio" name="board_secret" value="Y" checked="checked">비밀글로 설정
										<input type="radio" name="board_secret" value="N">공개글로 설정
									</div>
								</div>
							</div>
							
							<div class="row mt-2">
								<div class="col"></div>
								<div class="col"></div>
								<div class="col">
									<a href="./readContentPage?board_no=${data.boardVo.board_no }" class="btn btn-dark" style="float: right;">수정취소</a>
									<input type="submit" class="btn btn-dark" style="float: right;" value="수정완료">
								</div>
							</div>																							
						</div>
						
						<input type="hidden" name="board_no" value="${data.boardVo.board_no }">
						
					</form:form>
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