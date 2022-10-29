<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script type="text/javascript" src="../resources/js/frame/jquery-3.6.0.min.js"></script>
    
    <script type="text/javascript">
	    function fn_fileDown(fileNo){
			var formObj = $("form[name='readForm']");
			$("#FILE_NO").attr("value", fileNo);
			formObj.attr("action", "/board/fileDown");
			formObj.submit();
		}
	    
	    function goPage(userNo) {
	        var formObj = $("form[name='readForm']");
	        $("#USER_NO").attr("value", userNo);
	        formObj.attr("method", "post");
	        formObj.attr("action", "/board/myBoardPage");
	        formObj.submit();
	    }
	    
	    function goUpdatePage(boardNo) {
	        var formObj = $("form[name='updateForm']");
	        $("#BOARD_NO").attr("value", boardNo);
	        formObj.attr("method", "post");
	        formObj.attr("action", "/board/updateContentPage");
	    	formObj.submit();	    	
	    }
	    
	    function goCommentUpdatePage(commentNo) {
	    	var formObj = $("form[name='updateCommentForm']");
	    	$("#COMMENT_NO").attr("value", commentNo);
	    	formObj.attr("method", "post");
	    	formObj.attr("action", "/board/updateCommentContentPage");
	    	window.open('', 'updateCommentView', 'width=1024,height=1024,resizeable,scrollbars');
	    	formObj.attr("target", "updateCommentView");
	    	formObj.submit();
	    }
	    
	    function deleteCommentPage(commentNo, boardNo) {
	    	var formObj = $("form[name='deleteComment']");
	    	$("#COMMENTNO").attr("value", commentNo);
	    	$("#BOARDNO").attr("value", boardNo);
	    	formObj.attr("method", "post");
	    	formObj.attr("action", "/board/deleteCommentContentProcess");
	    	formObj.submit();
	    }
	    
	    function deleteContentPage(boardNo) {
	        var formObj = $("form[name='updateForm']");
	        $("#BOARD_NO").attr("value", boardNo);
	    	formObj.attr("method", "post");
	    	formObj.attr("action", "/board/deleteContentProcess");
	    	formObj.submit();	    	
	    }
	    
	    function writeCommentPage(boardNo) {
	    	var formObj = $("form[name='updateForm']");
	    	$("#BOARD_NO").attr("value", boardNo);
	    	formObj.attr("method", "post");
	    	formObj.attr("action", "/board/wirteCommentContentPage");
	    	window.open('', 'writeCommentView', 'width=1024,height=1024,resizeable,scrollbars');
	    	formObj.attr("target", "writeCommentView");
	    	formObj.submit();
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
                <!-- page Title -->
                <div class="row mt-1 conTitleArea">
					<h3 class="conTItle">
						<i class="bi bi-list"></i> 상세보기 페이지
					</h3>
                </div>
				
				<form name="readForm" role="form" method="post">
					<input type="hidden" id="FILE_NO" name="file_no" value="">
					<input type="hidden" id="USER_NO" name="user_no" value="">									
				</form>
				
				<form name="updateForm" role="form" method="post">
					<input type="hidden" id="BOARD_NO" name="board_no" value="">
				</form>
				
				<form name="updateCommentForm" role="form" method="post">
					<input type="hidden" id="COMMENT_NO" name="comment_no" value="">
				</form>

				<form name="deleteComment" role="form" method="post">
					<input type="hidden" id="COMMENTNO" name="comment_no" value="">
					<input type="hidden" id="BOARDNO" name="board_no" value="">
				</form>

                <!-- 페이지별 내용 시작-->
				<div class="row mt-2">
					<div class="col">
						<table class="table" style="border-top:1px solid steelblue; border-bottom:2px solid steelblue">
		               		<tr>
		               			<th scope="row" width=100px align="center" style="border-right:2px solid steelblue">작성자</th>
		               			<td width=100px> 
									<nav>
										<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
											<li class="nav-item dropdown">
												<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
													${data.userVo.user_nickname }
												</a>
												<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
													<li><a class="dropdown-item" href="javascript:goPage(${data.userVo.user_no });">게시글 보기</a></li>
													<li><a class="dropdown-item" href="../user/userNotePage">쪽지 보내기</a></li>
												</ul>
											</li>
										</ul>
									</nav>
		               			</td>
		               		</tr>
		
		                    <tr>
		                        <th scope="row" width=100px align="center" style="border-right:2px solid steelblue">제목</th>
		                        <td width=100px align="center"> 
		                        ${data.boardVo.board_title }
		                        </td>
		                    </tr>                    
		
		                    <tr>
		                       <th scope="row" width=100px align="center" style="border-right:1.5px solid steelblue">조회수</th>
		                       <td width=100px align="center"> 
		                       ${data.boardVo.board_readcount } 
		                       </td>
		                    </tr>
		
							<tr>
		                       <th scope="row" width=100px align="center" style="border-right:1.5px solid steelblue">좋아요수</th>
		                       <td width=100px align="center"> 
		                       ${totalLikeCount } 
		                       </td>
		                    </tr>		
							
		                    <tr>
		                       <th scope="row" width=100px align="center" style="border-right:1.5px solid steelblue">작성일시</th>
		                       <td width=100px align="center"> 
		                       <fmt:formatDate value="${data.boardVo.board_write_date }" pattern="yyyy년MM월dd일 HH시 mm분 ss초" />
		                       </td>
		                    </tr>
		                                   
		                    <tr>
		                    	<td colspan="4">
		                    	<br>
		                    	<br>
		                    	${data.boardVo.board_content }
		                    	<br>
		                    	<br>
		                    	<br>
		                    	</td>
		                    </tr>
		                </table>					
					</div>				
				</div>
				
				<div class="row mt-2">
					<span>파일 목록</span>
					<div class="col">
						<c:forEach items="${fileList }" var="file">
							<div class="form-group" style="border: 1px solid #dbdbdb;">
								<a href="#" onclick="fn_fileDown('${file.file_no}'); return false;">${file.org_file_name}</a>(${file.file_size}kb)<br>
							</div>
						</c:forEach>
					</div>
				</div> 
				
				<div class="row mt-3">
						<table class="table" style="border-top:1px solid steelblue; border-bottom:2px solid steelblue">
						<thead>
							<tr>
								<th scope="col">댓글번호</th>
								<th scope="col">댓글내용</th>
								<th scope="col">댓글작성자</th>
								<th scope="col">작성일</th>
								<th scope="col">좋아요수</th>
								<th scope="col">삭제</th>							
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${dataList }" var="date">
								<tr>
									<th class="text-center">${date.commentVo.comment_no }</th>
									<th class="text-center">${date.commentVo.comment_content }</th>
									<th class="text-center">${date.userVo.user_nickname }</th>
									<th class="text-center"><fmt:formatDate value="${date.commentVo.comment_write_date }" pattern="yyyy년MM월dd일 HH시 mm분 ss초" /></th>
									<th class="text-center">${date.totalCommentLikeCount }</th>
									<c:choose>
										<c:when test="${sessionUser.user_no == date.commentVo.user_no }">
											<th><a href="javascript:deleteCommentPage(${date.commentVo.comment_no }, ${date.commentVo.board_no })" type="button" class="btn btn-outline-primary">댓글삭제
																	</a>
												<a href="javascript:goCommentUpdatePage(${date.commentVo.comment_no });" type="button" class="btn btn-outline-primary">댓글수정</a>
												<a href="../board/commentLikeProcess?board_no=${date.commentVo.board_no }&comment_no=${date.commentVo.comment_no}" type="button" class="btn btn-outline-primary">댓글 좋아요</a>
											</th>
										</c:when>	
										<c:otherwise>
											<th><a href="../board/commentLikeProcess?board_no=${date.commentVo.board_no }&comment_no=${date.commentVo.comment_no}" type="button" class="btn btn-outline-primary">댓글 좋아요</a></th>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
						</tbody>	
					</table>
				</div>
                
                
				<div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <a href="./mainPage" class="btn btn-outline-primary" type="button">목록으로</a>
                    <a href="javascript:writeCommentPage(${data.boardVo.board_no })" type="button" class="btn btn-outline-primary">댓글작성
                    </a>
                    
					<c:choose>
						<c:when test="${myLikeCount } > 0 ">
							<a href="../board/likeProcess?board_no=${data.boardVo.board_no }" type="button" class="btn btn-outline-primary">좋아요 취소</a>
						</c:when>
						<c:otherwise>
							<a href="../board/likeProcess?board_no=${data.boardVo.board_no }" type="button" class="btn btn-outline-primary">좋아요</a>
						</c:otherwise>
					</c:choose>
					
                    
					<c:if test="${sessionUser.user_no == data.boardVo.user_no}">
                    	<a href="javascript:deleteContentPage(${data.boardVo.board_no })" class="btn btn-outline-primary" type="button">글삭제</a>
                    	<a href="javascript:goUpdatePage(${data.boardVo.board_no });" class="btn btn-outline-primary" type="button">글수정</a>	                    	
                    </c:if>
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