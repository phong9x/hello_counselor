/*
 * Created on 11 thg 1 2017 ( Time 10:17:14 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.UserNotification;
import org.trams.hello.bean.jpa.UserNotificationEntity;

/**
 * Business Service Interface for entity UserNotification.
 */
public interface UserNotificationService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	UserNotification findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<UserNotification> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<UserNotificationEntity> findAll(Integer page);

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
	UserNotification save(UserNotification entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	UserNotification update(UserNotification entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	UserNotification create(UserNotification entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<UserNotificationEntity> listPaging(Integer page,Integer size);
	
	Page<UserNotificationEntity> filter(Map<String, Object> params);

	long countNotification(Map<String, Object> params);

	UserNotificationEntity upsert(Map<String, Object> params);
	
	UserNotification findByUserIdAndNotifiId(Integer userId, Integer notifiId);
}