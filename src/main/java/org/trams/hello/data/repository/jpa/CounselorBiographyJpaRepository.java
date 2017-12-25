package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.CounselorBiographyEntity;
/**
 * Repository : CounselorBiography.
 */
public interface CounselorBiographyJpaRepository extends PagingAndSortingRepository<CounselorBiographyEntity, Integer> {
	
	@Query(
			"Select u From CounselorBiographyEntity u "
			)
	Page<CounselorBiographyEntity> listPaging(Pageable pageable);
	
	@Query(
			"Select u From CounselorBiographyEntity u where u.counselorId = ?1 "
			)
	List<CounselorBiographyEntity> listPagingByCounselorId(Integer counselorId);
	
	@Modifying
	@Query(
			value="delete from counselor_biography where counselor_id = ?1", nativeQuery = true
			)
	void deleteCounselorBiography(Integer counselorId);


    List<CounselorBiographyEntity> findByCounselorId(Integer id);
}
