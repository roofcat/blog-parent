package cl.crojas.blog.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 3054673018746464851L;

	private Integer idRole;

	private String nameRole;

	private List<UserDTO> users;

	public RoleDTO() {
		super();
	}

	public Integer getIdRole() {
		return this.idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public String getNameRole() {
		return this.nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	public List<UserDTO> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

	public UserDTO addUser(UserDTO user) {
		getUsers().add(user);
		user.setRole(this);

		return user;
	}

	public UserDTO removeUser(UserDTO user) {
		getUsers().remove(user);
		user.setRole(null);

		return user;
	}

}
