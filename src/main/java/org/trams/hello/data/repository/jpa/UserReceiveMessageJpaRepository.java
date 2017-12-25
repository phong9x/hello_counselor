package org.trams.hello.data.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserReceiveMessageEntity;

/**
 * Repository : UserReceiveMessage.
 */
public interface UserReceiveMessageJpaRepository extends PagingAndSortingRepository<UserReceiveMessageEntity, Integer> {
	@Query("Select u From UserReceiveMessageEntity u ")
	Page<UserReceiveMessageEntity> listPaging(Pageable pageable);

	@Query("Select u From UserReceiveMessageEntity u Where u.user.id = ?1 and u.status in ?2")
	Page<UserReceiveMessageEntity> filterByUser(Integer userId, Short[] statuses, Pageable pageable);
	
	@Query(value="SELECT c.thumbnail_url, c.id, u.fullname, m.recieve_date, m.content, um.status , um.id as id_ms, u.email FROM user_receive_message um "
			+ " INNER JOIN message m on um.message_id = m.id "
			+ " INNER JOIN user u on u.id = m.send_user_id "
			+ " INNER JOIN counselor c on u.id = c.id "
			+ " WHERE m.type_user = 0 and um.user_id = ?1 Order by id_ms DESC ", nativeQuery=true)
	List<Object[]> filterByUser(Integer userId);
	
	@Query(value="SELECT c.thumbnail_url, c.id, u.fullname, m.recieve_date, m.content, um.status, um.id as iyu , u.email "
			+ " FROM user_receive_message um "
			+ " INNER JOIN user u on u.id = um.user_id "
			+ " INNER JOIN counselor c on u.id = c.id "
			+ " INNER JOIN message m on um.message_id = m.id "
			+ " WHERE m.send_user_id=?1 and m.type_user = 1 Order by iyu DESC ", nativeQuery=true)
	List<Object[]> listSentMessage(Integer userId);
	
	@Query(value="Select u From UserReceiveMessageEntity u Where u.id = ?1 and u.message.user.id ?1 ", nativeQuery=true)
	UserReceiveMessageEntity findByUserReceiMessageId(Integer userRecMessId, Integer userId);
	
	
	@Query(value="Select COUNT(u) From UserReceiveMessageEntity u Where u.user.id = ?1")
	Long countInboxMessByUserId(Integer userId);
	
	@Query(value="Select COUNT(u) From MessageEntity u Where u.user.id = ?1")
	Long countSendMessByUserId(Integer userId);
	
	@Query(value="Select create_date From user_receive_message Where user_id = ?1 ORDER BY create_date ASC LIMIT 1", nativeQuery=true)
	Date findFirstDateInboxMessByUserid(Integer userId);
	
	@Query(value="Select create_date From message Where send_user_id = ?1 ORDER BY create_date ASC LIMIT 1", nativeQuery=true)
	Date findFirstDateSendMessByUserid(Integer userId);
}
