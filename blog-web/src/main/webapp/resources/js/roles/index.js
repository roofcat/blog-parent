"use strict";

var LIST_ROLES_ENDPOINT = CONTEXT_PATH + "/roles.json";

$( document ).ready( function () {
	
	listRoles();
	
});

function listRoles () {
	
	$( "#tableRoles" )
	$( "#tableRoles" ).DataTable({
		language: {
			url: DT_PARAMETERS,
		},
		processing: true,
		serverSide: false,
		pageLength: 10,
		lengthChange: false,
 		searching: false,
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
