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

<title>KoreaHipHop</title>

<!-- Bootstrap Core CSS -->
<link href="../../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

<!-- Custom CSS -->
<link href="../../resources/bootstrap/css/business-casual.css" rel="stylesheet">
<link href="../../resources/bootstrap/css/bootstrap-social.css" rel="stylesheet">

<!-- Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

<!-- template -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/x-handlebars-template" id="template">
	{{#each.}}
		<div class="replyLi" data-rno={{rno}} style="margin: 20px 0px">
			<p>{{prettifyDate regdate}} {{rno}}-{{replyer}}</p>
			<p class="timeline-body">{{replytext}}</p>
			<a class="btn btn-default btn-xs" data-toggle="modal" data-target="#modifyModal">modify</a>
		</div>
	{{/each}}
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#listNews").click(function(){
			history.go(-1);
			/* $("#formObj").attr("method", "get");
			$("#formObj").attr("action", "/sBoard/list");
			$("#formObj").submit(); */
		});
		
		$("#modify").click(function(){
			$("#formObj").attr("action", "/newsBoard/modifyNews");
			$("#formObj").attr("method", "get");
			$("#formObj").submit();
		});
		
		$("remove").click(function(){
			$("#formObj").attr("action", "/newsBoard/removeNews");
			$("#formObj").submit();
		});
		
	});
</script>

<style type="text/css">
body{/* background: url("../../resources/img/bg.jpg"); */ background-color: #EAEAEA;}
.box{margin-bottom: 0px; border-bottom: 1px solid #EAEAEA; background: rgba(255,255,255,1);}
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
        	
				<!-- form -->
				<form method="post" id="formObj">
					<input type="hidden" name="newsNo" value="${readNews.newsNo}" readonly="readonly" />
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
        			<strong> ${readNews.nTitle }</strong>
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
		    		${fn:replace(readNews.nContent, cn, br)}
		    		<br>
		    		<br>
		    		<br>
		    	</div>	
	                
	        </div>
	        
	        <!-- button -->
	        <div class="box" style="margin-bottom: 20px;">
		            
		    	<button type="button" class="btn btn-default" id="listNews"><span class="glyphicon glyphicon-list" aria-hidden="true"></span> 목록</button>
		    	
		    	<div class="btn-group" role="group" aria-label="...">
				  <input type="button" class="btn btn-default" value="Twitter" />
				  <input type="button" class="btn btn-default" value="Facebook" />
				</div>
				
				<button type="button" class="btn btn-default" id="modify">수정</button>
				<button type="button" class="btn btn-default" id="remove">삭제</button>
		    		                
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