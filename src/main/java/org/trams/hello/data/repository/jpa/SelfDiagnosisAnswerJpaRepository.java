package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.SelfDiagnosisAnswerEntity;

/**
 * Repository : SelfDiagnosisAnswer.
 */
public interface SelfDiagnosisAnswerJpaRepository
		extends PagingAndSortingRepository<SelfDiagnosisAnswerEntity, Integer> {
	@Query("Select u From SelfDiagnosisAnswerEntity u ")
	Page<SelfDiagnosisAnswerEntity> listPaging(Pageable pageable);

	@Query("Select u From SelfDiagnosisAnswerEntity u where u.selfDiagnosisField = ?1 ")
	List<SelfDiagnosisAnswerEntity> listBySelfDiagnosisField(Integer selfDiagnosisField);
}
