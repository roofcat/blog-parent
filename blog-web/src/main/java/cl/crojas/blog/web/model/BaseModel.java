package cl.crojas.blog.web.model;

import java.io.Serializable;

import cl.crojas.blog.web.enums.ActionEnum;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class BaseModel implements Serializable {

	private static final long serialVersionUID = -3155606598888647663L;

	private ActionEnum action;

	public BaseModel() {
		super();
	}

	public ActionEnum getAction() {
		return action;
	}

	public void setAction(ActionEnum action) {
		this.action = action;
	}

}
