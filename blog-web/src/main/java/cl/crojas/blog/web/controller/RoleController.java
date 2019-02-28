package cl.crojas.blog.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cl.crojas.blog.dto.RoleDTO;
import cl.crojas.blog.utils.Utils;
import cl.crojas.blog.utils.model.datatable.DataTablesResponse;
import cl.crojas.blog.web.enums.ActionEnum;
import cl.crojas.blog.web.model.RoleModel;
import cl.crojas.blog.web.routes.Routes;
import cl.crojas.blog.web.validator.RoleValidator;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@Controller
@RequestMapping(Routes.Roles.BASE)
public class RoleController extends BaseController {

	private static final Logger logger = Logger.getLogger(RoleController.class);

	private static final String MSG_ADD_ROLE_SUCCESS = "Rol creado exitosamente.";
	private static final String MSG_UPDATE_ROLE_SUCCESS = "Rol actualizado exitosamente.";

	private static final String INDEX_VIEW = "roles-index";

	@Autowired
	private RoleValidator roleValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.roleValidator);
	}

	@GetMapping
	public ModelAndView index() {

		final String methodName = "index(): ";

		ModelAndView mav = new ModelAndView(INDEX_VIEW);

		try {

			logger.debug(methodName + INICIANDO);

			logger.debug(methodName + PROCESO_FINALIZADO);

		} catch (Exception e) {

			logger.error(methodName + e.getMessage(), e);

		}

		logger.debug(methodName + FINALIZANDO);

		return mav;

	}

	@PostMapping
	@ResponseBody
	public DataTablesResponse<RoleDTO> list() {

		final String methodName = "list(): ";

		DataTablesResponse<RoleDTO> datatable = new DataTablesResponse<>();
		List<RoleDTO> roles = null;

		try {

			logger.debug(methodName + INICIANDO);

			roles = super.roleBO.findAll();

			datatable.setData(roles);

			logger.debug(methodName + PROCESO_FINALIZADO);

		} catch (Exception e) {

			logger.error(methodName + e.getMessage(), e);
			datatable.setError(e.getMessage());

		}

		logger.debug(methodName + FINALIZANDO);

		return datatable;

	}

	@PostMapping(Routes.Roles.SAVE)
	@ResponseBody
	public Model save(@RequestBody @Valid RoleModel roleModel, BindingResult bindingResult, Model model) {

		final String methodName = "save(): ";

		try {

			logger.debug(methodName + INICIANDO);

			logger.debug(methodName + "Json: \n" + Utils.getObjToJSON(roleModel));

			if (bindingResult.hasErrors()) {

				logger.debug(methodName + EXISTEN_ERRORES_VALIDACION);
				model.addAttribute(ERRORS, super.setupErrorMap(bindingResult));

			} else {

				logger.debug(methodName + SIN_ERRORES_VALIDACION);

				if (ActionEnum.ADD.equals(roleModel.getAction())) {

					RoleDTO roleDTO = new RoleDTO();
					roleDTO.setNameRole(roleModel.getName());

					super.roleBO.create(roleDTO);

					model.addAttribute(SUCCESS, MSG_ADD_ROLE_SUCCESS);

				} else if (ActionEnum.EDIT.equals(roleModel.getAction())) {

					RoleDTO roleDTO = super.roleBO.getById(roleModel.getId());
					roleDTO.setNameRole(roleModel.getName());

					super.roleBO.create(roleDTO);

					model.addAttribute(SUCCESS, MSG_UPDATE_ROLE_SUCCESS);

				}

			}

			logger.debug(methodName + PROCESO_FINALIZADO);

		} catch (Exception e) {

			logger.error(methodName + e.getMessage(), e);
			model.addAttribute(ERROR, e.getMessage());

		}

		logger.debug(methodName + FINALIZANDO);

		return model;

	}

}
