package cl.crojas.blog.web.utils;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class StringEscapeEditor extends PropertyEditorSupport {

	private boolean escapeHTML;

	private boolean escapeJavaScript;

	private boolean escapeSQL;

	public StringEscapeEditor() {
		super();
	}

	public StringEscapeEditor(boolean escapeHTML, boolean escapeJavaScript, boolean escapeSQL) {

		this.escapeHTML = escapeHTML;
		this.escapeJavaScript = escapeJavaScript;
		this.escapeSQL = escapeSQL;

	}

	/**
	 * Método que permite configurar Escapar los Textos de HTML, JavaScript y / o
	 * SQL La Anotación @Override indica que este método es sobreescrito de la clase
	 * que hereda o interfaz que implementa
	 * 
	 * @param text Objeto String que representa el Texto a Ser Escapado
	 * @author cristian.palavecinos
	 * 
	 */
	@Override
	public void setAsText(String text) {

		if (text == null) {

			super.setValue(null);

		} else {

			String value = text;

			if (escapeHTML)
				value = StringEscapeUtils.escapeHtml(value);

			if (escapeJavaScript)
				value = StringEscapeUtils.escapeJavaScript(value);

			if (escapeSQL)
				value = StringEscapeUtils.escapeSql(value);

			super.setValue(value);

		}

	}

	/**
	 * Método que permite configurar Obtener el Request como Texto La
	 * Anotación @Override indica que este método es sobreescrito de la clase que
	 * hereda o interfaz que implementa
	 * 
	 * @return Objeto String que representa el Texto Obtenido
	 * @author cristian.palavecinos
	 * 
	 */
	@Override
	public String getAsText() {
		Object value = super.getValue();
		return (value != null ? value.toString() : "");
	}

}
