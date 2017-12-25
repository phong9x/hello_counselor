/*
 * Created on 13 Apr 2017 ( Time 16:55:37 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.impl;

import java.util.ArrayList;
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
import org.trams.hello.bean.PaymentProfit;
import org.trams.hello.bean.jpa.PaymentProfitEntity;
import org.trams.hello.business.service.PaymentProfitService;
import org.trams.hello.business.service.mapping.PaymentProfitServiceMapper;
import org.trams.hello.data.repository.jpa.PaymentProfitJpaRepository;
/**
 * Implementation of PaymentProfitService
 */
@Component
@Transactional
public class PaymentProfitServiceImpl implements PaymentProfitService {

	@Resource
	private PaymentProfitJpaRepository paymentProfitJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private PaymentProfitServiceMapper paymentProfitServiceMapper;
	
	@Override
	public PaymentProfit findById(Integer id) {
		PaymentProfitEntity paymentProfitEntity = paymentProfitJpaRepository.findOne(id);
		return paymentProfitServiceMapper.mapPaymentProfitEntityToPaymentProfit(paymentProfitEntity);
	}

	@Override
	public Page<PaymentProfitEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return paymentProfitJpaRepository.findAll(request);
	}

	@Override
	public List<PaymentProfit> findAll() {
		Iterable<PaymentProfitEntity> entities = paymentProfitJpaRepository.findAll();
		List<PaymentProfit> beans = new ArrayList<PaymentProfit>();
		for(PaymentProfitEntity paymentProfitEntity : entities) {
			beans.add(paymentProfitServiceMapper.mapPaymentProfitEntityToPaymentProfit(paymentProfitEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = paymentProfitJpaRepository.count();
		return count;
	}

	@Override
	public PaymentProfit save(PaymentProfit paymentProfit) {
		return update(paymentProfit) ;
	}

	@Override
	public PaymentProfit create(PaymentProfit paymentProfit) {
/*
		PaymentProfitEntity paymentProfitEntity = paymentProfitJpaRepository.findOne(paymentProfit.getId());
		if( paymentProfitEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		paymentProfitEntity = new PaymentProfitEntity();
		paymentProfitServiceMapper.mapPaymentProfitToPaymentProfitEntity(paymentProfit, paymentProfitEntity);
		PaymentProfitEntity paymentProfitEntitySaved = paymentProfitJpaRepository.save(paymentProfitEntity);
		return paymentProfitServiceMapper.mapPaymentProfitEntityToPaymentProfit(paymentProfitEntitySaved);
*/
		PaymentProfitEntity paymentProfitEntity = new PaymentProfitEntity();
		paymentProfitServiceMapper.mapPaymentProfitToPaymentProfitEntity(paymentProfit, paymentProfitEntity);
		PaymentProfitEntity paymentProfitEntitySaved = paymentProfitJpaRepository.save(paymentProfitEntity);
		return paymentProfitServiceMapper.mapPaymentProfitEntityToPaymentProfit(paymentProfitEntitySaved);
	}

	@Override
	public PaymentProfit update(PaymentProfit paymentProfit) {
		PaymentProfitEntity paymentProfitEntity = paymentProfitJpaRepository.findOne(paymentProfit.getId());
		paymentProfitServiceMapper.mapPaymentProfitToPaymentProfitEntity(paymentProfit, paymentProfitEntity);
		PaymentProfitEntity paymentProfitEntitySaved = paymentProfitJpaRepository.save(paymentProfitEntity);
		return paymentProfitServiceMapper.mapPaymentProfitEntityToPaymentProfit(paymentProfitEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		paymentProfitJpaRepository.delete(id);
	}

	public PaymentProfitJpaRepository getPaymentProfitJpaRepository() {
		return paymentProfitJpaRepository;
	}

	public void setPaymentProfitJpaRepository(PaymentProfitJpaRepository paymentProfitJpaRepository) {
		this.paymentProfitJpaRepository = paymentProfitJpaRepository;
	}

	public PaymentProfitServiceMapper getPaymentProfitServiceMapper() {
		return paymentProfitServiceMapper;
	}

	public void setPaymentProfitServiceMapper(PaymentProfitServiceMapper paymentProfitServiceMapper) {
		this.paymentProfitServiceMapper = paymentProfitServiceMapper;
	}

	@Override
	public Page<PaymentProfitEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return paymentProfitJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

	@Override
	public Page<PaymentProfitEntity> findByName(String name, Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return paymentProfitJpaRepository.findByName(name, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<PaymentProfitEntity> filter(Map<String, Object> params) {
		int page = Integer.parseInt(params.get("page").toString());
		int size = Integer.parseInt(params.get("size").toString());

		PageRequest pageable = new PageRequest(page - 1, size, new Sort(new Order(Direction.fromString(String.valueOf(params.getOrDefault("direction", "DESC"))), "createDate")));

		Specification<PaymentProfitEntity> specification = new Specification<PaymentProfitEntity>() {
			@Override
			public Predicate toPredicate(Root<PaymentProfitEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();

				if (params.get("id") != null) {
					Integer id = Integer.parseInt(params.get("id").toString());
					predicates.add(
							criteriaBuilder.equal(root.get("id"), id)
					);
				}
				if (params.get("month") != null) {
					Integer month = Integer.parseInt(params.get("month").toString());
					predicates.add(
							criteriaBuilder.equal(root.get("month"), month)
					);
				}
				if (params.get("year") != null) {
					Integer month = Integer.parseInt(params.get("year").toString());
					predicates.add(
							criteriaBuilder.equal(root.get("year"), month)
					);
				}
				if (params.get("userId") != null) {
					Integer userId = Integer.parseInt(params.get("userId").toString());
					predicates.add(
							criteriaBuilder.equal(root.join("user").get("id"), userId)
					);
				}
				if (params.get("type") != null) {
					Short type = (Short) params.get("type");
					predicates.add(
							criteriaBuilder.equal(root.get("type"), type)
					);
				}

				if (predicates.isEmpty()) {
					return criteriaBuilder.isNotNull(root.get("id"));
				} else {
					return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
				}
			}
		};

		return paymentProfitJpaRepository.findAll(specification, pageable);
	}

	@Override
	public PaymentProfitEntity getTotalMoneyByMonth(int year, int month, Integer counselorId, Short type) {
		return paymentProfitJpaRepository.getTotalMoneyByMonth(year, month, counselorId, type);
	}

}