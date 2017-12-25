package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.CounselingMemoEntity;
/**
 * Repository : CounselingMemo.
 */
public interface CounselingMemoJpaRepository extends PagingAndSortingRepository<CounselingMemoEntity, Integer>, JpaSpecificationExecutor<CounselingMemoEntity> {
	@Query(
			"Select u From CounselingMemoEntity u "
			)
	Page<CounselingMemoEntity> listPaging(Pageable pageable);


}
