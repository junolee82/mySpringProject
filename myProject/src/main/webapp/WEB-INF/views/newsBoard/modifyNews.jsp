<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<link href="../../resources/bootstrap/css/bootstrap-social.css" rel="stylesheet">

<!-- Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(function(){
		$("#save").click(function(){
			$("#formObj").submit();
		});
		$("#cancel").click(function(){
			history.back();
		});
	});
	
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

	
	$(document).ready(function(){ 
		var fileTarget = $('.filebox .upload-hidden');
		
		fileTarget.on('change', function(){ // 값이 변경되면 
			
		if(window.FileReader){ // modern browser 
			var filename = $(this)[0].files[0].name; 
		} else { // old IE 
			var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출
		} // 추출한 파일명 삽입 
			$(this).siblings('.upload-name').val(filename); 
		});
		
		//preview image 
	    var imgTarget = $('.preview-image .upload-hidden');

	    imgTarget.on('change', function(){
	        var parent = $(this).parent();
	        parent.children('.upload-display').remove();

	        if(window.FileReader){
	            //image 파일만
	            if (!$(this)[0].files[0].type.match(/image\//)) return;
	            
	            var reader = new FileReader();
	            reader.onload = function(e){
	                var src = e.target.result;
	                parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img src="'+src+'" class="upload-thumb"></div></div>');
	            }
	            reader.readAsDataURL($(this)[0].files[0]);
	        }

	        else {
	            $(this)[0].select();
	            $(this)[0].blur();
	            var imgSrc = document.selection.createRange().text;
	            parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img class="upload-thumb"></div></div>');

	            var img = $(this).siblings('.upload-display').find('img');
	            img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""+imgSrc+"\")";        
	        }
	    });
		
	});

</script>

<style type="text/css">
body{/* background: url("../../resources/img/bg.jpg"); */ background-color: #EAEAEA;}
.box{margin-bottom: 0px; border-bottom: 1px solid #EAEAEA; background: rgba(255,255,255,1);}
#cke_1_contents {height: 600px !important;}
/* font */
p { font-size: 1em;}

.filebox input[type="file"] { position: absolute; width: 1px; height: 1px; padding: 0; margin: -1px; overflow: hidden; clip:rect(0,0,0,0); border: 0; } 
.filebox label { display: inline-block; padding: .5em .75em; margin: 0px; color: #999; font-size: inherit; line-height: normal; vertical-align: middle; background-color: #fdfdfd; cursor: pointer; border: 1px solid #ebebeb; border-bottom-color: #e2e2e2; border-radius: .25em; }
.filebox .upload-name { display: inline-block; padding: .5em .75em; font-size: inherit; font-family: inherit; line-height: normal; vertical-align: middle; background-color: #f5f5f5; border: 1px solid #ebebeb; border-bottom-color: #e2e2e2; border-radius: .25em; -webkit-appearance: none; -moz-appearance: none; appearance: none; }

/* imaged preview */ 
.filebox .upload-display {margin-bottom: 5px; } 
@media(min-width: 768px) { .filebox .upload-display { display: inline-block; margin-right: 5px; margin-bottom: 0; } } 
.filebox .upload-thumb-wrap {display: inline-block; width: 54px; padding: 2px; vertical-align: middle; border: 1px solid #ddd; border-radius: 5px; background-color: #fff; } 
.filebox .upload-display img {display: block; max-width: 100%; width: 100% \9; height: auto; }

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
        
        <form action="modifyNews" method="post" id="formObj">

				<input type="hidden" name="newsNo" value="${readNews.newsNo}"/>
						
				<input type="hidden" name="page" value="${cri.page }" />
				<input type="hidden" name="perPageNum" value="${cri.perPageNum }" />
				<!-- searchCriteria -->
				<input type="hidden" name="searchType" value="${cri.searchType }" readonly="readonly" />
				<input type="hidden" name="keyword" value="${cri.keyword }" readonly="readonly" />
        
        	<div class="box">
        		<div class="col-lg-12">
        			<input type="text" class="form-control" value="${readNews.nTitle }" name="nTitle"/>
					<div style="float: right;">
						<fmt:formatDate value="${readNews.regDate }" pattern="yyyy-MM-dd HH:mm"/>
					</div>
        		</div>
        	</div>
        	
        	<div class="box">
        		<div class="col-lg-12">
        			<div>
        				<strong> BY ${readNews.writer }</strong>
        				<div style="float: right;">
        					<span>조회수  ${readNews.viewCnt }</span> 
        					<span>추천수 ${readNews.recommend }</span>
        					<span>댓글 ${readNews.replyCnt }</span>
        				</div>

        			</div>
        		</div>
        	</div>

	        <div class="box">
		            
		    	<div class="col-md-12">
		    		<h4>${readNews.nTitle }</h4><br>
		    		<textarea class="form-control" rows="50" placeholder="내용" name="nContent">${fn:replace(readNews.nContent, cn, br)}</textarea>		    		
		    		<br>
		    		<br>
		    		<br>		    		
		    	</div>
		    	<div class="col-md-12">
		    		<form action="uploadForm" method="post" id="" enctype="multipart/form-data">
		    			<div class="filebox preview-image"> 
		    				<input class="upload-name" value="타이틀 이미지" disabled="disabled" >
		    				
		    				<label for="input-file">이미지 선택</label>
		    				<input type="file" id="input-file" class="upload-hidden"> 
		    			</div>
		    		</form>
		    	</div>
	                
	        </div>

	   </form>
	        
	        <!-- button -->
	        <div class="box" style="margin-bottom: 20px;">

				<button type="button" class="btn btn-default" id="save">저장</button>
				<button type="button" class="btn btn-default" id="cancel">취소</button>
		    		                
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