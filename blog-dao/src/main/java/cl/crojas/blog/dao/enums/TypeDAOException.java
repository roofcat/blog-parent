package cl.crojas.blog.dao.enums;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public enum TypeDAOException {

	EMPTY(0, "Empty"), DUPLICATED(1, "Duplicated"), HIBERNATE(2, "Hibernate"), INTERNO(3, "Interno");

	private int code;

	private String description;

	private TypeDAOException(int code, String description) {

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
