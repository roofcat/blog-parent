package cl.crojas.blog.dao.impl;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cl.crojas.blog.dao.PostDAO;
import cl.crojas.blog.entity.PostEntity;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@Repository
public class PostDAOImpl extends JPADAO<PostEntity, Long> implements PostDAO {

	private static final Logger logger = Logger.getLogger(PostDAOImpl.class);

	public PostDAOImpl() {
		super();
	}

	public PostDAOImpl(EntityManager em) {
		super(em);
	}

}
