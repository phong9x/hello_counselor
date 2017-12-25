package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.trams.hello.bean.jpa.BusinessSubEntity;

/**
 * Repository : BusinessSub.
 */
public interface BusinessSubJpaRepository extends PagingAndSortingRepository<BusinessSubEntity, Integer> {
	@Query(value =
			"SELECT BS.id, BS.manage_year, BS.business_sub_name, "
				+ "(SELECT COUNT(*) FROM user_business WHERE business_sub_id = BS.id) as user_count, "
				+ "V.used, BS.status, V.fee "+
			  "FROM business_sub BS, voucher V "+
	         "WHERE BS.business_id = :businessSubId "+
			   "AND V.business_sub_id = BS.id "+
			 "LIMIT :offset, :size",
			nativeQuery = true
	)
	List<Object[]> findForlist(@Param("businessSubId") Integer businessId, @Param("offset") Integer offset, @Param("size") Integer size);
	
	@Query(value = 
			"SELECT COUNT(BS.id) " +
			  "FROM business_sub BS, voucher V "+
	         "WHERE BS.business_id = :businessSubId "+
			   "AND V.business_sub_id = BS.id ",
			nativeQuery = true
	)
	Long countForList(@Param("businessSubId") Integer businessId);
	
	@Query(value =
			"SELECT BS.id, BS.manage_year, BS.business_sub_name, "
				+ "(SELECT COUNT(*) FROM user_business WHERE business_sub_id = BS.id) as user_count, "
				+ "V.used, BS.status, V.fee "+
			  "FROM business_sub BS, voucher V, business B "+
	         "WHERE B.business_type = :type "+
			   "AND V.business_sub_id = BS.id AND BS.business_id = B.id "+
			 "LIMIT :offset, :size",
			nativeQuery = true
	)
	List<Object[]> findForlistByType(@Param("type") Short businessId, @Param("offset") Integer offset, @Param("size") Integer size);
	
	@Query(value = 
			"SELECT COUNT(BS.id) " +
			  "FROM business_sub BS, voucher V, business B "+
	         "WHERE B.business_type = :type "+
			   "AND V.business_sub_id = BS.id AND BS.business_id = B.id",
			nativeQuery = true
	)
	Long countForListByType(@Param("type") Short type);
	
	
	List<BusinessSubEntity> findByBusinessId(Integer businessId);
	
	List<BusinessSubEntity> findByBusinessIdAndManageYear(Integer businessId, Integer manageYear);
}
