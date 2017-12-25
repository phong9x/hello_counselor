/*
 * Created on 23 thg 11 2016 ( Time 13:38:12 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.Inquiry;
import org.trams.hello.bean.jpa.InquiryEntity;

/**
 * Business Service Interface for entity Inquiry.
 */
public interface InquiryService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Inquiry findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<Inquiry> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<InquiryEntity> findAll(Integer page);

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
	Inquiry save(Inquiry entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Inquiry update(Inquiry entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Inquiry create(Inquiry entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<InquiryEntity> listPaging(Integer page,Integer size);
	
	List<Integer> totalInquiry();

}