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
		function cmaTextareaSize(obj, bsize) { // 객체명, 기본사이즈
			var sTextarea = document.getElementById(obj);
			var csize = (sTextarea.scrollHeight >= bsize) ? sTextarea.scrollHeight + "px" : bsize + "px";
			sTextarea.style.height = bsize + "px";
			sTextarea.style.height = csize;
		}
	</script>    
</head>
<body>
	<section class="container-fluid">
		<div class="row mt-4">
			<div class="col">
				<div class="row">
					<div class="col"><!-- 글로벌 nav -->
						<jsp:include page="../commons/mainNav.jsp"></jsp:include>
					</div>
				</div>
				<div class="row mt-1 conTitleArea">
					<h3 class="conTItle">
						<i class="bi bi-list"></i> 댓글수정 페이지
					</h3>
                </div>
                
                <div class="row mt-3">
                	<form:form action="./updateCommentContentProcess" modelAttribute="commentVo" id="updateCommentContentForm">
						<div class="col">

							<div class="row mt-3">
								<div class="col">
									작성자 : <input type="text" value="${data.userVo.user_nickname }" disabled="disabled">	
								</div>                		
							</div>
							
							<div class="row mt-3">
								<div class="col">
									게시글 번호 : <input type="text" value="${data.boardVo.board_no }" disabled="disabled">
								</div>
							</div>
							
							<div class="row mt-3">
								<div class="col">
									게시글 제목 : <input type="text" value="${data.boardVo.board_title }" disabled="disabled">
								</div>
							</div>

							<div class="row mt-3">
								<div class="col">
									<textarea onkeyup="cmaTextareaSize('floatingContent', 200);" class="form-control"
										id="floatingContent" name="board_content">${data.commentVo.comment_content }
									</textarea> 																
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
						
						<input type="hidden" name="comment_no" value="${data.commentVo.comment_no }">
						
					</form:form>
                </div>
                
			</div>
		</div>               	
	</section>
</body>
</html>