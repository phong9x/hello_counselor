package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserCertificateTempEntity;
/**
 * Repository : UserCertificateTemp.
 */
public interface UserCertificateTempJpaRepository extends PagingAndSortingRepository<UserCertificateTempEntity, Integer> {
	@Query(
			"Select u From UserCertificateTempEntity u "
			)
	Page<UserCertificateTempEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From UserCertificateTempEntity u Where u.counselorTempId = ?1 "
			)
	Page<UserCertificateTempEntity> listPagingByUserId( Integer userId, Pageable pageable);


	@Query(
			"Select u From UserCertificateTempEntity u Where u.name like ?1 "
			)
	Page<UserCertificateTempEntity> findByName(String name, Pageable pageable);
	
	@Query(
			"Select u From UserCertificateTempEntity u Where u.counselorTempId = ?1 "
			)
	List<UserCertificateTempEntity> listByUserId( Integer userId);
	
	@Modifying
	@Query(
			value ="delete from user_certificate_temp where counselor_temp_id = ?1 ", nativeQuery =true
			)
	void deleteByCounselorTempId(Integer counselorTempId);
	
	@Modifying
	@Query(
			value ="DELETE c FROM user_education_temp c inner join counselor_temp ct on c.counselor_temp_id = ct.counselor_id WHERE ct.counselor_id = ?1", nativeQuery =true
			)
	void deleteByCounselorId(Integer counselorId);
}
