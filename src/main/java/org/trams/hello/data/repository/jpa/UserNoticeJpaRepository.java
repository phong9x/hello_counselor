package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserNoticeEntity;

/**
 * Repository : UserNotice.
 */
public interface UserNoticeJpaRepository extends PagingAndSortingRepository<UserNoticeEntity, Integer> {
	@Query("Select u From UserNoticeEntity u ")
	Page<UserNoticeEntity> listPaging(Pageable pageable);

	@Query("Select u From UserNoticeEntity u Where u.userId = ?1 ")
	Page<UserNoticeEntity> listPagingByUserId(Integer userId, Pageable pageable);
	
	@Query("Select u From UserNoticeEntity u Where u.userId = ?1 and u.notice.type = 2  ")
	Page<UserNoticeEntity> listPagingByCounselorId(Integer counselorId, Pageable pageable);
	
	@Query("Select u From UserNoticeEntity u Where u.userId = ?2 and u.notice.id = ?1  ")
	UserNoticeEntity findByNoticeIdAndUserId(Integer noticeId, Integer userId);
	
	@Query("Select u From UserNoticeEntity u Where u.notice.type = 1  ")
	Page<UserNoticeEntity> listPagingByCounselorIdInWebC(Pageable pageable);
}
