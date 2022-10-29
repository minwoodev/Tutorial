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
	
	<script type="text/javascript">
	    function goPage(noteNo) {
	        var formObj = $("form[name='readForm']");
	        $("#NOTE_NO").attr("value", noteNo);
	        formObj.attr("method", "post");
	        formObj.attr("action", "/note/noteContentPage")
	        formObj.submit();
	    }
    </script>
	
</head>
<body>

	<jsp:include page="../commons/mainNav.jsp"></jsp:include>

    <!-- header part end-->

    <!-- body part start -->

    <section class="container-fluid">

        <div class="row mt-4">

            <jsp:include page="../commons/noteNav.jsp"></jsp:include>

            <div class="col">
                <!-- page Title -->
                <div class="row mt-1 conTitleArea">
                    <form action="../note/notePage" method="post">
                    	<div class="row mt-3">
                    		<div class="col">
                    			<select name="searchType" class="form-select">
                    				<option value="id">아이디</option>
                    				<option value="title">별명</option>
                    				<option value="content">내용</option>
                    			</select>
                    		</div>
                    		
                    		<div class="col-8">
                    			<input type="text" name="keyword" class="form-control" placeholder="검색할 단어를 입력하세요">
                    		</div>
                    		
                    		<div class="col d-grid">
                    			<input type="submit" value="검색" class="btn btn-outline-primary">
                    		</div>
                    	</div>
                    </form>
                </div>
                <!-- 페이지별 내용 시작-->

				<form name="readForm" role="form" method="post">					
					<input type="hidden" id="NOTE_NO" name="note_no" value="">
				</form>

				<div class="row formTable">
					<table>
						<thead>
							<tr>
								<th scope="col">보낸사람</th>
								<th scope="col">내용</th>
								<th scope="col">날짜</th>
								<th scope="col">신고</th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${dataList }" var="data">
								<tr>
									<td class="text-center">${data.noteVo.note_nickname }</td>
									<td class="text-center"><a href="javascript:goPage(${data.noteVo.note_no });">${data.noteVo.note_content }</a></td>
									<td class="text-center"><fmt:formatDate value="${data.noteVo.note_writedate }" pattern="yyyy:MM:dd: HH:mm:ss" /></td>
									<td class="text-center"><a href="#">신고</a></td>
								</tr>
							</c:forEach>
						</tbody>
						
					</table>
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