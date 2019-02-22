package cl.crojas.blog.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import cl.crojas.blog.dao.DAO;
import cl.crojas.blog.dao.enums.TypeDAOException;
import cl.crojas.blog.dao.exception.DAOException;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class JPADAO<T extends Object, A extends Serializable> implements DAO<T, A> {

	private static final Logger logger = Logger.getLogger(JPADAO.class);

	protected static final String ERROR_HIBERNATE = "Error Hibernate: ";
	protected static final String ERROR_INTERNO = "Error Interno: ";
	protected static final String ERROR_NEGOCIO = "Error Negocio: ";
	protected static final String ERROR_CONSULTA = "Error al ejecutar consulta en DB ";
	protected static final String ERROR_EMPTY_MSG = "No se encontró registro en la DB ";
	protected static final String ERROR_DUPLICATED_MSG = "Existe más de un registro en la DB ";
	protected static final String ERROR_QUERY_MSG = "Error al realizar la consulta a la DB ";

	protected static final String EXISTEN_RESULTADOS = "Se encontraron resultados...";
	protected static final String NO_EXISTEN_RESULTADOS = "No se encontraron resultados...";

	protected static final String INICIANDO = "Iniciando...";
	protected static final String PROCESO_FINALIZADO = "Proceso finalizado exitosamente...";
	protected static final String FINALIZANDO = "Finalizado...";

	protected Class<T> entityClass;

	protected EntityManager em;

	/**
	 * Constructor sin Argumentos de la Clase La Anotación @SuppressWarnings indica
	 * que se desactivaron los warnings de este método, en este caso, por clase no
	 * chequeada al instanciar un objeto con valores de la consulta
	 * 
	 * @author Christian Rojas N.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public JPADAO() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	/**
	 * Constructor con Argumentos de la Clase La Anotación @SuppressWarnings indica
	 * que se desactivaron los warnings de este método, en este caso, por clase no
	 * chequeada al instanciar un objeto con valores de la consulta
	 * 
	 * @param em Objeto de Tipo EntityManager con los Datos de Acceso a la Base de
	 *           Datos
	 * @author Christian Rojas N.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public JPADAO(EntityManager em) {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
		this.em = em;
	}

	/**
	 * Método utilitario para instanciar el EntityManager asociado al DAO La
	 * Anotación @Override indica que este método es sobreescrito de la clase que
	 * hereda o interfaz que implementa
	 * 
	 * @param em Objeto de Tipo EntityManager con los Datos de Acceso a la Base de
	 *           Datos
	 * @author Christian Rojas N.
	 * 
	 */
	@Override
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	/**
	 * Método utilitario para Crear Objetos JPA Esta Transacción es Mandatoria, se
	 * va a Ejecutar con la Primera Orden de Prioridad La Anotación @Override indica
	 * que este método es sobreescrito de la clase que hereda o interfaz que
	 * implementa
	 * 
	 * @param t Objeto de Tipo Genérico que representa el Registro a Editar
	 * @return T Objeto de Tipo Genérico con el Registro Creado
	 * @author Christian Rojas N.
	 * @throws DAOException
	 * 
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public T create(T t) throws DAOException {

		logger.debug("create(): Init");

		try {

			this.em.persist(t);
			this.em.flush();
			this.em.refresh(t);

		} catch (Exception e) {

			logger.error(ERROR_HIBERNATE + e.getMessage(), e);
			throw new DAOException(TypeDAOException.HIBERNATE, ERROR_HIBERNATE + e.getMessage(), e);

		}

		logger.debug("create(): End");

		return t;

	}

	/**
	 * Método utilitario para Obtener Objetos JPA La Anotación @Override indica que
	 * este método es sobreescrito de la clase que hereda o interfaz que implementa
	 * 
	 * @param id Objeto de Tipo Genérico que representa el Identificador del Objeto
	 *           a Obtener
	 * @return T Objeto de Tipo Genérico con el Registro Obtenido
	 * @author Christian Rojas N.
	 * @throws DAOException
	 * 
	 */
	@Override
	public T find(A id) throws DAOException {

		logger.debug("find(): Init");

		T result = null;

		try {

			result = this.em.find(this.entityClass, id);

		} catch (Exception e) {

			logger.error(ERROR_HIBERNATE + e.getMessage(), e);
			throw new DAOException(TypeDAOException.HIBERNATE, ERROR_HIBERNATE + e.getMessage(), e);

		}

		logger.debug("find(): End");

		return result;

	}

	/**
	 * Método utilitario para Editar Objetos JPA La Anotación @Override indica que
	 * este método es sobreescrito de la clase que hereda o interfaz que implementa
	 * 
	 * @param t Objeto de Tipo Genérico que representa el Registro a Editar
	 * @return T Objeto de Tipo Genérico con el Registro Editado
	 * @author Christian Rojas N.
	 * @throws DAOException
	 * 
	 */
	@Override
	public T update(T t) throws DAOException {

		logger.debug("update(): Init");

		T result = null;

		try {

			result = this.em.merge(t);

		} catch (HibernateException e) {

			logger.error(ERROR_HIBERNATE + e.getMessage(), e);
			throw new DAOException(TypeDAOException.HIBERNATE, ERROR_HIBERNATE + e.getMessage(), e);

		}

		logger.debug("update(): End");

		return result;

	}

	/**
	 * Método utilitario para Eliminar Objetos JPA Esta Transacción es Mandatoria,
	 * se va a Ejecutar con la Primera Orden de Prioridad La Anotación @Override
	 * indica que este método es sobreescrito de la clase que hereda o interfaz que
	 * implementa
	 * 
	 * @param id Objeto de Tipo Genérico que representa el Identificador del Objeto
	 *           a Eliminar
	 * @author Christian Rojas N.
	 * @throws DAOException
	 * 
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void delete(A id) throws DAOException {

		logger.debug("delete(): Init");

		try {

			Object ref = this.em.getReference(this.entityClass, id);

			this.em.remove(ref);

		} catch (HibernateException e) {

			logger.error(ERROR_HIBERNATE + e.getMessage(), e);
			throw new DAOException(TypeDAOException.HIBERNATE, ERROR_HIBERNATE + e.getMessage(), e);

		}

		logger.debug("create(): End");

	}

	/**
	 * Método utilitario para listar Objetos JPA La Anotación @Override indica que
	 * este método es sobreescrito de la clase que hereda o interfaz que implementa
	 * La Anotación @SuppressWarnings indica que se desactivaron los warnings de
	 * este método, en este caso, por clase no chequeada al instanciar un objeto con
	 * valores de la consulta
	 * 
	 * @return List<T> Lista de Objetos Genéricos Obtenidos
	 * @author Christian Rojas N.
	 * @throws DAOException
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> list() throws DAOException {

		logger.debug("list(): Init");

		List<T> result = null;

		try {

			String simpleName = this.entityClass.getSimpleName();

			Query query = this.em.createQuery("Select T from " + simpleName + " T ");

			result = query.getResultList();

		} catch (HibernateException e) {

			logger.error(ERROR_HIBERNATE + e.getMessage(), e);
			throw new DAOException(TypeDAOException.HIBERNATE, ERROR_HIBERNATE + e.getMessage(), e);

		}

		logger.debug("list(): End");

		return result;

	}

	/**
	 * Método utilitario para listar Objetos JPA La Anotación @Override indica que
	 * este método es sobreescrito de la clase que hereda o interfaz que implementa
	 * La Anotación @SuppressWarnings indica que se desactivaron los warnings de
	 * este método, en este caso, por clase no chequeada al instanciar un objeto con
	 * valores de la consulta
	 * 
	 * @param max Variable int que Indica la Cantidad Máxima de Resultados a Obtener
	 * @return List<T> Lista de Objetos Genéricos Obtenidos
	 * @author Christian Rojas N.
	 * @throws DAOException
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(int max) throws DAOException {

		logger.debug("list(max): Init");

		List<T> result = null;

		try {

			Query query = this.em.createQuery("SELECT T FROM " + this.entityClass.getSimpleName() + " T ");
			query.setFirstResult(0);
			query.setMaxResults(max);

			result = query.getResultList();

		} catch (HibernateException e) {

			logger.error(ERROR_HIBERNATE + e.getMessage(), e);
			throw new DAOException(TypeDAOException.HIBERNATE, ERROR_HIBERNATE + e.getMessage(), e);

		}

		logger.debug("list(max): End");

		return result;

	}

	/**
	 * Método utilitario para listar Objetos JPA La Anotación @Override indica que
	 * este método es sobreescrito de la clase que hereda o interfaz que implementa
	 * La Anotación @SuppressWarnings indica que se desactivaron los warnings de
	 * este método, en este caso, por clase no chequeada al instanciar un objeto con
	 * valores de la consulta
	 * 
	 * @param pageIndex Variable int que Indica la Primera Página
	 * @param pageSize  Variable int que Indica la Cantidad de Páginas a Obtener
	 * @return List<T> Lista de Objetos Genéricos Obtenidos
	 * @author Christian Rojas N.
	 * @throws DAOException
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(int pageIndex, int pageSize) throws DAOException {

		logger.debug("list(pageIndex, pageSize): Init");

		List<T> result = null;

		try {

			Query query = this.em.createQuery("SELECT T FROM " + this.entityClass.getSimpleName() + " T ");
			query.setFirstResult(pageIndex * pageSize);
			query.setMaxResults(pageSize);

			result = query.getResultList();

		} catch (HibernateException e) {

			logger.error(ERROR_HIBERNATE + e.getMessage(), e);
			throw new DAOException(TypeDAOException.HIBERNATE, ERROR_HIBERNATE + e.getMessage(), e);

		}

		logger.debug("list(pageIndex, pageSize): End");

		return result;

	}

	/**
	 * Método utilitario para inicializar proxies La Anotación @SuppressWarnings
	 * indica que se desactivaron los warnings de este método, en este caso, por
	 * clase no chequeada al instanciar un objeto con valores de la consulta
	 * 
	 * @param obj Objeto a inicializar
	 * @return <Y> Objeto casteado
	 * @author C. Alcaraz
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <Y> Y getInitializedObject(Object obj) {

		logger.debug("getInitializedObject(): Init");

		Y retorno = null;

		if (obj != null)
			retorno = (Y) em.unwrap(SessionImplementor.class).getPersistenceContext().unproxy(obj);

		logger.debug("getInitializedObject(): End");

		return retorno;

	}

}
