package cl.crojas.blog.dao.impl;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cl.crojas.blog.dao.RoleDAO;
import cl.crojas.blog.entity.RoleEntity;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@Repository
public class RoleDAOImpl extends JPADAO<RoleEntity, Long> implements RoleDAO {

	private static final Logger logger = Logger.getLogger(RoleDAOImpl.class);

	public RoleDAOImpl() {
		super();
	}

	public RoleDAOImpl(EntityManager em) {
		super(em);
	}

}
