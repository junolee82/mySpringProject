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

<!-- Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

<!-- template -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/x-handlebars-template" id="templateAttach">
	
	<div class="thumbnail text-center">

		<img src="{{getLink}}" alt="Attachment" style="margin-top: 20px;" />

		<div class="caption">
			<p style="margin: auto;">
				<a href="{{getLink}}">{{fileName}}</a>
								
				<small class="btn btn-default btn-xs delbtn" data-src="{{fullName}}" data-img="{{titleImg}}">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</small>
								
			</p>
		</div>
	</div>

</script>
<script type="text/x-handlebars-template" id="template">
	<div class="thumbnail text-center">

		<img src="{{getLink}}" alt="Attachment" style="margin-top: 20px;" />

		<div class="caption">
			<p style="margin: auto;">
				<a href="{{getLink}}">{{fileName}}</a>
								
				<small class="btn btn-default btn-xs delbtn" data-src="{{fullName}}" data-img="{{titleImg}}">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</small>
								
			</p>
		</div>
	</div>
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script type="text/javascript" src="/resources/js/upload_data.js"></script>
<script type="text/javascript">

	var newsNo = ${readNews.newsNo};
	
	// 타이틀 이미지 GET
	var template = Handlebars.compile($("#templateAttach").html());
	
	$.getJSON("/newsBoard/getAttach/" + newsNo, function(list){
		$(list).each(function(){
			var fileInfo = getFileInfoMod(this);
			var html = template(fileInfo);
			$(".readTitleImg").append(html);
		});
	});
	
	$(function(){
		/* $("#save").click(function(){
			$("#formObj").submit();
		}); */
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
	
	$(function(){
		var template = Handlebars.compile($("#template").html());		
		
		$(".fileDrop").on("dragenter dragover", function(event){
			event.preventDefault();
		});
		
		$(".fileDrop").on("drop", function(event){
			event.preventDefault();
			
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];
			var formData = new FormData();
			formData.append("file", file);
			
			$.ajax({
				url : "/uploadAjax",
				data : formData,
				dataType : "text",
				processData : false,
				contentType : false,
				type : "POST",
				success : function(data) {
					var fileInfo = getFileInfo(data);
					console.log(fileInfo);
					var html = template(fileInfo);
					$(".uploadedTitleImg").append(html);
				}
			});
		});
		
		$("#formObj").submit(function(event){
			event.preventDefault();
			
			var that = $(this);
			var str = "";
			
			$(".uploadedTitleImg .delbtn").each(function(index){
				str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("data-img") + "'>";
			});
			
			that.append(str);
			that.get(0).submit();
			
		});
		
		// Delete		
		$(".uploadedTitleImg").on("click", "small", function(event){
			
			var that = $(this);
			
			$.ajax({
				url : "/deleteFile",
				type : "post",
				data : {fileName:$(this).attr("data-src")},
				dataType : "text",
				success : function(result) {
					if(result == "deleted") {
						alert("deleted");
						$(".thumbnail").remove();
						//$(".formAjax").html("<div class='fileDrop'></div>");
					}
				}
			});
		});
		
		// Delete		
		$(".readTitleImg").on("click", "small", function(event){
			
			var that = $(this);
			
			$.ajax({
				url : "/deleteFile",
				type : "post",
				data : {fileName:$(this).attr("data-src")},
				dataType : "text",
				success : function(result) {
					if(result == "deleted") {
						alert("deleted");
						$(".thumbnail").remove();
						//$(".formAjax").html("<div class='fileDrop'></div>");
					}
				}
			});
		});
		
	}); // end ready
	
</script>

<style type="text/css">
body{/* background: url("../../resources/img/bg.jpg"); */ background-color: #F1F1F1;}
.box{margin-bottom: 0px; border-bottom: 1px solid #EAEAEA; background: rgba(255,255,255,1);}
#cke_1_contents {height: 600px !important;}
/* font */
p { font-size: 1em;}
/* FileDrop */
.fileDrop { width: 100%; height: 100px; border: 1px dotted gray; margin: auto; margin-bottom: 20px; text-align: center}
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
        
        <form action="modifyNews" method="post" id="formObj" enctype="multipart/form-data">

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

			    	<div class="fileDrop">
			    	
			    		<p>타이틀 이미지</p>
			    		
			    	</div>
			    		
			   	</div>
				<div class="col-lg-12">
					<div class="readTitleImg text-center">
		    			
		    		</div>
				</div>
				
				<div class="col-md-12 uploadedTitleImg">

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
	                
	        </div>

	   
	        
	        <!-- button -->
	        <div class="box" style="margin-bottom: 20px;">

				<button type="submit" class="btn btn-default" id="save">저장</button>
				<button type="button" class="btn btn-default" id="cancel">취소</button>
		    		                
	        </div>

    	</div>
    	
    	</form>    	
    	
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