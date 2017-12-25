package org.trams.hello.data.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.ReportDailyEntity;
/**
 * Repository : ReportDaily.
 */
public interface ReportDailyJpaRepository extends PagingAndSortingRepository<ReportDailyEntity, Integer> {
	@Query(
			"Select u From ReportDailyEntity u "
			)
	Page<ReportDailyEntity> listPaging(Pageable pageable);
	
	@Query(
			"Select u From ReportDailyEntity u where Date(u.createDate) = Date(?1) "
			)
	List<ReportDailyEntity> findReportByCreateDate(Date date);


}
