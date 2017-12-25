package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.VoucherUserEntity;

/**
 * Repository : VoucherUser.
 */
public interface VoucherUserJpaRepository extends PagingAndSortingRepository<VoucherUserEntity, Integer> {
	@Query("Select u From VoucherUserEntity u ")
	Page<VoucherUserEntity> listPaging(Pageable pageable);

	@Query("Select u From VoucherUserEntity u Where u.userId = ?1 ")
	Page<VoucherUserEntity> listPagingByUserId(Integer userId, Pageable pageable);
	
	@Query("Select u From VoucherUserEntity u Where u.userId = ?1 and u.statusUse = 0 and u.toDate >= now()")
	List<VoucherUserEntity> listVoucherCanUse(Integer userId, Pageable pageable);

	@Query("Select u From VoucherUserEntity u Where u.userId = ?1 and statusUse = ?2")
	List<VoucherUserEntity> listPagingByUserIdAndStatus(Integer userId, short status);

	@Query(value = "Select count(*) From voucher_user u Where u.user_id = ?1 and u.status_use = 0", nativeQuery = true)
	Integer totalVourcherByUserId(Integer userId);
	
	@Modifying
	@Query(value = "delete from voucher_user where id in (?1)", nativeQuery = true)
	void deleteVouchert(Integer[] voucherId);
	
	@Query(value = "Select count(*) From voucher_user u Where u.user_id = ?1  ", nativeQuery = true)
	Integer totalVoucherByUserId(Integer userId);
	
	@Query(value = "Select count(*) From voucher_user u Where u.user_id = ?1 and status_use = 0 ", nativeQuery = true)
	Integer totalVoucherNoUsedByUserId(Integer userId);
	
	@Query(value="Select * From voucher_user u Where u.user_id = ?1 ORDER BY u.create_date DESC LIMIT ?2,?3 ", nativeQuery=true)
	List<VoucherUserEntity> voucherUserEntitiesByUserIdLoadMore(Integer userId, Integer startElement, Integer defaultElement);
}
