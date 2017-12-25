package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserBusinessEntity;

/**
 * Repository : UserBusiness.
 */
public interface UserBusinessJpaRepository extends PagingAndSortingRepository<UserBusinessEntity, Integer> {
	List<UserBusinessEntity> findByUserIdAndIsDeleted(Integer userId, Boolean isDeleted);
	
	Page<UserBusinessEntity> findByBusinessSubIdAndIsDeleted(Integer businessSubId, boolean isDeleted, Pageable request);
}
