/**
 * 
 */
window.addEventListener("DOMContentLoaded", function () {
	var user_no = null;
	var questionValue = null;
	var Email = null;
	var checkSession = function () {

		$.ajax({
			type: "get",
			url: "./checkSession",
			data: {

			},
			dataType: "json",
			// contentType : "application/x-www-form-urlencoded", // post
			success: function (data) {

				if (data.result == 'fail') {
					alert("로그인 후 이용해주시기 바랍니다.");
					location.href = "../user/loginPage";
				} else {
					user_no = data.sessionUser.user_no;

				}
			}
		});
	}
	checkSession();
	
	var showDrop = function () {
		
		$("#modifyDiv").removeClass('rounded border border-bottom-0 border-2 border-info');
		$("#modifyDiv").addClass('border-bottom border-2 border-info');
		$("#division").addClass('border-bottom border-2 border-info');
		$("#dropDiv").addClass("rounded border border-bottom-0 border-2 border-info")
		$("input:radio[id=modifyDivRadio]").prop('checked', false);
		
		$("#infoBox").html("");
		$("#infoBox").append(
			'<div class="col>' +
			'<div class="row mt-3>' +
			'<div class="col-3 my-auto text center"><input class="form-control" id="user_pw" type="password" placeholder="비밀번호를 입력해주세요." aria-label="default input example" > </div>' +
			'<div class="col-2 d-grid my-auto" style="margin:0px;"><button type="button" id="inactiveButton" class="btnBasic bi bi-check2-square" style="height:36px;">&nbsp;탈퇴하기</button></div>' +
			'</div>' +
			'<div class="row mt-3">' +
			'<div class="col bi bi-exclamation-square-fill deepblue" >&nbsp;탈퇴 후에는 홈페이지 이용이 제한됩니다. 다시 로그인하실 경우 계정 활성화페이지로 이동하실 수 있습니다.</div>' +
			'</div>' +
			'</div>'
		);
		
		var deleteUserProcess = function () {

			console.log("user_pw : " + $("#user_pw").val());
			console.log("user_no : " + user_no);
			$.ajax({
				type: "post",
				url: "./deleteUserInfoByUserNo",
				data: {
					user_no: user_no,
					user_pw: $("#user_pw").val()
				},
				dataType: "json",
				contentType: "application/x-www-form-urlencoded", // post
				success: function (data) {
					if (data.result == 'fail') {
						alert("비밀번호가 일치하지 않습니다.");
					} else {
						alert("탈퇴가 완료되었습니다.");
						location.href = "../board/mainPage";
					}
				}
			});
		};

		$("#inactiveButton").click(deleteUserProcess);
	}
	

	
	var getUsnerInfo = function () {
		$.ajax({
			type: "get",
			url: "./getUserInfoByUserNo",
			data: {
				userNo: user_no
			},
			dataType: "json",
			// contentType : "application/x-www-form-urlencoded", // post
			
			success: function (data) {
				$("#userId").attr('value', data.userData.USER_ID);
				$("#userNickName").attr('value', data.userData.USER_NICKNAME);
				var userPhone = (data.userData.USER_PHONE).slice(-10);				
				$("#userPhone").attr('value', userPhone);
				$("#userQuestion").attr('value', data.userData.QUESTION_NO);
				$("#userEmail").attr('value', data.userData.USER_EMAIL);
				$("#userfindAnswer").attr('value', data.userData.USER_FINDANSWER);
				questionValue = data.userData.QUESTION_NO;
				Email = data.userData.USER_EMAIL;
			}
		});
	}
	
	
	var showModify = function() {
		$("#dropDiv").removeClass('rounded border border-bottom-0 border-2 border-info');
		$("#dropDiv").addClass('border-bottom border-2 border-info');
		$("#division").addClass('border-bottom border-2 border-info');
		$("#modifyDiv").addClass("rounded border border-bottom-0 border-2 border-info")
		$("input:radio[id=dropDivRadio]").prop('checked', false);
		
		$("#infoBox").html("");
		$("#infoBox").append(
			'<div class="col">' +
			'<div class="row mt-3">' +
			'<div class="col fs-5"><input class="form-control" id="userId" type="text" aria-label="default input example" disabled></div>' +
			'<div class="col-3"></div>' +
			'</div>' +	
				
			'<div class="col">' +
			'<div class="row mt-3">' +
			'<div class="col fs-5"><input class="form-control" id="changePassword" type="password" placeholder="비밀번호를 입력해 주세요." aria-label="default input example"></div>' +
			'<div class="col-6 my-auto" id="alterPassword"></div>' +
			'<div class="col-3"></div>' +
			'</div>' +
			
			'<div class="row mt-3">' +
			'<div class="col bi bi-exclamation-square-fill deepblue">&nbsp;계정 보안을 위하여 비밀번호를 변경하지 않으시더라도 비밀번호 입력이 필요합니다.</div>' +
			'<div class="col-3"></div>' +
			'</div>' +
			
			'<div class="col fs-5"><input class="form-control" id="userNickName" type="text" placeholder="닉네임을 입력해 주세요." aria-label="default input example"></div>' +
			'<div class="col-3"></div>' +
			'</div>' +
			
			'<div class="row mt-3">' +
			'<div class="col-2">' +
			'<select class="form-select" id="firstPhone" aria-label="Default select example">' +
			'<option value="010">010</option>' +
			'<option value="019">019</option>' +
			'<option value="02">02</option>' +
			'</select>' +
			'</div>' +
			
			'<div class="col fs-5"><input class="form-control" id="userPhone" type="text" placeholder="" aria-label="default input example"></div>' +
			'<div class="col-3"></div>' +
			'</div>' +
			
			'<div class="row mt-3">' +
			'<div class="col-4 fs-5"><input class="form-control" id="userEmail" type="text" placeholder="이메일을 입력해 주세요." aria-label="default input example"></div>' +
			'<div class="col-3 d-grid"><button type="button" id="checkEmailButton" class="btnBasic" style="height:36px;">인증번호 발송</button></div>' +
			'<div class="col-2"></div>' +
			'</div>' +
			
			'<div class="row mt-3">' +
			'<div class="col-3 fs-5"><input class="form-control" id="checkEmail" type="text" placeholder="인증번호를 입력해주세요." aria-label="default input example" disabled="disabled"></div>' +
			'<div class="col-2 d-grid"><button type="button" id="confirmEmailButton" class="btnBasic" style="height:36px;">인증확인</button></div>' +
			'<div class="col my-auto" id="alertEmail"></div>' +
			'</div>' +
			
			'<div class="row mt-3">' +
			'<div class="col fs-5">' +
			'<select class="form-select" id="userQuestion" aria-label="Default select example">' +
			'</select>' +
			'</div>' +			
			'<div class="col-3"></div>' +
			'</div>' +
			
			'<div class="row mt-3">' +
			'<div class="col fs-5"><input class="form-control" id="userfindAnswer" type="text" placeholder="" aria-label="default input example"></div>' +
			'<div class="col-3"></div>' +
			'</div>' +
			
			'<div class="row mt-3 mb-3">' +
			'<div class="col bi bi-exclamation-square-fill deepblue">&nbsp;제출 전 정보가 올바르게 기입되었는지 다시 한번 확인해주세요.</div>' +
			'<div class="col-2 d-grid"><button type="button" id="updateButton" class="btnBasic bi bi-check2-square" style="height:36px;">&nbsp;수정하기</button></div>' +
			'<div class="col-3"></div>' +
			'</div>' +
			'</div>'
		);
	
		/*이메일 a@a.m불가능  a@a.mm 가능*/
		var code = "";
		$("#checkEmailButton").click(function () {
			var userEmail = $("#userEmail").val();
			if (userEmail == '') {
				alert('이메일주소를 입력하세요');
				userEmail.focus();
				return false;
			} else {
				var emailRegex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
				if (!emailRegex.test(userEmail)) {
					alert('이메일 주소가 유효하지 않습니다. ex)abc@gmail.com');
					userEmail.focus();
					return false;
				}
			}

			$.ajax({
				type: "GET",
				url: "mailCheck?userEmail=" + userEmail,
				success: function (data) {
					if (data == "error") {
						alert("서버와 통신중 에러가 발생했습니다.");
						$("#userEmail").attr("autofocus", true);
						$("#alertEmail").text("서버와 통신중 에러가 발생했습니다.")
						$("#alertEmail").css({
							"color": "red"
						});
					} else {
						alert("인증번호 발송이 완료되었습니다. 입력한 이메일에서 인증번호 확인을 해주세요.");
						$("#checkEmail").attr("disabled", false);
						$("#alertEmail").text("인증번호를 입력한 뒤 인증 확인을 눌러주세요.")
						$("#alertEmail").css({
							"color": "green"
						});
						code = data;


					}
				}
			});

		});



		$("#confirmEmailButton").click(function () {
			if ($("#checkEmail").val().length != 6) {
				$("#alertEmail").text("인증번호가 일치하지 않습니다. 다시 확인해주시기 바랍니다.")
				$("#alertEmail").css({
					"color": "red"
				});
				$("#checkEmail").attr("autofocus", true);
			} else if ($("#checkEmail").val() == code) {
				$("#alertEmail").text("메일인증이 완료되었습니다.")
				$("#alertEmail").css({
					"color": "green"
				});
				$("#checkEmail").attr("disabled", true);
			} else {

			}
		});
		
		var getQuestionList = function () {

			$.ajax({
				type: "get",
				url: "./getQuestionList",
				data: {

				},
				dataType: "json",
				// contentType : "application/x-www-form-urlencoded", // post
				success: function (data) {
					for (list of data.questionList) {

						if (questionValue == list.question_no) {
							$("#userQuestion").append('<option value="' + questionValue + '">' + list.question_content + '</option>');
						} else {
							$("#userQuestion").append('<option selected value="' + list.question_no + '">' + list.question_content + '</option>');
						}


					}

				}
			});
		}
		
		getQuestionList();
		getUsnerInfo();
		
		$("#updateButton").click(function () {
			var nickname = $("#userNickName").val();
			var firstPhone = $("#firstPhone").val();
			var userPhone = $("#userPhone").val();
			var userEmail = $("#userEmail").val();
			var answer = $("#userfindAnswer").val();
			var changePassword = $("#changePassword").val();
			
			console.log("question_no : " + $("#userQuestion").val());
			
			$.ajax({
				type: "post",
				url: "./updateUserInfoByUserNo",
				data: {
					user_no: user_no,
					question_no: $("#userQuestion").val(),
					user_pw: $("#changePassword").val(),
					user_nickname: nickname,
					user_phone: firstPhone + userPhone,
					user_email: userEmail,
					user_findAnswer: answer
				},
				dataType: "json",
				contentType: "application/x-www-form-urlencoded", // post
				success: function (data) {
					alert("수정이 완료되었습니다.");
					location.href = "./logoutUserPorcess";
				}
			});
		});
	};
		
	$("input:radio[id=modifyDivRadio]").click(showModify);
	$("input:radio[id=dropDivRadio]").click(showDrop);
});