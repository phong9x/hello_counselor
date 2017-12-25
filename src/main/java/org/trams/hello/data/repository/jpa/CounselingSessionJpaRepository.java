package org.trams.hello.data.repository.jpa;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.CounselingSessionEntity;

/**
 * Repository : CounselingSession.
 */
public interface CounselingSessionJpaRepository extends PagingAndSortingRepository<CounselingSessionEntity, Integer>,
		JpaSpecificationExecutor<CounselingSessionEntity> {
	@Query("Select u From CounselingSessionEntity u Where u.isDelete = 0")
	Page<CounselingSessionEntity> listPaging(Pageable pageable);

	@Modifying
	@Query(value = "UPDATE counseling_session SET is_delete = 1 WHERE id = ?1", nativeQuery = true)
	void update_isDelete(Integer id);

	@Query(value = "select cs.id from counseling_session cs where cs.user_id = ?1 and cs.counselor_id = ?2 and cs.is_delete =0 and cs.id not in "
			+ "(select c.counseling_session_id  from comment c where c.user_id = ?1)", nativeQuery = true)
	Integer countSatisfactionEvaluation(Integer userId, Integer counselorId);

	@Query(value = "select count(*) from counseling_session cs where cs.user_id = ?1 and is_delete = 0", nativeQuery = true)
	Integer countCounselingSession(Integer userId);

	@Query(value = "select count(*) from counseling_session cs where cs.user_id = ?1 and cs.end_time < ?2 and cs.status = 1 and cs.is_delete = 0", nativeQuery = true)
	Integer countCounselingSessionHaveCounselYet(Integer userId, Date date);

	@Query(value = "select exists( select id from counseling_session where counselor_id = ?1 and status = ?2 and is_delete = 0 )", nativeQuery = true)
	Integer countCounselingSessionOfCounselor(Integer counselorId, Short status);

	@Query("Select u From CounselingSessionEntity u Where u.user.id = ?1 and u.startTime >= ?2 and u.startTime <= ?3 and u.status in ?4")
	List<CounselingSessionEntity> filterByDate(Integer userId, Date startDate, Date toDate, Short[] statuses);
	
	@Query("Select c From CounselingSessionEntity c Where c.user.id = ?1 and c.startTime > NOW() and c.status = 1 ORDER BY c.startTime ASC ")
	List<CounselingSessionEntity> findEarliestCounselingTime(Integer userId);
	
	@Query(value = "select count(*) from counseling_session cs where cs.user_id = ?1 and cs.is_delete = 0 and cs.status in (2,3,4) ", nativeQuery = true)
	Integer totalCompletedCounselingHistoryByUser(Integer userId);
	
	@Query(value = "select YEAR(c1.end_time) from counseling_session c1 JOIN counselor co1 on c1.counselor_id = co1.id JOIN user u1 on co1.id = u1.id WHERE c1.status IN (2,3,4) and c1.user_id = ?1 "
			+ "GROUP BY YEAR(c1.end_time) order by c1.end_time DESC ", nativeQuery = true)
	Integer[] getYearCompletedCounseling(Integer userId);
	
	@Query("Select TIME_TO_SEC(TIME(c.startTime)) From CounselingSessionEntity c Where c.counselor.id = ?2 and DATE(c.startTime) = DATE(?1) and c.counselType = 2 and c.status IN (2,3,4) order by c.startTime ASC ")
	List<Integer> listStartTimeCounselingSSByDay(Date dayConvert, Integer counselorId);
	
	@Query(value="SELECT DISTINCT u.fullname, u.email, u.id FROM counseling_session c "
			+ " INNER JOIN `user` u ON c.user_id = u.id "
			+ " WHERE c.counselor_id = ?1 "
			+ " and c.start_time >= (NOW() - INTERVAL 7 DAY) and c.start_time <= NOW() ", nativeQuery= true)
	List<Object[]> listCounselingSSInAWeek(Integer counselorId);
	
	@Query(value="SELECT DISTINCT u.fullname, u.email, u.id FROM counseling_session c "
			+ " INNER JOIN `user` u ON c.user_id = u.id "
			+ " WHERE c.counselor_id = ?1 and u.status_active != 3 ", nativeQuery= true)
	List<Object[]> listCounselingSessionByCounselorId(Integer counselorId);
	
	
	@Query("Select u From CounselingSessionEntity u Where u.user.id = ?1 and u.id = ?2 and u.isDelete = 0")
	CounselingSessionEntity findByIdAnhUserId(Integer userId, Integer revertionId);
	
	@Query(
			value ="Select count(*) From counseling_session u Where ((u.start_time <= ?1 and  ?1 < u.end_time) or ( u.start_time <= ?2 and  ?2 < u.end_time))  and u.counselor_id = ?3 and u.status = 1", nativeQuery = true
			)
	int countBy(Date start, Date end, Integer counselorId);
	
	@Query("Select c From CounselingSessionEntity c Where Year(c.startTime)=?1 and Month(c.startTime)=?2 and Day(c.startTime)=?3 and Hour(c.startTime)=?4 and MINUTE(c.startTime)=?5 "
			+ "and c.status = 1 and c.counselType =2 and c.user.reservationNotification = 1 ORDER BY c.counselor.id ASC ")
	List<CounselingSessionEntity> listNotificaionByMinutes(Integer year, Integer month, Integer day, Integer hour, Integer minute);
	
	@Query("Select c From CounselingSessionEntity c Where Year(c.startTime)=?1 and Month(c.startTime)=?2 and Day(c.startTime)=?3 and Hour(c.startTime)=?4 and MINUTE(c.startTime)=?5 "
			+ "and c.status = 1 and c.counselType =2 and c.user.reservationNotification = 1  ORDER BY c.counselor.id ASC ")
	List<CounselingSessionEntity> listNotificaionByHour(Integer year, Integer month, Integer day, Integer hour, Integer minute);
	
	@Query( value = "Select u.username, c.id, c.counselor_id, u.id as userId From counseling_session c inner join user u on c.user_id = u.id Where Year(c.end_time)=?1 and Month(c.end_time)=?2 and Day(c.end_time)=?3 and Hour(c.end_time)=?4 and MINUTE(c.end_time)=?5 and (c.status !=0) and c.id not in (select uq.counseling_session_id from user_questionaire uq where uq.type = 1) and u.reservation_notification = 1 ",nativeQuery = true)
	List<Object[]> listRequiredSatisfaction(Integer year, Integer month, Integer day, Integer hour, Integer minute);
	
	@Query(value="Select (HOUR(c.start_time) * 60 + MINUTE(c.start_time)) From counseling_session c Where DATE(c.start_time) = DATE(?2) and c.counselor_id = ?1 and c.counsel_type = 2 and c.status IN (1,2,3,4) ORDER BY c.start_time ASC ", nativeQuery=true)
	List<BigInteger> listTimeByDate(Integer couselorId, Date date);
	
	@Query(value= "SELECT COUNT(*) "
			+ " FROM "
			+ " counseling_session c "
			+ " WHERE "
			+ " c.start_time >= ?1 and c.start_time <= ?2 and c.status IN(2,3) and c.counselor_id = ?3 ", nativeQuery=true)
	Integer countTotalCouseling(Date startTime, Date endTime, Integer couselorId);
	@Modifying
	@Query(value="update counseling_session  set status =2 Where end_time <= NOW() and status = 1",nativeQuery = true)
	void updateCounselingFinished();
	@Modifying
	@Query(value="update counseling_session  set status =1 Where end_time > NOW() and status = 2",nativeQuery = true)
	void updateCounselingNotFinished();

    CounselingSessionEntity findByRoomId(String roomId);
    
    @Query(value="SELECT (SUM(c.point) / COUNT(c.id)) FROM counseling_session c WHERE c.counselor_id = ?1 ",nativeQuery = true)
    Float totalPointByCounselor(Integer counselorId);
    
    @Query(value="SELECT count(*) FROM counseling_session cs WHERE cs.user_id = ?1 and cs.status IN(2,3) ",nativeQuery = true)
    Integer totalCounselingSessionByUserAndCounselor(Integer userId);
    
    CounselingSessionEntity findByPreRoomId(String roomId);
}
