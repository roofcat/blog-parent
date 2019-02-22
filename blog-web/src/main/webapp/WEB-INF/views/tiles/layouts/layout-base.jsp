<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>

<!DOCTYPE html>
<html lang="es">
	
	<head>
		
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="author" content="Christian Rojas N.">
		<meta name="description" content="Blog Java Spring MVC JPA">
		
		<meta name="_csrf" content="${_csrf.token}"/>
		<meta name="_csrf_header" content="${_csrf.headerName}"/>
		
		<title><tiles:getAsString name="title" /></title>
		
		<!-- Favicon -->
		<link rel="shortcut icon" href="<c:url value='/resources/img/favicon.ico'/>" type="image/x-icon" >
		
        <!-- Fuentes -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans" >
		
		<!-- Estilos Personalizados para la Plantilla -->
		<link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet">
		
		<!-- Lista de CSS agregadas en tiles -->
		<tilesx:useAttribute id="cssList" name="css" classname="java.util.List" />
		<c:forEach var="cssFile" items="${cssList}">
			<link rel="stylesheet" href="${pageContext.request.contextPath}<c:out value="${cssFile}" />">
		</c:forEach>
		
	</head>
	
	<body>
		
		<tiles:insertAttribute name="header" />
		
		<div class="container" style="margin-top: 60px;">
			
			<!-- Mensaje General -->
		    <div id="globalMensaje" style="display:none" class="alert alert-dismissable">
				<a href="#" class="close" data-hide="alert" aria-label="close">×</a>
			   	<p id="textoAlerta"></p>
			</div>
			
			<tiles:insertAttribute name="body" />
			
		</div>
		
		<tiles:insertAttribute name="footer" />
		
		<!-- MODAL PROCESANDO -->
		<div class="modal modal-static fade" id="processingModal" role="dialog" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-body">
		                <div class="text-center">
		                	<img src="https://zippy.gfycat.com/SkinnySeveralAsianlion.gif" height="42" width="42"/>	                    
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		
		<!-- Scripts -->
        <script src="<c:url value='/resources/js/common/app.js' />" type="text/javascript"></script>
        
        <script>
			var CONTEXT_PATH = "${pageContext.request.contextPath}";
		</script>
		
		<!-- Lista de JS agregadas en tiles -->
		<tilesx:useAttribute id="jsList" name="js" classname="java.util.List" />
		<c:forEach var="jsFile" items="${jsList}">
			<script src="${pageContext.request.contextPath}<c:out value="${jsFile}" />"></script>
		</c:forEach>
        
	</body>
	
</html>