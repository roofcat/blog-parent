package cl.crojas.blog.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cl.crojas.blog.dto.RoleDTO;
import cl.crojas.blog.service.bo.RoleBO;
import cl.crojas.blog.utils.model.datatable.DataTablesResponse;
import cl.crojas.blog.web.routes.Routes;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@Controller
@RequestMapping(Routes.Roles.BASE)
public class RoleController extends BaseController {

	private static final Logger logger = Logger.getLogger(RoleController.class);

	private static final String INDEX_VIEW = "roles-index";

	@Autowired
	private RoleBO roleBO;

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

			roles = this.roleBO.findAll();

			datatable.setData(roles);

			logger.debug(methodName + PROCESO_FINALIZADO);

		} catch (Exception e) {

			logger.error(methodName + e.getMessage(), e);
			datatable.setError(e.getMessage());

		}

		logger.debug(methodName + FINALIZANDO);

		return datatable;

	}

}
