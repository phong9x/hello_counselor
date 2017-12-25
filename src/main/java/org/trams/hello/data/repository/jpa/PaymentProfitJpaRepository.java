package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.PaymentProfitEntity;

/**
 * Repository : PaymentProfit.
 */
public interface PaymentProfitJpaRepository extends PagingAndSortingRepository<PaymentProfitEntity, Integer>,
		JpaSpecificationExecutor<PaymentProfitEntity> {
	@Query("Select u From PaymentProfitEntity u ")
	Page<PaymentProfitEntity> listPaging(Pageable pageable);

	@Query("Select u From PaymentProfitEntity u Where u.name like ?1 ")
	Page<PaymentProfitEntity> findByName(String name, Pageable pageable);
	
	@Query("Select u From PaymentProfitEntity u Where u.year = ?1 and u.month = ?2 and u.user.id = ?3 and u.type = ?4 ")
	PaymentProfitEntity getTotalMoneyByMonth(Integer year, Integer month, Integer counselorId, Short type);

	@Query("Select u From PaymentProfitEntity u Where u.year = ?1 and u.month = ?2 and u.user.id = ?3 and u.type = ?4 and u.status = ?5")
	PaymentProfitEntity findByCounselorAndTime(Integer year, Integer month, Integer counselorId, Short type, Short status);

}
