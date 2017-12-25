/*
 * Created on 14 Mar 2017 ( Time 14:52:42 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.hello.bean.MenuAdminLevel1;
import org.trams.hello.bean.jpa.MenuAdminLevel1Entity;
import org.trams.hello.business.service.MenuAdminLevel1Service;
import org.trams.hello.business.service.mapping.MenuAdminLevel1ServiceMapper;
import org.trams.hello.data.repository.jpa.MenuAdminLevel1JpaRepository;

/**
 * Implementation of MenuAdminLevel1Service
 */
@Component
@Transactional
public class MenuAdminLevel1ServiceImpl implements MenuAdminLevel1Service {

	@Resource
	private MenuAdminLevel1JpaRepository menuAdminLevel1JpaRepository;

	private static final Integer PAGE_SIZE = 15;

	@Resource
	private MenuAdminLevel1ServiceMapper menuAdminLevel1ServiceMapper;

	@Override
	public MenuAdminLevel1 findById(Integer id) {
		MenuAdminLevel1Entity menuAdminLevel1Entity = menuAdminLevel1JpaRepository.findOne(id);
		return menuAdminLevel1ServiceMapper.mapMenuAdminLevel1EntityToMenuAdminLevel1(menuAdminLevel1Entity);
	}

	@Override
	public Page<MenuAdminLevel1Entity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, new Sort(new Order(Direction.DESC, "id")));
		return menuAdminLevel1JpaRepository.findAll(request);
	}

	@Override
	public List<MenuAdminLevel1> findAll() {
		Iterable<MenuAdminLevel1Entity> entities = menuAdminLevel1JpaRepository.findAll();
		List<MenuAdminLevel1> beans = new ArrayList<MenuAdminLevel1>();
		for (MenuAdminLevel1Entity menuAdminLevel1Entity : entities) {
			beans.add(menuAdminLevel1ServiceMapper.mapMenuAdminLevel1EntityToMenuAdminLevel1(menuAdminLevel1Entity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * 
	 * @return Long
	 */
	public Long countTotal() {
		Long count = menuAdminLevel1JpaRepository.count();
		return count;
	}

	@Override
	public MenuAdminLevel1 save(MenuAdminLevel1 menuAdminLevel1) {
		return update(menuAdminLevel1);
	}

	@Override
	public MenuAdminLevel1 create(MenuAdminLevel1 menuAdminLevel1) {
		/*
		 * MenuAdminLevel1Entity menuAdminLevel1Entity =
		 * menuAdminLevel1JpaRepository.findOne(menuAdminLevel1.getId()); if(
		 * menuAdminLevel1Entity != null ) { throw new
		 * IllegalStateException("already.exists"); }
		 * 
		 * menuAdminLevel1Entity = new MenuAdminLevel1Entity();
		 * menuAdminLevel1ServiceMapper.
		 * mapMenuAdminLevel1ToMenuAdminLevel1Entity(menuAdminLevel1,
		 * menuAdminLevel1Entity); MenuAdminLevel1Entity
		 * menuAdminLevel1EntitySaved =
		 * menuAdminLevel1JpaRepository.save(menuAdminLevel1Entity); return
		 * menuAdminLevel1ServiceMapper.
		 * mapMenuAdminLevel1EntityToMenuAdminLevel1(menuAdminLevel1EntitySaved)
		 * ;
		 */
		MenuAdminLevel1Entity menuAdminLevel1Entity = new MenuAdminLevel1Entity();
		menuAdminLevel1ServiceMapper.mapMenuAdminLevel1ToMenuAdminLevel1Entity(menuAdminLevel1, menuAdminLevel1Entity);
		MenuAdminLevel1Entity menuAdminLevel1EntitySaved = menuAdminLevel1JpaRepository.save(menuAdminLevel1Entity);
		return menuAdminLevel1ServiceMapper.mapMenuAdminLevel1EntityToMenuAdminLevel1(menuAdminLevel1EntitySaved);
	}

	@Override
	public MenuAdminLevel1 update(MenuAdminLevel1 menuAdminLevel1) {
		MenuAdminLevel1Entity menuAdminLevel1Entity = menuAdminLevel1JpaRepository.findOne(menuAdminLevel1.getId());
		menuAdminLevel1ServiceMapper.mapMenuAdminLevel1ToMenuAdminLevel1Entity(menuAdminLevel1, menuAdminLevel1Entity);
		MenuAdminLevel1Entity menuAdminLevel1EntitySaved = menuAdminLevel1JpaRepository.save(menuAdminLevel1Entity);
		return menuAdminLevel1ServiceMapper.mapMenuAdminLevel1EntityToMenuAdminLevel1(menuAdminLevel1EntitySaved);
	}

	@Override
	public void delete(Integer id) {
		menuAdminLevel1JpaRepository.delete(id);
	}

	public MenuAdminLevel1JpaRepository getMenuAdminLevel1JpaRepository() {
		return menuAdminLevel1JpaRepository;
	}

	public void setMenuAdminLevel1JpaRepository(MenuAdminLevel1JpaRepository menuAdminLevel1JpaRepository) {
		this.menuAdminLevel1JpaRepository = menuAdminLevel1JpaRepository;
	}

	public MenuAdminLevel1ServiceMapper getMenuAdminLevel1ServiceMapper() {
		return menuAdminLevel1ServiceMapper;
	}

	public void setMenuAdminLevel1ServiceMapper(MenuAdminLevel1ServiceMapper menuAdminLevel1ServiceMapper) {
		this.menuAdminLevel1ServiceMapper = menuAdminLevel1ServiceMapper;
	}

	@Override
	public Page<MenuAdminLevel1Entity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page - 1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return menuAdminLevel1JpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<MenuAdminLevel1Entity> findByName(String name, Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page - 1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return menuAdminLevel1JpaRepository.findByName(name, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<MenuAdminLevel1Entity> getAdminMenu() {
		PageRequest pageable = new PageRequest(0, Integer.MAX_VALUE, new Sort(new Order(Direction.DESC, "name")));

		Specification<MenuAdminLevel1Entity> specification = new Specification<MenuAdminLevel1Entity>() {
			@Override
			public Predicate toPredicate(Root<MenuAdminLevel1Entity> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.join("MenuAdminLevel2Entity").get("isShow"), 1));
				if (predicates.isEmpty()) {
					return criteriaBuilder.isNotNull(root.get("id"));
				} else {
					return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
				}
			}
		};

		return menuAdminLevel1JpaRepository.findAll(specification, pageable);
	}

}
