package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.MenuAdminLevel2Entity;
/**
 * Repository : MenuAdminLevel2.
 */
public interface MenuAdminLevel2JpaRepository extends PagingAndSortingRepository<MenuAdminLevel2Entity, Integer> {
	@Query(
			"Select u From MenuAdminLevel2Entity u "
			)
	Page<MenuAdminLevel2Entity> listPaging(Pageable pageable);


	@Query(
			"Select u From MenuAdminLevel2Entity u Where u.name like ?1 "
			)
	Page<MenuAdminLevel2Entity> findByName(String name, Pageable pageable);
	
	@Query(
			"Select u From MenuAdminLevel2Entity u"
			)
	List<MenuAdminLevel2Entity> findAllMenu(Pageable pageable);
	
	@Query(
			"Select u From MenuAdminLevel2Entity u Where u.isShow = ?1"
			)
	List<MenuAdminLevel2Entity> findByIsShow(Integer isShow, Pageable pageable);
	
	@Modifying
	@Query(
			value="update menu_admin_level_2 set is_show = 0 ",nativeQuery = true
			)
	void updateNotShow();
	
	@Modifying
	@Query(
			value="update menu_admin_level_2 set is_show = 1 where id in ?1",nativeQuery = true
			)
	void updateShow(Integer[] id);
}
