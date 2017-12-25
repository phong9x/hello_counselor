package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.MenuAdminLevel1Entity;
/**
 * Repository : MenuAdminLevel1.
 */
public interface MenuAdminLevel1JpaRepository extends PagingAndSortingRepository<MenuAdminLevel1Entity, Integer>,
JpaSpecificationExecutor<MenuAdminLevel1Entity> {
	@Query(
			"Select u From MenuAdminLevel1Entity u "
			)
	Page<MenuAdminLevel1Entity> listPaging(Pageable pageable);


	@Query(
			"Select u From MenuAdminLevel1Entity u Where u.name like ?1 "
			)
	Page<MenuAdminLevel1Entity> findByName(String name, Pageable pageable);
	

}
