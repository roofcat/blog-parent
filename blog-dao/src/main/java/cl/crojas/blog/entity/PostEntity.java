package cl.crojas.blog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the posts database table.
 * 
 */
@Entity
@Table(name = "posts")
public class PostEntity implements Serializable {

	private static final long serialVersionUID = 3428102807551777763L;

	@Id
	@Column(name = "ID_POST", unique = true, nullable = false)
	private Integer idPost;

	@Column(name = "BODY", length = 2147483647)
	private String body;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_ALT")
	private Date dateAlt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MOD")
	private Date dateMod;

	@Column(name = "IMAGE", length = 2147483647)
	private String image;

	@ManyToOne
	@JoinColumn(name = "fk_user")
	private UserEntity user;

	public PostEntity() {
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

	public UserEntity getUser() {
		return this.user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
