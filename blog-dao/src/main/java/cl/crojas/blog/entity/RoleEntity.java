package cl.crojas.blog.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name = "ROLES")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 1996052397833357030L;

	@Id
	@SequenceGenerator(name = "roles_id_role_generator", sequenceName = "roles_id_role_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_role_generator")
	@Column(name = "ID_ROLE", unique = true, nullable = false)
	private long idRole;

	@Column(name = "NAME_ROLE", length = 10)
	private String nameRole;

	@OneToMany(mappedBy = "role")
	private List<UserEntity> users;

	public RoleEntity() {
		super();
	}

	public long getIdRole() {
		return this.idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}

	public String getNameRole() {
		return this.nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	public List<UserEntity> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public UserEntity addUser(UserEntity user) {
		getUsers().add(user);
		user.setRole(this);

		return user;
	}

	public UserEntity removeUser(UserEntity user) {
		getUsers().remove(user);
		user.setRole(null);

		return user;
	}

}
