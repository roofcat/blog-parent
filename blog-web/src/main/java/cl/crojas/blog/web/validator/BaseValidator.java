package cl.crojas.blog.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;

import cl.crojas.blog.service.bo.RoleBO;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public abstract class BaseValidator implements Validator {

	protected static final String INICIANDO = "Iniciando...";
	protected static final String PROCESO_FINALIZADO = "Proceso finalizado exitosamente...";
	protected static final String FINALIZANDO = "Finalizado...";
	protected static final String EXISTEN_ERRORES_VALIDACION = "Existen errores de validación...";
	protected static final String SIN_ERRORES_VALIDACION = "Sin errores de validación...";

	@Autowired
	protected RoleBO roleBO;

}
