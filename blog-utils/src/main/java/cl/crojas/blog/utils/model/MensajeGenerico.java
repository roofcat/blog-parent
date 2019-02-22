package cl.crojas.blog.utils.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@XmlRootElement(name = "mensajeGenerico")
public class MensajeGenerico implements Serializable {

	private static final long serialVersionUID = -639457187459982287L;

	public static final long TIPO_ERROR = 1;

	public static final long TIPO_EXITO = 0;

	public static final String MENSAJE_GENERICO = "mensajeGenerico";

	private String mensaje;

	private long tipoMensaje;

	public MensajeGenerico() {
		super();
	}

	public MensajeGenerico(long tipoMensaje, String mensaje) {

		this.tipoMensaje = tipoMensaje;

		this.mensaje = mensaje;

	}

	@XmlElement
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	@XmlElement
	public void setTipoMensaje(long tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	public long getTipoMensaje() {
		return tipoMensaje;
	}

}
