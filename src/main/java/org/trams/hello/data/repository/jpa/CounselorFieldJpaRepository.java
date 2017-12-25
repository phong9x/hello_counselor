package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.CounselorFieldEntity;
import org.trams.hello.bean.jpa.CounselorFieldEntityKey;
/**
 * Repository : CounselorField.
 */
public interface CounselorFieldJpaRepository extends PagingAndSortingRepository<CounselorFieldEntity, CounselorFieldEntityKey> {
	@Query(
			"Select u From CounselorFieldEntity u "
			)
	Page<CounselorFieldEntity> listPaging(Pageable pageable);
	
	@Query(
			"Select u From CounselorFieldEntity u where u.compositePrimaryKey.counselorId = ?1"
			)
	List<CounselorFieldEntity> listBy_CounselorId(Integer counselorId);
	
	@Modifying
	@Query(
			value ="delete from counselor_field where counselor_id = ?1", nativeQuery= true
			)
	void deleteByCounselorId(Integer counselorId);
	
	@Query(
			value ="select c.category_name  from counselor_field cf inner join  category c on cf.counselor_field_id = c.id where cf.counselor_id = ?1 ", nativeQuery= true
			)
	List<Object> getCategoryNameBy_CouselorId(Integer counselorId);
	
	@Query(
			value ="select DISTINCT cf.counselor_id from counselor_field cf inner join counselor c on c.id = cf.counselor_id where cf.counselor_field_id in ?1 order by c.create_date DESC", nativeQuery= true
			)
	Integer[] getCounselorIdBy_CouselorFieldId(Integer[] counselorFieldId);


}
