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
<nav class="navbar navbar-default navbar-fixed-top">
	
	<div class="container">
		
    	<div class="navbar-header">
    		
    		<br/>
    		
    		<a class="navbar-brand" href="${pageContext.request.contextPath}/">
				<img alt="blog" src="<c:url value='/img/logo.png' />" >
 			</a>
    		
    	</div>
    	
    	<div id="navbar" class="collapse navbar-collapse">
    		
    		
      		
    	</div>
    	
  	</div>
  	
</nav>

<br/>