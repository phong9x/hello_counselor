package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserRoleEntity;
/**
 * Repository : UserRole.
 */
public interface UserRoleJpaRepository extends PagingAndSortingRepository<UserRoleEntity, Integer> {
	@Query(
			"Select u From UserRoleEntity u "
			)
	Page<UserRoleEntity> listPaging(Pageable pageable);


}
