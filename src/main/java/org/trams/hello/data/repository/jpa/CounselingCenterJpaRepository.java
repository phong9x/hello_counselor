package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.CounselingCenterEntity;
/**
 * Repository : CounselingCenter.
 */
public interface CounselingCenterJpaRepository extends PagingAndSortingRepository<CounselingCenterEntity, Integer> {
	@Query(
			"Select u From CounselingCenterEntity u Where u.isDelete = 0"
			)
	Page<CounselingCenterEntity> listPaging(Pageable pageable);

	@Modifying
	@Query(
			value="UPDATE counseling_center SET is_delete = 1 WHERE id = ?1",nativeQuery=true
			)
	void update_isDelete(Integer id);
	
	@Modifying
	@Query(
			value="UPDATE user SET role_id = 2 WHERE id in (select id from counselor c where c.counselor_center_id = ?1)",nativeQuery=true
			)
	void updateCounselorFreelancerBy_CounselingCenter(Integer counselingCenterId);
	
	@Query(
			value="select count(p.money) from (counseling_session cs inner join payment_history p) "
					+ "inner join counselor c on cs.counselor_id = c.id and c.counselor_center_id = ?1  "
					+ "where p.status = 1 and cs.status = 2",nativeQuery=true
			)
	Integer totalMoneyHaveNotPayForCounSelorCenter(Integer counselorCenterId);
	
	@Query(
			"Select u From CounselingCenterEntity u Where u.isDelete = 0 and u.status = ?1 and u.centerName like ?2"
			)
	Page<CounselingCenterEntity> listPagingBy_StatusAndName(Integer status, String centerName, Pageable pageable);
	
	@Query(
			"Select u From CounselingCenterEntity u Where u.isDelete = 0 and u.centerName like ?1 "
			)
	Page<CounselingCenterEntity> listPagingBy_Name(String centerName, Pageable pageable);

}
