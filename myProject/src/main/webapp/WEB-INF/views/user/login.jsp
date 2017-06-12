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
	$(function(){
		$("form[name='formObj']").submit(function(){
			if(!checkInputEmpty($("input[name]"))){
				return false;
			}
		});		
	});
</script>

<style type="text/css">
#wrap{ width:1000px; padding: 0; text-align: center;}
#logo{margin-top: 40px;}
#logo img{ width: 300px;}
/* 폼태크 */
#form1 { width: 460px; border: 1px solid #EAEAEA; margin: 0 auto; margin-top: 80px;}
#form2 { width: 460px; border: 1px solid #EAEAEA; margin: 0 auto; margin-top: 20px;}
#form3 { width: 460px; margin: 0 auto; margin-top: 40px;}
.form-group { padding: 10px 15px 10px 15px; border-bottom: 1px solid #EAEAEA; margin: 0px;}
input[type=text], input[type=password], input[type=submit] { border: none; box-shadow: none;}
input[type=submit] { width: 460px; height: 60px; background-color: #212121; color: white;}
/* 푸터 */
#loginOption { border-top: 1px solid #EAEAEA; width: 458px; margin: 0 auto; margin-top: 50px;}
#loginOption p { padding-top: 30px; color: #A6A6A6;}
#banner { margin-top: 100px;}
#footer{ margin-top: 50px; margin-bottom: 20px;}
#footer img { width: 60px; margin-right: 20px;}
a{ color: #670000;}
/* 에러 메세지 */
.error { display: none; color: red; padding: 0; margin: 0 0 0 0; font-size: 12px; padding-top: 8px;}
</style>

</head>
<body>

	<div class="container" id="wrap">
		<div id="logo">
			<!-- <img src="image/megabox_logo.png"> -->
			<div class="brand" style="margin-bottom: 20px">KoreaHipHop.com</div>	
		</div>
		
		<form action="/user/loginPost" method="post" name="formObj">
		
			<div id="form1">
				<div class="form-group">
					<input type="text" name="uId" class="form-control" id="" placeholder="아이디">
					<span class="error">아이디를 입력하세요.</span>
				</div>
			</div>
			
			<div id="form2">
				<div class="form-group">
					<input type="password" name="uPw" class="form-control" id="" placeholder="비밀번호">
					<span class="error">비밀번호를 입력하세요.</span>
				</div>
				<div>
					<!-- <input type="checkbox" name="useCookie" /> -->
				</div>
			</div>
			
			<div id="form3">
				<input type="submit" value="로그인">
			</div>
			
		</form>
		
		<div id="loginOption">
			<p><a href="#">아이디 찾기</a>&nbsp; | &nbsp;<a href="#">비밀번호 찾기</a>&nbsp; | &nbsp;<a href="#">회원가입</a></p>
		</div>
			
		<div id="banner">
			<!-- <img src="image/banner.jpg"> -->
		</div>
		<div id="footer">			
			<!-- <img src="image/megabox_logo.png"> -->
			<span>Copyright © KOREAHIPHOP.COM Corp. All Rights Reserved.</span>
		</div>
	</div>
	
</body>
</html>