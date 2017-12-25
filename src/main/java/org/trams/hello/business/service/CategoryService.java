/*
 * Created on 23 thg 11 2016 ( Time 13:38:12 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.Category;
import org.trams.hello.bean.jpa.CategoryEntity;

/**
 * Business Service Interface for entity Category.
 */
public interface CategoryService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Category findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<Category> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<CategoryEntity> findAll(Integer page);

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
	Category save(Category entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Category update(Category entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Category create(Category entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<CategoryEntity> listPaging(Integer page,Integer size);
	
	List<CategoryEntity> findByType(Short type);
	
	List<CategoryEntity> findByTypeAndID(Integer id, Short type);
	
	List<CategoryEntity> findByTypeAndIsShow(Short type, Short isShow);
}
