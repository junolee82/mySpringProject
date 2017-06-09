<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>KoreaHipHop</title>

<!-- Bootstrap Core CSS -->
<link href="../../resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />

<!-- Custom CSS -->
<link href="../../resources/bootstrap/css/MyPage.css" rel="stylesheet">

<!-- Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
	rel="stylesheet" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		// 아이디 중복 검사
		var uid = $("input[name='uId']").val();

		$("input[name='uId']").on("blur", function() {
			$.ajax({
				url : "checkId/" + uid,
				type : "post",
				timeout : 30000,
				datatype : "text",
				data : {
					uId : $("input[name='uId']").val()
				},
				success : function(data) {

					alert(data);
					/* if(data == false){
						$(".errorId").css("display", "block");
						return false;
					} else {
						$(".errorId").css("display", "none");
					} */

				}
			});
		});

		// 회원가입 검사
		$("form[name='f1']").submit(
				function() {

					// 회원가입 폼창 정보가 빈칸일때 가입 false
					if (!checkInputEmpty($("input[name]"))) {
						if (!($('#radGroup').checked)) {
							$(".errorRad").css("display", "block");
						}
						return false;
					}

					// password == confirmpassword
					if ($("input[name='password']").val() != $(
							"input[name='confirmPassword']").val()) {
						var $next = $("input[name='confirmPassword']").nextAll(
								".error").eq(1);
						$next.css("display", "block");
						return false;
					}

				});

	});
</script>

<style type="text/css">
#wrap {
	width: 1000px;
	padding: 0;
	text-align: center;
}

#logo {
	margin-top: 40px;
}

#logo img {
	width: 300px;
}
/* 폼태크 */
#form1 {
	width: 460px;
	border: 1px solid #EAEAEA;
	margin: 0 auto;
	margin-top: 40px;
}

#form2 {
	width: 460px;
	border: 1px solid #EAEAEA;
	margin: 0 auto;
	margin-top: 20px;
}

#form3 {
	width: 460px;
	margin: 0 auto;
	margin-top: 20px;
}

.form-group {
	padding: 10px 15px 10px 15px;
	border-bottom: 1px solid #EAEAEA;
	margin: 0px;
}

input[type=text], input[type=password], input[type=date], input[type=email],
	input[type=tel], input[type=submit] {
	border: none;
	box-shadow: none;
}

label {
	background-color: white;
	border: 1px solid #EAEAEA;
	padding: 5px 90px;
	margin: 0;
}

input[type=radio] {
	display: none;
}

input[type=radio]:checked+label {
	border: 1px solid #3F0099;
}

input[type=submit] {
	width: 460px;
	height: 60px;
	background-color: #353535;
	color: white;
}
/* 푸터 */
#footer {
	margin-top: 100px;
	margin-bottom: 20px;
}

#footer img {
	width: 60px;
	margin-right: 20px;
}
</style>

</head>
<body>

	<div class="container" id="wrap">

		<div id="logo">
			<div class="brand" style="margin-bottom: 20px">KoreaHipHop.com</div>
		</div>

		<form action="/user/join" method="post">

			<div id="form1">
				<div class="form-group">
					<input type="text" name="uId" class="form-control" id=""
						placeholder="아이디">
				</div>
				<div class="form-group">
					<input type="password" name="uPw" class="form-control" id=""
						placeholder="비밀번호">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id=""
						placeholder="비밀번호 재확인">
				</div>
			</div>

			<div id="form2">
				<div class="form-group">
					<input type="text" name="uName" class="form-control" id=""
						placeholder="이름">
				</div>

				<div class="form-group">
					<input type="email" name="uEmail" class="form-control" id=""
						placeholder="이메일">
				</div>

				<div class="form-group">
					<input type="tel" name="uPhone" class="form-control" id=""
						placeholder="휴대전화번호">
				</div>
			</div>

			<div id="form3">
				<input type="submit" value="가입하기">
			</div>

		</form>

		<div id="footer">
			<!-- <img src="image/megabox_logo.png"> -->
			<span>Copyright © MEGABOX Corp. All Rights Reserved.</span>
		</div>

	</div>

</body>
</html>