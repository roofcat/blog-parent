package cl.crojas.blog.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cl.crojas.blog.dao.RoleDAO;
import cl.crojas.blog.dao.enums.TypeDAOException;
import cl.crojas.blog.dao.exception.DAOException;
import cl.crojas.blog.entity.RoleEntity;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@Repository
public class RoleDAOImpl extends JPADAO<RoleEntity, Long> implements RoleDAO {

	private static final Logger logger = Logger.getLogger(RoleDAOImpl.class);

	private static final String PAR_NAME_ROLE = "nameRole";

	public RoleDAOImpl() {
		super();
	}

	public RoleDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public RoleEntity findByName(String name) throws DAOException {

		final String methodName = "findByName(): ";

		RoleEntity role = null;

		try {

			logger.debug(methodName + INICIANDO);

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT r FROM RoleEntity r ");
			sql.append("WHERE LOWER ( r.nameRole ) = :nameRole ");

			TypedQuery<RoleEntity> query = this.em.createQuery(sql.toString(), RoleEntity.class);
			query.setParameter(PAR_NAME_ROLE, name.toLowerCase());

			role = query.getSingleResult();

			logger.debug(methodName + PROCESO_FINALIZADO);

		} catch (NoResultException e) {

			logger.error(methodName + ERROR_EMPTY_MSG + e.getMessage(), e);
			throw new DAOException(TypeDAOException.EMPTY, ERROR_EMPTY_MSG + e.getMessage(), e);

		} catch (NonUniqueResultException e) {

			logger.error(methodName + ERROR_DUPLICATED_MSG + e.getMessage(), e);
			throw new DAOException(TypeDAOException.DUPLICATED, ERROR_DUPLICATED_MSG + e.getMessage(), e);

		} catch (Exception e) {

			logger.error(methodName + ERROR_QUERY_MSG + e.getMessage(), e);
			throw new DAOException(TypeDAOException.INTERNO, ERROR_QUERY_MSG + e.getMessage(), e);

		}

		logger.debug(methodName + FINALIZANDO);

		return role;

	}

}
