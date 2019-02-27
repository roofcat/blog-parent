package cl.crojas.blog.service.bo;

import java.util.List;

import cl.crojas.blog.dto.UserDTO;
import cl.crojas.blog.service.exception.BusinessException;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public interface UserBO {

	UserDTO getById(long id) throws BusinessException;

	List<UserDTO> findAll() throws BusinessException;

	UserDTO create(UserDTO userDTO) throws BusinessException;

	void update(UserDTO userDTO) throws BusinessException;

	void delete(long id) throws BusinessException;

}
