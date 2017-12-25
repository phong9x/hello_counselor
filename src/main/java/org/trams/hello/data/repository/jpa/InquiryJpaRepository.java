package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.InquiryEntity;
/**
 * Repository : Inquiry.
 */
public interface InquiryJpaRepository extends PagingAndSortingRepository<InquiryEntity, Integer> {
	@Query(
			"Select u From InquiryEntity u "
			)
	Page<InquiryEntity> listPaging(Pageable pageable);

	@Query(
			value="select (select count(*) from inquiry where type_user = 1) as count_1,"
					+ " (select count(*) from inquiry where type_user = 2) as count_2"
					,nativeQuery=true
			)
	List<Object[]> totalInquiry();
}
