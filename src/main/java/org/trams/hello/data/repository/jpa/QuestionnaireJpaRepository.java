package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.QuestionnaireEntity;

/**
 * Repository : Questionnaire.
 */
public interface QuestionnaireJpaRepository extends PagingAndSortingRepository<QuestionnaireEntity, Integer> {
	@Query("Select u From QuestionnaireEntity u ")
	Page<QuestionnaireEntity> listPaging(Pageable pageable);

	@Query("Select u From QuestionnaireEntity u Where u.title like ?1 ")
	Page<QuestionnaireEntity> findByTitle(String title, Pageable pageable);
	
	@Query("Select u From QuestionnaireEntity u Where u.isShow = 1 ")
	QuestionnaireEntity findQuestionnaireByIsShow();
}
