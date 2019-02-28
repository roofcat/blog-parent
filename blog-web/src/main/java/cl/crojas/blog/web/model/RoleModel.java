package cl.crojas.blog.web.model;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class RoleModel extends BaseModel {

	private static final long serialVersionUID = 8359732102433983849L;

	private Long id;

	private String name;

	public RoleModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
