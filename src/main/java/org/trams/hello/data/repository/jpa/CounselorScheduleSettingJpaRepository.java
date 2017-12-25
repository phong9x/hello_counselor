package org.trams.hello.data.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.CounselorScheduleSettingEntity;

/**
 * Repository : CounselorScheduleSetting.
 */
public interface CounselorScheduleSettingJpaRepository
		extends PagingAndSortingRepository<CounselorScheduleSettingEntity, Integer>, JpaSpecificationExecutor<CounselorScheduleSettingEntity> {

	@Query("Select u From CounselorScheduleSettingEntity u ")
	Page<CounselorScheduleSettingEntity> listPaging(Pageable pageable);

	@Query("Select u From CounselorScheduleSettingEntity u WHERE u.counselor.id = ?2 and MONTH(u.reservationDate) = ?1 and DATE(u.reservationDate) >= DATE(NOW()) and YEAR(u.reservationDate) = ?3 order by u.reservationDate DESC " )
	List<CounselorScheduleSettingEntity> listByMonth(Integer month, Integer counselorId, Integer year);
	
	@Query("Select u From CounselorScheduleSettingEntity u WHERE DATE(u.reservationDate) = DATE(?1) and u.counselor.id = ?2")
	CounselorScheduleSettingEntity findCounselorScheduleSettingByReservationDate(Date reservationDate, Integer counselorId );
	
	@Query("Select u From CounselorScheduleSettingEntity u WHERE DATE(u.reservationDate) >= DATE(?1) and DATE(u.reservationDate) <= DATE(?2) and u.counselor.id = ?3 order by u.reservationDate ASC " )
	List<CounselorScheduleSettingEntity> listByWeekAndCounselorId(Date from, Date to, Integer counselorId);
	
	@Query("Select u From CounselorScheduleSettingEntity u WHERE DATE(u.reservationDate) >= DATE(?1) and DATE(u.reservationDate) <= DATE(?2) order by u.reservationDate ASC " )
	List<CounselorScheduleSettingEntity> listByWeek(Date from, Date to);

}
