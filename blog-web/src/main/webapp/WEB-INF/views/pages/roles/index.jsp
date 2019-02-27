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
				
				<!-- inicio panel datatables -->
				<div class="card">
					
					<div class="card-header">Mantenedor de Roles</div>
					
					<div class="card-body">
						
						<h5 class="card-title">Listado de Roles</h5>
					
						<table class="table table-striped table-bordered table-hover" id="tableRoles">
							<thead class="thead-dark">
								<tr>
									<th>Nombre Rol</th>
									<th>Acciones</th>
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
