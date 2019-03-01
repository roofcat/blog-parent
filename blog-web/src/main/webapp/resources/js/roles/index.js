"use strict";

var ROLES_ENDPOINT = "/roles";
var LIST_ROLES_ENDPOINT = CONTEXT_PATH + ROLES_ENDPOINT + ".json";
var SAVE_ROLES_ENDPOINT = CONTEXT_PATH + ROLES_ENDPOINT + "/save.json"
var DELETE_ROLES_ENDPOINT = CONTEXT_PATH + ROLES_ENDPOINT + "/delete.json"

var ROLE_MODAL = $( "#roleModal" );

var datatables = null;

$( document ).ready( function () {
	
	listRoles();
	
	controlNewModal();
	
	controlSaveRoleModal();
	
	roleModalOnHidden();
	
});

function reloadRoles () {
	if ( datatables ) {
		datatables.search( "" ).draw();
		datatables.ajax.reload();
	} else {
		listRoles();
	}
}

function listRoles () {
	
	datatables = $( "#tableRoles" ).DataTable({
		language: {
			url: DT_PARAMETERS,
		},
		//destroy: true,
		processing: true,
		serverSide: false,
		pageLength: 10,
		lengthChange: false,
 		searching: true,
 		cache: false,
		ajax: {
			type : "POST",
			contentType: "application/json; charset=utf-8",
			url: LIST_ROLES_ENDPOINT,
			dataSrc: function ( response ) {
				
				console.log( response );
				return response.data;
				
			},
			error: function ( jqXHR, textStatus, errorThrown ) {
				console.log( errorThrown.message );
				console.log( errorThrown.stack );	
			},
		},
		columns: [ 
			{
				"data": "nameRole", 
				"defaultContent" : "",
				"render": function ( data, type, row, meta ) {
					var html = "<div align=\"center\">";
					html += row.nameRole;
					html += "</div>";
					return html;
				},
			},
			{
				"title": "Acciones",
				"defaultContent" : "",
				"render": function ( data, type, row, meta ) {
					var html = "<div align=\"center\">";
					html += "<a class=\"btn btn-link\" href=\"#\" ";
					html += "data-role-id=\"" + row.idRole + "\" ";
					html += "data-role-name=\"" + row.nameRole + "\" ";
					html += "onclick=\"editModal( this ); return false;\">Editar</a>";
					html += "</div>";
					return html;
				},
				
			}
		],
		sDom: "<\"toolbar\">frtip",
		initComplete: function () {
			$( "div.toolbar" ).html( $( "#filtroPeriodo" ).html() );
		},
	});
	
}

function controlNewModal () {
	
	$( "#btnNewRole" ).on( "click", function () {
		
		showRoleModal( null, null, "ADD" );
		
	});
	
}

function controlSaveRoleModal () {
	
	$( "#btnRoleModalSave" ).on( "click", function () {
		
		var model = setupModel();
		
		console.log( model );
		
		if ( validateModel( model ) ) {
			
			$.ajax({
				
				type: "POST",
				dataType: "json",	
				url: SAVE_ROLES_ENDPOINT,
				contentType: "application/json",
				data: JSON.stringify( model ),
				
			}).done( function ( response ) {
				
				if ( response.success )
					ROLE_MODAL.modal( "hide" );
				
				console.log( response );
				showMessage( response );
				
				reloadRoles();
				
			}).fail( function ( jqXHR, textStatus, errorThrown ) {
				console.log( textStatus + ", " + errorThrown );
			});
			
		} else {
			showMessage( { "error": "Formulario Inválido" } );
		}
		
	});
	
}

function editModal ( component ) {
	
	var ahref = $( component );
	var id = $.trim( ahref.data( "role-id" ) );
	var name = $.trim( ahref.data( "role-name" ) );
	
	showRoleModal( id, name, "EDIT" );
	
}

function showRoleModal ( id, name, action ) {
	
	var roleModalTitle = $( "#roleModalTitle" );
	var roleModalId = $( "#roleModalId" );
	var roleModalAction = $( "#roleModalAction" );
	var roleModalName = $( "#roleModalName" );
	
	if ( id == null || id == "" )
		roleModalTitle.empty().append( "Crear Rol" );
	else
		roleModalTitle.empty().append( "Editar Rol: " + name );
	
	if ( name == null || name == "" ) {
		roleModalId.empty();
		roleModalName.empty();
	} else {
		roleModalId.empty().val( id );
		roleModalName.empty().val( name );
	}
	
	roleModalAction.empty().val( action );
	
	ROLE_MODAL.modal( "show", "true" );
	
}

function validateModel ( model ) {
	
	var valid = false;
	
	if ( model.action === "ADD" && model.name !== "" && model.id === "" )
		valid = true;
	
	if ( model.action === "EDIT" && model.name !== "" && model.id !== "" )
		valid = true;
	
	return valid;
	
}

function emptyModal () {
	
	$( "#roleModalTitle" ).empty();
	$( "#roleModalId" ).empty();
	$( "#roleModalAction" ).empty();
	$( "#roleModalName" ).empty();
	
}

function setupModel () {
	var model = {};
	model.id = $( "#roleModalId" ).val();
	model.action = $( "#roleModalAction" ).val();
	model.name = $( "#roleModalName" ).val();
	return model;
}

function roleModalOnHidden () {
	
	ROLE_MODAL.on( "hide.bs.modal", function ( e ) {
		emptyModal();
	});
	
}
