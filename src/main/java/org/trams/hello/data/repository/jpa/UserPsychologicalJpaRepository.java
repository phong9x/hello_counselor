package org.trams.hello.data.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserPsychologicalEntity;

/**
 * Repository : UserPsychological.
 */
public interface UserPsychologicalJpaRepository extends PagingAndSortingRepository<UserPsychologicalEntity, Integer> {
	@Query("Select u From UserPsychologicalEntity u ")
	Page<UserPsychologicalEntity> listPaging(Pageable pageable);

	@Query("Select u From UserPsychologicalEntity u Where u.userId = ?1 ")
	Page<UserPsychologicalEntity> listPagingByUserId(Integer userId, Pageable pageable);

	@Query("Select u From UserPsychologicalEntity u ")
	List<UserPsychologicalEntity> listByUserIdNotPayment(Integer userId, Integer counselorId, Date requestPsychological);
	
	@Query("Select u From UserPsychologicalEntity u where u.requestUserPsychological.id = ?1 order by psychologicalTestSetting.companyName")
	List<UserPsychologicalEntity> listByRequestId(Integer reqId);
	
	@Query(value = " Select uu.fullname, u.counselor_id, u.request_psychological, u.id, u.effect_payment_psychological, p.test_name, p.fee "
			+ " From user_psychological u JOIN `user` uu ON u.counselor_id = uu.id "
			+ " JOIN psychological_test_setting p ON u.psychological_test_id = p.id "
			+ " Where u.user_id = ?1 and u.status = 1 and now() <= u.effect_payment_psychological ORDER BY u.counselor_id ASC " , nativeQuery = true)
	List<Object[]> list(Integer userId);
	
	
	
}
