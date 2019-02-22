package cl.crojas.blog.service.exception;

import cl.crojas.blog.service.enums.TypeBusinessException;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1369899643972200140L;

	private TypeBusinessException type;

	public BusinessException(TypeBusinessException type, String message) {

		super(message);
		this.setType(type);

	}

	public BusinessException(TypeBusinessException type, String message, Throwable cause) {

		super(message, cause);
		this.setType(type);

	}

	public TypeBusinessException getType() {
		return type;
	}

	public void setType(TypeBusinessException type) {
		this.type = type;
	}

}
