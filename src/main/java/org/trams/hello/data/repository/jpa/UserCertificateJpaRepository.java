package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserCertificateEntity;

/**
 * Repository : UserCertificate.
 */
public interface UserCertificateJpaRepository extends PagingAndSortingRepository<UserCertificateEntity, Integer> {
	@Query(
			"Select u From UserCertificateEntity u "
			)
	Page<UserCertificateEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From UserCertificateEntity u Where u.userId = ?1 "
			)
	Page<UserCertificateEntity> listPagingByUserId( Integer userId, Pageable pageable);


	@Query(
			"Select u From UserCertificateEntity u Where u.name like ?1 "
			)
	Page<UserCertificateEntity> findByName(String name, Pageable pageable);

	@Query(
			"select count(*) from UserCertificateEntity u where u.userId = ?1"
	)
    int countByUser(Integer userId);
	
	@Modifying
	@Query(
			value = "delete from user_certificate where user_id = ?1",nativeQuery = true
	)
    void deleteByUserId(Integer userId);

    List<UserCertificateEntity> findByUserId(Integer id);

    UserCertificateEntity findByUserIdAndTypeAndName(Integer userId, short type, String diploma);

	void deleteByUserIdAndTypeAndName(Integer userId, short type, String diploma);
}
