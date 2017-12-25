package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.PromotionPageLinkEntity;
/**
 * Repository : PromotionPageLink.
 */
public interface PromotionPageLinkJpaRepository extends PagingAndSortingRepository<PromotionPageLinkEntity, Integer> {
	@Query(
			"Select u From PromotionPageLinkEntity u "
			)
	Page<PromotionPageLinkEntity> listPaging(Pageable pageable);


	@Query(
			"Select u From PromotionPageLinkEntity u Where u.title like ?1 "
			)
	Page<PromotionPageLinkEntity> findByTitle(String title, Pageable pageable);
	
	@Query(
			"Select u From PromotionPageLinkEntity u Where u.promotionPage.id = ?1 "
			)
	List<PromotionPageLinkEntity> findByPromotionPageId(Integer promotionPageId);
	
	@Modifying
	@Query(
			value = "delete from promotion_page_link where promotion_page_id = ?1 ", nativeQuery = true
			)
	void deleteByPromotionPageId(Integer promotionPageId);
	
	@Modifying
	@Query(
			value = "delete from promotion_page_link where promotion_page_id in (select id from promotion_page where status = 1 and business_id =?1) ", nativeQuery = true
			)
	void deleteByPromotionPageBy_BusinessId(Integer businessId);
}
