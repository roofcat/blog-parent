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
				<div class="card">
					
					<div class="card-header bg-dark text-white"">Mantenedor de Roles</div>
					
					<div class="card-body">
						
						<button class="btn btn-primary" type="button" id="btnNewRole">Crear nuevo rol</button>
						
					</div>
					
				</div>
				<!-- fin panel menu -->
				
				<br>
				
				<!-- inicio panel datatables -->
				<div class="card">
					
					<div class="card-header bg-dark text-white">Listado de Roles</div>
					
					<div class="card-body">
						
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

<!-- Modal Roles -->
<div class="modal fade" id="roleModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="roleModalTitle"></h5>
			</div>
			<div class="modal-body">
				
				<input type="hidden" id="roleModalId">
				<input type="hidden" id="roleModalAction">
				
				<div class="form-group">
					<label class="col-form-label">Rol:</label>
					<input class="form-control" type="text" id="roleModalName" autofocus >
				</div>
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
			  	<button type="button" class="btn btn-primary" id="btnRoleModalSave">Guardar</button>
			</div>
		</div>
	</div>
</div>
