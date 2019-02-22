package cl.crojas.blog.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import cl.crojas.blog.dao.exception.DAOException;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public interface DAO<T extends Object, A extends Serializable> {

	void setEntityManager(EntityManager em);

	T create(T t) throws DAOException;

	T find(A id) throws DAOException;

	T update(T t) throws DAOException;

	void delete(A id) throws DAOException;

	List<T> list() throws DAOException;

	List<T> list(int max) throws DAOException;

	List<T> list(int pageIndex, int pageSize) throws DAOException;

}
