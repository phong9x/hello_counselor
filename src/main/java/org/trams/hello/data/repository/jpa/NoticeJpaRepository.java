package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.NoticeEntity;

/**
 * Repository : Notice.
 */
public interface NoticeJpaRepository extends PagingAndSortingRepository<NoticeEntity, Integer> {
	@Query("Select u From NoticeEntity u ")
	Page<NoticeEntity> listPaging(Pageable pageable);

	@Query("Select u From NoticeEntity u Where u.title like ?1 ")
	Page<NoticeEntity> findByTitle(String title, Pageable pageable);
	
	@Query("SELECT u FROM NoticeEntity u WHERE u.type = 2 AND u.isShow = 1 ")
	Page<NoticeEntity> listPagingByCounselorIdInWebC(Pageable pageable);
}
