package org.trams.hello.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.UserBusiness;
import org.trams.hello.bean.jpa.UserBusinessEntity;

/**
 * Business Service Interface for entity UserBusiness.
 */
public interface UserBusinessService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	UserBusiness findById( Integer id  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<UserBusiness> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	UserBusiness save(UserBusiness entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	UserBusiness update(UserBusiness entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	UserBusiness create(UserBusiness entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	List<UserBusinessEntity> findByUserIdAndIsDeleted(Integer userId, Boolean isDeleted);
	
	Page<UserBusinessEntity> findByBusinessSubIdAndIsDeleted(Integer businessSubId, boolean isDeleted, Integer page, Integer size);
}
