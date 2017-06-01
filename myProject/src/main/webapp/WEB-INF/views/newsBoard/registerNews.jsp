<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link href="../../resources/bootstrap/css/business-casual.css" rel="stylesheet">

<!-- Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript">

	// ckEditor setting
	var ckeditor_config = {
	     resize_enabled : false, // 에디터 크기를 조절하지 않음
	     enterMode : CKEDITOR.ENTER_BR , // 엔터키를 <br> 로 적용함.
	     shiftEnterMode : CKEDITOR.ENTER_P ,  // 쉬프트 +  엔터를 <p> 로 적용함.
	     toolbarCanCollapse : true , 
	     removePlugins : "elementspath", // DOM 출력하지 않음
	     filebrowserUploadUrl: '/file_upload', // 파일 업로드를 처리 할 경로 설정.
	
	     // 에디터에 사용할 기능들 정의
	     toolbar : [
	                
	       [ 'Source', '-' , 'NewPage', 'Preview' ],
	       [ 'Cut', 'Copy', 'Paste', 'PasteText', '-', 'Undo', 'Redo' ],
	       [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript'],
	       [ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ],
	       '/',
	       [ 'Styles', 'Format', 'Font', 'FontSize' ],
	       [ 'TextColor', 'BGColor' ],
	       [ 'Image', 'Flash', 'Table' , 'SpecialChar' , 'Link', 'Unlink']
	
	     ]
	
	   };
	
	var editor = null;

	jQuery(function() {
		// ckEditor 적용
	  editor = CKEDITOR.replace( "nContent" , ckeditor_config );
	});

	// 입력 항목의 체크 함수
	function filter(options) {
	    var is = true;
	
	    jQuery(options).each(function() {
	        var item = this;
	
	        switch (item.filter) {
	            case 'empty' :
	                var val = jQuery(item.target).val();
	                if (val == '') {
	                    alert(item.title + '을(를) 입력하세요.');
	                    jQuery(item.target).focus();
	                    is = false;
	                }
	            break;
	
	            case 'number' :
	                var val = jQuery(item.target).val();
	                var num_regx = /[^0-9]/;
	                if (num_regx.test(val)) {
	                alert(item.title + '을(를) 숫자만 입력하세요.');
	                jQuery(item.target).focus();
	                    is = false;
	                }
	            break;
	
	        }
	    });
	    return is;
	}

</script>

<style type="text/css">
body{/* background: url("../../resources/img/bg.jpg"); */ background-color: #EAEAEA;}
.box{border-bottom: 1px solid #EAEAEA;}
input[type=submit] {border: none; width: 460px; height: 60px; background-color: #353535; color: white;}
#cke_1_contents {height: 600px !important;}
</style>

</head>
<body>

    <div class="brand" style="margin-bottom: 20px">KoreaHipHop.com</div>
    <!-- <div class="address-bar">3481 Melrose Place | Beverly Hills, CA 90210 | 123.456.7890</div> -->

    <!-- Navigation -->
    <nav class="navbar navbar-default" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                <a class="navbar-brand" href="index.html">Business Casual</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/newsBoard/news">NEWS</a>
                    </li>
                    <li>
                        <a href="about.html">MAGAZINE</a>
                    </li>
                    <li>
                        <a href="blog.html">LIFESTYLE</a>
                    </li>
                    <li>
                        <a href="contact.html">VIDEO</a>
                    </li>
                    <li>
                        <a href="contact.html">COMMUNITY</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <div class="container">

        <div class="row">
        
        	<div class="box">
        		<div class="col-lg-12">
        			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
        			&nbsp;
        			<strong> NEWS REGISTER</strong>
        		</div>
        	</div>
        	
     	
            <div class="box">
            
            <form action="registerNews" method="post" id="registerForm">
	            <div class="col-md-12 text-center">
					<div class="input-group">
  						<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-file" aria-hidden="true"></span></span>
  						<input type="text" class="form-control" placeholder="제목" aria-describedby="basic-addon1" name="nTitle">
					</div>
					
					<div class="input-group">
  						<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
  						<input type="text" class="form-control" placeholder="작성자" aria-describedby="basic-addon1" name="writer">
					</div>
					
					<textarea class="form-control" rows="40" placeholder="내용" name="nContent" id="nContent"></textarea>
										
					<input type="submit" value="등록" class="text-center" />
					
	            </div>            
	            
	        </form>
	                            
            </div>

       
        </div>

    </div>
    <!-- /.container -->

    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    
</body>
</html>