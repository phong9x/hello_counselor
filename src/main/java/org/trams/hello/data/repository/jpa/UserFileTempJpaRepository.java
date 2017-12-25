package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserFileTempEntity;
/**
 * Repository : UserFileTemp.
 */
public interface UserFileTempJpaRepository extends PagingAndSortingRepository<UserFileTempEntity, Integer> {
	@Query(
			"Select u From UserFileTempEntity u "
			)
	Page<UserFileTempEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From UserFileTempEntity u Where u.counselorTempId = ?1 "
			)
	Page<UserFileTempEntity> listPagingByUserId( Integer userId, Pageable pageable);

	@Modifying
	@Query(
			value ="DELETE c FROM user_file_temp c inner join counselor_temp ct on c.counselor_temp_id = ct.counselor_id WHERE ct.counselor_id = ?1", nativeQuery =true
			)
	void deleteByCounselorId(Integer counselorId);
}
