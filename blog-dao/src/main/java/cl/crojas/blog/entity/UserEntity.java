package cl.crojas.blog.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "USERS")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -7346070786845090389L;

	@Id
	@Column(name = "RUT", unique = true, nullable = false, length = 10)
	private String rut;

	@Column(name = "EMAIL", nullable = false, length = 30)
	private String email;

	@Column(name = "NAME_USER", nullable = false, length = 15)
	private String nameUser;

	@Column(name = "NICKNAME", nullable = false, length = 20)
	private String nickname;

	@Column(name = "PASS", nullable = false, length = 20)
	private String pass;

	@Column(name = "SURNAME", nullable = false, length = 20)
	private String surname;

	@OneToMany(mappedBy = "user")
	private List<PostEntity> posts;

	@ManyToOne
	@JoinColumn(name = "fk_user_role")
	private RoleEntity role;

	public UserEntity() {
		super();
	}

	public String getRut() {
		return this.rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNameUser() {
		return this.nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<PostEntity> getPosts() {
		return this.posts;
	}

	public void setPosts(List<PostEntity> posts) {
		this.posts = posts;
	}

	public PostEntity addPost(PostEntity post) {
		getPosts().add(post);
		post.setUser(this);

		return post;
	}

	public PostEntity removePost(PostEntity post) {
		getPosts().remove(post);
		post.setUser(null);

		return post;
	}

	public RoleEntity getRole() {
		return this.role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

}
