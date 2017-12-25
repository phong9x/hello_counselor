package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.MenuAdminLevel3Entity;
/**
 * Repository : MenuAdminLevel3.
 */
public interface MenuAdminLevel3JpaRepository extends PagingAndSortingRepository<MenuAdminLevel3Entity, Integer> {
	@Query(
			"Select u From MenuAdminLevel3Entity u "
			)
	Page<MenuAdminLevel3Entity> listPaging(Pageable pageable);


	@Query(
			"Select u From MenuAdminLevel3Entity u Where u.name like ?1 "
			)
	Page<MenuAdminLevel3Entity> findByName(String name, Pageable pageable);
}
