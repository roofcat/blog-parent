package cl.crojas.blog.dao;

import cl.crojas.blog.dao.exception.DAOException;
import cl.crojas.blog.entity.RoleEntity;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public interface RoleDAO extends DAO<RoleEntity, Long> {

	RoleEntity findByName(String name) throws DAOException;

}
