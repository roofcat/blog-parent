package cl.crojas.blog.service.enums;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public enum TypeBusinessException {

	EMPTY(0, "Empty"), 
	DUPLICATED(1, "Duplicated"),
	EXISTS(2, "Registro Existe"),
	NOT_VALID(3, "Not Valid"),
	INVALID_RECAPTCHA(4, "Recaptcha Invalido"),
	DAO_ERROR(5, "Error en capa DAO");

	private int code;

	private String description;

	private TypeBusinessException(int code, String description) {

		this.setCode(code);
		this.setDescription(description);

	}

	public int getCode() {
		return code;
	}

	private void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

}
