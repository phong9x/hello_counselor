package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.PasswordHistoryEntity;

/**
 * Repository : PasswordHistory.
 */
public interface PasswordHistoryJpaRepository extends PagingAndSortingRepository<PasswordHistoryEntity, Integer> {
	@Query("Select u From PasswordHistoryEntity u ")
	Page<PasswordHistoryEntity> listPaging(Pageable pageable);

	@Query(value="SELECT COUNT(*) FROM password_history p WHERE p.user_id = ?1 and p.password = ?2 ", nativeQuery = true)
	Integer countChangedPass(Integer userId, String pass);
}
