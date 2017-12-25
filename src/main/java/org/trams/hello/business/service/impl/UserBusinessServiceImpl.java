package org.trams.hello.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.hello.bean.UserBusiness;
import org.trams.hello.bean.jpa.UserBusinessEntity;
import org.trams.hello.business.service.UserBusinessService;
import org.trams.hello.business.service.mapping.UserBusinessServiceMapper;
import org.trams.hello.data.repository.jpa.UserBusinessJpaRepository;

/**
 * Implementation of UserBusinessService
 */
@Component
@Transactional
public class UserBusinessServiceImpl implements UserBusinessService {

	@Resource
	private UserBusinessJpaRepository userBusinessJpaRepository;

	@Resource
	private UserBusinessServiceMapper userBusinessServiceMapper;
	
	@Override
	public UserBusiness findById(Integer id) {
		UserBusinessEntity userBusinessEntity = userBusinessJpaRepository.findOne(id);
		return userBusinessServiceMapper.mapUserBusinessEntityToUserBusiness(userBusinessEntity);
	}

	@Override
	public List<UserBusiness> findAll() {
		Iterable<UserBusinessEntity> entities = userBusinessJpaRepository.findAll();
		List<UserBusiness> beans = new ArrayList<UserBusiness>();
		for(UserBusinessEntity userBusinessEntity : entities) {
			beans.add(userBusinessServiceMapper.mapUserBusinessEntityToUserBusiness(userBusinessEntity));
		}
		return beans;
	}

	@Override
	public UserBusiness save(UserBusiness userBusiness) {
		return update(userBusiness) ;
	}

	@Override
	public UserBusiness create(UserBusiness userBusiness) {
		UserBusinessEntity userBusinessEntity = userBusinessJpaRepository.findOne(userBusiness.getId());
		if( userBusinessEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		userBusinessEntity = new UserBusinessEntity();
		userBusinessServiceMapper.mapUserBusinessToUserBusinessEntity(userBusiness, userBusinessEntity);
		UserBusinessEntity userBusinessEntitySaved = userBusinessJpaRepository.save(userBusinessEntity);
		return userBusinessServiceMapper.mapUserBusinessEntityToUserBusiness(userBusinessEntitySaved);
	}

	@Override
	public UserBusiness update(UserBusiness userBusiness) {
		UserBusinessEntity userBusinessEntity = userBusinessJpaRepository.findOne(userBusiness.getId());
		userBusinessServiceMapper.mapUserBusinessToUserBusinessEntity(userBusiness, userBusinessEntity);
		UserBusinessEntity userBusinessEntitySaved = userBusinessJpaRepository.save(userBusinessEntity);
		return userBusinessServiceMapper.mapUserBusinessEntityToUserBusiness(userBusinessEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		userBusinessJpaRepository.delete(id);
	}

	public UserBusinessJpaRepository getUserBusinessJpaRepository() {
		return userBusinessJpaRepository;
	}

	public void setUserBusinessJpaRepository(UserBusinessJpaRepository userBusinessJpaRepository) {
		this.userBusinessJpaRepository = userBusinessJpaRepository;
	}

	public UserBusinessServiceMapper getUserBusinessServiceMapper() {
		return userBusinessServiceMapper;
	}

	public void setUserBusinessServiceMapper(UserBusinessServiceMapper userBusinessServiceMapper) {
		this.userBusinessServiceMapper = userBusinessServiceMapper;
	}

	@Override
	public List<UserBusinessEntity> findByUserIdAndIsDeleted(Integer userId, Boolean isDeleted) {
		return userBusinessJpaRepository.findByUserIdAndIsDeleted(userId, isDeleted);
	}

	@Override
	public Page<UserBusinessEntity> findByBusinessSubIdAndIsDeleted(Integer businessSubId, boolean isDeleted,
			Integer page, Integer size) {
		PageRequest request = new PageRequest(page - 1, size, new Sort(new Order(Direction.DESC, "id")));
		return userBusinessJpaRepository.findByBusinessSubIdAndIsDeleted(businessSubId, isDeleted, request);
	}

}
