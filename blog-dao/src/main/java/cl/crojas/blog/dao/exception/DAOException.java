package cl.crojas.blog.dao.exception;

import cl.crojas.blog.dao.enums.TypeDAOException;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class DAOException extends Exception {

	private static final long serialVersionUID = -8527029653875795225L;

	private TypeDAOException type;

	public DAOException() {
		super();
	}

	public DAOException(TypeDAOException type, String message) {

		super(message);
		this.setType(type);

	}

	public DAOException(TypeDAOException type, String message, Throwable cause) {

		super(message, cause);
		this.setType(type);

	}

	public TypeDAOException getType() {
		return type;
	}

	public void setType(TypeDAOException type) {
		this.type = type;
	}

}
