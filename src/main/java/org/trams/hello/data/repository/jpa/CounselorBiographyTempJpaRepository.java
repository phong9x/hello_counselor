package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.CounselorBiographyTempEntity;
/**
 * Repository : CounselorBiographyTemp.
 */
public interface CounselorBiographyTempJpaRepository extends PagingAndSortingRepository<CounselorBiographyTempEntity, Integer> {
	@Query(
			"Select u From CounselorBiographyTempEntity u "
			)
	Page<CounselorBiographyTempEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From CounselorBiographyTempEntity u where u.counselorTempId = ?1"
			)
	Page<CounselorBiographyTempEntity> listPagingByUserId(Integer counselorId, Pageable pageable);
	
	@Query(
			"Select u From CounselorBiographyTempEntity u where u.counselorTempId = ?1"
			)
	List<CounselorBiographyTempEntity> listByUserId(Integer counselorId);
	
	@Modifying
	@Query(
			value ="delete from counselor_biography_temp where counselor_temp_id = ?1", nativeQuery =true
			)
	void deleteByUserId(Integer counselorTempId);
	
	@Modifying
	@Query(
			value ="DELETE c FROM counselor_biography_temp c inner join counselor_temp ct on c.counselor_temp_id = ct.counselor_id WHERE ct.counselor_id = ?1", nativeQuery =true
			)
	void deleteByCounselorId(Integer counselorId);
}
