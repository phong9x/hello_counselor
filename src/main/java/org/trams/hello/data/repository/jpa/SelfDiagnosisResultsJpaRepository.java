package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.SelfDiagnosisResultsEntity;

/**
 * Repository : SelfDiagnosisResults.
 */
public interface SelfDiagnosisResultsJpaRepository
		extends PagingAndSortingRepository<SelfDiagnosisResultsEntity, Integer> {
	@Query("Select u From SelfDiagnosisResultsEntity u ")
	Page<SelfDiagnosisResultsEntity> listPaging(Pageable pageable);

	@Query("Select u From SelfDiagnosisResultsEntity u where u.fromPoint < ?1 and u.toPoint >= ?1 and u.questionaireId = ?2 ")
	SelfDiagnosisResultsEntity findSelfDiagnosisResultsByPoint(Float point, Integer questionaireId);
	
	@Query("Select u From SelfDiagnosisResultsEntity u where u.questionaireId = ?1 ")
	List<SelfDiagnosisResultsEntity> listSelfDiagnosisResultsByPoint(Integer questionaireId);
}
