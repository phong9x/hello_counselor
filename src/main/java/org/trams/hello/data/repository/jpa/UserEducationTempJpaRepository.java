package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserEducationTempEntity;
/**
 * Repository : UserEducationTemp.
 */
public interface UserEducationTempJpaRepository extends PagingAndSortingRepository<UserEducationTempEntity, Integer> {
	@Query(
			"Select u From UserEducationTempEntity u "
			)
	Page<UserEducationTempEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From UserEducationTempEntity u Where u.counselorTempId = ?1 "
			)
	Page<UserEducationTempEntity> listPagingByUserId( Integer userId, Pageable pageable);
	
	@Query(
			"Select u From UserEducationTempEntity u Where u.counselorTempId = ?1 "
			)
	List<UserEducationTempEntity> listByUserId( Integer userId);

	@Modifying
	@Query(
			value ="delete from counselor_biography_temp where counselor_temp_id = ?1", nativeQuery =true
			)
	void deleteByCounselorTempId(Integer counselorTempId);
}
