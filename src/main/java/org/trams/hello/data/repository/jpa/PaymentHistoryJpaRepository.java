package org.trams.hello.data.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.PaymentHistoryEntity;

/**
 * Repository : PaymentHistory.
 */
public interface PaymentHistoryJpaRepository extends PagingAndSortingRepository<PaymentHistoryEntity, Integer>,
		JpaSpecificationExecutor<PaymentHistoryEntity> {
	@Query("Select u From PaymentHistoryEntity u ")
	Page<PaymentHistoryEntity> listPaging(Pageable pageable);

	@Query("Select u From PaymentHistoryEntity u where u.user.id =?1 ")
	Page<PaymentHistoryEntity> listPagingByUserId(Integer userId, Pageable pageable);

	@Query("Select u From PaymentHistoryEntity u where u.tid = ?1")
	PaymentHistoryEntity getBy_Tid(String tid);

	@Query(value = "select sum(coin) from payment_history p where p.type_coin =1 and p.type_payment = 1 and status = 1", nativeQuery = true)
	Integer totalAdd_HelloCoin();

	@Query(value = "select sum(coin) from payment_history p where p.type_coin =1 and p.type_payment = 2", nativeQuery = true)
	Integer totalDeduction_HelloCoin();

	@Query("Select r From PaymentHistoryEntity r where r.user.id = ?1 and r.typePayment = ?2 and status = 1")
	Page<PaymentHistoryEntity> listPagingByUserId(Integer userId, Integer typePayment, Pageable pageable);
	
	@Query("Select r From PaymentHistoryEntity r where r.user.id = ?1 and r.typePayment = ?2 and status = 1")
	List<PaymentHistoryEntity> listPagingByUserId(Integer userId, Integer typePayment);
	
	@Query("Select r From PaymentHistoryEntity r where r.user.id = ?1 and r.typeCoin = ?2 and r.status = 1 ")
	Page<PaymentHistoryEntity> listPagingByUserIdAndTypeCoin(Integer userId, Short typeCoin, Pageable pageable);

	@Query(value = "SELECT COUNT(*) FROM payment_history p WHERE user_id = ?1 and p.type_payment = 1 and status = 1 and p.type_use =40", nativeQuery = true)
	Integer totallistPagingByUserId(Integer userId);
	
	@Query(value = "SELECT COUNT(*) FROM payment_history p WHERE user_id = ?1 and p.type_payment = 1 and p.type_coin = 2 and p.type_use = ?2 and p.reason = ?3 and Date(p.create_date) = Date(?4)", nativeQuery = true)
	Integer counShareBy_TypeShare(Integer userId, Integer typeUse, String type, Date now);
}
