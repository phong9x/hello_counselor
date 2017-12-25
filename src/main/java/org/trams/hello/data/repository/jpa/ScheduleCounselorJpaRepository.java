package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.ScheduleCounselorEntity;
/**
 * Repository : ScheduleCounselor.
 */
public interface ScheduleCounselorJpaRepository extends PagingAndSortingRepository<ScheduleCounselorEntity, Integer> {
	@Query(
			"Select u From ScheduleCounselorEntity u "
			)
	Page<ScheduleCounselorEntity> listPaging(Pageable pageable);


}
