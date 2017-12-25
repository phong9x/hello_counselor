package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.PromotionPageEntity;
/**
 * Repository : PromotionPage.
 */
public interface PromotionPageJpaRepository extends PagingAndSortingRepository<PromotionPageEntity, Integer>, JpaSpecificationExecutor<PromotionPageEntity> {
	@Query(
			"Select u From PromotionPageEntity u "
			)
	Page<PromotionPageEntity> listPaging(Pageable pageable);
	
	@Query(
			"Select u From PromotionPageEntity u where (u.status = 0 or u.status = 2) and u.business.businessName like ?1"
			)
	Page<PromotionPageEntity> listPagingRequest(String businessName, Pageable pageable);
	
	
	@Query(
			"Select u From PromotionPageEntity u where u.status in ?1 and u.business.businessName like ?2"
			)
	Page<PromotionPageEntity> listPagingBy_StatusAndBussinessName(Short[] status, String businessName, Pageable pageable);
	
	@Query(
			"Select u From PromotionPageEntity u where u.status = 1 and u.business.id = ?1"
			)
	List<PromotionPageEntity> listPagingBy_StatusAndBussinessId(Integer businessId, Pageable pageable);
	
	@Modifying
	@Query(
			value = "delete from promotion_page where status = 1 and business_id = ?1", nativeQuery = true
			)
	void deletePromotionPageBy_BussinessId(Integer businessId);
	
	
}
