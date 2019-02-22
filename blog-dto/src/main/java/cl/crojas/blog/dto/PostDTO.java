package cl.crojas.blog.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class PostDTO implements Serializable {

	private static final long serialVersionUID = -4143823015419938434L;

	private Integer idPost;

	private String body;

	private Date dateAlt;

	private Date dateMod;

	private String image;

	private UserDTO user;

	public PostDTO() {
		super();
	}

	public Integer getIdPost() {
		return this.idPost;
	}

	public void setIdPost(Integer idPost) {
		this.idPost = idPost;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDateAlt() {
		return this.dateAlt;
	}

	public void setDateAlt(Date dateAlt) {
		this.dateAlt = dateAlt;
	}

	public Date getDateMod() {
		return this.dateMod;
	}

	public void setDateMod(Date dateMod) {
		this.dateMod = dateMod;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public UserDTO getUser() {
		return this.user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

}
