package cl.crojas.blog.web.routes;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class Routes {

	private Routes() {
		super();
	}

	/**
	 * 
	 * @author Christian Rojas N.
	 *
	 */
	public static class Home {

		private Home() {
			super();
		}

		public static final String BASE = "/home";

	}

	/**
	 * 
	 * @author Christian Rojas N.
	 *
	 */
	public static class Roles {

		private Roles() {
			super();
		}

		public static final String BASE = "/roles";

		public static final String SAVE = "/save";

	}

}
