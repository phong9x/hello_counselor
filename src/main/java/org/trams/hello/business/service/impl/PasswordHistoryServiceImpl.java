/*
 * Created on 15 Mar 2017 ( Time 17:07:52 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.hello.bean.PasswordHistory;
import org.trams.hello.bean.jpa.PasswordHistoryEntity;
import org.trams.hello.business.service.PasswordHistoryService;
import org.trams.hello.business.service.mapping.PasswordHistoryServiceMapper;
import org.trams.hello.data.repository.jpa.PasswordHistoryJpaRepository;
/**
 * Implementation of PasswordHistoryService
 */
@Component
@Transactional
public class PasswordHistoryServiceImpl implements PasswordHistoryService {

	@Resource
	private PasswordHistoryJpaRepository passwordHistoryJpaRepository;
	@Resource
	private EntityManager em;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private PasswordHistoryServiceMapper passwordHistoryServiceMapper;
	
	@Override
	public PasswordHistory findById(Integer id) {
		PasswordHistoryEntity passwordHistoryEntity = passwordHistoryJpaRepository.findOne(id);
		return passwordHistoryServiceMapper.mapPasswordHistoryEntityToPasswordHistory(passwordHistoryEntity);
	}

	@Override
	public Page<PasswordHistoryEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return passwordHistoryJpaRepository.findAll(request);
	}

	@Override
	public List<PasswordHistory> findAll() {
		Iterable<PasswordHistoryEntity> entities = passwordHistoryJpaRepository.findAll();
		List<PasswordHistory> beans = new ArrayList<PasswordHistory>();
		for(PasswordHistoryEntity passwordHistoryEntity : entities) {
			beans.add(passwordHistoryServiceMapper.mapPasswordHistoryEntityToPasswordHistory(passwordHistoryEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = passwordHistoryJpaRepository.count();
		return count;
	}

	@Override
	public PasswordHistory save(PasswordHistory passwordHistory) {
		return update(passwordHistory) ;
	}

	@Override
	public PasswordHistory create(PasswordHistory passwordHistory) {
/*
		PasswordHistoryEntity passwordHistoryEntity = passwordHistoryJpaRepository.findOne(passwordHistory.getId());
		if( passwordHistoryEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		passwordHistoryEntity = new PasswordHistoryEntity();
		passwordHistoryServiceMapper.mapPasswordHistoryToPasswordHistoryEntity(passwordHistory, passwordHistoryEntity);
		PasswordHistoryEntity passwordHistoryEntitySaved = passwordHistoryJpaRepository.save(passwordHistoryEntity);
		return passwordHistoryServiceMapper.mapPasswordHistoryEntityToPasswordHistory(passwordHistoryEntitySaved);
*/
		PasswordHistoryEntity passwordHistoryEntity = new PasswordHistoryEntity();
		passwordHistoryServiceMapper.mapPasswordHistoryToPasswordHistoryEntity(passwordHistory, passwordHistoryEntity);
		PasswordHistoryEntity passwordHistoryEntitySaved = passwordHistoryJpaRepository.save(passwordHistoryEntity);
		return passwordHistoryServiceMapper.mapPasswordHistoryEntityToPasswordHistory(passwordHistoryEntitySaved);
	}

	@Override
	public PasswordHistory update(PasswordHistory passwordHistory) {
		PasswordHistoryEntity passwordHistoryEntity = passwordHistoryJpaRepository.findOne(passwordHistory.getId());
		passwordHistoryServiceMapper.mapPasswordHistoryToPasswordHistoryEntity(passwordHistory, passwordHistoryEntity);
		PasswordHistoryEntity passwordHistoryEntitySaved = passwordHistoryJpaRepository.save(passwordHistoryEntity);
		return passwordHistoryServiceMapper.mapPasswordHistoryEntityToPasswordHistory(passwordHistoryEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		passwordHistoryJpaRepository.delete(id);
	}

	public PasswordHistoryJpaRepository getPasswordHistoryJpaRepository() {
		return passwordHistoryJpaRepository;
	}

	public void setPasswordHistoryJpaRepository(PasswordHistoryJpaRepository passwordHistoryJpaRepository) {
		this.passwordHistoryJpaRepository = passwordHistoryJpaRepository;
	}

	public PasswordHistoryServiceMapper getPasswordHistoryServiceMapper() {
		return passwordHistoryServiceMapper;
	}

	public void setPasswordHistoryServiceMapper(PasswordHistoryServiceMapper passwordHistoryServiceMapper) {
		this.passwordHistoryServiceMapper = passwordHistoryServiceMapper;
	}

	@Override
	public Page<PasswordHistoryEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return passwordHistoryJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Integer countChangedPass(Integer userId, String pass) {
		Integer count = passwordHistoryJpaRepository.countChangedPass(userId, pass);
		if (count == null) {
			count = 0;
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PasswordHistoryEntity> findLastestByUser(Integer userId, Integer size) {
		return em.createQuery("select p from PasswordHistoryEntity p join p.user u where u.id = :userid order by p.id desc")
				.setParameter("userid", userId)
				.setMaxResults(size)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PasswordHistoryEntity> findLastestByUserAndCreateDate(Integer userId, Integer size) {
		return em.createQuery("select p from PasswordHistoryEntity p join p.user u where u.id = :userid order by p.createDate desc")
				.setParameter("userid", userId)
				.setMaxResults(size)
				.getResultList();
	}
}
