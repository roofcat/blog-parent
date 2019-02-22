package cl.crojas.blog.service.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.crojas.blog.dao.UserDAO;
import cl.crojas.blog.dao.impl.UserDAOImpl;
import cl.crojas.blog.dto.UserDTO;
import cl.crojas.blog.entity.UserEntity;
import cl.crojas.blog.service.bo.UserBO;
import cl.crojas.blog.service.enums.TypeBusinessException;
import cl.crojas.blog.service.exception.BusinessException;
import cl.crojas.blog.utils.PojoUtils;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@Service
@Transactional(readOnly = true)
public class UserBOImpl extends BaseBO implements UserBO {

	private static final Logger logger = Logger.getLogger(UserBOImpl.class);

	@PersistenceContext
	private EntityManager em;

	private UserDAO userDAO;

	@PostConstruct
	public void setUp() {
		this.userDAO = new UserDAOImpl(this.em);
	}

	@Override
	public UserDTO getById(long id) throws BusinessException {

		final String methodName = "getById(): ";

		UserDTO dto = new UserDTO();

		try {

			logger.debug(methodName + INICIANDO);

			UserEntity post = this.userDAO.find(id);

			PojoUtils.copyAllFields(post, dto);

			logger.debug(methodName + PROCESO_FINALIZADO);

		} catch (Exception e) {

			logger.error(methodName + e.getMessage(), e);
			throw new BusinessException(TypeBusinessException.EMPTY, e.getMessage(), e);

		}

		logger.debug(methodName + FINALIZANDO);

		return dto;

	}

	@Override
	public List<UserDTO> findAll() throws BusinessException {

		final String methodName = "findAll(): ";

		List<UserDTO> listado = null;

		try {

			logger.debug(methodName + INICIANDO);

			List<UserEntity> users = this.userDAO.list();

			if (users != null && !users.isEmpty()) {

				listado = new ArrayList<>();

				for (UserEntity user : users) {

					UserDTO dto = new UserDTO();

					PojoUtils.copyAllFields(user, dto);

					listado.add(dto);

				}

			}

			logger.debug(methodName + PROCESO_FINALIZADO);

		} catch (Exception e) {

			logger.error(methodName + e.getMessage(), e);
			throw new BusinessException(TypeBusinessException.EMPTY, e.getMessage(), e);

		}

		logger.debug(methodName + FINALIZANDO);

		return listado;

	}

	@Override
	@Transactional(readOnly = false)
	public UserDTO create(UserDTO postDTO) throws BusinessException {

		final String methodName = "create(): ";

		UserDTO dto = new UserDTO();

		try {

			logger.debug(methodName + INICIANDO);

			UserEntity entity = new UserEntity();

			PojoUtils.copyAllFields(postDTO, entity);

			UserEntity post = this.userDAO.create(entity);

			PojoUtils.copyAllFields(post, dto);

			logger.debug(methodName + PROCESO_FINALIZADO);

		} catch (Exception e) {

			logger.error(methodName + e.getMessage(), e);
			throw new BusinessException(TypeBusinessException.EMPTY, e.getMessage(), e);

		}

		logger.debug(methodName + FINALIZANDO);

		return dto;

	}

	@Override
	@Transactional(readOnly = false)
	public void update(UserDTO postDTO) throws BusinessException {

		final String methodName = "update(): ";

		try {

			logger.debug(methodName + INICIANDO);

			UserEntity entity = new UserEntity();

			PojoUtils.copyAllFields(postDTO, entity);

			this.userDAO.update(entity);

			logger.debug(methodName + PROCESO_FINALIZADO);

		} catch (Exception e) {

			logger.error(methodName + e.getMessage(), e);
			throw new BusinessException(TypeBusinessException.EMPTY, e.getMessage(), e);

		}

		logger.debug(methodName + FINALIZANDO);

	}

	@Override
	@Transactional(readOnly = false)
	public void delete(long id) throws BusinessException {

		final String methodName = "delete(): ";

		try {

			logger.debug(methodName + INICIANDO);

			this.userDAO.delete(id);

			logger.debug(methodName + PROCESO_FINALIZADO);

		} catch (Exception e) {

			logger.error(methodName + e.getMessage(), e);
			throw new BusinessException(TypeBusinessException.EMPTY, e.getMessage(), e);

		}

		logger.debug(methodName + FINALIZANDO);

	}

}
