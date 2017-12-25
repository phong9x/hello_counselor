package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.CategoryEntity;

/**
 * Repository : Category.
 */
public interface CategoryJpaRepository extends PagingAndSortingRepository<CategoryEntity, Integer> {
	@Query("Select u From CategoryEntity u ")
	Page<CategoryEntity> listPaging(Pageable pageable);

	@Query("Select u From CategoryEntity u where u.type =?1")
	List<CategoryEntity> findByType(Short type);
	
	@Query("Select u From CategoryEntity u where u.id = ?1 and u.type =?2 ")
	List<CategoryEntity> findByTypeAndID(Integer id, Short type);
	
	List<CategoryEntity> findByTypeAndIsShow(Short type, Short isShow);
}
