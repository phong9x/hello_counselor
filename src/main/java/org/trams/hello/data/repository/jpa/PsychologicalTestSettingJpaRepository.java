package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.PsychologicalTestSettingEntity;
/**
 * Repository : PsychologicalTestSetting.
 */
public interface PsychologicalTestSettingJpaRepository extends PagingAndSortingRepository<PsychologicalTestSettingEntity, Integer> {
	@Query(
			"Select u From PsychologicalTestSettingEntity u "
			)
	Page<PsychologicalTestSettingEntity> listPaging(Pageable pageable);


}
