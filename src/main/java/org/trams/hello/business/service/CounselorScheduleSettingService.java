/*
 * Created on 7 Mar 2017 ( Time 09:52:29 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.CounselorScheduleSetting;
import org.trams.hello.bean.api.ReservationDate;
import org.trams.hello.bean.jpa.CounselorScheduleSettingEntity;

/**
 * Business Service Interface for entity CounselorScheduleSetting.
 */
public interface CounselorScheduleSettingService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	CounselorScheduleSetting findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<CounselorScheduleSetting> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<CounselorScheduleSettingEntity> findAll(Integer page);

	/**
	 * Count all entities
	 * @return Long
	 */
	Long countTotal();
	

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	CounselorScheduleSetting save(CounselorScheduleSetting entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	CounselorScheduleSetting update(CounselorScheduleSetting entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	CounselorScheduleSetting create(CounselorScheduleSetting entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<CounselorScheduleSettingEntity> listPaging(Integer page,Integer size);
	
	List<ReservationDate> listByMonth(Integer month, Integer counselorId, Integer year);
	
	CounselorScheduleSetting findCounselorScheduleSettingByReservationDate(Date reservationDate,  Integer counselorId);

	List<CounselorScheduleSettingEntity> listByWeekAndCounselorId(Date from, Date to, Integer counselorId);
	
	List<CounselorScheduleSettingEntity> listByWeek(Date from, Date to);

	Page<CounselorScheduleSettingEntity> filter(Map<String, Object> params);

	void repeatSchedule(Map<String, Object> params);

	void resetSchedule(Integer counselorId);
}
