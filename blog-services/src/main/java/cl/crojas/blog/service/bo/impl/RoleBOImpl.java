package cl.crojas.blog.service.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.crojas.blog.dao.RoleDAO;
import cl.crojas.blog.dao.impl.RoleDAOImpl;
import cl.crojas.blog.dto.RoleDTO;
import cl.crojas.blog.entity.RoleEntity;
import cl.crojas.blog.service.bo.RoleBO;
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
public class RoleBOImpl extends BaseBO implements RoleBO {

	private static final Logger logger = Logger.getLogger(RoleBOImpl.class);

	@PersistenceContext
	private EntityManager em;

	private RoleDAO roleDAO;

	@PostConstruct
	public void setUp() {
		this.roleDAO = new RoleDAOImpl(this.em);
	}

	@Override
	public RoleDTO getById(long id) throws BusinessException {

		final String methodName = "getById(): ";

		RoleDTO dto = new RoleDTO();

		try {

			logger.debug(methodName + INICIANDO);

			RoleEntity post = this.roleDAO.find(id);

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
	public List<RoleDTO> findAll() throws BusinessException {

		final String methodName = "findAll(): ";

		List<RoleDTO> listado = null;

		try {

			logger.debug(methodName + INICIANDO);

			List<RoleEntity> roles = this.roleDAO.list();

			if (roles != null && !roles.isEmpty()) {

				listado = new ArrayList<>();

				for (RoleEntity role : roles) {

					RoleDTO dto = new RoleDTO();

					PojoUtils.copyAllFields(role, dto);

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
	public RoleDTO create(RoleDTO postDTO) throws BusinessException {

		final String methodName = "create(): ";

		RoleDTO dto = new RoleDTO();

		try {

			logger.debug(methodName + INICIANDO);

			RoleEntity entity = new RoleEntity();

			PojoUtils.copyAllFields(postDTO, entity);

			RoleEntity post = this.roleDAO.create(entity);

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
	public void update(RoleDTO postDTO) throws BusinessException {

		final String methodName = "update(): ";

		try {

			logger.debug(methodName + INICIANDO);

			RoleEntity entity = new RoleEntity();

			PojoUtils.copyAllFields(postDTO, entity);

			this.roleDAO.update(entity);

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

			this.roleDAO.delete(id);

			logger.debug(methodName + PROCESO_FINALIZADO);

		} catch (Exception e) {

			logger.error(methodName + e.getMessage(), e);
			throw new BusinessException(TypeBusinessException.EMPTY, e.getMessage(), e);

		}

		logger.debug(methodName + FINALIZANDO);

	}

}
