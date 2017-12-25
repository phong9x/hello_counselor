/*
 * Created on 9 thg 1 2017 ( Time 15:56:09 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.CounselorBiography;
import org.trams.hello.bean.jpa.CounselorBiographyEntity;

/**
 * Business Service Interface for entity CounselorBiography.
 */
public interface CounselorBiographyService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	CounselorBiography findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<CounselorBiography> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<CounselorBiographyEntity> findAll(Integer page);

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
	CounselorBiography save(CounselorBiography entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	CounselorBiography update(CounselorBiography entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	CounselorBiography create(CounselorBiography entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<CounselorBiographyEntity> listPaging(Integer page,Integer size);
	
	List<CounselorBiographyEntity> listPagingByCounselorId(Integer counselorId);
	
	void deleteCounselorBiography(Integer counselorId);

    List<CounselorBiographyEntity> findByCounselorId(Integer id);
}
