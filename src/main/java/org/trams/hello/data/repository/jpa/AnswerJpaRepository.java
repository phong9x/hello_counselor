package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.AnswerEntity;

/**
 * Repository : Answer.
 */
public interface AnswerJpaRepository extends PagingAndSortingRepository<AnswerEntity, Integer> {
	@Query("Select u From AnswerEntity u ")
	Page<AnswerEntity> listPaging(Pageable pageable);

	@Query("Select u From AnswerEntity u where u.question.id = ?1 ")
	List<AnswerEntity> getAnswersByQuestionId(Integer questionId);
}
