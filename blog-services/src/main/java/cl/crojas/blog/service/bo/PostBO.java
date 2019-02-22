package cl.crojas.blog.service.bo;

import java.util.List;

import cl.crojas.blog.dto.PostDTO;
import cl.crojas.blog.service.exception.BusinessException;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public interface PostBO {

	PostDTO getById(long id) throws BusinessException;

	List<PostDTO> findAll() throws BusinessException;

	PostDTO create(PostDTO postDTO) throws BusinessException;

	void update(PostDTO postDTO) throws BusinessException;

	void delete(long id) throws BusinessException;

}
