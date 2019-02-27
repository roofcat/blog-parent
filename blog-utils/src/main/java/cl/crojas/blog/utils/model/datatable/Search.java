package cl.crojas.blog.utils.model.datatable;

import java.io.Serializable;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class Search implements Serializable {

	private static final long serialVersionUID = 6768562886140537255L;

	private String value;

	private boolean regex;

	public Search() {
		super();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isRegex() {
		return regex;
	}

	public void setRegex(boolean regex) {
		this.regex = regex;
	}

}
