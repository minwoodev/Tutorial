/**
 * 
 */

window.addEventListener("DOMContentLoaded", function () {

	$("#recoveryButton").click(function () {


		$.ajax({
			type: "get",
			url: "./recoveryUserByInfo",
			data: {
				user_id: $("#userId").val(),
				user_nickname: $("#userNickName").val(),
				user_email: $("#userEmail").val(),
			},
			dataType: "json",
			//contentType : "application/x-www-form-urlencoded", // post
			success: function (data) {
				if (data.result == 'fail') {
					alert("일치하는 사용자 정보가 없습니다.");
					return;
				} else {
					alert("계정이 활성화되었습니다");
					location.href = "../user/loginPage";
				}
			}
		});
	});

});