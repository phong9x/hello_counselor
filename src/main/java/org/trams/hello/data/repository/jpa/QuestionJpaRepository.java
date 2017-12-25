package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.QuestionEntity;
/**
 * Repository : Question.
 */
public interface QuestionJpaRepository extends PagingAndSortingRepository<QuestionEntity, Integer> {
	@Query(
			"Select u From QuestionEntity u "
			)
	Page<QuestionEntity> listPaging(Pageable pageable);

	@Query(
			value="select (select count(*) from user where is_delete =0) as count_1,"
					+ " (select count(*) from user where is_delete =0 and role_id = 2) as count_2,"
					+ " (select count(*) from user where is_delete =0 and (role_id = 5 or role_id = 6)) as count_3,"
					+ " (select count(*) from user where is_delete =0 and role_id = 4) as count_4,"
					+ " (select count(*) from business b inner join user u on b.id = u.id and u.is_delete = 0"
					+ " where DATEDIFF(NOW(), b.end_contract_date) < 30  ) as count_5"
					,nativeQuery=true
			)
	List<Object[]> totalUserHome();
	
	@Query("SELECT q FROM QuestionEntity q WHERE q.questionnaire.isShow = 1 and q.questionnaire.type = ?1 order by q.orderNumber ASC ")
	List<QuestionEntity> listQuestionByIsShowQsn(Short questionnaireType);
	
	@Query("SELECT q FROM QuestionEntity q WHERE q.questionnaire.isShow = 1 and q.questionnaire.type = ?1 and q.questionnaire.selfDiagnosisField = ?2 order by q.orderNumber ASC")
	List<QuestionEntity> listQuestionStressSelfDiagnosisByIsShowQsn(Short questionnaireType, Integer selfDiagnosisField);
	
}
