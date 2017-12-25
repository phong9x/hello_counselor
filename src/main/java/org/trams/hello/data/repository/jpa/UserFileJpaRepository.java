package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserFileEntity;
/**
 * Repository : UserFile.
 */
public interface UserFileJpaRepository extends PagingAndSortingRepository<UserFileEntity, Integer> {
	@Query(
			"Select u From UserFileEntity u "
			)
	Page<UserFileEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From UserFileEntity u Where u.userId = ?1 "
			)
	Page<UserFileEntity> listPagingByUserId( Integer userId, Pageable pageable);
	
	@Query(
			"Select u From UserFileEntity u Where u.userId = ?2 and u.type = ?1"
			)
	List<UserFileEntity> listPagingByTypeAndUserId( Short type, Integer userId, Pageable pageable);


    void deleteByUserIdAndType(Integer id, Short type);

    List<UserFileEntity> findByUserIdAndType(Integer userId, short type);
}
