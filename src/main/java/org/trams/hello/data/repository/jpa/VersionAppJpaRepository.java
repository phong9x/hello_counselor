package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.VersionAppEntity;
/**
 * Repository : VersionApp.
 */
public interface VersionAppJpaRepository extends PagingAndSortingRepository<VersionAppEntity, Integer> {
	@Query(
			"Select u From VersionAppEntity u "
			)
	Page<VersionAppEntity> listPaging(Pageable pageable);
	
	@Query(
			"Select u From VersionAppEntity u where os = ?1"
			)
	Page<VersionAppEntity> listPagingBy_Os(Short os,Pageable pageable);
	
	@Query(
			"Select u From VersionAppEntity u where os = ?1"
			)
	List<VersionAppEntity> listBy_Os(Short os,Pageable pageable);


}
