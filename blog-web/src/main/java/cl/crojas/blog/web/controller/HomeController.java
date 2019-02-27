package cl.crojas.blog.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cl.crojas.blog.dto.PostDTO;
import cl.crojas.blog.dto.RoleDTO;
import cl.crojas.blog.service.bo.PostBO;
import cl.crojas.blog.service.bo.RoleBO;
import cl.crojas.blog.web.routes.Routes;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@Controller
@RequestMapping(Routes.Home.BASE)
public class HomeController extends BaseController {

	private static final Logger logger = Logger.getLogger(HomeController.class);

	private static final String INDEX_VIEW = "home-index";

	@Autowired
	private PostBO postBO;

	@Autowired
	private RoleBO roleBO;

	@GetMapping
	public ModelAndView index() {

		final String methodName = "index(): ";

		ModelAndView mav = new ModelAndView(INDEX_VIEW);

		try {

			logger.debug(methodName + INICIANDO);

			this.postBO.findAll();
			
			RoleDTO roleDTO = new RoleDTO();
			roleDTO.setNameRole("RoleTest");
			
			this.roleBO.create(roleDTO);

		} catch (Exception e) {

			logger.error(methodName + e.getMessage(), e);

		}

		logger.debug(methodName + FINALIZANDO);

		return mav;

	}

}
