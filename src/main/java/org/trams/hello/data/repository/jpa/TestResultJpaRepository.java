package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.TestResultEntity;
/**
 * Repository : TestResult.
 */
public interface TestResultJpaRepository extends PagingAndSortingRepository<TestResultEntity, Integer>, JpaSpecificationExecutor<TestResultEntity> {
	@Query(
			"Select u From TestResultEntity u "
			)
	Page<TestResultEntity> listPaging(Pageable pageable);
	
	@Query(
			"Select u From TestResultEntity u where u.userQuestionaire.id = ?1"
			)
	List<TestResultEntity> listResultBy_UserQuestionId(Integer userQuestionaireId);
	
	
	@Query(
			"Select u From TestResultEntity u where u.userQuestionaire.user.id = ?1 and u.userQuestionaire.type = ?2"
			)
	List<TestResultEntity> findBy_UserAndType(Integer user, Integer type, Pageable pageable);
	
	@Query(
			"Select u From TestResultEntity u where u.userQuestionaire.id = ?1"
			)
	List<TestResultEntity> findBy_UserQuestionaireId(Integer userQuestionaireId, Pageable pageable);
	
	@Modifying
	@Query(
			value="delete from test_result where user_questionaire_id = ?1", nativeQuery = true
			)
	void deleteBy_UserQuestionId(Integer userQuestionaireId);


}
