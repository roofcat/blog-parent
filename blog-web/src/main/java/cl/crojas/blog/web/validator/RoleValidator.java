package cl.crojas.blog.web.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import cl.crojas.blog.dto.RoleDTO;
import cl.crojas.blog.utils.Utils;
import cl.crojas.blog.web.enums.ActionEnum;
import cl.crojas.blog.web.model.RoleModel;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@Component
public class RoleValidator extends BaseValidator {

	private static final Logger logger = Logger.getLogger(RoleValidator.class);

	private static final String FIELD_ID = "id";
	private static final String FIELD_NAME = "name";
	private static final String FIELD_ACTION = "action";

	private static final String ERROR_FIELD_ID = "error.id";
	private static final String ERROR_FIELD_NAME = "error.name";
	private static final String ERROR_FIELD_ACTION = "error.action";

	private static final String MSG_FIELD_ID = "Campo ID es requerido.";
	private static final String MSG_FIELD_ID_EXIST = "El rol a modificar ya existe.";
	private static final String MSG_FIELD_NAME = "Campo Nombre es requerido.";
	private static final String MSG_ROLE_EXIST = "El rol ya existe.";
	private static final String MSG_ROLE_NOT_EXIST = "El rol no existe.";
	private static final String MSG_FIELD_ACTION = "Acción no válido.";

	@Override
	public boolean supports(Class<?> clazz) {
		return RoleModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		final String methodName = "validate(): ";

		RoleModel model = (RoleModel) target;

		try {

			logger.debug(methodName + INICIANDO);

			logger.debug(methodName + model.getAction());

			if (ActionEnum.ADD.equals(model.getAction())) {

				boolean exist = this.roleBO.existByName(model.getName());

				if (exist)
					errors.rejectValue(FIELD_NAME, ERROR_FIELD_NAME, MSG_ROLE_EXIST);

				if (Utils.isNullOrEmpty(model.getName()))
					errors.rejectValue(FIELD_NAME, ERROR_FIELD_NAME, MSG_FIELD_NAME);

			} else if (ActionEnum.EDIT.equals(model.getAction())) {

				if (Utils.isNullOrEmpty(model.getId()))
					errors.rejectValue(FIELD_ID, ERROR_FIELD_ID, MSG_FIELD_ID);

				if (Utils.isNullOrEmpty(model.getName()))
					errors.rejectValue(FIELD_NAME, ERROR_FIELD_NAME, MSG_FIELD_NAME);

				RoleDTO role = this.roleBO.findByName(model.getName());

				if (role.getIdRole() != model.getId().longValue())
					errors.rejectValue(FIELD_ID, ERROR_FIELD_ID, MSG_FIELD_ID_EXIST);

			} else if (ActionEnum.DELETE.equals(model.getAction())) {

				if (Utils.isNullOrEmpty(model.getId()))
					errors.rejectValue(FIELD_ID, ERROR_FIELD_ID, MSG_FIELD_ID);

				boolean exist = super.roleBO.existById(model.getId());

				if (!exist)
					errors.rejectValue(FIELD_NAME, ERROR_FIELD_NAME, MSG_ROLE_NOT_EXIST);

			} else {

				errors.rejectValue(FIELD_ACTION, ERROR_FIELD_ACTION, MSG_FIELD_ACTION);
			}

		} catch (Exception e) {

			logger.error(methodName + e.getMessage(), e);

		}

		logger.debug(methodName + FINALIZANDO);

	}

}
