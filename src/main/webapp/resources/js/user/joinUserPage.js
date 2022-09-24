/**
 * 
 */
window.addEventListener("DOMContentLoaded", function () {	

	$("#checkIdButton").click(function () {
		var value = $("#joinIdInput").val();

		if (value.replace(/\s|　/gi, "").length == 0) {
			$("#alertId").css({
				"color": "red"
			});
			$("#alertId").text("!  아이디를 입력해주세요.")
			return;
		}
	
		$.ajax({
			type: "get",
			url: "./checkId",
			data: {
				user_id: $("#joinIdInput").val()
			},
			dataType: "json",
			//contentType : "application/x-www-form-urlencoded", // post
			success: function (data) {
				if (data.result == "fail") {
					$("#alertId").css({
						"color": "red"
					});
					$("#alertId").text("! 이미 사용중인 아이디입니다.")
				} else {
					$("#alertId").css({
						"color": "black"
					});
					$("#alertId").text("✔  사용 가능한 아이디입니다.")
					}
				}
		});		
	});
	
	$("#checkNickNameButton").click(function () {
		var value = $("#userNickName").val();

		if (value.replace(/\s|　/gi, "").length == 0) {
			$("#alertNickName").css({
				"color": "red"
			});
			$("#alertNickName").text("!  닉네임를 입력해주세요.")
			return;
		}
	
		$.ajax({
			type: "get",
			url: "./checkNickName",
			data: {
				user_nickname: $("#userNickName").val()
			},
			dataType: "json",
			//contentType : "application/x-www-form-urlencoded", // post
			success: function (data) {
				if (data.result == "fail") {
					$("#alertNickName").css({
						"color": "red"
					});
					$("#alertNickName").text("! 이미 사용중인 닉네임입니다.")
				} else {
					$("#alertNickName").css({
						"color": "black"
					});
					$("#alertNickName").text("✔  사용 가능한 닉네임입니다.")
					}
				}
		});		
	});
	
	$("#checkPhoneNumberButton").click(function () {
		var value = $("#userPhone").val();

		if (value.replace(/\s|　/gi, "").length == 0) {
			$("#alertPhoneNumber").css({
				"color": "red"
			});
			$("#alertPhoneNumber").text("!  휴대폰번호를 입력해주세요.")
			return;
		}
	
		$.ajax({
			type: "get",
			url: "./checkPhoneNumber",
			data: {
				user_phone: $("#userPhone").val()
			},
			dataType: "json",
			//contentType : "application/x-www-form-urlencoded", // post
			success: function (data) {
				if (data.result == "fail") {
					$("#alertPhoneNumber").css({
						"color": "red"
					});
					$("#alertPhoneNumber").text("! 이미 사용중인 휴대폰번호입니다.")
				} else {
					$("#alertPhoneNumber").css({
						"color": "black"
					});
					$("#alertPhoneNumber").text("✔  사용 가능한 휴대폰번호입니다.")
					}
				}
		});		
	});
	
	$("#checkEmailButton").click(function () {
		var value = $("#userEmail").val();

		if (value.replace(/\s|　/gi, "").length == 0) {
			$("#alertEmail").css({
				"color": "red"
			});
			$("#alertEmail").text("!  이메일주소을 입력해주세요.")
			return;
		}
	
		$.ajax({
			type: "get",
			url: "./checkEmail",
			data: {
				user_email: $("#userEmail").val()
			},
			dataType: "json",
			//contentType : "application/x-www-form-urlencoded", // post
			success: function (data) {
				if (data.result == "fail") {
					$("#alertEmail").css({
						"color": "red"
					});
					$("#alertEmail").text("! 이미 사용중인 이메일주소입니다.")
				} else {
					$("#alertEmail").css({
						"color": "black"
					});
					$("#alertEmail").text("✔  사용 가능한 이메일주소입니다.")
					}
				}
		});		
	});
	
	$("#joinButton").click(function () {
		if ($("#alertId").text() != "✔  사용 가능한 아이디입니다.") {
			alert("아이디 중복 확인을 해주세요.");
			return;
		} 
		
		if ($("#alertNickName").text() != "✔  사용 가능한 닉네임입니다.") {
			alert("닉네임 중복확인을 해주세요");
			return;
		} 
		
		if ($("#alertPhoneNumber").text() != "✔  사용 가능한 휴대폰번호입니다.") {
			alert("휴대폰번호 중복 확인을 해주세요.");
			return;
		} 
		
		if ($("#alertEmail").text() != "✔  사용 가능한 이메일주소입니다.") {
			alert("이메일 중복 확인을 해주세요");
			return;
		} 
		
		$("#insertForm").submit();
		alert("회원가입이 완료 되었습니다. 이메일 인증을 해주세요");
	});
	
});