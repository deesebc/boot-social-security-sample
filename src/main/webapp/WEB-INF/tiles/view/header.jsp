<%@ include file="taglib.jsp"%>
<header>
<div class="navbar-header">
  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
    <span class="sr-only">Toggle navigation</span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
  </button>
  <a class="navbar-brand" href="#">Boot Social Security Sample</a>
</div>
<div id="navbar" class="collapse navbar-collapse">
  <ul class="nav navbar-nav">
    <li class="active"><a href="/">Home</a></li>
    <li><a href="/restricted">Restricted Page</a></li>
    <security:authorize access="isFullyAuthenticated()">
    	<li><a href="#contact">Hello <security:authentication property="principal.username" /></a></li>
    	<li><a href="/logout">Logout</a></li>
    </security:authorize>
  </ul>
</div><!--/.nav-collapse -->
</header>