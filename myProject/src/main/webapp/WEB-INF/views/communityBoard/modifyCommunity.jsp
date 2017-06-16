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

<title>HipHopDX</title>

<!-- Bootstrap Core CSS -->
<link href="../../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

<!-- Custom CSS -->
<link href="../../resources/bootstrap/css/MyPage.css" rel="stylesheet">

<!-- Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

<!-- template -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<!-- 게시물 타이틀 이미지 뷰 -->
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
<!-- AFTER 게시물 타이틀 이미지 뷰 -->
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

	var cNo = ${readCommunity.cNo};
	

	
	$(function(){
		$("#cancel").click(function(){
			history.back();
		});
	});
	
	// ckEditor setting
	var ckeditor_config = {
	     resize_enabled : false, // 에디터 크기를 조절하지 않음
	     enterMode : CKEDITOR.ENTER_BR , // 엔터키를 <br> 로 적용함.
	     shiftEnterMode : CKEDITOR.ENTER_P ,  // 쉬프트 + 엔터를 <p> 로 적용함.
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

	jQuery(function() {	// ckEditor 적용
		editor = CKEDITOR.replace( "cContent" , ckeditor_config );
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
body{background-color: #F1F1F1;}
.box{margin-bottom: 0px; border-bottom: 1px solid #EAEAEA; background: rgba(255,255,255,1);}
#cke_1_contents {height: 600px !important;}
a {color: black;}
a:hover {text-decoration: none; color: #980000;}
/* font */
p { font-size: 1em;}
/* FileDrop */
.fileDrop { width: 100%; height: 100px; border: 1px dotted gray; margin: auto; margin-bottom: 20px; text-align: center}
</style>

</head>
<body>

	<!-- HEADER -->
	
	<jsp:include page="../header/header.jsp" />
	
	<!-- /HEADER -->
	    	
	
    <div class="container">

        <div class="row">
        
        <form action="modifyCommunity" method="post" id="formObj" enctype="multipart/form-data">

				<input type="hidden" name="cNo" value="${readCommunity.cNo}"/>
						
				<input type="hidden" name="page" value="${cri.page }" />
				<input type="hidden" name="perPageNum" value="${cri.perPageNum }" />
				<!-- searchCriteria -->
				<input type="hidden" name="searchType" value="${cri.searchType }" readonly="readonly" />
				<input type="hidden" name="keyword" value="${cri.keyword }" readonly="readonly" />
        
        	<div class="box">
        		<div class="col-lg-12">
        			<input type="text" class="form-control" value="${readCommunity.cTitle }" name="cTitle"/>
					<div style="float: right;">
						<fmt:formatDate value="${readCommunity.regDate }" pattern="yyyy-MM-dd HH:mm"/>
					</div>
        		</div>
        	</div>

        	<div class="box">
        		<div class="col-lg-12">
        			<div>
        				<strong> BY ${readCommunity.writer }</strong>
        				<div style="float: right;">
        					<span>조회수  ${readCommunity.viewCnt }</span> 
        					<span>추천수 ${readCommunity.recommend }</span>
        					<span>댓글 ${readCommunity.replyCnt }</span>
        				</div>

        			</div>
        		</div>
        	</div>

			<!-- <div class="box">
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
			</div> -->

	        <div class="box">
		            
		    	<div class="col-md-12">
		    		<h4>${readCommunity.cTitle }</h4><br>
		    		<textarea class="form-control" rows="50" placeholder="내용" name="cContent">${fn:replace(readCommunity.cContent, cn, br)}</textarea>		    		
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
	        
		</form>
		
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