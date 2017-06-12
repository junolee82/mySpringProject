<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<link href="../../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

<!-- Custom CSS -->
<link href="../../resources/bootstrap/css/MyPage.css" rel="stylesheet">

<!-- Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		// 아이디 중복 검사
		$("input[name='uId']").on("blur", function() {
			
			var uId = $("input[name='uId']").val();
			
			$.ajax({
				type : "post",
				url : "checkId/" + uId,				
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				data : JSON.stringify({
					uId : uId
				}),
				dataType : "json",
				success : function(data) {
					console.log(data);
					
					if(data == false){
						$(".errorId").css("display", "block");
						return false;
					} else {
						$(".errorId").css("display", "none");
					}

				}
			});
		});

		// 회원가입 검사
		$("form[name='formObj']").submit(function(){
			// 회원가입 폼창 정보가 빈칸일때 가입 false
			if (!checkInputEmpty($("input[name]"))) {
				return false;
			}

			// password == confirmpassword
			if ($("input[name='uPw']").val() != $("input[name='confirmPassword']").val()) {
				var $next = $("input[name='confirmPassword']").nextAll(".error").eq(1);
					$next.css("display", "block");
					
				return false;
			}
			
		});

	});
</script>

<style type="text/css">
#wrap {width: 1000px; padding: 0; text-align: center;}
#logo {margin-top: 40px;}
#logo img {width: 300px;}
/* 폼태크 */
#form1 {width: 460px; border: 1px solid #EAEAEA; margin: 0 auto; margin-top: 40px;}
#form2 {width: 460px; border: 1px solid #EAEAEA; margin: 0 auto; margin-top: 20px;}
#form3 {width: 460px; margin: 0 auto; margin-top: 20px;}
.form-group {padding: 10px 15px 10px 15px; border-bottom: 1px solid #EAEAEA; margin: 0px;}
input[type=text], input[type=password], input[type=date], input[type=email],input[type=tel], input[type=submit] {border: none; box-shadow: none;}
label {background-color: white;	border: 1px solid #EAEAEA; padding: 5px 90px; margin: 0;}
input[type=radio] {display: none;}
input[type=radio]:checked+label {border: 1px solid #3F0099;}
input[type=submit] {width: 460px; height: 60px; background-color: #353535; color: white;}
/* 푸터 */
#footer {margin-top: 100px; margin-bottom: 20px;}
#footer img {width: 60px; margin-right: 20px;}
/* 에러 메세지 */
.errorId { display: none; color: red; padding: 0; margin: 0 0 0 0; font-size: 12px;}
.error { display: none; color: red; padding: 0; margin: 0 0 0 0; font-size: 12px; padding-top: 8px;}
</style>

</head>
<body>

	<div class="container" id="wrap">

		<div id="logo">
			<div class="brand" style="margin-bottom: 20px">KoreaHipHop.com</div>
		</div>

		<form action="/user/join" method="post" name="formObj">

			<div id="form1">
				<div class="form-group">
					<input type="text" name="uId" class="form-control" id="" placeholder="아이디">
					<span class="errorId">이미 사용중이거나 탈퇴한 아이디입니다.</span>
					<span class="error">필수 정보입니다.</span>
				</div>
				<div class="form-group">
					<input type="password" name="uPw" class="form-control" id="" placeholder="비밀번호">
					<span class="error">필수 정보입니다.</span>
				</div>
				<div class="form-group">
					<input type="password" name="confirmPassword" class="form-control" id="" placeholder="비밀번호 재확인">
					<span class="error">필수 정보입니다.</span>
					<span class="error">비밀번호가 일치하지 않습니다.</span>
				</div>
			</div>

			<div id="form2">
				<div class="form-group">
					<input type="text" name="uName" class="form-control" id="" placeholder="이름">
					<span class="error">필수 정보입니다.</span>
				</div>

				<div class="form-group">
					<input type="email" name="uEmail" class="form-control" id="" placeholder="이메일">
					<span class="error">필수 정보입니다.</span>
				</div>

				<div class="form-group">
					<input type="tel" name="uPhone" class="form-control" id="" placeholder="휴대전화번호">
					<span class="error">필수 정보입니다.</span>
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