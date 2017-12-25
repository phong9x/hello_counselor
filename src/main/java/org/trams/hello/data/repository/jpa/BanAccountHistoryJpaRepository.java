package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.BanAccountHistoryEntity;
/**
 * Repository : BanAccountHistory.
 */
public interface BanAccountHistoryJpaRepository extends PagingAndSortingRepository<BanAccountHistoryEntity, Integer> {
	@Query(
			"Select u From BanAccountHistoryEntity u "
			)
	Page<BanAccountHistoryEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From BanAccountHistoryEntity u Where userId = ?1 "
			)
	Page<BanAccountHistoryEntity> listPagingByUserId( Integer userId, Pageable pageable);
	
	@Query(
			"Select u From BanAccountHistoryEntity u Where userId = ?1"
			)
	List<BanAccountHistoryEntity> listByUserId( Integer userId);
	
	@Modifying
	@Query(
			value = "delete from ban_account_history  where user_id = ?1 order by create_date DESC limit 1", nativeQuery = true
			)
	void deleteFirstByUser( Integer userId);


}
