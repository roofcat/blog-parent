package cl.crojas.blog.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 6531737894298067784L;

	private String rut;

	private String email;

	private String nameUser;

	private String nickname;

	private String pass;

	private String surname;

	private List<PostDTO> posts;

	private RoleDTO role;

	public UserDTO() {
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

	public List<PostDTO> getPosts() {
		return this.posts;
	}

	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}

	public PostDTO addPost(PostDTO post) {
		getPosts().add(post);
		post.setUser(this);

		return post;
	}

	public PostDTO removePost(PostDTO post) {
		getPosts().remove(post);
		post.setUser(null);

		return post;
	}

	public RoleDTO getRole() {
		return this.role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

}
