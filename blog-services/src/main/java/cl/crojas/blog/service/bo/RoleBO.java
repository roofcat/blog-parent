package cl.crojas.blog.service.bo;

import java.util.List;

import cl.crojas.blog.dto.RoleDTO;
import cl.crojas.blog.service.exception.BusinessException;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public interface RoleBO {

	RoleDTO getById(long id) throws BusinessException;

	List<RoleDTO> findAll() throws BusinessException;

	RoleDTO create(RoleDTO roleDTO) throws BusinessException;

	void update(RoleDTO roleDTO) throws BusinessException;

	void delete(long id) throws BusinessException;

	boolean existById(long id) throws BusinessException;

	boolean existByName(String name) throws BusinessException;

	RoleDTO findByName(String name) throws BusinessException;

}
