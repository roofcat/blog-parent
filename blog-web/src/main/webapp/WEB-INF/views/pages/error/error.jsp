<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<nav class="navbar navbar-custom navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">
				<img alt="blog" src="<c:url value='/img/logo.png' />" height="150" width="420" style="margin-top: -70px;" >
			</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-center"></ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"> 
					<a href="#" class="dropdown-toggle" id="drop3" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
					</a>
				</li>
			</ul>
		</div>
	</div>
</nav>

<div class="container">
	
	<div class="jumbotron text-center">
		
		<h2>${title}</h2>
		<p>${message}</p>
		
		<br>
		<center>
			<a class="btn btn-success" href="${pageContext.request.contextPath}/">Volver a inicio</a>
		</center>
		
	</div>
	
</div>