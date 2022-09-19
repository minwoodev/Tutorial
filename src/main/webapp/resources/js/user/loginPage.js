/**
 * 
 */

window.addEventListener("DOMContentLoaded", function () {
	
	$("#inputId").keypress(function (e) {
		if (e.keyCode == 13) {
			$("#loginButton").click();
		}
	});
	
	$("#inputPw").keypress(function (e) {
		if (e.keyCode == 13) {
			$("#loginButton").click();
		}
	});
	
	$("#loginButton").click(function () {
		
		var id = $("#inputId").val();
		var pw = $("#inputPw").val();
		
		if (id.replace(/\s|　/gi, "").length == 0) {
			$("#alertId").css({
				"color": "red"
			});
			$("#alertId").text("!  아이디를 입력해주세요.")
			return;
		}
		
		if (pw.replace(/\s|　/gi, "").length == 0) {
			$("#alertPw").css({
				"color": "red"
			});
			$("#alertPw").text("!  패스워드를 입력해주세요.")
			return;
		}
		
		$.ajax({
			type: "get",
			url: "../user/userLoginParocess",
			data: {
				user_id: $("#inputId").val(),
				user_pw: $("#inputPw").val(),
			},
			dataType: "json",
			// contentType : "application/x-www-form-urlencoded", // post
			success: function (data) {
				if (data.result == "success") {
					location.href="../board/mainPage"
				} else {
					alert("로그인에 실패하였습니다. 아이디와 비밀번호를 확인해 주세요.");
				}
			}
		});
	});
});