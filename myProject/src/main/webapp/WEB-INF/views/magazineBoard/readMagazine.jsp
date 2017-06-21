<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    // 줄바꿈 
    pageContext.setAttribute("br", "<br/>");
    pageContext.setAttribute("cn", "\n");
%>
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
<link href="../../resources/bootstrap/css/bootstrap.min.css?ver=1" rel="stylesheet" type="text/css" />

<!-- Custom CSS -->
<link href="../../resources/bootstrap/css/MyPage.css" rel="stylesheet">

<!-- Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

<!-- template -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<!-- 댓글 리스트 -->
<script type="text/x-handlebars-template" id="template">
	{{#each.}}
		<div class="box replyLi" data-rNo={{rNo}} style="margin: 0px 20px 20px 20px;">
			
			<div class="col-md-12">
				<p>
				{{replyer}}
				{{prettifyDate regDate}}
				</p>
			</div>
				
			<div class="col-md-12">
				<p>
				<span class="timeline-body">{{replyText}}</span>
				
				{{#eqReplyer replyer}}
				<a class="btn btn-default btn-xs" data-toggle="modal" data-target="#modifyModal">댓글수정</a>
				{{/eqReplyer}}
				
				</p>
			</div>
				
		</div>
	{{/each}}
</script>
<!-- 게시물 타이틀 이미지 -->
<script type="text/x-handlebars-template" id="templateAttach">
	
	<img src="{{getLink}}" alt="templateAttach" data-src="{{fullName}}" />

</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script type="text/javascript">
	
	var mNo = ${readMagazine.mNo};
	
	// 게시물 타이틀 이미지 GET
	var template = Handlebars.compile($("#templateAttach").html());
	
	$.getJSON("/magazineBoard/getAttach/" + mNo, function(list){
		$(list).each(function(){
			var fileInfo = getFileInfoMod(this);
			var html = template(fileInfo);
			$(".readTitleImg").append(html);
		});
	});
	
	// 로그인한 사용자 댓글 수정/삭제 버튼
	Handlebars.registerHelper("eqReplyer", function(replyer, block){
		var accum = "";
		if(replyer == '${login.uId}') {
			accum += block.fn();
		}
		return accum;
	});
	
	// ready
	$(function(){
		$("#listNews").click(function(){
			history.go(-1);
		});
		
		// 게시물 수정 버튼
		$("#modify").click(function(){
			$("#formObj").attr("action", "/magazineBoard/modifyMagazine");
			$("#formObj").attr("method", "get");
			$("#formObj").submit();
		});
		
		// 게시물 삭제 버튼
		$("#remove").click(function(){
			
			var replyCnt = $("#newsDelReplyCnt").text().replace(/[^0-9]/g, "");
			
			if(replyCnt > 0) {
				alert("댓글이 달린 게시물을 삭제할 수 없습니다.");
				return;
			}
			
			var arr = [];
			$(".readTitleImg img").each(function(index){
				arr.push($(this).attr("data-src"));
			});
			
			if(arr.length > 0) {
				$.post("/deleteAllFiles", {files:arr}, function(){
					
				});
			}
			
			$("#formObj").attr("action", "/magazineBoard/removeMagazine");
			$("#formObj").submit();
		});
		
		
		
		/* LIST REPLY */
		getPage("/mReplies/" + mNo + "/1");
		
		/* 댓글 페이징 이벤트 */
		$(".pagination").on("click", "li a", function(event) {
			event.preventDefault();
			replyPage = $(this).attr("href");
			getPage("/mReplies/" + mNo + "/" + replyPage);
		});
		
		/* 댓글 등록 */
		$("#replyAddBtn").on("click", function(){
			var replyerObj = $("#newReplyWriter");
			var replyTextObj = $("#newReplyText");
			var replyer = replyerObj.val();
			var replyText = replyTextObj.val();
			
			if(replyText == "") {
				return;
			}
			
			$.ajax({
				type : "post",
				url : "/mReplies",
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : "text",
				data : JSON.stringify({
					mNo : mNo,
					replyer : replyer,
					replyText : replyText
				}),
				success : function(result) {
					if(result == "SUCCESS") {
						alert("등록 되었습니다.");
						replyPage = 1;
						getPage("/mReplies/" + mNo + "/" + replyPage);
						replyerObj.val("");
						replyTextObj.val("");
					}
				}
			});
		});
		
		/* modal setting */
		$(document).on("click", ".replyLi", function(){
			var reply = $(this);
			// alert(reply.find(".timeline-body").text());
			$("#replyText").val(reply.find(".timeline-body").text());
			$(".rNo").html(reply.attr("data-rNo"));
		});
		
		/* 댓글 수정 */
		$("#replyModBtn").on("click", function(){
			var rNo = $(".rNo").html();
			var replyText = $("#replyText").val();
			
			$.ajax({
				type : "put",
				url : "/mReplies/" + rNo,
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "PUT"
				},
				data : JSON.stringify({
					replyText : replyText
				}),
				dataType : "text",
				success : function(result) {
					console.log("result: " + result);
					if(result == "SUCCESS") {
						alert("수정 되었습니다");
						getPage("/mReplies/" + mNo + "/" + replyPage);
						$('#modifyModal').modal("hide");
					}
				}
			});
		});
		
		/* 댓글 삭제 */
		$("#replyDelBtn").on("click", function(){
			var rNo = $(".rNo").html();
			var replyText = $("#replyText").val();
			
			$.ajax({
				type : "delete",
				url : "/mReplies/" + rNo,
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "DELETE"
				},
				dataType : "text",
				success : function(result) {
					console.log("result: " + result);
					if(result == "SUCCESS") {
						alert("삭제 되었습니다.");
						getPage("/mReplies/" + mNo + "/" + replyPage);
						$('#modifyModal').modal("hide");
					}
				}
			});
		});
		
		// 게시글 추천 버튼
		$("#recommend").on("click", function(){
			if(confirm("게시글을 추천하시겠습니까?")) {
				$.getJSON("/magazineBoard/updateRecommend/" + mNo, function(){});
				alert("게시글을 추천하였습니다.");				
			} else {
				return;
			}			
		});
		
		
		
	}); //end ready
	
</script>

<style type="text/css">
* {line-height: 1.8;}
body{/* background: url("../../resources/img/bg.jpg"); */ background-color: #F1F1F1;}
.box{margin-bottom: 0px; border-bottom: 1px solid #EAEAEA; background: rgba(255,255,255,1);}
textarea.form-control{ border: none; box-shadow: none; padding: 15px 20px; border-bottom: 1px solid #EAEAEA;}
textarea.form-control:focus{outline: none; border-color: none; box-shadow: none;}
input[type=text] { border: none; box-shadow: none; padding: 15px 20px; margin: 10px 0px;}
input[type=text].form-control:focus{outline: none; border-color: none; box-shadow: none;}
button[type=submit] {border: none; width: 100%; height: 60px; background-color: #353535; color: white;}
#beforeAddBtn[type=button] {border: none; width: 100%; height: 60px; background-color: #353535; color: white;}
#recommend[type=button] {border: 2px solid #353535; width: 200px; height: 60px; background-color: white; color: #353535; font-weight: bold; font-size: 20px;}
#recommend[type=button]:hover {border: none; width: 200px; height: 60px; background-color: #353535; color: white; font-weight: bold; font-size: 20px;}
a {color: black;}
a:hover {text-decoration: none; color: #980000;}
/* font */
p { font-size: 1em;}
p img {}
.form-control[readonly] {background-color: white;}
</style>

</head>
<body>

	<!-- HEADER -->
	
	<jsp:include page="../header/header.jsp" />
	
	<!-- /HEADER -->

    <div class="container">

        <div class="row">
        	
				<!-- form -->
				<form method="post" id="formObj">
					<input type="hidden" name="mNo" value="${readMagazine.mNo}" readonly="readonly" />
					<input type="hidden" name="page" value="${cri.page }" readonly="readonly" />
					<input type="hidden" name="perpageNum" value="${cri.perPageNum }" readonly="readonly" />
					<!-- searchCriteria -->
					<input type="hidden" name="searchType" value="${cri.searchType }" readonly="readonly" />
					<input type="hidden" name="keyword" value="${cri.keyword }" readonly="readonly" />
				</form>        	
        	
        	<div class="box">
        		<div class="col-lg-12">
        			<span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span>
        			&nbsp;
        			<strong> ${readMagazine.mTitle }</strong>
					<div style="float: right;">
						<fmt:formatDate value="${readMagazine.regDate }" pattern="yyyy-MM-dd HH:mm"/>
					</div>
        		</div>
        	</div>
        	
        	<div class="box">
        		<div class="col-lg-12">
        			<div>
        				<strong> BY ${readMagazine.writer }</strong>
        				<div style="float: right;">
        					<span>조회수  ${readMagazine.viewCnt }</span> 
        					<span>추천수 ${readMagazine.recommend }</span>
        					<span>댓글 <span id="newsDelReplyCnt">${readMagazine.replyCnt }</span></span>
        				</div>

        			</div>
        		</div>
        	</div>

	        <div class="box">
		            
		    	<div class="col-md-12" style="padding: 60px">
		    	
		    		<div class="readTitleImg text-center" style="margin-bottom: 80px;">
		    			
		    		</div>
		    	
		    		<h4>${readMagazine.mTitle }</h4><br>
		    		${fn:replace(readMagazine.mContent, cn, br)}
		    		<br>
		    		<br>
		    		<br>
		    		
		    		<div class="text-center" style="padding-top: 40px;">
		    			<button type="button" id="recommend"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"> 추천!</span></button>
		    		</div>
		    	</div>	
	                
	        </div>
	        
	        <!-- board button -->
	        <div class="box" style="margin-bottom: 20px;">
				
				<div class="col-md-12">
				
			    	<button type="button" class="btn btn-default" id="listNews"><span class="glyphicon glyphicon-list" aria-hidden="true"></span> 목록</button>
			    	
			    	<div class="btn-group" role="group" aria-label="...">
					  <input type="button" class="btn btn-default" value="Twitter" />
					  <input type="button" class="btn btn-default" value="Facebook" />
					</div>
					
					<c:if test="${login.uId == 'admin' }">
					<button type="button" class="btn btn-default" id="modify">수정</button>
					<button type="button" class="btn btn-default" id="remove">삭제</button>
					</c:if>
				
		    	</div>
		    	
	        </div>
	        
	        
			
			<!-- reply -->
			
			<c:if test="${not empty login }">
				<div class="box" style="margin: 0px 20px 20px 20px; padding: 0px;" >
					<textarea class="form-control" rows="4" style="resize:none" placeholder="COMMENT" id="newReplyText"></textarea>
					<input type="text" class="form-control" value="${login.uId }" id="newReplyWriter" readonly="readonly"/>
					
					<button type="submit" id="replyAddBtn">Post Comment</button>
				</div>
			</c:if>
			<c:if test="${empty login }">
				<div class="box" style="margin: 0px 20px 20px 20px; padding: 0px;" >
					<a href="/user/login">
					<textarea class="form-control" rows="4" style="resize:none" placeholder="COMMENT&#13;&#10;&#13;&#10; - 로그인이 필요한 서비스입니다. 로그인 하시겠습니까?" id="newReplyText"></textarea>
					<input type="text" class="form-control" placeholder="USER ID" id="newReplyWriter" readonly="readonly"/>
					</a>
					
					<button type="button" id="beforeAddBtn">Post Comment</button>
				</div>
			</c:if>
			
			<div id="repliesDiv">
			
			</div>
			
			<div class="text-center">
				<ul id="pagination" class="pagination pagination-sm no-margin">
					
				</ul>
			</div>
			
    	</div>
    	<!-- end row -->
    	
    </div>
    <!-- /.container -->
    
    	
	
    <footer style="margin-top: 54px;">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <p>&copy; 2017 HIPHOPDX All Right Reserved.</p>
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

	<!-- Modal -->
	<div id="modifyModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modifyModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
			
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">COMMENT-<span class="rNo"></span></h4>
				</div>
				
				<div class="modal-body" data-rNo>
					<p><input type="text" id="replyText" class="form-control" /></p>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-info" id="replyModBtn" >Modify</button>
					<button type="button" class="btn btn-info" id="replyDelBtn" >Delete</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
				
			</div>
		</div>
	</div>
   
</body>
</html>