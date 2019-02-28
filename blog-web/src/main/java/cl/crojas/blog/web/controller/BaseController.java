package cl.crojas.blog.web.controller;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import cl.crojas.blog.service.bo.RoleBO;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public abstract class BaseController {

	private static final Logger logger = Logger.getLogger(BaseController.class);

	protected static final String INICIANDO = "Iniciando...";
	protected static final String PROCESO_FINALIZADO = "Proceso finalizado exitosamente...";
	protected static final String FINALIZANDO = "Finalizado...";
	protected static final String EXISTEN_ERRORES_VALIDACION = "Existen errores de validación...";
	protected static final String SIN_ERRORES_VALIDACION = "Sin errores de validación...";

	protected static final String ERROR_NEGOCIO = "Error de negocio: ";
	protected static final String ERROR_INTERNO = "Error Interno: ";
	protected static final String ERROR_ACCESO = "Acceso no permitido";

	protected static final String ERROR = "error";
	protected static final String ERRORS = "errors";
	protected static final String SUCCESS = "success";

	protected static final String APPLICATION_XML = "application/xml";

	protected static final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;

	protected static final String REDIRECT = "redirect:/";

	protected static final String COMA = ",";
	protected static final String DOS_PUNTOS = ":";

	private MessageSource messageSource;

	@Autowired
	protected RoleBO roleBO;

	@Autowired
	protected void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	protected String getMessage(String key) {
		return messageSource.getMessage(key, null, null);
	}

	protected Map<String, String> setupErrorMap(BindingResult bindingResult) {
		Map<String, String> errors = new HashMap<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return errors;
	}

}
