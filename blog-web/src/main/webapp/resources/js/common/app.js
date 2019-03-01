"use strict";

var DT_PARAMETERS = "/blog-web/resources/json/dataTable_ES.json";

var ACTIVO = "Activo";
var INACTIVO = "Inactivo";
var CODIGO_MENSAJE_EXITO = 0;
var CODIGO_MENSAJE_ERROR = 1;
var TIEMPO_FADE = 1000;
var TIEMPO_RETARDO = 3000;
var TIEMPO_RETARDO_MENSAJE = 5000;
var TIEMPO_LINK_DESCARGA = 3000;
var TIEMPO_EXPIRACION_SESION = 2000;

function showProcessingModal () {
	$( "#processingModal" ).modal( "show" );
}

function hideProcessingModalTime ( time ) {
	setTimeout( function () {
		hideProcessingModal();
	}, time );
}

function hideProcessingModal () {
	$( "#processingModal" ).modal( "hide" );
}

function cerrar () {
	$( "#globalMessage" ).fadeOut( "slow", function () {
		$( "#globalMessage" ).removeClass( "alert-danger" );
		$( "#globalMessage" ).removeClass( "alert-success" );
		$( "#globalMessage" ).removeClass( "alert-info" );
	});
}

function setupMensajesByName ( data ) {
	
	$( ".help-block" ).remove();
	$( ".has-error" ).removeClass( "has-error" );
	$( "#globalMessage" ).hide();
	$( "#textAlert").html( "" );
	
	if ( data.error ) {
		
		$( "html, body" ).animate({
		      scrollTop: $( "#globalMessage" ).offset().top
		    }, TIEMPO_FADE );
		
		$( "#globalMessage" ).removeClass( "alert alert-success" );
		$( "#globalMessage" ).addClass( "alert alert-danger" );
		$( "#globalMessage" ).fadeIn();
		$( "#textAlert" ).html( data.error );
	}
	
	if ( data.errors ) {
		
		$.each( data.errors, function ( key, value ) {
			
			var nombreArray = key.split( "." );
			var nombre = "";
			var targetName = "";
			var target = $( "#" + targetName );
			
			if ( target.hasClass( "has-select2" ) )
				target.nextAll( "span:first" ).after( "<span class=\"help-block\">" + value + "</span>" );
			else
				target.after( "<span class=\"help-block\">" + value + "</span>" );
			
			target.parent( "div" ).addClass( "has-error" );
	        
        });
		
	} 
	
	if ( data.success ) {
		
		$( "html, body" ).animate({
		      scrollTop: $( "#globalMessage" ).offset().top
		    }, 1000 );
		
		$( "#globalMessage" ).removeClass( "alert alert-danger" );
		$( "#globalMessage" ).addClass( "alert alert-success" );			
		$( "#textAlert").html( data.success );
		$( "#globalMessage" ).fadeIn( TIEMPO_FADE, function () {
			$( this ).focus();
		});
		
	}
	
	setTimeout( function () {
		cerrar();
	}, TIEMPO_RETARDO_MENSAJE );
	
}

function showMessage ( data ) {
	hideProcessingModal();
	setupMensajesByName( data );
}
