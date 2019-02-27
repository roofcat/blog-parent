package cl.crojas.blog.utils.model;

import java.io.Serializable;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class ElementoSimpleModel implements Serializable {

	private static final long serialVersionUID = -2154200707955024323L;

	private long id;

	private String identificador;

	private String nombre;

	public ElementoSimpleModel() {
		super();
	}

	public ElementoSimpleModel(long id, String identificador, String nombre) {

		this.setId(id);
		this.setIdentificador(identificador);
		this.setNombre(nombre);

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

}
