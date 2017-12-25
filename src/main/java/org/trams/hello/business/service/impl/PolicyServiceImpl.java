/*
 * Created on 23 thg 11 2016 ( Time 13:38:12 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
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
import org.trams.hello.bean.Policy;
import org.trams.hello.bean.jpa.PolicyEntity;
import org.trams.hello.business.service.PolicyService;
import org.trams.hello.business.service.mapping.PolicyServiceMapper;
import org.trams.hello.data.repository.jpa.PolicyJpaRepository;
/**
 * Implementation of PolicyService
 */
@Component
@Transactional
public class PolicyServiceImpl implements PolicyService {

	@Resource
	private PolicyJpaRepository policyJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private PolicyServiceMapper policyServiceMapper;
	
	@Override
	public Policy findById(Integer id) {
		PolicyEntity policyEntity = policyJpaRepository.findOne(id);
		return policyServiceMapper.mapPolicyEntityToPolicy(policyEntity);
	}

	@Override
	public Page<PolicyEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return policyJpaRepository.findAll(request);
	}

	@Override
	public List<Policy> findAll() {
		Iterable<PolicyEntity> entities = policyJpaRepository.findAll();
		List<Policy> beans = new ArrayList<Policy>();
		for(PolicyEntity policyEntity : entities) {
			beans.add(policyServiceMapper.mapPolicyEntityToPolicy(policyEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = policyJpaRepository.count();
		return count;
	}

	@Override
	public Policy save(Policy policy) {
		return update(policy) ;
	}

	@Override
	public Policy create(Policy policy) {
/*
		PolicyEntity policyEntity = policyJpaRepository.findOne(policy.getId());
		if( policyEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		policyEntity = new PolicyEntity();
		policyServiceMapper.mapPolicyToPolicyEntity(policy, policyEntity);
		PolicyEntity policyEntitySaved = policyJpaRepository.save(policyEntity);
		return policyServiceMapper.mapPolicyEntityToPolicy(policyEntitySaved);
*/
		PolicyEntity policyEntity = new PolicyEntity();
		policyServiceMapper.mapPolicyToPolicyEntity(policy, policyEntity);
		PolicyEntity policyEntitySaved = policyJpaRepository.save(policyEntity);
		return policyServiceMapper.mapPolicyEntityToPolicy(policyEntitySaved);
	}

	@Override
	public Policy update(Policy policy) {
		PolicyEntity policyEntity = policyJpaRepository.findOne(policy.getId());
		policyServiceMapper.mapPolicyToPolicyEntity(policy, policyEntity);
		PolicyEntity policyEntitySaved = policyJpaRepository.save(policyEntity);
		return policyServiceMapper.mapPolicyEntityToPolicy(policyEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		policyJpaRepository.delete(id);
	}

	public PolicyJpaRepository getPolicyJpaRepository() {
		return policyJpaRepository;
	}

	public void setPolicyJpaRepository(PolicyJpaRepository policyJpaRepository) {
		this.policyJpaRepository = policyJpaRepository;
	}

	public PolicyServiceMapper getPolicyServiceMapper() {
		return policyServiceMapper;
	}

	public void setPolicyServiceMapper(PolicyServiceMapper policyServiceMapper) {
		this.policyServiceMapper = policyServiceMapper;
	}

	@Override
	public Page<PolicyEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return policyJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Policy> listByIsShow(Short typePolicy) {
		List<PolicyEntity> poEntities = policyJpaRepository.list(typePolicy);
		List<Policy> beans = new ArrayList<>(); 
		for (PolicyEntity p : poEntities) {
			beans.add(policyServiceMapper.mapPolicyEntityToPolicy(p));
		}
		return beans;
	}
	
	@Override
	public Policy getPoli(Short typePolicy, Short typeMember) {
		return policyServiceMapper.mapPolicyEntityToPolicy(policyJpaRepository.getPoli(typePolicy, typeMember));
	}

}
