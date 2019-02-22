package cl.crojas.blog.dao.impl;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cl.crojas.blog.dao.UserDAO;
import cl.crojas.blog.entity.UserEntity;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@Repository
public class UserDAOImpl extends JPADAO<UserEntity, Long> implements UserDAO {

	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	public UserDAOImpl() {
		super();
	}

	public UserDAOImpl(EntityManager em) {
		super(em);
	}

}
