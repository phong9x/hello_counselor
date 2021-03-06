/*
 * Created on 13 Apr 2017 ( Time 16:55:37 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.PaymentProfit;
import org.trams.hello.bean.jpa.PaymentProfitEntity;

/**
 * Business Service Interface for entity PaymentProfit.
 */
public interface PaymentProfitService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	PaymentProfit findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<PaymentProfit> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<PaymentProfitEntity> findAll(Integer page);

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
	PaymentProfit save(PaymentProfit entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	PaymentProfit update(PaymentProfit entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	PaymentProfit create(PaymentProfit entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<PaymentProfitEntity> listPaging(Integer page,Integer size);
	
	Page<PaymentProfitEntity> findByName(String name, Integer page, Integer size);

	Page<PaymentProfitEntity> filter(Map<String, Object> params);
	
	PaymentProfitEntity getTotalMoneyByMonth(int year, int month, Integer counselorId, Short type);
	
}
