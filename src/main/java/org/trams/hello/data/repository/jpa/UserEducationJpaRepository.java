package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserEducationEntity;
/**
 * Repository : UserEducation.
 */
public interface UserEducationJpaRepository extends PagingAndSortingRepository<UserEducationEntity, Integer> {
	@Query(
			"Select u From UserEducationEntity u "
			)
	Page<UserEducationEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From UserEducationEntity u Where u.userId = ?1 "
			)
	Page<UserEducationEntity> listPagingByUserId( Integer userId, Pageable pageable);
	
	@Query(
			"Select u From UserEducationEntity u Where u.userId = ?1 "
			)
	List<UserEducationEntity> listPagingByUserId( Integer userId);
	
	@Modifying
	@Query(
			value = "delete from user_education where user_id = ?1", nativeQuery = true
			)
	void deleteByUserId( Integer userId);

	@Query(
			value = "select count(*) from UserEducationEntity u where u.userId = ?1"
	)
    int countByUser(Integer userId);

    UserEducationEntity findByUserId(Integer id);
}
