package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.BankEntity;
/**
 * Repository : Bank.
 */
public interface BankJpaRepository extends PagingAndSortingRepository<BankEntity, Integer> {
	@Query(
			"Select u From BankEntity u "
			)
	Page<BankEntity> listPaging(Pageable pageable);


}
