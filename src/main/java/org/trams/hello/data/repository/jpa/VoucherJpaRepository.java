package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.VoucherEntity;

/**
 * Repository : Voucher.
 */
public interface VoucherJpaRepository extends PagingAndSortingRepository<VoucherEntity, Integer> {
	@Query("Select u From VoucherEntity u ")
	Page<VoucherEntity> listPaging(Pageable pageable);

	@Query("Select u From VoucherEntity u Where u.name like ?1 ")
	Page<VoucherEntity> findByName(String name, Pageable pageable);

	@Query("Select u From VoucherEntity u where u.businessSub.id = ?1 ")
	VoucherEntity findOneByBusinessId(Integer businessId);
	
	@Query("Select u From VoucherEntity u where u.businessSub.id = ?1 ")
	List<VoucherEntity> listByBusinessId(Integer businessId);
	
	@Query("Select u From VoucherEntity u where u.typeVoucher = ?1 ")
	VoucherEntity findByTypeHeart(Short type);
	
	VoucherEntity findByBusinessSubId(Integer businessSubId);
}
