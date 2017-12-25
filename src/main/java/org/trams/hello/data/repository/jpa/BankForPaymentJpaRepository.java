package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.BankForPaymentEntity;
/**
 * Repository : BankForPayment.
 */
public interface BankForPaymentJpaRepository extends PagingAndSortingRepository<BankForPaymentEntity, Integer> {
	@Query(
			"Select u From BankForPaymentEntity u "
			)
	Page<BankForPaymentEntity> listPaging(Pageable pageable);
	
	@Query(
			"Select u From BankForPaymentEntity u where u.bankCode = ?1 "
			)
	BankForPaymentEntity findByBankCode(String bankCode);


}
