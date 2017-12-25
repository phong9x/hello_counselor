package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.BusinessEntity;
/**
 * Repository : Business.
 */
public interface BusinessJpaRepository extends PagingAndSortingRepository<BusinessEntity, Integer> {
	@Query(
			"Select u From BusinessEntity u Where u.user.isDelete = 0"
			)
	Page<BusinessEntity> listPaging(Pageable pageable);
	
	@Query(
			"Select u From BusinessEntity u Where u.user.isDelete = 0"
			)
	List<BusinessEntity> findAllOrderBy(Pageable pageable);
	
	@Query(
			"Select u.id From BusinessEntity u Where u.isDelete = 0 and u.businessName like ?1"
			)
	List<BusinessEntity> listPagingByName(String businessName);

	@Modifying
	@Query(
			value="UPDATE business SET is_delete = 1 WHERE id = ?1",nativeQuery=true
			)
	void update_isDelete(Integer id);
	
	@Query(
			"Select u From BusinessEntity u Where u.businessType = ?1 and u.businessName like ?2 and u.user.username like ?3 and u.user.isDelete = 0"
			)
	Page<BusinessEntity> listPagingBy_BusineesTypeAndBusinessNameAndUsername(Short businessType, String businessName, String username, Pageable pageable);
	
	@Query(
			"Select u From BusinessEntity u Where u.businessName like ?1 and u.user.username like ?2 and u.user.isDelete = 0"
			)
	Page<BusinessEntity> listPagingBy_BusinessNameAndUsername(String businessName, String username, Pageable pageable);
	
	@Query(
			"Select u From BusinessEntity u Where u.parentBusinessId > 0 and u.user.isDelete = 0"
			)
	Page<BusinessEntity> listPagingSubBusiness(Pageable pageable);
	
	@Query(
			"Select u From BusinessEntity u Where u.parentBusinessId = ?1 and u.user.isDelete = 0"
			)
	Page<BusinessEntity> listPagingBy_ParentBusinessId(Integer parentBusinessId, Pageable pageable);
	
	
	@Query(
			"Select u From BusinessEntity u Where u.businessType = ?1 and u.parentBusinessId > 0 and u.user.isDelete = 0"
			)
	Page<BusinessEntity> listPagingBy_BusinessType(Short businessType, Pageable pageable);
	
	@Query(
			"Select u From BusinessEntity u Where u.businessType = ?1 and u.parentBusinessId = 0 and u.user.isDelete = 0"
			)
	List<BusinessEntity> listBy_BusinessType(Short businessType);
	
	@Query(
			"Select u From BusinessEntity u Where u.parentBusinessId = 0 and u.user.isDelete = 0"
			)
	List<BusinessEntity> listParentBusiness();
	
	@Query(
			value = "select "
					+ " (select count(*) from business_sub sub where sub.business_id = b.id and sub.status = 1 ) as sub_active_total,"
					+ " (select count(*) from business_sub sub where sub.business_id = b.id and sub.status != 0) as sub_total"
					+ " from business b where b.id = ?1", nativeQuery = true
			)
	List<Object[]> getTotalSubActiveAndTotalSubBy_BusinessId(Integer businessId);
	
	
	
	
	

}
