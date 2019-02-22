package cl.crojas.blog.service.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.crojas.blog.dao.PostDAO;
import cl.crojas.blog.dao.impl.PostDAOImpl;
import cl.crojas.blog.dto.PostDTO;
import cl.crojas.blog.entity.PostEntity;
import cl.crojas.blog.service.bo.PostBO;
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
public class PostBOImpl extends BaseBO implements PostBO {

	private static final Logger logger = Logger.getLogger(PostBOImpl.class);

	@PersistenceContext
	private EntityManager em;

	private PostDAO postDAO;

	@PostConstruct
	public void setUp() {
		this.postDAO = new PostDAOImpl(this.em);
	}

	@Override
	public PostDTO getById(long id) throws BusinessException {

		final String methodName = "getById(): ";

		PostDTO dto = new PostDTO();

		try {

			logger.debug(methodName + INICIANDO);

			PostEntity post = this.postDAO.find(id);

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
	public List<PostDTO> findAll() throws BusinessException {

		final String methodName = "findAll(): ";

		List<PostDTO> listado = null;

		try {

			logger.debug(methodName + INICIANDO);

			List<PostEntity> posts = this.postDAO.list();

			if (posts != null && !posts.isEmpty()) {

				listado = new ArrayList<>();

				for (PostEntity post : posts) {

					PostDTO dto = new PostDTO();

					PojoUtils.copyAllFields(post, dto);

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
	public PostDTO create(PostDTO postDTO) throws BusinessException {

		final String methodName = "create(): ";

		PostDTO dto = new PostDTO();

		try {

			logger.debug(methodName + INICIANDO);

			PostEntity entity = new PostEntity();

			PojoUtils.copyAllFields(postDTO, entity);

			PostEntity post = this.postDAO.create(entity);

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
	public void update(PostDTO postDTO) throws BusinessException {

		final String methodName = "update(): ";

		try {

			logger.debug(methodName + INICIANDO);

			PostEntity entity = new PostEntity();

			PojoUtils.copyAllFields(postDTO, entity);

			this.postDAO.update(entity);

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

			this.postDAO.delete(id);

			logger.debug(methodName + PROCESO_FINALIZADO);

		} catch (Exception e) {

			logger.error(methodName + e.getMessage(), e);
			throw new BusinessException(TypeBusinessException.EMPTY, e.getMessage(), e);

		}

		logger.debug(methodName + FINALIZANDO);

	}

}
