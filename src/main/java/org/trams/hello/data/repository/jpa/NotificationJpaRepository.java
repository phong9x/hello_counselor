package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.NotificationEntity;

/**
 * Repository : Notification.
 */
public interface NotificationJpaRepository
		extends PagingAndSortingRepository<NotificationEntity, Integer>, JpaSpecificationExecutor<NotificationEntity> {
	@Query("Select u From NotificationEntity u ")
	Page<NotificationEntity> listPaging(Pageable pageable);

	@Query("Select u From NotificationEntity u Where u.title like ?1 ")
	Page<NotificationEntity> findByTitle(String title, Pageable pageable);
	
	@Query("Select u From NotificationEntity u Where u.id like ?1 ")
	NotificationEntity findByNotifiId(Integer id);

}
