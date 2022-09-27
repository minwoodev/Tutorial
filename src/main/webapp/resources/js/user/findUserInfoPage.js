/**
 * 
 */

window.addEventListener("DOMContentLoaded", function () {
	
	$("input:radio[id=findIdDiv]").click(function () {
		$("#pwDiv").removeClass('rounded border border-bottom-0 border-2 border-info');
		$("#pwDiv").addClass('border-bottom border-2 border-info');
		$("#division").addClass('border-bottom border-2 border-info');
		$("#idDiv").addClass("rounded border border-bottom-0 border-2 border-info")
		$("input:radio[id=findPwDiv]").prop('checked', false);

		$("#infoBox").html("");
		$("#infoBox").append(
				'<div class="col my-auto text-center"><input class="form-control" id ="findNameInput" type="text" placeholder="닉네임을 입력해주세요" aria-label="default input example"></div>' +
				'<div class="col my-auto text-center"><input class="form-control" id ="userEmail" type="text" placeholder="이메일을 입력해주세요" aria-label="default input example"></div>' +
				'<div class="col-2 d-grid my-auto text-center"><button type="button" class="btnBasic inputSubmit" style="height:36px;" id="findIdButton">아이디 찾기</button></div>' +
				'<div class="row mt-3"></div>' +
				'<div class="col my-auto text-center" id="answerLine"></div>'
		);
		
		$("#findIdButton").click(function () {
			$.ajax({
				type: "get",
				url: "../user/getUserIdByNameAndEmail",
				data: {
					user_nickname: $("#findNameInput").val(),
					user_email: $("#userEmail").val(),
				},
				dataType: "json",
				// contentType : "application/x-www-form-urlencoded", // post
				success: function (data) {
					if (data.result == 'fail') {
						$("#answerLine").css({
							"color": "red"
						});
						$("#answerLine").text("일치하는 아이디가 없습니다. 다시 확인해주세요.");
					} else {
						$("#answerLine").css({
							"color": "red"
						});
						$("#answerLine").text('찾으시는 ID는 "' + data.userInfo.USER_ID + '" 입니다.');
					}
				}
			});
		});	
	});
	
	$("input:radio[id=findPwDiv]").click(function () {
		$("#idDiv").removeClass('rounded border border-bottom-0 border-2 borderInfo');
		$("#idDiv").addClass('border-bottom border-2 borderInfo');
		$("#division").addClass('border-bottom border-2 borderInfo');
		$("#pwDiv").addClass("rounded border border-bottom-0 border-2 borderInfo")
		$("input:radio[id=findIdDiv]").prop('checked', false);



		$("#infoBox").html("");
		$("#infoBox").append(
			'<div class="col my-auto text-center inputTitle" style="padding:8px 0px 11.2px 0px;">아이디</div>' +
			'<div class="col d-grid my-auto text-center"><input class="form-control" id="findIdInput" type="text" placeholder="아이디를 입력해주세요." aria-label="default input example"></div>' +
			'<div class="col-2 d-grid my-auto text-center"><button type="button" class="btnBasic inputSubmit" style="height:36px;" id="findQuestionButton">힌트 조회</button></div>' +
			'<div class="col my-auto text-center inputTitle" style="padding:8px 0px 11.2px 0px;">힌트답</div>' +
			'<div class="col d-grid my-auto text-center"><input class="form-control" id="findAnswerInput" type="text" placeholder="답을 입력해주세요." aria-label="default input example"></div>' +
			'<div class="col-2 d-grid my-auto text-center"><button type="button" class="btnBasic inputSubmit" style="height:36px;" id="findPwButton">비밀번호 찾기</button></div>' +
			'<div class="row mt-3"></div>' +
			'<div class="col-6 text-center" id="answerLine"></div>' +
			'<div class="col-6 text-center" id="answerLine2""></div>' +
			'<div class="row mt-5">' +
			'<div class="col-1"></div>' +
			'<div class="col-10  bi bi-exclamation-square-fill deepblue">&nbsp;가입하신 아이디로 힌트를 조회하고 비밀번호찾기 답변을 입력하시면 가입시 입력한 이메일로 임시 비밀번호가 발송됩니다.<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;메일 발송은 서버 상황에 따라 5초에서 10초정도 시간이 소요될 수 있습니다.<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;로그인 후 개인정보 보호를 위해 가급적 비밀번호를 변경해 주시기 바랍니다.</div>' +
			'<div class="col-1"></div>' +
			'</div>'
		);
		
		$("#findQuestionButton").click(function () {
			
			$.ajax({
				type: "get",
				url: "../user/getUserQuestionById",
				data: {
					user_id: $("#findIdInput").val(),
				},
				dataType: "json",
				//contentType : "application/x-www-form-urlencoded", // post
				success: function (data) {
					if (data.result == 'fail') {
						$("#answerLine").css({
							"color": "red"
						});
						$("#answerLine").text("일치하는 아이디가 없습니다. 다시 확인해주세요.");
					} else {
						$("#answerLine").css({
							"color": "red"
						});
						$("#answerLine").text('' + data.userInfo.QUESTION_CONTENT + '');
					}
				}
			});
		});
		
		$("#findPwButton").click(function () {

			$.ajax({
				type: "get",
				url: "./getUserPwByfindAnswer",
				data: {
					user_id: $("#findIdInput").val(),
					user_findAnswer: $("#findAnswerInput").val(),
				},
				dataType: "json",
				//contentType : "application/x-www-form-urlencoded", // post
				success: function (data) {
					if (data.result == 'fail') {
						$("#answerLine2").css({
							"color": "red"
						});
						$("#answerLine2").text("답이 올바르지 않습니다. 다시 확인해주세요.");
					} else {
						alert("가입시 입력하신 이메일로 임시비밀번호가 발송되었습니다. 서버 상황에 따라 메일발송이 지연될 수 있습니다.");
					}
				}
			});
		});
	});
});