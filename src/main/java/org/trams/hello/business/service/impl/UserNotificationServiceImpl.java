/*
 * Created on 11 thg 1 2017 ( Time 10:17:14 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.trams.hello.bean.UserNotification;
import org.trams.hello.bean.jpa.UserNotificationEntity;
import org.trams.hello.business.service.UserNotificationService;
import org.trams.hello.business.service.mapping.UserNotificationServiceMapper;
import org.trams.hello.data.repository.jpa.UserNotificationJpaRepository;

/**
 * Implementation of UserNotificationService
 */
@Component
@Transactional
public class UserNotificationServiceImpl implements UserNotificationService {

	@Resource
	private UserNotificationJpaRepository userNotificationJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private UserNotificationServiceMapper userNotificationServiceMapper;
	
	@Override
	public UserNotification findById(Integer id) {
		UserNotificationEntity userNotificationEntity = userNotificationJpaRepository.findOne(id);
		return userNotificationServiceMapper.mapUserNotificationEntityToUserNotification(userNotificationEntity);
	}

	@Override
	public Page<UserNotificationEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return userNotificationJpaRepository.findAll(request);
	}

	@Override
	public List<UserNotification> findAll() {
		Iterable<UserNotificationEntity> entities = userNotificationJpaRepository.findAll();
		List<UserNotification> beans = new ArrayList<UserNotification>();
		for(UserNotificationEntity userNotificationEntity : entities) {
			beans.add(userNotificationServiceMapper.mapUserNotificationEntityToUserNotification(userNotificationEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = userNotificationJpaRepository.count();
		return count;
	}

	@Override
	public UserNotification save(UserNotification userNotification) {
		return update(userNotification) ;
	}

	@Override
	public UserNotification create(UserNotification userNotification) {
/*
		UserNotificationEntity userNotificationEntity = userNotificationJpaRepository.findOne(userNotification.getId());
		if( userNotificationEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		userNotificationEntity = new UserNotificationEntity();
		userNotificationServiceMapper.mapUserNotificationToUserNotificationEntity(userNotification, userNotificationEntity);
		UserNotificationEntity userNotificationEntitySaved = userNotificationJpaRepository.save(userNotificationEntity);
		return userNotificationServiceMapper.mapUserNotificationEntityToUserNotification(userNotificationEntitySaved);
*/
		UserNotificationEntity userNotificationEntity = new UserNotificationEntity();
		userNotificationServiceMapper.mapUserNotificationToUserNotificationEntity(userNotification, userNotificationEntity);
		UserNotificationEntity userNotificationEntitySaved = userNotificationJpaRepository.save(userNotificationEntity);
		return userNotificationServiceMapper.mapUserNotificationEntityToUserNotification(userNotificationEntitySaved);
	}

	@Override
	public UserNotification update(UserNotification userNotification) {
		UserNotificationEntity userNotificationEntity = userNotificationJpaRepository.findOne(userNotification.getId());
		userNotificationServiceMapper.mapUserNotificationToUserNotificationEntity(userNotification, userNotificationEntity);
		UserNotificationEntity userNotificationEntitySaved = userNotificationJpaRepository.save(userNotificationEntity);
		return userNotificationServiceMapper.mapUserNotificationEntityToUserNotification(userNotificationEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		userNotificationJpaRepository.delete(id);
	}

	public UserNotificationJpaRepository getUserNotificationJpaRepository() {
		return userNotificationJpaRepository;
	}

	public void setUserNotificationJpaRepository(UserNotificationJpaRepository userNotificationJpaRepository) {
		this.userNotificationJpaRepository = userNotificationJpaRepository;
	}

	public UserNotificationServiceMapper getUserNotificationServiceMapper() {
		return userNotificationServiceMapper;
	}

	public void setUserNotificationServiceMapper(UserNotificationServiceMapper userNotificationServiceMapper) {
		this.userNotificationServiceMapper = userNotificationServiceMapper;
	}

	@Override
	public Page<UserNotificationEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return userNotificationJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<UserNotificationEntity> filter(Map<String, Object> params) {
		int page = Integer.parseInt(params.get("page").toString());
		int size = Integer.parseInt(params.get("size").toString());

		PageRequest pageable = new PageRequest(page - 1, size, new Sort(new Order(Direction.DESC, "createDate")));

		Specification<UserNotificationEntity> specification = new Specification<UserNotificationEntity>() {
			@Override
			public Predicate toPredicate(Root<UserNotificationEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();

				if (params.get("id") != null) {
					Integer id = Integer.parseInt(params.get("id").toString());
					predicates.add(
							criteriaBuilder.equal(root.get("id"), id)
					);
				}
				if (params.get("notificationId") != null) {
					Integer notificationId = Integer.parseInt(params.get("notificationId").toString());
					predicates.add(
							criteriaBuilder.equal(root.join("notification").get("id"), notificationId)
					);
				}
				if (params.get("notificationIds") != null) {
					Integer[] notificationIds = (Integer[]) params.get("notificationIds");
					predicates.add(
							criteriaBuilder.isTrue(root.join("notification").get("id").in(notificationIds))
					);
				}
				if (params.get("userId") != null) {
					Integer userId = Integer.parseInt(params.get("userId").toString());
					predicates.add(
							criteriaBuilder.equal(root.join("user").get("id"), userId)
					);
				}
				if (params.get("reads") != null) {
					Short[] reads = (Short[]) params.get("reads");
					predicates.add(
							criteriaBuilder.isTrue(root.get("isRead").in(reads))
					);
				}
				if (params.get("uuid") != null) {
					String uuid = String.valueOf(params.get("uuid"));
					predicates.add(
							criteriaBuilder.equal(root.get("uuid"), uuid)
					);
				}

				if (predicates.isEmpty()) {
					return criteriaBuilder.isNotNull(root.get("id"));
				} else {
					return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
				}
			}
		};

		return userNotificationJpaRepository.findAll(specification, pageable);
	}

	public long countNotification(Map<String, Object> params) {
		Specification<UserNotificationEntity> specification = new Specification<UserNotificationEntity>() {
			@Override
			public Predicate toPredicate(Root<UserNotificationEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				if (params.get("userId") != null) {
					Integer userId = Integer.parseInt(params.get("userId").toString());
					predicates.add(
							criteriaBuilder.equal(root.join("user").get("id"), userId)
					);
				}
				if (params.get("uuid") != null) {
					String uuid = String.valueOf(params.get("uuid"));
					predicates.add(
							criteriaBuilder.equal(root.get("uuid"), uuid)
					);
				}
				if (params.get("reads") != null) {
					Short[] reads = (Short[]) params.get("reads");
					predicates.add(
							criteriaBuilder.isTrue(root.get("isRead").in(reads))
					);
				}

				if (predicates.isEmpty()) {
					return criteriaBuilder.isNotNull(root.get("id"));
				} else {
					return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
				}
			}
		};

		return userNotificationJpaRepository.count(specification);
	}

	@Override
	public UserNotificationEntity upsert(Map<String, Object> params) {

		String uuid 			= String.valueOf(params.get("uuid"));
		Integer notificationId 	= Integer.parseInt(params.get("notificationId").toString());

		Specification<UserNotificationEntity> userNotificationEntitySpecification;

		if (params.get("userId") != null) {
			// find by userId
			userNotificationEntitySpecification = new Specification<UserNotificationEntity>() {
				@Override
				public Predicate toPredicate(Root<UserNotificationEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
					List<Predicate> predicates = new ArrayList<>();

					Integer userId = Integer.parseInt(params.get("userId").toString());
					predicates.add(
							criteriaBuilder.equal(root.join("user").get("id"), userId)
					);
					predicates.add(
							criteriaBuilder.equal(root.join("notification").get("id"), notificationId)
					);

					if (predicates.isEmpty()) {
						return criteriaBuilder.isNotNull(root.get("id"));
					} else {
						return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
					}
				}
			};
		} else {
			// find by uuid
			userNotificationEntitySpecification = new Specification<UserNotificationEntity>() {
				@Override
				public Predicate toPredicate(Root<UserNotificationEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
					List<Predicate> predicates = new ArrayList<>();

					predicates.add(
							criteriaBuilder.equal(root.join("notification").get("id"), notificationId)
					);

					if (predicates.isEmpty()) {
						return criteriaBuilder.isNotNull(root.get("id"));
					} else {
						return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
					}
				}
			};
		}

		UserNotificationEntity userNotificationEntity = userNotificationJpaRepository.findOne(userNotificationEntitySpecification);

		if (userNotificationEntity != null) {
			userNotificationEntity.setUuid(uuid);
			userNotificationEntity.setIsRead((short) 1);
			userNotificationEntity.setUpdateDate(new Date());
		} else {

			UserNotification userNotification = new UserNotification();
			userNotification.setIsRead((short) 1);
			userNotification.setUuid(uuid);
			userNotification.setNotificationId(notificationId);
			userNotification.setCreateDate(new Date());
			if (params.get("userId") != null) {
				Integer userId = Integer.parseInt(params.get("userId").toString());
				userNotification.setUserId(userId);
			}

			userNotificationEntity = new UserNotificationEntity();
			userNotificationServiceMapper.mapUserNotificationToUserNotificationEntity(userNotification, userNotificationEntity);
		}

		return userNotificationJpaRepository.save(userNotificationEntity);
	}

	@Override
	public UserNotification findByUserIdAndNotifiId(Integer userId, Integer notifiId) {
		return userNotificationServiceMapper.mapUserNotificationEntityToUserNotification(userNotificationJpaRepository.findByUserIdAndNotifiId(userId, notifiId));
	}

}