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

<title>HipHopDX</title>

<!-- Bootstrap Core CSS -->
<link href="../../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

<!-- Custom CSS -->
<link href="../../resources/bootstrap/css/MyPage.css" rel="stylesheet">

<!-- Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		// 게시물 등록 버튼
		$("#newBtn").on("click", function(){
			self.location = "registerVideo";
		});
		
		// 게시물 검색 버튼
		$("#searchBtn").on("click", function(){
			self.location = "video"
				+ "${pageMaker.makeSearch(1)}"
				+ "&searchType="
				+ $("select option:selected").val()
				+ "&keyword=" + encodeURIComponent($("#keywordInput").val());
		});
		
	}); //end ready
</script>

<style type="text/css">
body{/* background: url("../../resources/img/bg.jpg"); */ background-color: #F1F1F1;}
.box{margin-bottom: 0px; border-bottom: 1px solid #EAEAEA; background: rgba(255,255,255,1);}
.nClick:hover {}
.nClick:hover img {opacity: 0.8;}
.nClick:hover a {color: #980000;}
.nClick:hover p {color: #980000;}
a {color: black;}
a:hover {text-decoration: none; color: #980000;}
.contentBox{height: 80px; margin-bottom: 10px; overflow:hidden; text-overflow: ellipsis; text-align: left;}
.content{font-size: 12px;}
/* font */
p { font-size: 1em;}
</style>

</head>
<body>

	<!-- HEADER -->
	
	<jsp:include page="../header/header.jsp" />
	
	<!-- /HEADER -->

	<div class="container">

        <div class="row">
        
        	<div class="box">
        		<div class="col-lg-12">
        			<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
        			<strong> VIDEO</strong>
        		</div>
        	</div>

			<div class="box">
				<table class="table table-hover">
					<thead class="text-center">
						<tr>
							<td><strong>번호</strong></td>
							<td><strong>제목</strong></td>
							<td><strong>추천</strong></td>
							<td><strong>조회</strong></td>
							<td><strong>날짜</strong></td>
						</tr>
					</thead>
					
					<c:forEach items="${list }" var="videoBoardVo">
					<tr>
 					 	<td class="text-center">${videoBoardVo.vNo }</td>
 					 	<td style="padding-left: 30px;">
 					 		<a href="/videoBoard/readVideo${pageMaker.makeQuery(pageMaker.cri.page) }&vNo=${videoBoardVo.vNo }">
 					 		${videoBoardVo.vTitle }
 					 		</a>
 					 		&nbsp;&nbsp;&nbsp;<span class="badge">${videoBoardVo.replyCnt }</span>
 					 	</td>
 					 	<td class="text-center">${videoBoardVo.recommend }</td>
 					 	<td class="text-center">${videoBoardVo.viewCnt }</td>
 					 	<td class="text-center"><fmt:formatDate value="${videoBoardVo.regDate }" pattern="yyyy-MM-dd HH:mm"/></td>
 					 </tr>
					</c:forEach>
 					 
				</table>
			</div>

				<!-- Search -->
				<div class="text-left" style="margin-bottom: 0px; margin-top: 24px;">
					<div class="btn-group form-inline">
					<select name="searchType" id="searchType" class="form-control" style="width: 100px">
						<option value="n" <c:out value="${cri.searchType == null?'selected' : ''}" />> ------------------------------------- </option>						
						<option value="t" <c:out value="${cri.searchType eq 't'?'selected' : ''}" />> Title </option>
						<option value="c" <c:out value="${cri.searchType eq 'c'?'selected' : ''}" />> Content </option>
						<option value="w" <c:out value="${cri.searchType eq 'w'?'selected' : ''}" />> Writer </option>
						<option value="tc" <c:out value="${cri.searchType eq 'tc'?'selected' : ''}" />> Title OR Content </option>
						<option value="cw" <c:out value="${cri.searchType eq 'cw'?'selected' : ''}" />> Content OR Writer </option>
						<option value="tcw" <c:out value="${cri.searchType eq 'tcw'?'selected' : ''}" />> Title OR Content OR Writer </option>
					</select>
					
					<input type="text" class="form-control" name="keyword" id="keywordInput" value="${cri.keyword }" />
					
					</div>
					<button id="searchBtn" class="btn btn-primary">Search</button>
					
					<c:if test="${login.uId == 'admin'}">
					<button id="newBtn" class="btn btn-primary">New Board</button>
					</c:if>
					
				</div>            
            
				<!-- pagination -->
				<div class="text-center">
					<ul class="pagination">
						<c:if test="${pageMaker.prev }">
							<li><a href="video${pageMaker.makeQuery(pageMaker.startPage - 1) }">&laquo;</a></li>
						</c:if>
						
						<c:forEach var="idx" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
							<li <c:out value="${pageMaker.cri.page == idx?'class = active' : '' }"/>>
								<a href="video${pageMaker.makeQuery(idx) }">${idx }</a>
							</li>
						</c:forEach>
						
						<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
							<li><a href="video${pageMaker.makeQuery(pageMaker.endPage + 1) }">&raquo;</a></li>
						</c:if>
					</ul>
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