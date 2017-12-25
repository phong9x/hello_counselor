package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.MessageEntity;

/**
 * Repository : Message.
 */
public interface MessageJpaRepository extends PagingAndSortingRepository<MessageEntity, Integer> {
	@Query("Select u From MessageEntity u ")
	Page<MessageEntity> listPaging(Pageable pageable);

	@Query("Select u From MessageEntity u where u.id = ?1 ")
	MessageEntity findByMessageId(Integer id);
	
	@Modifying
	@Query(value = "UPDATE message set is_delete = 1 WHERE id = ?1 ", nativeQuery = true)
	void updateIsDeleteByMessageId(Integer id);
}
