package cl.crojas.blog.utils.model;

import java.io.Serializable;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class ModelBase implements Serializable {

	private static final long serialVersionUID = 8373223344609047449L;

	private MensajeGenerico mensajeGenerico;

	public MensajeGenerico getMensajeGenerico() {
		return mensajeGenerico;
	}

	public void setMensajeGenerico(MensajeGenerico mensajeGenerico) {
		this.mensajeGenerico = mensajeGenerico;
	}

}
