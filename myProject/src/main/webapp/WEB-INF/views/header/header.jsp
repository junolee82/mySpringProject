
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="brand" style="margin-bottom: 20px">
	    <a href="/main">
		   	<img src="../../resources/img/logo-black.svg" alt="" style="width: 400px;"/>
		</a>
	</div>
    <div class="address-bar">
    
    	<c:choose>
    		<c:when test="${empty login }">
    		<a href="/user/login" style="margin-right: 6px;"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span></a>
    		</c:when>
    		<c:when test="${not empty login }">
    		<a href="/user/logout" style="margin-right: 6px;"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span></a>
    		</c:when>
    	</c:choose>
    	
    	<a href="/user/join" style="margin-left: 6px;"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
    	<a href="http://www.google.com" style="margin-left: 6px;"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>
    	
    </div>

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
                        <a href="/magazineBoard/magazine">MAGAZINE</a>
                    </li>
                    <li>
                        <a href="/lifestyleBoard/lifestyle">LIFESTYLE</a>
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
    