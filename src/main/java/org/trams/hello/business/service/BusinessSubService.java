package org.trams.hello.business.service;

import java.util.List;
import java.util.Map;

import org.trams.hello.bean.BusinessSub;
import org.trams.hello.bean.jpa.BusinessSubEntity;
import org.trams.hello.bean.web.admin.BusinessSubDate;

/**
 * Business Service Interface for entity BusinessSub.
 */
public interface BusinessSubService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	BusinessSub findById( Integer id  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<BusinessSub> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	BusinessSub save(BusinessSub entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	BusinessSub update(BusinessSub entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	BusinessSub create(BusinessSub entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	List<Map<String, Object>> findForlist(Integer businessId, Integer page, Integer pageSize);
	
	List<Map<String, Object>> findForlistByType(Short type, Integer page, Integer pageSize);
	
	Long countForListByType(Short type);
	
	Long countForList(Integer businessId);
		
	BusinessSubEntity findEntityById( Integer id  ) ;
	
	void updateBusinessSub(Integer businessSubId, BusinessSubEntity businessSubEntity, BusinessSubDate businessSubDate);
	
	List<BusinessSub> findByBusinessId(Integer businessId);
	
	List<BusinessSub> findByBusinessIdAndManageYear(Integer businessId, Integer manageYear);
}
