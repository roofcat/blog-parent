package cl.crojas.blog.utils.model.datatable;

import java.io.Serializable;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class Order implements Serializable {

	private static final long serialVersionUID = -3020975396215198550L;

	private int column;

	private String dir;

	public Order() {
		super();
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

}
