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
				$("#userId").text(data.userData.USER_ID);
				$("#userNickName").attr('value', data.userData.USER_NICKNAME);
				var userPhone = (data.userData.USER_PHONE).slice(-8);				
				$("#userPhone").attr('value', userPhone);
				$("#userQuestion").attr('value', data.userData.QUESTION_NO);
				$("#userEmail").attr('value', data.userData.USER_EMAIL);
				$("#userfindAnswer").attr('value', data.userData.USER_FINDANSWER);
				questionValue = data.userData.QUESTION_NO;
				Email = data.userData.USER_EMAIL;
			}
		});
	}
	
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
	
	$("input:radio[id=dropDivRadio]").click(showDrop);
});