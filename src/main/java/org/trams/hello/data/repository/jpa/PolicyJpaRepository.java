package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.PolicyEntity;

/**
 * Repository : Policy.
 */
public interface PolicyJpaRepository extends PagingAndSortingRepository<PolicyEntity, Integer> {
	@Query("Select u From PolicyEntity u ")
	Page<PolicyEntity> listPaging(Pageable pageable);
	
	@Query("Select u From PolicyEntity u where u.isShow =1 and u.typePolicy = ?1 ")
	List<PolicyEntity> list(Short typePolicy);
	
	@Query("Select u From PolicyEntity u where u.isShow =1 and u.typePolicy = ?1 and u.typeMember = ?2 ")
	PolicyEntity getPoli(Short typePolicy, Short typeMember);
}
