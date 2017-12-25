package org.trams.hello.data.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserQuestionaireEntity;

/**
 * Repository : UserQuestionaire.
 */
public interface UserQuestionaireJpaRepository extends PagingAndSortingRepository<UserQuestionaireEntity, Integer>,
		JpaSpecificationExecutor<UserQuestionaireEntity> {
	@Query("Select u From UserQuestionaireEntity u ")
	Page<UserQuestionaireEntity> listPaging(Pageable pageable);

	@Query("Select u From UserQuestionaireEntity u Where u.user.id = ?1 ")
	Page<UserQuestionaireEntity> listPagingByUserId(Integer userId, Pageable pageable);
	
	@Query("Select u.user.fullname, u.point, u.comment From UserQuestionaireEntity u Where u.counselor.id = ?1 and u.type = 1 ")
	Page<Object[]> listCommentByCounselorId(Integer counselorId, Pageable pageable);
	
	@Query("Select u From UserQuestionaireEntity u Where u.user.id = ?1 and u.type = ?2 ")
	List<UserQuestionaireEntity> listPagingByUserIdAndType(Integer userId, short type, Pageable pageable);

	@Query("Select u From UserQuestionaireEntity u Where u.type = ?1 ")
	Page<UserQuestionaireEntity> listPagingByType(Short type, Pageable pageable);

	@Query("Select u From UserQuestionaireEntity u Where u.type = ?1 and u.createDate <= ?2 and u.createDate >= ?3 and u.user.fullname like ?4 and u.counselor.user.fullname like ?5 and u.comment like ?5")
	Page<UserQuestionaireEntity> listPagingBy_QuestionaireTypeAndTimeAndUserNameAndCounselorNameAndComment(Short type,
			Date startTiem, Date endTime, String username, String counselorName, String comment, Pageable pageable);

	@Query("Select u From UserQuestionaireEntity u Where u.type = ?1  and u.user.fullname like ?2 and u.counselor.user.fullname like ?3 and u.comment like ?4")
	Page<UserQuestionaireEntity> listPagingBy_QuestionaireTypeAndUserNameAndCounselorNameAndComment(Short type,
			String username, String counselorName, String comment, Pageable pageable);

	@Query(value = "select ROUND( AVG(u.point),1 ) from user_questionaire u where u.counselor_id = ?1 and type =1", nativeQuery = true)
	Float totalPointCounsolerId(Integer counsolerId);
	
	@Query(value = "select case  when sum(u.point) is NULL then 0 else sum(point) end from user_questionaire u where u.counselor_id = ?1 and u.type =1", nativeQuery = true)
	Integer totalRecommendBYCounselorId(Integer counsolerId);
	
	@Query(value = "Select COUNT(*) From user_questionaire u Where user_id = ?1 and u.type =2", nativeQuery = true)
	Integer totalTestMindCheckByUserId(Integer userId);
	
	@Query("Select u From UserQuestionaireEntity u Where u.user.id = ?1 and u.type = ?2 Group BY u.questionnaire.selfDiagnosisField")
	List<UserQuestionaireEntity> listSeftDiagnosisByUserIdAndType(Integer userId, short type, Pageable pageable);
	
	@Modifying
	@Query(value = "update user_questionaire u set u.point = (select round(avg(t.point),1) from test_result t where t.user_questionaire_id = u.id) where u.id = ?1", nativeQuery = true)
	void updatePointByUserQuestionAire(Integer userQuestionId);
	
	@Query("Select u From UserQuestionaireEntity u Where u.counselor.id = ?1 and u.type = ?2 ")
	Page<UserQuestionaireEntity> listUserQuestionareByCouselorId(Integer couselorId, Short type, Pageable pageable);
	
	@Query("Select u From UserQuestionaireEntity u Where u.counselingSessionId = ?1")
	List<UserQuestionaireEntity> getStatisficEvolutionBy_CounselingSessionId(Integer counselingSessionId, Pageable pageable);
	
	@Query("Select u From UserQuestionaireEntity u Where u.questionnaire.id = ?1 and u.user.id = ?2 and u.type = 3 ORDER BY u.createDate DESC ")
	List<UserQuestionaireEntity> getSelfDiagnosis(Integer questionaireId, Integer userId);
}

