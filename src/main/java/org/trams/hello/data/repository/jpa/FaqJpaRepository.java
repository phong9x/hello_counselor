package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.FaqEntity;

/**
 * Repository : Faq.
 */
public interface FaqJpaRepository extends PagingAndSortingRepository<FaqEntity, Integer> {
	@Query("Select u From FaqEntity u ")
	Page<FaqEntity> listPaging(Pageable pageable);

	@Query("Select u From FaqEntity u Where u.title like ?1 AND u.isShow = 1")
	Page<FaqEntity> findByTitle(String title, Pageable pageable);
	
	@Query("Select u From FaqEntity u Where u.isShow = 1 and u.type = 2 ")
	List<FaqEntity> listByIsShow();
	
	@Query(value="Select * from faq WHERE type =2 AND is_show = 1 ORDER BY order_number ASC ", nativeQuery=true)
	List<FaqEntity> listInCounselorWeb();
	
	@Query("SELECT u FROM FaqEntity u "
			+ "WHERE u.type = 2 AND u.isShow = 1 AND (u.title like ?1 or u.content like ?1 ) "
			+ "ORDER BY u.orderNumber ASC")
	List<FaqEntity> findByTitleOrContent(String keyword);
	
	@Query("SELECT u FROM FaqEntity u WHERE u.isShow = 1 AND u.type = ?1 ")
	List<FaqEntity> listByIsShowAndType(Short type);
}
