package cl.crojas.blog.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

}
