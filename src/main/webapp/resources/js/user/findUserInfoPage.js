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
			
			console.log($("#findNameInput").val());
			console.log($("#userEmail").val());

			
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
});