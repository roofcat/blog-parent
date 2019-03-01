<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>

<tilesx:useAttribute name="menu" id="menu" classname="java.lang.String" />
<tilesx:useAttribute name="smenu" id="smenu" classname="java.lang.String "/>

<!-- Fixed navbar -->
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
	
	<a class="navbar-brand" href="${pageContext.request.contextPath}/">
		<%-- <img alt="blog" src="<c:url value='/resources/img/logo.png' />" > --%>
		Blog
	</a>
  	<div class="collapse navbar-collapse" id="navbarCollapse">
  		
  		<ul class="navbar-nav mr-auto">
	 		
<!-- 			<li class="nav-item"> -->
<!-- 				<a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a> -->
<!-- 			</li> -->
			
			<li class="nav-item dropdown">
				
				<a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Mantenedores</a>
				
				<div class="dropdown-menu">
		        	<a class="dropdown-item" href="${pageContext.request.contextPath}/roles">Roles</a>
		        </div>
				
			</li>
	  		
  		</ul>
  		
  	</div>
  	
</nav>

<br/>