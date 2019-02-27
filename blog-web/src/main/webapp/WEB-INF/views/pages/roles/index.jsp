<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>

<div>
	
	<form:form name="roleForm" modelAttribute="roleModel" method="POST">
		
		<div class="row">
			
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				
				<!-- inicio panel menu -->
				<div class="panel panel-primary">
					
					<div class="panel-heading">Mantenedor de Roles</div>
					
				</div>
				
				<!-- fin panel menu -->
				
				<!-- inicio panel datatables -->
				<div class="panel panel-info">
					
					<div class="panel-heading">Lista de Roles</div>
					
					<div class="panel-body">
						
						<table class="table" id="tableRoles">
							<thead class="thead-dark">
								<tr>
									<td>Nombre Rol</td>
									<td>Acciones</td>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
						
					</div>
					
				</div>
				<!-- fin panel datatables -->
				
			</div>
			
		</div>
		
	</form:form>
	
</div>
